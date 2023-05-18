package repository;

import static org.junit.Assert.*;

import org.junit.Test;

import error.LogInIncorrectoException;
import modelo.Empleado;

public class RepositorioLogInTest {
	
	@Test
	public void test() throws LogInIncorrectoException {
		RepositorioLogIn reporistorioLogIn = new RepositorioLogIn();	
		Empleado empleado = reporistorioLogIn.getLogInCorrect("12345678A", "1234");
		
		assertEquals(empleado.getDni(), "12345678A");
	}
}
