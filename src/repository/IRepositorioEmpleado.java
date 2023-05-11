package repository;

import java.util.List;

import modelo.Empleado;

public interface IRepositorioEmpleado {
	public void anadirUser(Empleado empleado);
	public Empleado getLogInCorrect(String DNI, String password);
	public List <Empleado> getListaDeEmpleadosPorVerificar();
	public boolean updateDir(String dni, String dir);
	public boolean updateSalary(String dni, int salario);
	public void updateUser(Empleado user, int id);
	public List <Empleado> getListaDeEmpleados();
	public boolean updateIdJefe(String dni, int idJefe);
	public boolean updateTelefono(String dni, int telefono);
	public boolean cambiarAMecanico(int id);
	public boolean cambiarAVendedor(int id);
}
