package modelo;


import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import controller.Controller;

public class MacanicoTest {
	
	private static List <Empleado> listDeEmpleados;
	private static Controller controller;
	private static Mecanico mecanico;
	private static Date date;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		Controller controller1 = new Controller();
		controller = controller1;
		controller.cargarListaDeEmpleados();
		listDeEmpleados = controller.getAllEmpleado();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
        	date = formatoFecha.parse("2020-01-10");
		} catch (Exception e) {
			// TODO: handle exception
		}
        mecanico = new Mecanico("cdvasdas", "dfcdfasdfs", "dasdas", 123456789, date, "ndfsomnkdfs", "dasmddasd@dsdais.com");
	}
	
	@Test
	public void encontrarMecanicoEnLista() {
		Mecanico mecanico2 =  mecanico.encontrarMecanicoEnLista("45678901D", controller.getAllEmpleado());
		assertEquals(mecanico2.getDni(), "45678901D");
	}
	
	@Test
	public void calcularAntiguedad() {
		assertEquals(mecanico.calcularEdad(), 3);
		
	}

}
