package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    protected TipoDeEmpleado tipoDeEmpleado;
    
   
	@Override
	public String toString() {
		return "Empleado [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", fechaNacimiento=" + fechaNacimiento + ", fechaContratacion=" + fechaContratacion
				+ ", numeroTelefono=" + numeroTelefono + ", direccion=" + direccion + ", email=" + email + ", salario="
				+ salario + ", jefe=" + jefe + ", tipoDeEmpleado=" + tipoDeEmpleado + "]";
	}
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
	
	
	public TipoDeEmpleado getTipoDeEmpleado() {
		return tipoDeEmpleado;
	}
	public void setTipoDeEmpleado(TipoDeEmpleado tipoDeEmpleado) {
		this.tipoDeEmpleado = tipoDeEmpleado;
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
	
	public Empleado(String dni, String nombre, String apellido, int numeroTelefono, Date fechaNacimiento,
			String direccion, String email) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.numeroTelefono = numeroTelefono;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.email = email;
	}
	
	public Empleado() {
		
	}
	
	public Empleado(String dni, String nombre, String apellido) {
		super();		
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
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
	
	public Empleado(int id, String dni, String nombre, String apellido, Date fechaNacimiento, Date fechaAlt,
			int telefono, String dir, String correo, int salario, int idjefe, TipoDeEmpleado tipodeEmpleado) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaContratacion = fechaAlt;
		this.numeroTelefono = telefono;
		this.direccion = dir;
		this.email = correo;
		this.salario = salario;
		this.jefe = idjefe;
		this.tipoDeEmpleado = tipodeEmpleado;
	}
	
	public List <Empleado> getAllEmpeladosMecanico(List<Empleado> todosLosEmplados) {
		List<Empleado> listaEmpleadosMecanicos = new ArrayList<Empleado>();
		for(Empleado empleadoActual : todosLosEmplados) {
			if(empleadoActual.getTipoDeEmpleado().equals(TipoDeEmpleado.mecanico)) {
				listaEmpleadosMecanicos.add(empleadoActual);
			}
		}
		return listaEmpleadosMecanicos;
	}
	
	public List <Empleado> getAllEmpeladosVendedor(List<Empleado> todosLosEmplados) {
		System.out.println(todosLosEmplados.size());
		List<Empleado> listaEmpleadosVendedor = new ArrayList<Empleado>();
		for(Empleado empleadoActual : todosLosEmplados) {
			System.out.println(empleadoActual.getTipoDeEmpleado());
			if(TipoDeEmpleado.vendedor.equals(empleadoActual.getTipoDeEmpleado())) {
				listaEmpleadosVendedor.add(empleadoActual);
			}
		}
		return listaEmpleadosVendedor;
	}
	
	public Empleado getEmpleadoConDni(String dni, List<Empleado> listDeEmplado) {
		for(Empleado empleado : listDeEmplado) {
			if(dni.equals(empleado.getDni())) {
				return empleado;
			}
		}
		return null;
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
