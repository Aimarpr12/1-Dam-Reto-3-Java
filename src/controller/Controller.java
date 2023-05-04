package controller;

import java.util.List;

import clases.Cliente;
import clases.ClienteVehiculo;
import clases.Coche;
import clases.Empleado;
import clases.Moto;
import clases.Reparacion;
import clases.TipoDeVehiculo;
import clases.Vehiculo;
import clases.Venta;
import error.VehiculoNoEncontradoException;
import repository.ActualizarVerificado;
import repository.ComprobarLogIn;
import repository.ComprobarQueEsUserNoEstaRegistrado;
import repository.ComprobarQueLaContraseñaEsIgual;
import repository.ComprobarSiEsJefe;
import repository.ComprobarSiEsMecanico;
import repository.ConseguirDatosDeLosMecanicos;
import repository.ConseguirDatosDeLosVendedores;
import repository.InsertarCliente;
import repository.InsertarClienteVehiculo;
import repository.InsertarCoche;
import repository.InsertarMoto;
import repository.InsertarReparacion;
import repository.InsertarVehiculo;
import repository.InsertarVenta;
import repository.ListDeEmpleadosPorVerificar;
import repository.ObtenerListaDeClienteVehiculos;
import repository.ObtenerListaDeClientes;
import repository.ObtenerListaDeTodasLasReparaciones;
import repository.ObtenerListaDeTodasLasVentas;
import repository.ObtenerListaDeTodosLosEmpleados;
import repository.ObtenerListaDeVehiculos;
import repository.ObtenerNombreDelUser;
import repository.UpdateDireccion;
import repository.UpdateIdJefe;
import repository.UpdatePassword;
import repository.UpdateReparacion;
import repository.UpdateSalary;
import repository.UpdateTelefono;
import repository.UpdateUser;
import xmlParser.InputReparaciones;
import xmlParser.InputVentas;
import xmlParser.OutputReparaciones;
import xmlParser.OutputVentas;

import java.util.ArrayList;
import java.util.Date;

public class Controller {
	
