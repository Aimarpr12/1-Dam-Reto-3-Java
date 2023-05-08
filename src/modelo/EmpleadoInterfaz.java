package modelo;

public interface EmpleadoInterfaz {
	boolean addEmpleado(Empleado user); 
	boolean deleteEmpleado(Empleado user);
	int calcularAntiguedad();
	int calcularEdad();
	
}
