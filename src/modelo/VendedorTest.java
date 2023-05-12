package modelo;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import controller.Controller;

public class VendedorTest {

		
	private static List <Empleado> listDeEmpleados;
	private static Controller controller;
	private static Vendedor vendedor;
	private static Date date;
	private static Date date2;
		
	@BeforeClass
	public static void setUpBeforeClass(){
		Controller controller1 = new Controller();
		controller = controller1;
		controller.cargarListaDeEmpleados();
		listDeEmpleados = controller.getAllEmpleado();
		date = new Date();
		date2 = new Date();
        
        // Crea un objeto SimpleDateFormat con el formato deseado
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
        	date2 = formatoFecha.parse("2010-01-01");
        	date = formatoFecha.parse("2020-01-10");
		} catch (Exception e) {
			// TODO: handle exception
		}
		vendedor = new Vendedor(1, "cdvasdas", "dfcdfasdfs", "dasdas", date, date2, 123456789, "ndfsomnkdfs", "dasmddasd@dsdais.com", 1200, 1, TipoDeEmpleado.vendedor);
	}
	
	@Test
	public void addDatosVendedor() {
		Empleado empleado = new Empleado(1, "cdvasdas", "dfcdfasdfs", "dasdas", date, date2, 123456789, "ndfsomnkdfs", "dasmddasd@dsdais.com", 1200, 1, TipoDeEmpleado.vendedor);
		vendedor.addDatosVendedor(empleado);
		assertEquals(vendedor.getId(), empleado.getId());
	}
	
	@Test
	public void setComision() {
		vendedor.setComision(2);
		//assertFalse(vendedor.setComision(vendedor));
	}
	
	@Test
	public void calcularAntiguedad() {
		assertEquals(vendedor.calcularAntiguedad(), 13);
		
	}

	@Test
	public void calcularEdad() {
		assertNotEquals(vendedor.calcularEdad(), 1);
		
	}

}
