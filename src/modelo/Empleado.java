package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import controller.Controller;
import error.VehiculoNoEncontradoException;

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
	public Empleado(Empleado[] empleado) {
		// TODO Auto-generated constructor stub
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
	
	public List <Empleado> getAllEmpeladosMecanico(Controller controller) {
		List<Empleado> todosLosEmplados = controller.getAllEmpleado();
		List<Empleado> listaEmpleadosMecanicos = new ArrayList<Empleado>();
		for(Empleado empleadoActual : todosLosEmplados) {
			if(empleadoActual.getTipoDeEmpleado().equals(TipoDeEmpleado.mecanico)) {
				listaEmpleadosMecanicos.add(empleadoActual);
			}
		}
		return listaEmpleadosMecanicos;
	}
	
	public List <Empleado> getAllEmpeladosVendedor(Controller controller) {
		List<Empleado> todosLosEmplados = controller.getAllEmpleado();
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
	
	public Empleado getEmpleadoConDni(String dni, Controller controller) {
		controller.cargarListaDeEmpleados();
		for(Empleado empleado : controller.getAllEmpleado()) {
			if(dni.equals(empleado.getDni())) {
				return empleado;
			}
		}
		return null;
	}
	
	public boolean editSalario(String dni, int salario, Controller controller) {
		Empleado empleadoAModificar = new Empleado();
		empleadoAModificar.getEmpleadoConDni(dni, controller);
		int salarioAnterior = empleadoAModificar.getSalario();
		boolean sehaActualizadoCorrectamentEnBD = controller.UpdateSalario(dni, salario);
		if(!sehaActualizadoCorrectamentEnBD) {
			return false;
		}
		boolean seHaEditadoCorrectamente = false;
		List<Empleado> todosLosEmplados = controller.getAllEmpleado();
		for(Empleado empladoActual : todosLosEmplados) {
			if(empladoActual.getDni().equals(dni)) {
				empladoActual.setSalario(salario);
				seHaEditadoCorrectamente = true;
			}
		}
		if(!seHaEditadoCorrectamente) {
			controller.UpdateSalario(dni, salarioAnterior);
		}
		return seHaEditadoCorrectamente;
	}
	@Override
	public int calcularAntiguedad() {
		 return Period.between(this.getFechaContratacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();		
	}
	@Override
	public int calcularEdad() {
		 return Period.between(this.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();		
		
	}
	public boolean editIdJefe(String dni, int idJfe, Controller controller) {
		boolean sehaActualizadoCorrectamentEnBD = controller.UpdateIdJefe(dni, idJfe);
		if(!sehaActualizadoCorrectamentEnBD) {
			return false;
		} 
		boolean seHaEditadoCorrectamente = false;;
		List<Empleado> todosLosEmplados = controller.getAllEmpleado();
		for(Empleado empladoActual : todosLosEmplados) {
			if(empladoActual.getDni().equals(dni)) {
				empladoActual.setJefe(idJfe);
				seHaEditadoCorrectamente = true;
			}
		}
		return seHaEditadoCorrectamente;
	}
	
	public boolean editTelefono(String dni2, int telefono, Controller controller) {
		boolean sehaActualizadoCorrectamentEnBD = controller.updateTelefono(dni2, telefono);
		if(!sehaActualizadoCorrectamentEnBD) {
			return false;
		} 
		boolean seHaEditadoCorrectamente = false;;
		List<Empleado> todosLosEmplados = controller.getAllEmpleado();
		for(Empleado empladoActual : todosLosEmplados) {
			if(empladoActual.getDni().equals(dni2)) {
				empladoActual.setNumeroTelefono(telefono);
				seHaEditadoCorrectamente = true;
			}
		}
		return seHaEditadoCorrectamente;
	}
	public boolean editDireccion(String dni2, String direccion2, Controller controller) {
		boolean sehaActualizadoCorrectamentEnBD = controller.updateDireccion(dni2, direccion2);
		if(!sehaActualizadoCorrectamentEnBD) {
			return false;
		} 
		boolean seHaEditadoCorrectamente = false;;
		List<Empleado> todosLosEmplados = controller.getAllEmpleado();
		for(Empleado empladoActual : todosLosEmplados) {
			if(empladoActual.getDni().equals(dni2)) {
				empladoActual.setDireccion(direccion2);
				seHaEditadoCorrectamente = true;
			}
		}
		return seHaEditadoCorrectamente;
	}
	
//	public Mecanico devolerusuarioComoMecanico() throws UsuarioNoValidoException {
//		try {
//		    Mecanico mecanico = (Mecanico) this;
//		    return mecanico;
//		} catch (Exception e1) {
//			throw new UsuarioNoValidoException("El usuario no es un Mecanico ni un Vendedor válido");		}
//	}
	
  
}
