package Clases;

import java.util.Date;

public class Vendedor extends Empleado {
	protected double comisionVenta;

	public Vendedor(Empleado empleado, double comisionVenta) {
		super(empleado.id, empleado.dni, empleado.nombre, empleado.apellido, empleado.fechaNacimiento, 
				empleado.fechaContratacion, empleado.numeroTelefono, empleado.direccion, empleado.email, 
				empleado.salario, empleado.jefe);
		this.comisionVenta = comisionVenta;
	}
	
	public Vendedor(int id, String dni, String nombre, String apellido, Date fechaNacimiento, Date fechaContratacion,
			int numeroTelefono, String direccion, String email, int salario, int jefe, double comisionVenta) {
		super(id, dni, nombre, apellido, fechaNacimiento, fechaContratacion, numeroTelefono, direccion, email, salario, jefe);
		this.comisionVenta = comisionVenta;
	}

	public double getComisionVenta() {
		return comisionVenta;
	}

	public void setComisionVenta(double comisionVenta) {
		this.comisionVenta = comisionVenta;
	}

	
	
}
