package repository;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Venta;

public class RepositorioVentaTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		Date date = new Date();
		Venta venta = new Venta(21, 2131, date, "23456789A", "12345678901234567", 2);
		RepositorioVenta repositorio = new RepositorioVenta();
		
		
	}

}
