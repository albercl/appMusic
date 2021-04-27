package tds.appMusic.gui;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import tds.appMusic.modelo.AppMusic;

public class LoginWindowTest {
	
	AppMusic controlador;
	
	@Before
	public void before() {
		controlador = AppMusic.getInstanciaUnica();
	}
	
	@Test
	public void test() {		
		Date fecha = new Date (2000, 9, 24);
		controlador.register("Sergio Escudero Manzano", fecha, "sergiem10@hotmail.com", "Seresma", "1234");
		boolean resultado = controlador.login("Seresma","1234");
		assertTrue(resultado);
		
	}
	
	@Test
	public void test2() {		
		Date fecha = new Date (2000, 9, 23);
		controlador.register("Sergio Escudero Manzano", fecha, "sergiem105@hotmail.com", "Seresma2", "1234");
		boolean resultado = controlador.login("Seresma2","1234");
		assertTrue(resultado);
		
	}
	
	
}
