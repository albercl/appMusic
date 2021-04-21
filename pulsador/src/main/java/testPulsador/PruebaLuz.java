package testPulsador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.JTextField;

import pulsador.IEncendidoListener;
import pulsador.Luz;

import javax.swing.JTextArea;

public class PruebaLuz {

	private JFrame frame;
	private JTextArea textArea;
	private Luz[] luces;
	private String[] colores = {"amarillo", "rojo", "azul"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PruebaLuz window = new PruebaLuz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PruebaLuz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		luces = new Luz[3];
		
		Luz luzyellow = new Luz();
		luces[0]= luzyellow; 
		luzyellow.setBounds(26, 10, 67, 70);
		luzyellow.addEncendidoListener(new IEncendidoListener() {
			public void enteradoCambioEncendido(EventObject e) {
				cambioBoton(0);
			}
		});
		frame.getContentPane().setLayout(null);
		luzyellow.setColor(Color.YELLOW);
		frame.getContentPane().add(luzyellow);
		
		Luz luzred = new Luz();
		luces[1]= luzred;
		luzred.setColor(Color.RED);
		luzred.addEncendidoListener(new IEncendidoListener() {
			public void enteradoCambioEncendido(EventObject e) {
				cambioBoton(1);
			}
		});
		luzred.setBounds(49, 115, 30, 30);
		frame.getContentPane().add(luzred);
		
		Luz luzblue = new Luz();
		luces[2]= luzblue;
		luzblue.setColor(Color.BLUE);
		luzblue.addEncendidoListener(new IEncendidoListener() {
			public void enteradoCambioEncendido(EventObject e) {
				cambioBoton(2);
			}
		});
		luzblue.setBounds(49, 188, 30, 30);
		frame.getContentPane().add(luzblue);
		
		textArea = new JTextArea();
		textArea.setBounds(153, 43, 260, 194);
		frame.getContentPane().add(textArea);
		
		
	}
	private void cambioBoton(int n){
		String valor;
		if (luces[n].isEncendido()) {
			valor = "encendido";
		}
		else {
			valor = "apagado";	
		}
		textArea.append("Pulsador " + colores[n] + " " + valor);
		textArea.append(System.getProperty("line.separator")); 
	}
}
