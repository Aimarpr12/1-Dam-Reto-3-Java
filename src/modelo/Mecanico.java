package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Mecanico extends Empleado {

	protected List <Reparacion> listaDeReparaciones;
	protected String rango;
	
	
	public Mecanico(String dni, String nombre, String apellido, int numeroTelefono, Date fechaNacimiento,
			String direccion, String email) {
		super(dni, nombre, apellido, numeroTelefono, fechaNacimiento, direccion, email);
		// TODO Auto-generated constructor stub
	}

	

	public void setRango(String rango) {
		this.rango = rango;
	}



	public List<Reparacion> getListaDeReparaciones() {
		return listaDeReparaciones;
	}



	public void setListaDeReparaciones(List<Reparacion> listaDeReparaciones) {
		this.listaDeReparaciones = listaDeReparaciones;
	}



	public Mecanico(Empleado empleado, String rango) {
		super(empleado.getId(), empleado.getDni(), empleado.getNombre(),empleado.getApellido(), empleado.getFechaNacimiento(), empleado.getFechaContratacion(), empleado.getNumeroTelefono(), empleado.getDireccion(), empleado.getEmail(), empleado.getSalario(), empleado.getJefe());
		this.rango = rango; 
	}

	public Mecanico(int id, String dni, String nombre, String apellido, Date fechaNacimiento, Date fechaContratacion,
			int numeroTelefono, String direccion, String email, int salario, int jefe, TipoDeEmpleado tipoDeEmpleado, String rango) {
		super(id, dni, nombre, apellido, fechaNacimiento, fechaContratacion, numeroTelefono, direccion, email, salario, jefe, tipoDeEmpleado);
		this.rango = rango;
	}

	public Mecanico() {
		// TODO Auto-generated constructor stub
	}



	public String getRango() {
		return rango;
	}
	
	@Override
	public String toString() {
		return "Mecanico ["
				+ "id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido="
				+ apellido + ", fechaNacimiento=" + fechaNacimiento + ", fechaContratacion=" + fechaContratacion
				+ ", numeroTelefono=" + numeroTelefono + ", direccion=" + direccion + ", email=" + email + ", salario="
				+ salario + ", jefe=" + jefe + "]";
	}
	
	public Mecanico encontrarMecanicoEnLista(String dni, List <Empleado> listEmpleado) {
		for(Empleado empleado : listEmpleado) {
			if(dni.equals(empleado.getDni())) {
				return (Mecanico) empleado;
			}
		}
		return null;
	}
	
	public void addDatosMecanico(Empleado empleado) {
		this.id = empleado.getId();
		this.dni = empleado.getDni();
		this.nombre = empleado.getNombre();
		this.apellido = empleado.getApellido();
		this.fechaNacimiento = empleado.getFechaNacimiento();
		this.fechaContratacion = empleado.getFechaContratacion();
		this.numeroTelefono = empleado.getNumeroTelefono();
		this.direccion = empleado.getDireccion();
		this.email = empleado.getEmail();
		this.salario = empleado.getSalario();
		this.jefe = empleado.getJefe();
	}
	
	public void anadirReparacionAListaDeReparacion(Reparacion reparacion) {
		listaDeReparaciones.add(reparacion);
	}



	public void removeReparacionDeLaLista(int idReparacion) {
		List <Reparacion> listaNuevaDeReparacion = new ArrayList<Reparacion>();
		for(Reparacion reparacion : listaDeReparaciones) {
			if(idReparacion == reparacion.getIdReparacion()) {
				
			}else {
				listaNuevaDeReparacion.add(reparacion);
			}
		}
		listaDeReparaciones = listaNuevaDeReparacion;
		
	}

}
