package modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MotoTest {

	@Test
	public void testGetCilindrada() {
		Moto moto = new Moto("1234ABC", "M-1234-BC", "Yamaha", "YZF-R6", 2019, TipoDeVehiculo.moto, 600);
		assertEquals(600, moto.getCilindrada());
	}

	@Test
	public void testSetCilindrada() {
		Moto moto = new Moto("1234ABC", "M-1234-BC", "Yamaha", "YZF-R6", 2019, TipoDeVehiculo.moto, 600);
		moto.setCilindrada(1000);
		assertEquals(1000, moto.getCilindrada());
	}

	@Test
	public void testEquals() {
		Moto moto = new Moto("1234ABC", "M-1234-BC", "Yamaha", "YZF-R6", 2019, TipoDeVehiculo.moto, 600);
		Moto otraMoto = new Moto("1234ABC", "M-1234-BC", "Yamaha", "YZF-R6", 2019, TipoDeVehiculo.moto, 600);
		Moto otraMoto2 = new Moto("1111AAA", "M-1111-AA", "Kawasaki", "Ninja", 2020, TipoDeVehiculo.moto, 1000);
		assertTrue(moto.equals(otraMoto));
		assertFalse(moto.equals(otraMoto2));
	}


}
