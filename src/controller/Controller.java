package controller;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import error.VehiculoNoEncontradoException;
import modelo.Cliente;
import modelo.ClienteVehiculo;
import modelo.Coche;
import modelo.Empleado;
import modelo.Mecanico;
import modelo.Moto;
import modelo.Reparacion;
import modelo.TipoDeVehiculo;
import modelo.Vehiculo;
import modelo.Vendedor;
import modelo.Venta;
import repository.RepositorioCargarListas;
import repository.RepositorioDeEstadisticas;
import repository.RepositorioDeInserts;
import repository.RepositorioDeLogIn;
import repository.RepositorioDeUpdates;
import xmlParser.InputReparaciones;
import xmlParser.InputVentas;
import xmlParser.OutputReparaciones;
import xmlParser.OutputVentas;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Controller {
	
	private static OutputReparaciones outputReparaciones = new OutputReparaciones();
	private static OutputVentas outputVentas = new OutputVentas();	
	private static InputReparaciones intputReparaciones = new InputReparaciones();
	private static InputVentas intputVentas = new InputVentas();
	private static RepositorioDeLogIn repositorioDeLogIn = new RepositorioDeLogIn();
	private static RepositorioCargarListas repositorioDeCargarListas = new RepositorioCargarListas();
	private static RepositorioDeInserts repositorioDeInserts = new RepositorioDeInserts();
	private static RepositorioDeUpdates repositorioDeUpdates = new RepositorioDeUpdates();
	private static RepositorioDeEstadisticas repositorioDeEstadisticas = new RepositorioDeEstadisticas();
	
	private List<Empleado> listaEmpleados = new ArrayList<Empleado>();
	
	private List<Venta> listaDeVentas = new ArrayList<Venta>();
	
	private List<Reparacion> listDeReparaciones = new ArrayList<Reparacion>();
	
	private List<Vehiculo> listDeVehiculos = new ArrayList<Vehiculo>();
	
	private List<Cliente> listDeClientes = new ArrayList<Cliente>();
	
	private List<ClienteVehiculo> listDeClienteVehiculos = new ArrayList<ClienteVehiculo>();
	
	private List<Empleado> listEmpleadosNoVerificados = new ArrayList<Empleado>();
	
	public Controller() {
		
	}
	
	public List<Empleado> getAllEmpleadosNoVerificados(){
		return listEmpleadosNoVerificados;
	}
	
	public List<ClienteVehiculo> getAllClienteVehiculos(){
		return listDeClienteVehiculos;
	}
	
	public List<Cliente> getAllClientes(){
		return listDeClientes;
	}
	
	public List<Vehiculo> getAllVehiculos(){
		return listDeVehiculos;
	}
	
	public List<Venta> getAllVentas(){
		return listaDeVentas;
	}
	
	public List<Reparacion> getAllReparaciones(){
		return listDeReparaciones;
	}
	
	public List<Empleado> getAllEmpleado(){
		return listaEmpleados;
	}

	public Empleado getLogInCorrect(String text, String valueOf) {
		Empleado logInCorrecto = repositorioDeLogIn.getLogInCorrect(text, valueOf);
		return logInCorrecto;
	} 
	
	public boolean comprobarUser(String text) {
		return repositorioDeLogIn.comprobarSiExisteUser(text);
	}

	public boolean verificarQueLaContrasenaEsCorrecta(String dni, String passwordString) {
		return repositorioDeLogIn.comprobarContrasena(dni, passwordString);
	}

	public void changePassword(String dni, String newPasswordString) {
		repositorioDeLogIn.updatePass(dni, newPasswordString);	
		
	}

	public void actualizarUser(Empleado updateDelUser, int id) {
		repositorioDeUpdates.updateUser(updateDelUser, id);
	}

	

	public void cargarListaDeEmpleados() {
		listaEmpleados =  repositorioDeCargarListas.getListaDeEmpleados();
		repositorioDeEstadisticas.obtenerTopDosVendedores();
	}
	
	public void cargarListaDeVentas() {
		listaDeVentas =  repositorioDeCargarListas.getListaDeVentas();
	}
	
	public void cargarListaDeVehiculos() {
		listDeVehiculos =  repositorioDeCargarListas.getListaVehiculos();
	}
	
	public void cargarListaDeClientes() {
		listDeClientes =  repositorioDeCargarListas.getListaDeClientes();
	}
	
	public void cargarListaDeClienteVehiculos() {
		listDeClienteVehiculos =  repositorioDeCargarListas.getListaDeClienteVehiculos();
	}
	
	public void cargarListaDeReparaciones() {
		listDeReparaciones =  repositorioDeCargarListas.getListaDeReparaciones();
	}

	public boolean UpdateSalario(String dni, int salario) {
		return repositorioDeUpdates.updateSallary(dni, salario);
	}

	public boolean UpdateIdJefe(String dni, int salario) {
		return repositorioDeUpdates.updateIdJefe(dni, salario);
	}

	public boolean updateTelefono(String dni2, int telefono) {
		return repositorioDeUpdates.updateTelefono(dni2, telefono);
	}

	public boolean updateDireccion(String dni2, String direccion2) {
		return repositorioDeUpdates.updateDir(dni2, direccion2);
	}

	public TipoDeVehiculo averiguarTipoDeVehiculo(String matricula) throws VehiculoNoEncontradoException{
		for(Vehiculo vehiculoActual : listDeVehiculos) {
			if(matricula.equals(vehiculoActual.getMatricula())) {
				if(TipoDeVehiculo.coche.equals(vehiculoActual.getTipoDeVehiculo())) {
					return TipoDeVehiculo.coche;
				}else if(TipoDeVehiculo.moto.equals(vehiculoActual.getTipoDeVehiculo())) {
					return TipoDeVehiculo.moto;
				}
			}
		}
		throw new VehiculoNoEncontradoException("No se ha encontrado");
	}
	
	public boolean UpdateReparacion(Date fechaFin2, int idReparacion2) {
		return repositorioDeUpdates.updateReparacion(idReparacion2, fechaFin2);
	}

	public Reparacion anadirReparacion(Reparacion reparacaion) {
		reparacaion = repositorioDeInserts.createReparacion(reparacaion);
		return reparacaion;
	}

	public boolean anadirMoto(Moto moto) {
		boolean sehaInsertadoElVehiculo = repositorioDeInserts.insertarVehiculo(moto);
		if(sehaInsertadoElVehiculo) {
			boolean seHaInsertadoLaMoto = repositorioDeInserts.insertarMoto(moto);
			if(seHaInsertadoLaMoto) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}

	public boolean anadirCoche(Coche coche) {
		boolean sehaInsertadoElVehiculo = repositorioDeInserts.insertarVehiculo(coche);
		if(sehaInsertadoElVehiculo) {
			boolean seHaInsertadoElCoche = repositorioDeInserts.insertarCoche(coche);
			if(seHaInsertadoElCoche) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	public boolean addClienteBD(Cliente cliente) {
		return repositorioDeInserts.insertarCliente(cliente);
	}

	public void anadirCliente(Cliente cliente) {
		listDeClientes.add(cliente);
		
	}

	public void addMoto(Moto moto) {
		listDeVehiculos.add(moto);
		
	}
	public void addCoche(Coche coche) {
		listDeVehiculos.add(coche);
		
	}

	public void addReparacion(Reparacion reparacaion) {
		listDeReparaciones.add(reparacaion);
		
	}

	public boolean averiguarSiElVehiculoNoTieneDueño(String bastidor) {
		for(ClienteVehiculo clienteVehiculo : listDeClienteVehiculos) {
			if(bastidor.equals(clienteVehiculo.getBastidor())) {
				return false;
			}
		}
		return true;
	}

	public boolean anadirClienteVehiculoBD(ClienteVehiculo clienteVehiculo) {
		return repositorioDeInserts.insertarClienteVehiculo(clienteVehiculo);
	}

	public void addClienteVehiculo(ClienteVehiculo clienteVehiculo) {
		listDeClienteVehiculos.add(clienteVehiculo);
		
	}

	public void anadirUser(Empleado empleadoNuevo, String valueOf) {
		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");   
		String fechaActualFormateada = formatoFecha.format(new Date());
		Date fechaActual = new Date();
		try {
			fechaActual = formatoFecha.parse(fechaActualFormateada);
			empleadoNuevo.setFechaContratacion(fechaActual);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repositorioDeInserts.anadirUser(empleadoNuevo);
		repositorioDeLogIn.anadirLogIn(empleadoNuevo.getDni(), valueOf);
		
	}
	
	public Venta anadirVenta(Venta venta) {
		venta = repositorioDeInserts.createVenta(venta);
		return venta;
	}

	public void addVenta(Venta venta) {
		listaDeVentas.add(venta);
		
	}

	public void verificarCuenteSeleccionada(String dni) {
		repositorioDeUpdates.actualizarBarcoPorId(dni);
		List <Empleado> listaNoverificados1 = new ArrayList<Empleado>();
		for(Empleado empleadoActual : listEmpleadosNoVerificados) {
			if(dni.equals(empleadoActual.getDni())) {
				
			}else {
				listaNoverificados1.add(empleadoActual);
			}
		}
		listEmpleadosNoVerificados = listaNoverificados1;
	}

	public void empleadosAVerificar() {
		listEmpleadosNoVerificados = repositorioDeLogIn.getListaDeEmpleadosPorVerificar();
		
	}
	public void exportarDatos() {
		outputReparaciones.outputReparaciones(this);
		outputVentas.outputVentas(this);
	}
	
	public void importarDatos() {
		List <Venta> listaDeVentasImportada = new ArrayList <Venta>();
		List <Reparacion> listaDeReparacionesImportada = new ArrayList <Reparacion>();
		try {
			listaDeReparacionesImportada = intputReparaciones.inputReparaciones();
			listaDeVentasImportada = intputVentas.inputVentas();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		comprobarListaVentas(listaDeVentasImportada);
		comprobarListaReparaciones(listaDeReparacionesImportada);
	}

	private void comprobarListaReparaciones(List<Reparacion> listaDeReparacionesImportada) {
		for(Reparacion reparacionImportada : listaDeReparacionesImportada) {
			boolean nuevaReparacion = true;
			for(Reparacion reparacionController : listDeReparaciones ) {
				if(reparacionController.equals(reparacionImportada)) {
					nuevaReparacion = false;
				}else if(reparacionController.getIdReparacion() == reparacionImportada.getIdReparacion()) {
					nuevaReparacion = false;
					actualizarDatosReparacionImport(reparacionController, reparacionImportada);
					repositorioDeUpdates.updateReparacion(reparacionController);
				}
			}
			if(nuevaReparacion) {
				anadirReparacion(reparacionImportada);
				listDeReparaciones.add(reparacionImportada);			
			}
		}
	}

	private void actualizarDatosReparacionImport(Reparacion reparacionController, Reparacion reparacionImportada) {
		if(!reparacionController.getIdVehiculo().equals(reparacionImportada.getIdVehiculo()) || !reparacionController.getDescripcion().equals(reparacionImportada.getDescripcion()) ||
				reparacionController.getCoste() != reparacionImportada.getCoste() || reparacionController.getPrecio() != reparacionImportada.getPrecio() ||
				!reparacionController.getFechaIni().equals(reparacionImportada.getFechaIni()) || !reparacionController.getFechaFin().equals(reparacionImportada.getFechaFin()) ||
				reparacionController.getIdMecanico() != reparacionImportada.getIdMecanico()
				) {
			reparacionController.setIdVehiculo(reparacionImportada.getIdVehiculo());
			reparacionController.setDescripcion(reparacionImportada.getDescripcion());
			reparacionController.setCoste(reparacionImportada.getCoste());
			reparacionController.setPrecio(reparacionImportada.getPrecio());
			reparacionController.setFechaIni(reparacionImportada.getFechaIni());
			reparacionController.setFechaFin(reparacionImportada.getFechaFin());
			reparacionController.setIdMecanico(reparacionImportada.getIdMecanico());
		}
			
		
	}

	private void comprobarListaVentas(List<Venta> listaDeVentasImportada) {
		for(Venta ventaImport : listaDeVentasImportada) {
			boolean nuevaVenta = true;
			for(Venta ventaDelController : listaDeVentas) {
				if(ventaDelController.equals(ventaImport)) {
					nuevaVenta = false;
				}else if(ventaDelController.getIdVenta() == ventaImport.getIdVenta()) {
					actualizarDatosVentaImport(ventaDelController, ventaImport);
					repositorioDeUpdates.updateVenta(ventaDelController);
					nuevaVenta = false;
				}
			}
			if(nuevaVenta) {
				repositorioDeInserts.createVenta(ventaImport);
				listaDeVentas.add(ventaImport);
			}
		}
		
	}

	private void actualizarDatosVentaImport(Venta ventaDelController, Venta ventaImport) {
		if(ventaDelController.getPrecio() != ventaImport.getPrecio() || !ventaDelController.getFecha().equals(ventaImport.getFecha()) ||
				ventaDelController.getIdVendedor() != ventaImport.getIdVendedor() || !ventaDelController.getBastidor().equals(ventaImport.getBastidor()) ||
				!ventaDelController.getIdCliente().equals(ventaImport.getIdCliente())
				) {
			ventaDelController.setPrecio(ventaImport.getPrecio());
			ventaDelController.setFecha(ventaImport.getFecha());
			ventaDelController.setIdVendedor(ventaImport.getIdVendedor());
			ventaDelController.setBastidor(ventaImport.getBastidor());
			ventaDelController.setIdCliente(ventaImport.getIdCliente());
		}
	}

	public boolean updateComision(Vendedor vendedor) {
		return repositorioDeUpdates.insertarComision(vendedor);
	}

	public boolean updateComisionList(Vendedor vendedor) {
		List <Empleado> listaNueva = new ArrayList<Empleado>();
		boolean seHaActualizado = false;
		for(Empleado empleado : listaEmpleados) {
			if(vendedor.getDni().equals(empleado.getDni())) {
				listaNueva.add(vendedor);
				seHaActualizado = true;
			}else {
				listaNueva.add(empleado);
			}
		}
		listaEmpleados = listaNueva;
		return seHaActualizado;
	}

	public boolean updateRango(Mecanico mecanico) {
		return repositorioDeUpdates.insertarRango(mecanico);
	}

	public boolean updateRangoList(Mecanico mecanico) {
		List <Empleado> listaNueva = new ArrayList<Empleado>();
		boolean seHaActualizado = false;
		for(Empleado empleado : listaEmpleados) {
			if(mecanico.getDni().equals(empleado.getDni())) {
				listaNueva.add(mecanico);
				seHaActualizado = true;
			}else {
				listaNueva.add(empleado);
			}
		}
		listaEmpleados = listaNueva;
		return seHaActualizado;
	}


}
