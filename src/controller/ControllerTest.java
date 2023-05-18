package controller;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import repository.IRepositorioCliente;
import repository.IRepositorioClienteVehiculo;
import repository.IRepositorioCoche;
import repository.IRepositorioEmpleado;
import repository.IRepositorioEstadisticasVentas;
import repository.IRepositorioLogIn;
import repository.IRepositorioMecanico;
import repository.IRepositorioMoto;
import repository.IRepositorioReparacion;
import repository.IRepositorioVehiculo;
import repository.IRepositorioVendedor;
import repository.IRepositorioVenta;
import repository.RepositorioCliente;
import repository.RepositorioClienteVehiculo;
import repository.RepositorioCoche;
import repository.RepositorioEmpleado;
import repository.RepositorioEstadisticasVentas;
import repository.RepositorioLogIn;
import repository.RepositorioMecanico;
import repository.RepositorioMoto;
import repository.RepositorioReparacion;
import repository.RepositorioVehiculo;
import repository.RepositorioVendedor;
import repository.RepositorioVenta;

public class ControllerTest {

	private static Controller controller;
	
	@BeforeClass
	public static void setUpBeforeClass(){ 
		IRepositorioCliente repositorioCliente=new RepositorioCliente();
		 IRepositorioClienteVehiculo repositorioClienteVehiculo= new RepositorioClienteVehiculo();
		 IRepositorioCoche repositorioCoche= new RepositorioCoche();
		 IRepositorioEmpleado repositorioEmpleado = new RepositorioEmpleado();
		 IRepositorioEstadisticasVentas repositorioEstadisticasVentas = new RepositorioEstadisticasVentas();
		 IRepositorioLogIn repositorioLogIn = new RepositorioLogIn();
		 IRepositorioMecanico repositorioMecanico = new RepositorioMecanico();
		 IRepositorioMoto repositorioMoto = new RepositorioMoto();
		 IRepositorioReparacion repositorioReparacion = new RepositorioReparacion();
		 IRepositorioVehiculo repositorioVehiculo = new RepositorioVehiculo();
		 IRepositorioVendedor repositorioVendedor = new RepositorioVendedor();
		 IRepositorioVenta repositorioVenta = new RepositorioVenta();
		 controller = new Controller(repositorioCliente, repositorioClienteVehiculo,  repositorioCoche,  repositorioEmpleado,  repositorioEstadisticasVentas,  repositorioLogIn,  repositorioMecanico,  repositorioMoto,  repositorioReparacion,  repositorioVehiculo,  repositorioVendedor,  repositorioVenta);
		
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
