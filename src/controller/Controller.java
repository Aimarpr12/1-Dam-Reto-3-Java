package controller;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import error.LogInIncorrectoException;
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

	/**
	 * Declara el controller con los repositorios necesarios
	 * @param repositorioCliente .
	 * @param repositorioClienteVehiculo .
	 * @param repositorioCoche .
	 * @param repositorioEmpleado .
	 * @param repositorioEstadisticasVentas .
	 * @param repositorioLogIn .
	 * @param repositorioMecanico .
	 * @param repositorioMoto .
	 * @param repositorioReparacion .
	 * @param repositorioVehiculo .
	 * @param repositorioVendedor .
	 * @param repositorioVenta .
	 */
	public Controller(IRepositorioCliente repositorioCliente, IRepositorioClienteVehiculo repositorioClienteVehiculo,
			IRepositorioCoche repositorioCoche, IRepositorioEmpleado repositorioEmpleado,
			IRepositorioEstadisticasVentas repositorioEstadisticasVentas, IRepositorioLogIn repositorioLogIn,
			IRepositorioMecanico repositorioMecanico, IRepositorioMoto repositorioMoto,
			IRepositorioReparacion repositorioReparacion, IRepositorioVehiculo repositorioVehiculo,
			IRepositorioVendedor repositorioVendedor, IRepositorioVenta repositorioVenta) {
		this.repositorioCliente = repositorioCliente;
		this.repositorioClienteVehiculo = repositorioClienteVehiculo;
		this.repositorioCoche = repositorioCoche;
		this.repositorioEmpleado = repositorioEmpleado;
		this.repositorioEstadisticasVentas = repositorioEstadisticasVentas;
		this.repositorioLogIn = repositorioLogIn;
		this.repositorioMecanico = repositorioMecanico;
		this.repositorioMoto = repositorioMoto;
		this.repositorioReparacion = repositorioReparacion;
		this.repositorioVehiculo = repositorioVehiculo;
		this.repositorioVendedor = repositorioVendedor;
		this.repositorioVenta = repositorioVenta;
	}
	/**
	 * Declara listaEmpleados
	 */
	private List<Empleado> listaEmpleados = new ArrayList<Empleado>();
	/**
	 * Declara listaDeVentas
	 */
	private List<Venta> listaDeVentas = new ArrayList<Venta>();
	/**
	 * Declara listDeReparaciones
	 */
	private List<Reparacion> listDeReparaciones = new ArrayList<Reparacion>();
	/**
	 * Declara listDeVehiculos
	 */
	private List<Vehiculo> listDeVehiculos = new ArrayList<Vehiculo>();
	/**
	 * Declara listDeClientes
	 */
	private List<Cliente> listDeClientes = new ArrayList<Cliente>();
	/**
	 * Declara listDeClienteVehiculos
	 */
	private List<ClienteVehiculo> listDeClienteVehiculos = new ArrayList<ClienteVehiculo>();
	/**
	 * Declara listEmpleadosNoVerificados
	 */
	private List<Empleado> listEmpleadosNoVerificados = new ArrayList<Empleado>();
	/**
	 * Declara listDeEstadisticasDeVentas
	 */
	private List<EstadisticasDeVentas> listDeEstadisticasDeVentas = new ArrayList<EstadisticasDeVentas>();

	/**
	 * Controller vacio
	 */
	public Controller() {

	}
	
	/**
	 * Metodo para obtener de la base de datos la lista EstadisticasVentas
	 * @return listDeEstadisticasDeVentas .
	 */
	public List<EstadisticasDeVentas> getEstadisticasTop2Vendedores() {
		return listDeEstadisticasDeVentas;
	}
	
	/**
	 * Metodo para obtener de la base de datos la lista de empleados no verificados
	 * @return listEmpleadosNoVerificados .
	 */
	public List<Empleado> getAllEmpleadosNoVerificados() {
		return listEmpleadosNoVerificados;
	}
	/**
	 * Metodo para obtener de la base de datos la lista de Vehiculos vinculados a Clientes
	 * @return listDeClienteVehiculos .
	 */
	public List<ClienteVehiculo> getAllClienteVehiculos() {
		return listDeClienteVehiculos;
	}
	/**
	 * Metodo para obtener de la base de datos la lista de Clientes
	 * @return listDeClientes .
	 */
	public List<Cliente> getAllClientes() {
		return listDeClientes;
	}
	/**
	 * Metodo para obtener de la base de datos la lista de Vehiculos
	 * @return listDeVehiculos .
	 */
	public List<Vehiculo> getAllVehiculos() {
		return listDeVehiculos;
	}

	/**
	 * Metodo para obtener de la base de datos la lista de Ventas
	 * @return listaDeVentas .
	 */
	public List<Venta> getAllVentas(){
		return listaDeVentas;
	}
	
	/**
	 * Metodo para obtener de la base de datos la lista de Reparaciones
	 * @return listDeReparaciones .
	 */
	public List<Reparacion> getAllReparaciones(){
		return listDeReparaciones;
	}
	
	/**
	 * Metodo para obtener de la base de datos la lista de Empleados
	 * @return listaEmpleados .
	 */
	public List<Empleado> getAllEmpleado(){
		return listaEmpleados;
	}
	/**
	 * Metodo para obtener el login del empleado y comprobar si es correcto
	 * @param dni del empleado
	 * @param pass contrasenna del empleado
	 */
	public Empleado getLogInCorrect(String dni, String pass) {
		try {
			Empleado logInCorrecto = repositorioLogIn.getLogInCorrect(dni, pass);
			return logInCorrecto;
		} catch (LogInIncorrectoException e) {
			return null;
		}
	}

	/**
	 * Metodo para comprobar si ya hay un empleado registrado con el dni introducido
	 * @param dni del empleado
	 * @return boolean si existe
	 */
	public boolean comprobarUser(String dni) {
		return repositorioLogIn.comprobarSiExisteUser(dni);
	}

	/**
	 * Verifica que la contrasenna es correcta antes de poder cambiarla
	 * @param dni del empleado
	 * @param passwordString contrasenna del empleado
	 * @return boolean si es correcto
	 */
	public boolean verificarQueLaContrasenaEsCorrecta(String dni, String passwordString) {
		return repositorioLogIn.comprobarContrasena(dni, passwordString);
	}

	/**
	 * Cambia la contrasenna en la bbdd
	 * @param dni del empleado
	 * @param newPasswordString nueva contrasenna del empleado
	 */
	public void changePassword(String dni, String newPasswordString) {
		repositorioLogIn.updatePass(dni, newPasswordString);
	}
	/**
	 * Actualiza en bbdd los datos del empleado
	 * @param updateDelUser empleado al que se van a hacer los cambios
	 * @param id del empleado 
	 */
	public void actualizarUser(Empleado updateDelUser, int id) {
		repositorioEmpleado.updateUser(updateDelUser, id);
	}

	/**
	 * Carga la lista de empleados de la bbdd
	 * Carga empleados vendedores y empleados mecanicos en las listas correspondientes
	 */
	public void cargarListaDeEmpleados() {
		listaEmpleados = repositorioEmpleado.getListaDeEmpleados();
		insertarListasDeVendedoresYMecanicos();
	}

	/**
	 * Carga empleados vendedores y empleados mecanicos en las listas correspondientes
	 */
	private void insertarListasDeVendedoresYMecanicos() {
		for(Empleado empleado : listaEmpleados) {
			if(TipoDeEmpleado.mecanico == empleado.getTipoDeEmpleado()) {
				Mecanico mecanico = (Mecanico) empleado;
				mecanico.setListaDeReparaciones(getAllReparacionesPorMecanico(mecanico.getId()));
			}else if(TipoDeEmpleado.vendedor == empleado.getTipoDeEmpleado()) {
				Vendedor vendedor = (Vendedor) empleado;
				vendedor.setListaDeVentas(getAllVentasPorVendedor(vendedor.getId()));
			}
		}	
	}

	/**
	 * Carga la lista de ventas de la bbdd
	 */
	public void cargarListaDeVentas() {
		listaDeVentas =  repositorioVenta.getListaDeVentas();
	}
	
	/**
	 * Carga la lista de vehiculos de la bbdd
	 */
	public void cargarListaDeVehiculos() {
		listDeVehiculos =  repositorioVehiculo.getListaVehiculos();
	}
	
	/**
	 * Carga la lista de estadisticas de la bbdd
	 */
	public void cargarListaDeEstadisticasDeVentas() {
		listDeEstadisticasDeVentas = repositorioEstadisticasVentas.obtenerTopDosVendedores();
	}
	
	/**
	 * Carga la lista de clientes de la bbdd
	 */
	public void cargarListaDeClientes() {
		listDeClientes =  repositorioCliente.getListaDeClientes();
	}
	
	/**
	 * Carga la lista de vehiculos asociados a clientes de la bbdd
	 */
	public void cargarListaDeClienteVehiculos() {
		listDeClienteVehiculos =  repositorioClienteVehiculo.getListaDeClienteVehiculos();
	}

	/**
	 * Carga la lista de reparaciones de la bbdd
	 */
	public void cargarListaDeReparaciones() {
		listDeReparaciones =  repositorioReparacion.getListaDeReparaciones();
	}
	/**
	 * Obteiene el tipo de vehiculo segun la matricula
	 * @param matricula del vehiculo
	 * @return el tipo
	 */
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
	
	/**
	 * Actualiza la fecha fin de la reparacion
	 * @param fechaFin de la rep
	 * @param idReparacion de la rep
	 * @return boolean si se ha insertado
	 */
	public boolean UpdateReparacion(Date fechaFin, int idReparacion) {
		return repositorioReparacion.updateReparacion(idReparacion, fechaFin);
	}

	/**
	 * Annade una nueva reparacion en la bbdd
	 * @param reparacion  a insertar
	 * @return reparacion insertada
	 */
	public Reparacion anadirReparacion(Reparacion reparacion) {
		reparacion = repositorioReparacion.createReparacion(reparacion);
		return reparacion;
	}

	/**
	 * Annade una nueva moto en la bbdd, primero a la tabla Vehiculo y despues la tabla Moto
	 * @param moto a insertar
	 * @return boolean si se ha insertado
	 */
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

	/**
	 * Annade un nuevo coche en la bbdd, primero a la tabla Vehiculo y despues la tabla Coche
	 * @param coche a insertar
	 * @return boolean si se ha insertado
	 */
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

	/**
	 * Annade un nuevo cliente en la bbdd
	 * @param cliente nuevo 
	 * @return boolean si se ha insertado
	 */
	public boolean addClienteBD(Cliente cliente) {
		return repositorioCliente.insertarCliente(cliente);
	}

	/**
	 * Annade un nuevo cliente a la lista cargada en memoria
	 * @param cliente nuevo 
	 */
	public void anadirCliente(Cliente cliente) {
		listDeClientes.add(cliente);
	}

	/**
	 * Annade una nueva moto a la lista cargada en memoria
	 * @param moto nueva
	 */
	public void addMoto(Moto moto) {
		listDeVehiculos.add(moto);
	}
	
	/**
	 * Annade un nuevo coche a la lista cargada en memoria
	 * @param coche nuevo 
	 */
	public void addCoche(Coche coche) {
		listDeVehiculos.add(coche);	
	}

	/**
	 * Annade una nueva reparacion a la lista cargada en memoria
	 * @param reparacion nueva
	 */
	public void addReparacion(Reparacion reparacion) {
		listDeReparaciones.add(reparacion);	
	}

	/**
	 * Averigua si el coche esta en listDeClienteVehiculos o por contrario no tiene duenno
	 * @param bastidor del vehiculo
	 * @return boolean si tiene dueno
	 */
	public boolean averiguarSiElVehiculoNoTieneDueno(String bastidor) {
		for (ClienteVehiculo clienteVehiculo : listDeClienteVehiculos) {
			if (bastidor.equals(clienteVehiculo.getBastidor())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Annade un nuevo clienteVehiculo en la bbdd
	 * @param clienteVehiculo
	 * @return boolean si se ha insertado
	 */
	public boolean anadirClienteVehiculoBD(ClienteVehiculo clienteVehiculo) {
		return repositorioClienteVehiculo.insertarClienteVehiculo(clienteVehiculo);
	}

	/**
	 * Annade un nuevo clienteVehiculo en la lista de memoria
	 * @param clienteVehiculo si se ha insertado en lista
	 */
	public void addClienteVehiculo(ClienteVehiculo clienteVehiculo) {
		listDeClienteVehiculos.add(clienteVehiculo);	
	}

	/**
	 * Crea un nuevo empleado, creando un log in y poniendo la fecha de contratacion como la de hoy
	 * @param empleadoNuevo
	 * @param pass contrasenna
	 */
	public void anadirUser(Empleado empleadoNuevo, String pass) {
		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");   
		String fechaActualFormateada = formatoFecha.format(new Date());
		Date fechaActual = new Date();
		try {
			fechaActual = formatoFecha.parse(fechaActualFormateada);
			empleadoNuevo.setFechaContratacion(fechaActual);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		repositorioEmpleado.anadirUser(empleadoNuevo);
		repositorioLogIn.anadirLogIn(empleadoNuevo.getDni(), pass);
	}

	/**
	 * Annade una nueva Venta a la bbdd
	 * @param venta
	 * @return venta
	 */
	public Venta anadirVenta(Venta venta) {
		return repositorioVenta.createVenta(venta); 
	}

	/**
	 * Annade una nueva Venta a la lista en  memoria
	 * @param venta
	 */
	public void addVenta(Venta venta) {
		listaDeVentas.add(venta);
	}

	/**
	 * Verifica la cuenta de un empleado para que pueda hacer login.
	 * Actualiza la lista de empleadosNoVerificados en memoria.
	 * @param dni
	 */
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
	
	/**
	 * Carga la lista desde la bbdd de empleados por verificar
	 */
	public void empleadosAVerificar() {
		listEmpleadosNoVerificados = repositorioEmpleado.getListaDeEmpleadosPorVerificar();
	}
	
	/**
	 * Exporta los datos de la listaDeReparaciones y la ListaDeVentas a xml
	 */
	public void exportarDatos() {
		outputReparaciones.outputReparaciones(this);
		outputVentas.outputVentas(this);
	}
	/**
	 * Importa los datos de xml a listaDeReparaciones y la ListaDeVentas
	 * Si hay datos nuevos, se annadiran a las listas en memoria y a la base de datos
	 * Si hay alguna modificacion de los datos que ya estan en las listas, se modificaran usando los datos del xml
	 */
	public void importarDatos() {
		List<Venta> listaDeVentasImportada = new ArrayList<Venta>();
		List<Reparacion> listaDeReparacionesImportada = new ArrayList<Reparacion>();
		try {
			listaDeReparacionesImportada = intputReparaciones.inputReparaciones();
			listaDeVentasImportada = intputVentas.inputVentas();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		comprobarListaVentas(listaDeVentasImportada);
		comprobarListaReparaciones(listaDeReparacionesImportada);
	}

	/**
	 * Se comparan los datos del xml con la lista en memoria, si son diferentes, se añaden los nuevos 
	 * o se modifican los que tengan id comun.
	 * @param listaDeReparacionesImportada del xml
	 */
	private void comprobarListaReparaciones(List<Reparacion> listaDeReparacionesImportada) {
		for (Reparacion reparacionImportada : listaDeReparacionesImportada) {
			boolean nuevaReparacion = true;
			for (Reparacion reparacionController : listDeReparaciones) {
				if (reparacionController.equals(reparacionImportada)) {
					nuevaReparacion = false;
				} else if (reparacionController.getIdReparacion() == reparacionImportada.getIdReparacion()) {
					nuevaReparacion = false;
					actualizarDatosReparacionImport(reparacionController, reparacionImportada);
					repositorioReparacion.updateReparacion(reparacionController);
				}
			}
			if (nuevaReparacion) {
				anadirReparacion(reparacionImportada);
				listDeReparaciones.add(reparacionImportada);
			}
		}
	}

	/**
	 * Se actualizan las reparaciones en memoria que tengan datos diferentes al xml
	 * @param reparacionController que es la reparacion en memoria
	 * @param reparacionImportada que es la reparacion del xml
	 */
	private void actualizarDatosReparacionImport(Reparacion reparacionController, Reparacion reparacionImportada) {
		if (!reparacionController.getIdVehiculo().equals(reparacionImportada.getIdVehiculo())
				|| !reparacionController.getDescripcion().equals(reparacionImportada.getDescripcion())
				|| reparacionController.getCoste() != reparacionImportada.getCoste()
				|| reparacionController.getPrecio() != reparacionImportada.getPrecio()
				|| !reparacionController.getFechaIni().equals(reparacionImportada.getFechaIni())
				|| !reparacionController.getFechaFin().equals(reparacionImportada.getFechaFin())
				|| reparacionController.getIdMecanico() != reparacionImportada.getIdMecanico()) {
			reparacionController.setIdVehiculo(reparacionImportada.getIdVehiculo());
			reparacionController.setDescripcion(reparacionImportada.getDescripcion());
			reparacionController.setCoste(reparacionImportada.getCoste());
			reparacionController.setPrecio(reparacionImportada.getPrecio());
			reparacionController.setFechaIni(reparacionImportada.getFechaIni());
			reparacionController.setFechaFin(reparacionImportada.getFechaFin());
			reparacionController.setIdMecanico(reparacionImportada.getIdMecanico());
		}

	}

	/**
	 * Se recorre la lista de ventas en memoria para ver si los datos del xml son iguales o diferentes, 
	 * si son nuevos se annaden a la lista
	 * si no, se actualizan los datos en memoria
	 * @param listaDeVentasImportada del xml
	 */
	private void comprobarListaVentas(List<Venta> listaDeVentasImportada) {
		for (Venta ventaImport : listaDeVentasImportada) {
			boolean nuevaVenta = true;
			for (Venta ventaDelController : listaDeVentas) {
				if (ventaDelController.equals(ventaImport)) {
					nuevaVenta = false;
				} else if (ventaDelController.getIdVenta() == ventaImport.getIdVenta()) {
					actualizarDatosVentaImport(ventaDelController, ventaImport);
					repositorioVenta.updateVenta(ventaDelController);
					nuevaVenta = false;
				}
			}
			if (nuevaVenta) {
				repositorioVenta.createVenta(ventaImport);
				listaDeVentas.add(ventaImport);
			}
		}

	}

	/**
	 * Actualiza las ventas que difieran algun dato con el xml
	 * @param ventaDelController que es una venta en memoria
	 * @param ventaImport que es una venta importada del xml
	 */
	private void actualizarDatosVentaImport(Venta ventaDelController, Venta ventaImport) {
		if (ventaDelController.getPrecio() != ventaImport.getPrecio()
				|| !ventaDelController.getFecha().equals(ventaImport.getFecha())
				|| ventaDelController.getIdVendedor() != ventaImport.getIdVendedor()
				|| !ventaDelController.getBastidor().equals(ventaImport.getBastidor())
				|| !ventaDelController.getIdCliente().equals(ventaImport.getIdCliente())) {
			ventaDelController.setPrecio(ventaImport.getPrecio());
			ventaDelController.setFecha(ventaImport.getFecha());
			ventaDelController.setIdVendedor(ventaImport.getIdVendedor());
			ventaDelController.setBastidor(ventaImport.getBastidor());
			ventaDelController.setIdCliente(ventaImport.getIdCliente());
		}
	}
	
	/**
	 * Actualiza la comision de un vendedor
	 * @param vendedor
	 * @return boolean
	 */
	public boolean updateComision(Vendedor vendedor) {
		return repositorioVendedor.insertarComision(vendedor);
	}

	/**
	 * Actualiza la lista de Empleados con el vendedor actualizado
	 * @param vendedor
	 * @return boolean
	 */
	public boolean updateComisionList(Vendedor vendedor) {
		List<Empleado> listaNueva = new ArrayList<Empleado>();
		boolean seHaActualizado = false;
		for (Empleado empleado : listaEmpleados) {
			if (vendedor.getDni().equals(empleado.getDni())) {
				listaNueva.add(vendedor);
				seHaActualizado = true;
			} else {
				listaNueva.add(empleado);
			}
		}
		listaEmpleados = listaNueva;
		return seHaActualizado;
	}
	
	/**
	 * Actualiza el rango de un mecanico
	 * @param mecanico
	 * @return boolean
	 */
	public boolean updateRango(Mecanico mecanico) {
		return repositorioMecanico.insertarRango(mecanico);
	}
	
	/**
	 * Actualiza la lista de empleados con el mecanico modificado
	 * @param mecanico
	 * @return boolean
	 */
	public boolean updateRangoList(Mecanico mecanico) {
		List<Empleado> listaNueva = new ArrayList<Empleado>();
		boolean seHaActualizado = false;
		for (Empleado empleado : listaEmpleados) {
			if (mecanico.getDni().equals(empleado.getDni())) {
				listaNueva.add(mecanico);
				seHaActualizado = true;
			} else {
				listaNueva.add(empleado);
			}
		}
		listaEmpleados = listaNueva;
		return seHaActualizado;
	}
	
	/**
	 * Agrega una nueva reparacion a la bbdd, 
	 * si lo hace correctamente, se annade a la lista en memoria
	 * @param reparacion a annadir
	 * @return boolean si se ha annadido
	 */
	public boolean anadirReparacionFuncion(Reparacion reparacion) {
		reparacion = anadirReparacion(reparacion);
		if (0 != reparacion.getIdReparacion()) {
			addReparacion(reparacion);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Agrega una nueva comision a la bbdd, 
	 * si lo hace correctamente, se annade a la lista en memoria
	 * @param vendedor para cambiar comision
	 * @return boolean si se ha insertado
	 */
	public boolean setComisionFuncion(Vendedor vendedor) {
		boolean seHaInsertado = updateComision(vendedor);
		if (seHaInsertado) {
			return updateComisionList(vendedor);
		} else {
			return false;
		}
	}

	/**
	 * Pone fecha fin a una reparacion, 
	 * si lo hace correctamente, se annade a la lista en memoria
	 * @param fechaFin de la rep
	 * @param idReparacion de la rep
	 * @return boolean si se ha actualizado
	 */
	public boolean finalizarReaparacion(Date fechaFin, int idReparacion) {
		boolean sehaActualizadoCorrectamentEnBD = UpdateReparacion(fechaFin, idReparacion);
		if (!sehaActualizadoCorrectamentEnBD) {
			return false;
		}
		Date fechaFinVieja = new Date();
		boolean seHaEditadoCorrectamente = false;
		List<Reparacion> todosLasReparaciones = getAllReparaciones();
		for (Reparacion reparacion : todosLasReparaciones) {
			if (idReparacion == reparacion.getIdReparacion()) {
				fechaFinVieja = reparacion.getFechaFin();
				reparacion.setFechaFin(fechaFin);
				seHaEditadoCorrectamente = true;
			}
		}
		if (!sehaActualizadoCorrectamentEnBD) {
			UpdateReparacion(fechaFinVieja, idReparacion);
		}
		return seHaEditadoCorrectamente;
	}
	
	/**
	 * Annade una venta a la bbdd
	 * si lo hace correctamente, se annade a la lista en memoria
	 * @param venta a añadir
	 * @return boolean si se ha añadido
	 */
	public boolean anadirVentaFuncion(Venta venta) {
		venta = anadirVenta(venta);	
		if(0 != venta.getIdVenta()) {
			addVenta(venta);
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Obtiene las reparaciones de un mecanico
	 * @param id del mecanico
	 * @return listDeReparacionesPorMecanico .
	 */
	public List <Reparacion> getAllReparacionesPorMecanico(int id) {
		List <Reparacion> listDeReparacionesPorMecanico = new ArrayList <Reparacion>();
		for(Reparacion reparacion : getAllReparaciones()) {
			if(id == reparacion.getIdMecanico()){
				listDeReparacionesPorMecanico.add(reparacion);
			}
		}
		return listDeReparacionesPorMecanico;
	}
	
	/**
	 * Obtiene todas las ventas de un vendedor
	 * @param id del vendeddor
	 * @return listDeVentasPorVendedor
	 */
	public List <Venta> getAllVentasPorVendedor(int id) {
		List <Venta> listDeVentasPorVendedor = new ArrayList <Venta>();
		for(Venta vendedor : getAllVentas()) {
			if(id == vendedor.getIdVendedor()){
				listDeVentasPorVendedor.add(vendedor);
			}
		}
		return listDeVentasPorVendedor;
	}
	/**
	 * Con el id de una reparacion se modifica el id del mecanico de la misma
	 * actualiza las listas de reparaciones
	 * @param idMecanicoViejo de la rep
	 * @param idMecanicoNuevo de la rep
	 * @param idReparacion de la rep
	 * @return boolean si se ha editado
	 */
	public boolean editMecanicoDeUnaReparacion(int idMecanicoNuevo, int idMecanicoViejo, int idReparacion) {
		boolean seHaCambiadoElMecanico = false;
		Reparacion reparacion = new Reparacion();
		for(Reparacion reparacionActual : listDeReparaciones) {
			if(idReparacion == reparacionActual.getIdReparacion()) {
				reparacion = reparacionActual;
				reparacionActual.setIdMecanico(idMecanicoNuevo);
				seHaCambiadoElMecanico = repositorioReparacion.updateReparacionIdMecanico(reparacionActual);
				if(!seHaCambiadoElMecanico) {
					reparacionActual.setIdMecanico(idMecanicoViejo);
				}
			}
		}
		if(seHaCambiadoElMecanico) {
			for(Empleado empleado : listaEmpleados) {
				if(idMecanicoViejo == empleado.getId()) {
					Mecanico mecanico = (Mecanico) empleado;
					mecanico.removeReparacionDeLaLista(idReparacion);
				}				
			}
			for(Empleado empleado : listaEmpleados) {
				if(idMecanicoNuevo == empleado.getId()) {
					Mecanico mecanico = (Mecanico) empleado;
					mecanico.anadirReparacionAListaDeReparacion(reparacion);
				}				
			}
		}else {
			return false;
		} 
		return true;
	}
	/**
	 * Con el id de una venta se modifica el id del vendedor de la misma
	 * actualiza las listas de ventas
	 * @param idEmpleadoViejo de la venta
	 * @param idNuevo de la venta
	 * @param idVenta de la venta 
	 * @return boolean si se ha cambiado
	 */
	public boolean editVendedorDeUnaVenta(int idNuevo, int idEmpleadoViejo, int idVenta) {
		boolean seHaCambiadoElVendedor = false;
		Venta venta = new Venta();
		for(Venta ventaActual : listaDeVentas) {
			if(idVenta == ventaActual.getIdVenta()) {
				venta = ventaActual;
				ventaActual.setIdVendedor(idNuevo);
				seHaCambiadoElVendedor = repositorioVenta.updateVentaIdVendedor(ventaActual);
				if(!seHaCambiadoElVendedor) {
					ventaActual.setIdVendedor(idEmpleadoViejo);
				}
			}
		}
		if(seHaCambiadoElVendedor) {
			for(Empleado empleado : listaEmpleados) {
				if(idEmpleadoViejo == empleado.getId()) {
					Vendedor vendedor = (Vendedor) empleado;
					vendedor.removeVentaDeLaLista(idVenta);
				}				
			}
			for(Empleado empleado : listaEmpleados) {
				if(idEmpleadoViejo == empleado.getId()) {
					Vendedor vendedor = (Vendedor) empleado;
					vendedor.anadirVentaAListaDeVentas(venta);
				}				
			}
		}else {
			return false;
		}
		return false;
	}
}
