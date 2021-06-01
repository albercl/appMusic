package tds.appMusic.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import tds.appMusic.modelo.AppMusic;
import tds.appMusic.modelo.Cancion;
import tds.appMusic.modelo.DescuentoJoven;
import tds.appMusic.modelo.DescuentoJubilado;
import tds.appMusic.modelo.IDescuento;
import tds.appMusic.modelo.Playlist;
import tds.appMusic.modelo.Usuario;

public class UsuarioTest {
		
	@Test
	public void testDescuentoJoven() {
		//Año 2000
		Usuario usuario = new Usuario(200, "Gregorio Martínez López", new Date (100, 9, 24), "sergiem10@hotmail.com", "sergi", "1234", false);
		IDescuento descuento = usuario.getMejorDescuento();
		assertTrue(descuento instanceof DescuentoJoven);
	}
	
	@Test
	public void testDescuentoJubilado() {		
		Usuario usuario = new Usuario(200, "Adolfo Ochoa Pérez", new Date (54, 4, 10), "adolfop@hotmail.com", "adolfop", "ochoa", false);
		IDescuento descuento = usuario.getMejorDescuento();
		assertTrue(descuento instanceof DescuentoJubilado);
	}
	
	@Test
	public void testSinDescuento() {		
		Usuario usuario = new Usuario(200, "Javier Rodríguez Noguera", new Date (64, 2, 12), "javierrn@hotmail.com", "javier", "rodri", false);
		IDescuento descuento = usuario.getMejorDescuento();
		assertNull(descuento);
	}
	
	@Test
	public void testCrearPlaylist() {		
		Usuario usuario = new Usuario(200, "Javier Rodríguez Noguera", new Date (64, 2, 12), "javierrn@hotmail.com", "javier", "rodri", false);
		usuario.addPlaylist("playlist", new LinkedList<Cancion>());
		Playlist pl = usuario.getPlaylist("playlist");
		assertNotNull(pl);
	}
	
	@Test
	public void testEliminarPlaylist() {		
		Usuario usuario = new Usuario(200, "Javier Rodríguez Noguera", new Date (64, 2, 12), "javierrn@hotmail.com", "javier", "rodri", false);
		usuario.addPlaylist("playlist", new LinkedList<Cancion>());
		usuario.removePlaylist("playlist");
		Playlist pl = usuario.getPlaylist("playlist");
		assertNull(pl);
	}
}
