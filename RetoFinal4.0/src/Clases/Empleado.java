package Clases;

import java.util.Date;
import java.util.Objects;

public class Empleado implements EmpleadoInterfaz {
	protected int id;
    protected String dni;
    protected String nombre;
    protected String apellido;
    protected Date fechaNacimiento;
    protected Date fechaContratacion;
    protected int numeroTelefono;
    protected String direccion;
    protected String email;
    protected int salario;
    protected int jefe;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Date getFechaContratacion() {
		return fechaContratacion;
	}
	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	public int getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(int numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	public int getJefe() {
		return jefe;
	}
	public void setJefe(int jefe) {
		this.jefe = jefe;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(dni, other.dni) && Objects.equals(email, other.email)
				&& Objects.equals(fechaContratacion, other.fechaContratacion)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento) && id == other.id && jefe == other.jefe
				&& Objects.equals(nombre, other.nombre) && numeroTelefono == other.numeroTelefono
				&& salario == other.salario;
	}
	
	public Empleado(int id, String dni, String nombre, String apellido, Date fechaNacimiento, Date fechaContratacion,
			int numeroTelefono, String direccion, String email, int salario, int jefe) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaContratacion = fechaContratacion;
		this.numeroTelefono = numeroTelefono;
		this.direccion = direccion;
		this.email = email;
		this.salario = salario;
		this.jefe = jefe;
	}
	public Empleado(Empleado[] empleado) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean addEmpleado(Empleado user) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteEmpleado(Empleado user) {
		// TODO Auto-generated method stub
		return false;
	}
  
}
