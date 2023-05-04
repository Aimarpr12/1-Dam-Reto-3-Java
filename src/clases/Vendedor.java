package clases;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Vendedor extends Empleado {
	protected List <Venta> listaDeVentas;
	protected double comision;
	
	
	public List<Venta> getListaDeVentas() {
		return listaDeVentas;
	}



	public void setListaDeVentas(List<Venta> listaDeVentas) {
		this.listaDeVentas = listaDeVentas;
	}



	public double getComision() {
		return comision;
	}



	public void setComision(double comision) {
		this.comision = comision;
	}



	public Vendedor(Empleado empleado, double comisionVenta) {
		super(empleado.id, empleado.dni, empleado.nombre, empleado.apellido, empleado.fechaNacimiento, 
				empleado.fechaContratacion, empleado.numeroTelefono, empleado.direccion, empleado.email, 
				empleado.salario, empleado.jefe);
		this.comision = comisionVenta;
	}
	
	
	
	public Vendedor(String dni, String nombre, String apellido, int numeroTelefono, Date fechaNacimiento,
			String direccion, String email) {
		super(dni, nombre, apellido, numeroTelefono, fechaNacimiento, direccion, email);
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "Vendedor [comision=" + comision + ", id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido="
				+ apellido + ", fechaNacimiento=" + fechaNacimiento + ", fechaContratacion=" + fechaContratacion
				+ ", numeroTelefono=" + numeroTelefono + ", direccion=" + direccion + ", email=" + email + ", salario="
				+ salario + ", jefe=" + jefe+ "]";
	}



	public Vendedor(int id, String dni, String nombre, String apellido, Date fechaNacimiento, Date fechaContratacion,
			int numeroTelefono, String direccion, String email, int salario, int jefe, TipoDeEmpleado tipoDeEmpleado, double comision) {
		super(id, dni, nombre, apellido, fechaNacimiento, fechaContratacion, numeroTelefono, direccion, email, salario, jefe, tipoDeEmpleado);
		this.comision = comision;
	}

	
	@Override
	public int calcularAntiguedad() {
		 return Period.between(this.getFechaContratacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();		
	}
	@Override
	public int calcularEdad() {
		 return Period.between(this.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();		
		
	}

	
	
}
