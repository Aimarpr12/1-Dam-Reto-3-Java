package controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import error.VehiculoNoEncontradoException;
import modelo.Cliente;
import modelo.ClienteVehiculo;
import modelo.Coche;
import modelo.Empleado;
import modelo.EstadisticasDeVentas;
import modelo.Mecanico;
import modelo.Moto;
import modelo.Reparacion;
import modelo.TipoDeEmpleado;
import modelo.TipoDeVehiculo;
import modelo.Vehiculo;
import modelo.Vendedor;
import modelo.Venta;
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
	
	private IRepositorioCliente repositorioCliente;
	private IRepositorioClienteVehiculo repositorioClienteVehiculo;
	private IRepositorioCoche repositorioCoche;
	private IRepositorioEmpleado repositorioEmpleado;
	private IRepositorioEstadisticasVentas repositorioEstadisticasVentas;
	private IRepositorioLogIn repositorioLogIn;
	private IRepositorioMecanico repositorioMecanico;
	private IRepositorioMoto repositorioMoto;
	private IRepositorioReparacion repositorioReparacion;
	private IRepositorioVehiculo repositorioVehiculo;
	private IRepositorioVendedor repositorioVendedor;
	private IRepositorioVenta repositorioVenta;


	public Controller(IRepositorioCliente repositorioCliente, IRepositorioClienteVehiculo repositorioClienteVehiculo, IRepositorioCoche repositorioCoche, IRepositorioEmpleado repositorioEmpleado, IRepositorioEstadisticasVentas repositorioEstadisticasVentas, IRepositorioLogIn repositorioLogIn, IRepositorioMecanico repositorioMecanico, IRepositorioMoto repositorioMoto, IRepositorioReparacion repositorioReparacion, IRepositorioVehiculo repositorioVehiculo, IRepositorioVendedor repositorioVendedor, IRepositorioVenta repositorioVenta) {
		this.repositorioCliente=repositorioCliente;
		this.repositorioClienteVehiculo=repositorioClienteVehiculo;
		this.repositorioCoche=repositorioCoche;
		this.repositorioEmpleado=repositorioEmpleado;
		this.repositorioEstadisticasVentas=repositorioEstadisticasVentas;
		this.repositorioLogIn=repositorioLogIn;
		this.repositorioMecanico=repositorioMecanico;
		this.repositorioMoto=repositorioMoto;
		this.repositorioReparacion=repositorioReparacion;
		this.repositorioVehiculo=repositorioVehiculo;
		this.repositorioVendedor=repositorioVendedor;
		this.repositorioVenta=repositorioVenta;
	}
	
	private List<Empleado> listaEmpleados = new ArrayList<Empleado>();
	
	private List<Venta> listaDeVentas = new ArrayList<Venta>();
	
	private List<Reparacion> listDeReparaciones = new ArrayList<Reparacion>();
	
	private List<Vehiculo> listDeVehiculos = new ArrayList<Vehiculo>();
	
	private List<Cliente> listDeClientes = new ArrayList<Cliente>();
	
	private List<ClienteVehiculo> listDeClienteVehiculos = new ArrayList<ClienteVehiculo>();
	
	private List<Empleado> listEmpleadosNoVerificados = new ArrayList<Empleado>();
	
	private List<EstadisticasDeVentas> listDeEstadisticasDeVentas = new ArrayList<EstadisticasDeVentas>();
	
	public Controller() {
		
	}
	
	public List<EstadisticasDeVentas> getEstadisticasTop2Vendedores() {
		return listDeEstadisticasDeVentas;
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
		Empleado logInCorrecto = repositorioLogIn.getLogInCorrect(text, valueOf);
		return logInCorrecto;
	} 
	
	public boolean comprobarUser(String text) {
		return repositorioLogIn.comprobarSiExisteUser(text);
	}

	public boolean verificarQueLaContrasenaEsCorrecta(String dni, String passwordString) {
		return repositorioLogIn.comprobarContrasena(dni, passwordString);
	}

	public void changePassword(String dni, String newPasswordString) {
		repositorioLogIn.updatePass(dni, newPasswordString);	
		
	}

	public void actualizarUser(Empleado updateDelUser, int id) {
		repositorioEmpleado.updateUser(updateDelUser, id);
	}

	

	public void cargarListaDeEmpleados() {
		listaEmpleados =  repositorioEmpleado.getListaDeEmpleados();
	}
	
	public void cargarListaDeVentas() {
		listaDeVentas =  repositorioVenta.getListaDeVentas();
	}
	
	public void cargarListaDeVehiculos() {
		listDeVehiculos =  repositorioVehiculo.getListaVehiculos();
	}
	
	public void cargarListaDeEstadisticasDeVentas() {
		listDeEstadisticasDeVentas = repositorioEstadisticasVentas.obtenerTopDosVendedores();
	}
	
	public void cargarListaDeClientes() {
		listDeClientes =  repositorioCliente.getListaDeClientes();
	}
	
	public void cargarListaDeClienteVehiculos() {
		listDeClienteVehiculos =  repositorioClienteVehiculo.getListaDeClienteVehiculos();
	}
	
	public void cargarListaDeReparaciones() {
		listDeReparaciones =  repositorioReparacion.getListaDeReparaciones();
	}

	public boolean UpdateSalario(String dni, int salario) {
		return repositorioEmpleado.updateSalary(dni, salario);
	}

	public boolean UpdateIdJefe(String dni, int salario) {
		return repositorioEmpleado.updateIdJefe(dni, salario);
	}

	public boolean updateTelefono(String dni2, int telefono) {
		return repositorioEmpleado.updateTelefono(dni2, telefono);
	}

	public boolean updateDireccion(String dni2, String direccion2) {
		return repositorioEmpleado.updateDir(dni2, direccion2);
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
		return repositorioReparacion.updateReparacion(idReparacion2, fechaFin2);
	}

	public Reparacion anadirReparacion(Reparacion reparacaion) {
		reparacaion = repositorioReparacion.createReparacion(reparacaion);
		return reparacaion;
	}

	public boolean anadirMoto(Moto moto) {
		boolean sehaInsertadoElVehiculo = repositorioVehiculo.insertarVehiculo(moto);
		if(sehaInsertadoElVehiculo) {
			boolean seHaInsertadoLaMoto = repositorioMoto.insertarMoto(moto);
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
		boolean sehaInsertadoElVehiculo = repositorioVehiculo.insertarVehiculo(coche);
		if(sehaInsertadoElVehiculo) {
			boolean seHaInsertadoElCoche = repositorioCoche.insertarCoche(coche);
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
		return repositorioCliente.insertarCliente(cliente);
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
		return repositorioClienteVehiculo.insertarClienteVehiculo(clienteVehiculo);
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
		repositorioEmpleado.anadirUser(empleadoNuevo);
		repositorioLogIn.anadirLogIn(empleadoNuevo.getDni(), valueOf);
		
	}
	
	public Venta anadirVenta(Venta venta) {
		return repositorioVenta.createVenta(venta); 
	}

	public void addVenta(Venta venta) {
		listaDeVentas.add(venta);
		
	}

	public void verificarCuenteSeleccionada(String dni) {
		repositorioLogIn.actualizarLoginPorId(dni);
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
		listEmpleadosNoVerificados = repositorioEmpleado.getListaDeEmpleadosPorVerificar();
		
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
					repositorioReparacion.updateReparacion(reparacionController);
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
					repositorioVenta.updateVenta(ventaDelController);
					nuevaVenta = false;
				}
			}
			if(nuevaVenta) {
				repositorioVenta.createVenta(ventaImport);
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
		return repositorioVendedor.insertarComision(vendedor);
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
		return repositorioMecanico.insertarRango(mecanico);
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
	
	public boolean cambiarDepartamentoEmpleado(String dni, Empleado user) {
		int id=0;
		Empleado empleadoNuevo=new Empleado();
		boolean cambiado=false;
		for (Empleado empleado:listaEmpleados) {
			if (empleado.getDni().equals(dni)) {
				if (empleado instanceof Vendedor) {
					id=empleado.getId();
					cambiado= repositorioEmpleado.cambiarAMecanico(id);
					empleadoNuevo=empleado;
				}else if (empleado instanceof Mecanico) {
					id=empleado.getId();
					cambiado= repositorioEmpleado.cambiarAVendedor(id);
					empleado.setTipoDeEmpleado(TipoDeEmpleado.vendedor);
					empleadoNuevo=empleado;
				}else {
					return false;
				}
			}
		}
		if (cambiado) {
			actualizarLista(empleadoNuevo);
			actualizarListaReparaciones(empleadoNuevo);
			actualizarListaVentas(empleadoNuevo);
		}
		return true;
	}

	private void actualizarLista(Empleado empleadoNuevo) {
	List<Empleado>listaEmpleadoNuevo=new ArrayList<Empleado>();
	for (Empleado empleado: listaEmpleados) {
		if (empleado.getId()==empleadoNuevo.getId()) {
			listaEmpleadoNuevo.add(empleadoNuevo);
		}else {
			listaEmpleadoNuevo.add(empleado);
		}
	}
	listaEmpleados=listaEmpleadoNuevo;
	}
	private void actualizarListaReparaciones(Empleado empleadoNuevo) {
		List<Reparacion>listaReparacionesNuevas=new ArrayList<Reparacion>();
		for (Reparacion reparacion: listDeReparaciones) {
			if (reparacion.getIdMecanico()==empleadoNuevo.getId()) {
				reparacion.setIdMecanico(0);
				listaReparacionesNuevas.add(reparacion);		
			}else {
				listaReparacionesNuevas.add(reparacion);
			}
		}
		listDeReparaciones=listaReparacionesNuevas;
		
		}
	
	private void actualizarListaVentas(Empleado empleadoNuevo) {
		List<Venta>listaDeVentasNuevas=new ArrayList<Venta>();
		for (Venta venta: listaDeVentas) {
			if (venta.getIdVendedor()==empleadoNuevo.getId()) {
				venta.setIdVendedor(0);
				listaDeVentasNuevas.add(venta);	
			}else {
				listaDeVentasNuevas.add(venta);
			}
		}
		listaDeVentas=listaDeVentasNuevas;
	}

	
}