	private static ComprobarLogIn comprobarLogIn = new ComprobarLogIn();
	private static ComprobarSiEsJefe comprobarjefe = new ComprobarSiEsJefe();
	private static ComprobarSiEsMecanico comprobarMecanico = new ComprobarSiEsMecanico();
	private static ConseguirDatosDeLosMecanicos datosDeLosMecanicos = new ConseguirDatosDeLosMecanicos();
	private static ConseguirDatosDeLosVendedores datosDeLosVendedores = new ConseguirDatosDeLosVendedores();
	private static ComprobarQueEsUserNoEstaRegistrado comprobarUser = new ComprobarQueEsUserNoEstaRegistrado();
	private static ComprobarQueLaContraseñaEsIgual samePass = new ComprobarQueLaContraseñaEsIgual();
	private static UpdatePassword updatePass = new UpdatePassword();
	private static UpdateUser updateUser = new UpdateUser();
	private static ObtenerNombreDelUser nombreUser = new ObtenerNombreDelUser();
	private static ObtenerListaDeTodosLosEmpleados allEmpleados = new ObtenerListaDeTodosLosEmpleados();
	private static UpdateSalary updateSallary = new UpdateSalary();
	private static UpdateIdJefe updateIdJefe = new UpdateIdJefe();
	private static UpdateTelefono updateTelefono = new UpdateTelefono();
	private static UpdateDireccion updateDir = new UpdateDireccion();
	private static ObtenerListaDeTodasLasVentas allVentas = new ObtenerListaDeTodasLasVentas();
	private static ObtenerListaDeTodasLasReparaciones allReparaciones = new ObtenerListaDeTodasLasReparaciones();
	private static ObtenerListaDeVehiculos allVehiculos = new ObtenerListaDeVehiculos();
	private static ObtenerListaDeClientes allClientes = new ObtenerListaDeClientes();
	private static ObtenerListaDeClienteVehiculos allClienteVehiculos = new ObtenerListaDeClienteVehiculos();
	private static UpdateReparacion updateReparacion = new UpdateReparacion();
	private static InsertarReparacion anadirReparqacion = new InsertarReparacion();	
	private static InsertarVehiculo anadirVehiculo = new InsertarVehiculo();
	private static InsertarMoto anadirMoto = new InsertarMoto();
	private static InsertarCoche anadirCoche = new InsertarCoche();
	private static InsertarCliente anadirCliente = new InsertarCliente();
	private static InsertarClienteVehiculo anadirClienteVehiculo = new InsertarClienteVehiculo();
	private static InsertarVenta anadirVenta = new InsertarVenta();
	private static ActualizarVerificado actualizarVerificado = new ActualizarVerificado();
	private static ListDeEmpleadosPorVerificar listDeUsersPorVerificar = new ListDeEmpleadosPorVerificar();
	private static OutputReparaciones outputReparaciones = new OutputReparaciones();
	private static OutputVentas outputVentas = new OutputVentas();	
	private static InputReparaciones intputReparaciones = new InputReparaciones();
	private static InputVentas intputVentas = new InputVentas();	
	
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
		Empleado logInCorrecto = comprobarLogIn.getLogInCorrect(text, valueOf);
		return logInCorrecto;
	}

	public boolean comporbarSiEsJefe(int id) {
		boolean esJefe = comprobarjefe.comprobarJefe(id);
		return esJefe;
	}

	public boolean comprobarSiEsMecanico(int id) {
		boolean esMecanico = comprobarMecanico.comprobarMecanico(id);
		return esMecanico;
	}
	
	public String conseguirDatosDeMecanico(int id) {
		String rango = datosDeLosMecanicos.conseguirDatosMecanicos(id);
		return rango;
	}

	public double conseguirDatosDeVendedor(int id) {
		double comisionDeVenta = datosDeLosVendedores.conseguirDatosVendedores(id);
		return comisionDeVenta;
	}

	public boolean comprobarUser(String text) {
		return comprobarUser.comprobarSiExisteUser(text);
	}

	public boolean verificarQueLaContrasenaEsCorrecta(String dni, String passwordString) {
		return samePass.comprobarContrasena(dni, passwordString);
	}

	public void changePassword(String dni, String newPasswordString) {
		updatePass.updatePass(dni, newPasswordString);	
		
	}

	public void actualizarUser(Empleado updateDelUser, int id) {
		updateUser.updateUser(updateDelUser, id);
	}

	public String getNombreJefe(int jefe) {
		return nombreUser.obtenerNombreDelUser(jefe);
	}

	public void cargarListaDeEmpleados() {
		listaEmpleados =  allEmpleados.getListaDeEmpleados();
	}
	
	public void cargarListaDeVentas() {
		listaDeVentas =  allVentas.getListaDeVentas();
	}
	
	public void cargarListaDeVehiculos() {
		listDeVehiculos =  allVehiculos.getListaVehiculos();
	}
	
	public void cargarListaDeClientes() {
		listDeClientes =  allClientes.getListaDeClientes();
	}
	
	public void cargarListaDeClienteVehiculos() {
		listDeClienteVehiculos =  allClienteVehiculos.getListaDeClienteVehiculos();
	}
	
	public void cargarListaDeReparaciones() {
		listDeReparaciones =  allReparaciones.getListaDeReparaciones();
	}

	public boolean UpdateSalario(String dni, int salario) {
		return updateSallary.updateSallary(dni, salario);
	}

	public boolean UpdateIdJefe(String dni, int salario) {
		return updateIdJefe.updateIdJefe(dni, salario);
	}

	public boolean updateTelefono(String dni2, int telefono) {
		return updateTelefono.updateTelefono(dni2, telefono);
	}

	public boolean updateDireccion(String dni2, String direccion2) {
		return updateDir.updateDir(dni2, direccion2);
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
	
	public List<Vehiculo> getAllVehiculosDisponibles() {
		List <Vehiculo> listDeVehiculoDisponible =  new ArrayList<Vehiculo>();
		for(Vehiculo vehiculoActual : listDeVehiculos) {
			boolean vehiculoLibre = true;
			for(ClienteVehiculo clienteVehiculoActual: listDeClienteVehiculos) {
				if(vehiculoActual.getBastidor().equals(clienteVehiculoActual.getBastidor())) {
					vehiculoLibre = false;
				}
			}
			if(vehiculoLibre) {
				listDeVehiculoDisponible.add(vehiculoActual);
			}
			
		}
		return listDeVehiculoDisponible;
	}

	public List<Reparacion> getAllReparacionesPorMecanico(int id) {
		List <Reparacion> listDeTodasLasReparaciones = getAllReparaciones();
		List <Reparacion> listDeReparacionesPorMecanico = new ArrayList <Reparacion>();
		for(Reparacion reparacion : listDeTodasLasReparaciones) {
			if(id == reparacion.getIdMecanico()){
				listDeReparacionesPorMecanico.add(reparacion);
			}
		}
		return listDeReparacionesPorMecanico;
	}
	
	public List<Venta> getAllVentasPorVendedor(int id) {
		List <Venta> listDeTodasLasVentas = getAllVentas();
		List <Venta> listDeVentasPorVendedor = new ArrayList <Venta>();
		for(Venta venta : listDeTodasLasVentas) {
			if(id == venta.getIdVendedor()){
				listDeVentasPorVendedor.add(venta);
			}
		}
		return listDeVentasPorVendedor;
	}
	
	public boolean UpdateReparacion(Date fechaFin2, int idReparacion2) {
		return updateReparacion.updateReparacion(idReparacion2, fechaFin2);
	}

	public Reparacion anadirReparacion(Reparacion reparacaion) {
		reparacaion = anadirReparqacion.createReparacion(reparacaion);
		return reparacaion;
	}

	public boolean anadirMoto(Moto moto) {
		boolean sehaInsertadoElVehiculo = anadirVehiculo.insertarVehiculo(moto);
		if(sehaInsertadoElVehiculo) {
			boolean seHaInsertadoLaMoto = anadirMoto.insertarMoto(moto);
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
		boolean sehaInsertadoElVehiculo = anadirVehiculo.insertarVehiculo(coche);
		if(sehaInsertadoElVehiculo) {
			boolean seHaInsertadoElCoche = anadirCoche.insertarCoche(coche);
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
		return anadirCliente.insertarCliente(cliente);
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
		return anadirClienteVehiculo.insertarClienteVehiculo(clienteVehiculo);
	}

	public void addClienteVehiculo(ClienteVehiculo clienteVehiculo) {
		listDeClienteVehiculos.add(clienteVehiculo);
		
	}

	public void anadirUser(Empleado empleadoNuevo, String valueOf) {
		// TODO Auto-generated method stub
		
	}
	
	public Venta anadirVenta(Venta venta) {
		venta = anadirVenta.createVenta(venta);
		return venta;
	}

	public void addVenta(Venta venta) {
		listaDeVentas.add(venta);
		
	}

	public void verificarCuenteSeleccionada(String dni) {
		actualizarVerificado.actualizarBarcoPorId(dni);
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
		listEmpleadosNoVerificados = listDeUsersPorVerificar.getListaDeEmpleadosPorVerificar();
		
	}

	public boolean comprobarSiEstanVerificados(String text) {
		for(Empleado empleadoActual : listEmpleadosNoVerificados) {
			if(text.equals(empleadoActual.getDni())) {
				return false;
			}
		}
		return true;
	}

	public void exportarDatos() {
		outputReparaciones.outputReparaciones(this);
		outputVentas.outputVentas(this);
	}
	
	public void importarDatos() {
		intputReparaciones.inputReparaciones();
		intputVentas.inputVentas();
	}
}
