package tds.appMusic.modelo.util;

import tds.appMusic.modelo.Cancion;
import um.tds.componente.Canciones;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;

public class Cargador {
    public static final String OS_TYPE = System.getProperty("os.name");
    public static final String SONGS_PATH = OS_TYPE.equals("Linux") ? System.getProperty("user.home") + "/tds/canciones/" : "C:\\\\tds\\\\canciones\\\\";

    public static List<Cancion> cargarCancionesDisco() {
        try {
            List<Cancion> cancionesList = new LinkedList<>();

            File rootDir = new File(SONGS_PATH);
            if (System.getProperty("os.name").equals("Linux"))
                rootDir = new File(System.getProperty("user.home") + "/tds/canciones");

            if (!rootDir.isDirectory())
                throw new IOException("No se ha podido abrir el directorio de canciones");

            File[] estilos = rootDir.listFiles();
            assert estilos != null;
            for (File f : estilos) {
                File[] canciones = f.listFiles();
                assert canciones != null;
                for (File c : canciones) {
                    String ruta = f.getName() + "\\" + c.getName();

                    String[] parts = c.getName().split("-");

                    String interpretesString = parts[0];
                    String[] interpretes = interpretesString.split("&");
                    for (int i = 0; i < interpretes.length; i++)
                        interpretes[i] = interpretes[i].trim();

                    String name = parts[1].substring(0, parts[1].length() - 4).trim();

                    Cancion song = new Cancion(0, name, ruta, f.getName(), interpretes);
                    cancionesList.add(song);
                }
            }

            return cancionesList;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Cancion> descargarCanciones(Canciones canciones) {
        List<Cancion> cancionesList = new LinkedList<>();

        File rootDir = new File(SONGS_PATH);
        if(System.getProperty("os.name").equals("Linux"))
            rootDir = new File(System.getProperty("user.home") + "/tds/canciones");

        for(um.tds.componente.Cancion cancion : canciones.getCancion()) {
            try {
                //Comprobar estilo
                String estilo = cancion.getEstilo().toUpperCase();
                String stylePath = Paths.get(rootDir.getPath(), estilo).toString();

                File targetDirectory = null;
                File[] directories = rootDir.listFiles();
                assert directories != null;
                for (int i = 0; i < directories.length && targetDirectory == null; i++) {
                    File directory = directories[i];
                    if (directory.isDirectory() && directory.getName().equals(estilo)) {
                        targetDirectory = directory;
                    }
                }

                //Si no existe carpeta de estilo, crear una
                if (targetDirectory == null) {
                    Files.createDirectory(Paths.get(stylePath));
                }

                //Descargar canción
                URL uri = new URL(cancion.getURL());

                Path song = Paths.get(stylePath, cancion.getInterprete() + " - " + cancion.getTitulo() + ".mp3");
                System.out.println("Descargando: " + cancion.getTitulo());

                InputStream stream = uri.openStream();
                Files.copy(stream, song, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Descarga terminada!");

                String builder =
                        cancion.getEstilo().toUpperCase() + '/' +
                                cancion.getInterprete() + " - " + cancion.getTitulo() +
                                ".mp3";

                Cancion c = new Cancion(0,
                        cancion.getTitulo(),
                        builder,
                        cancion.getEstilo().toUpperCase(),
                        cancion.getInterprete());

                cancionesList.add(c);
            } catch (Exception e) {
                System.out.println("No se ha podido descargar cancion: " + cancion.getTitulo());
            }
        }

        return cancionesList;
    }
}
