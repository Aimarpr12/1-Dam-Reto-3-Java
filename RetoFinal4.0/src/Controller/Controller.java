package Controller;

import Clases.Empleado;
import Repository.ComprobarLogIn;
import Repository.ComprobarSiEsJefe;
import Repository.ComprobarSiEsMecanico;
import Repository.ConseguirDatosDeLosMecanicos;
import Repository.ConseguirDatosDeLosVendedores;
import Repository.ComprobarQueEsUserNoEstaRegistrado;

public class Controller {
	
	private static ComprobarLogIn comprobarLogIn = new ComprobarLogIn();
	private static ComprobarSiEsJefe comprobarjefe = new ComprobarSiEsJefe();
	private static ComprobarSiEsMecanico comprobarMecanico = new ComprobarSiEsMecanico();
	private static ConseguirDatosDeLosMecanicos datosDeLosMecanicos = new ConseguirDatosDeLosMecanicos();
	private static ConseguirDatosDeLosVendedores datosDeLosVendedores = new ConseguirDatosDeLosVendedores();
	private static ComprobarQueEsUserNoEstaRegistrado comprobarUser = new ComprobarQueEsUserNoEstaRegistrado();
	
	public Controller() {
		
	}

	public Empleado getLogInCorrect(String text, String valueOf) {
		Empleado logInCorrecto = comprobarLogIn.getLogInCorrect(text, valueOf);
		return logInCorrecto;
	}

	public boolean comporbarSiEsJefe(int id) {
		boolean esJefe = comprobarjefe.comprobarJefe(id);
		return esJefe;
	}

	public boolean comprobarSiEsMecanico(int id) {
		boolean esMecanico = comprobarMecanico.comprobarMecanico(id);
		return esMecanico;
	}
	
	public String conseguirDatosDeMecanico(int id) {
		String rango = datosDeLosMecanicos.conseguirDatosMecanicos(id);
		return rango;
	}

	public double conseguirDatosDeVendedor(int id) {
		double comisionDeVenta = datosDeLosVendedores.conseguirDatosVendedores(id);
		return comisionDeVenta;
	}

	public boolean comprobarUser(String text) {
		return comprobarUser.comprobarSiExisteUser(text);
	}

}
