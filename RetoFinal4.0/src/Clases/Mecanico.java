package Clases;

import java.util.Date;

public class Mecanico extends Empleado {

	protected String rango;

	public Mecanico(Empleado empleado, String rango) {
		super(empleado.id, empleado.dni, empleado.nombre, empleado.apellido, empleado.fechaNacimiento, 
				empleado.fechaContratacion, empleado.numeroTelefono, empleado.direccion, empleado.email, 
				empleado.salario, empleado.jefe);
		this.rango = rango;
	}
	
	public Mecanico(int id, String dni, String nombre, String apellido, Date fechaNacimiento, Date fechaContratacion,
			int numeroTelefono, String direccion, String email, int salario, int jefe, String rango) {
		super(id, dni, nombre, apellido, fechaNacimiento, fechaContratacion, numeroTelefono, direccion, email, salario, jefe);
		this.rango = rango;
	}

	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}
	
	

}
