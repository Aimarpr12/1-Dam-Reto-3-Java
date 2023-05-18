package repository;

import java.util.List;

import modelo.Empleado;

public interface IRepositorioEmpleado {
	public void anadirUser(Empleado empleado);
	public Empleado getLogInCorrect(String DNI, String password);
	public List <Empleado> getListaDeEmpleadosPorVerificar();
	public void updateUser(Empleado user, int id);
	public List <Empleado> getListaDeEmpleados();
	public boolean cambiarAMecanico(int id);
	public boolean cambiarAVendedor(int id);
}
