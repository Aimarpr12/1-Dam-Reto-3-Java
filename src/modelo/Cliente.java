package modelo;

import java.util.List;

import controller.Controller;

public class Cliente {
	protected String dni;
	protected String nombre;
	protected String apellido;
	protected int telefono;
	protected String dir;
	protected String correo;

	public Cliente(String dni, String nombre, String apellido, int telefono, String dir, String correo) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.dir = dir;
		this.correo = correo;
	}

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Cliente getUserFromList(Controller controller, String dni2) {
		List <Cliente> listDeClientes = controller.getAllClientes();
		for(Cliente clienteActual : listDeClientes) {
			if(dni2.equals(clienteActual.getDni())){
				return clienteActual;
			}
		}
		return null;
		
	}

	public boolean addCliente(Cliente cliente, Controller controller) {
		boolean seHaInsertadoCorrectamente = controller.addClienteBD(cliente);
		if(seHaInsertadoCorrectamente) {
			controller.anadirCliente(cliente);
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono
				+ ", dir=" + dir + ", correo=" + correo + "]";
	}
	
	
}
