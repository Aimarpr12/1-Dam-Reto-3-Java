package controller;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ControllerTest {

	private static Controller controller;
	
	@BeforeClass
	public static void setUpBeforeClass(){ 
		controller = new Controller();
		controller.cargarListaDeClientes();
		controller.cargarListaDeClienteVehiculos();
		controller.cargarListaDeEmpleados();
		controller.cargarListaDeReparaciones();
		controller.cargarListaDeVehiculos();
		controller.cargarListaDeVentas();
		controller.empleadosAVerificar();
	}
	
	@Test
	public void getAllEmpleadosNoVerificadosTest() {
		boolean hayAlgunEmplePorVerificar = false;
		if(controller.getAllEmpleadosNoVerificados().size() != 0) {
			hayAlgunEmplePorVerificar = true;
		}
		assertTrue(hayAlgunEmplePorVerificar);
	}
	
	@Test
	public void getAllClienteVehiculosTest() {
		boolean hayAlgunCocheAsosciadoACliente = false;
		if(controller.getAllClienteVehiculos().size() != 0) {
			hayAlgunCocheAsosciadoACliente = true;
		}
		assertTrue(hayAlgunCocheAsosciadoACliente);
	}
	
	@Test
	public void getAllClientesTest() {
		boolean hayAlgunCliente = false;
		if(controller.getAllClientes().size() != 0) {
			hayAlgunCliente = true;
		}
		assertTrue(hayAlgunCliente );
	}
	
	@Test
	public void getAllVehiculosTest() {
		boolean hayAlgunVehiculo = false;
		if(controller.getAllVehiculos().size() != 0) {
			hayAlgunVehiculo = true;
		}
		assertTrue(hayAlgunVehiculo);
	}
	
	@Test
	public void getAllVentasTest() {
		boolean hayAlgunaVenta = false;
		if(controller.getAllVentas().size() != 0) {
			hayAlgunaVenta = true;
		}
		assertTrue(hayAlgunaVenta);
	}
	
	@Test
	public void getAllReparacionesTest() {
		boolean hayAlgunaReparacion = false;
		if(controller.getAllReparaciones().size() != 0) {
			hayAlgunaReparacion = true;
		}
		assertTrue(hayAlgunaReparacion);
	}
	
	@Test
	public void getAllEmpleadoTest() {
		boolean hayAlgunEmpleado = false;
		if(controller.getAllEmpleado().size() != 0) {
			hayAlgunEmpleado = true;
		}
		assertTrue(hayAlgunEmpleado);
	}
 
	@Test
	public void getLogInCorrectTest() {
		String user = "sdada";
		String contrasena = "sadad";
		assertNull(controller.getLogInCorrect(user, contrasena));	
		
		user = "12345678A";
		contrasena = "1234";
		assertNotNull(controller.getLogInCorrect(user, contrasena));
	}

	
	
}
