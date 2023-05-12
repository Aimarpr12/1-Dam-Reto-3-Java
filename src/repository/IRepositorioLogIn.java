package repository;

import error.LogInIncorrectoException;
import modelo.Empleado;

public interface IRepositorioLogIn {
	public void anadirLogIn(String dni, String password);
	public boolean comprobarContrasena(String dni, String passWord);
	public void updatePass(String dni, String pass);
	public void actualizarLoginPorId(String dni);
	public Empleado getLogInCorrect(String dni, String password) throws LogInIncorrectoException;
	public boolean comprobarSiExisteUser(String dni);
}
