package xmlParser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import modelo.Reparacion;

public class InputReparacionesTest {

	@Test
	public void testInputReparaciones() throws ParserConfigurationException, SAXException, IOException, ParseException {
		InputReparaciones inputReparaciones = new InputReparaciones();
		List<Reparacion> reparaciones = inputReparaciones.inputReparaciones();

		// Comprueba si se han leído las reparaciones correctamente
		assertEquals(3, reparaciones.size());

		// Comprueba los detalles de la primera reparación
		Reparacion reparacion1 = reparaciones.get(0);
		assertEquals(1, reparacion1.getIdReparacion());
		assertEquals("456123789012345XZ", reparacion1.getIdVehiculo());
		assertEquals("ReparaciÃ³n de motor", reparacion1.getDescripcion());
		assertEquals(500.0, reparacion1.getCoste(), 0.001);
		assertEquals(728.5, reparacion1.getPrecio(), 0.001);

		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaIni1 = formatoFecha.parse("2023-04-10");
		Date fechaFin1 = formatoFecha.parse("2023-04-12");
		assertEquals(fechaIni1, reparacion1.getFechaIni());
		assertEquals(fechaFin1, reparacion1.getFechaFin());
		assertEquals(4, reparacion1.getIdMecanico());

		// Comprueba los detalles de la segunda reparación
		Reparacion reparacion2 = reparaciones.get(1);
		assertEquals(2, reparacion2.getIdReparacion());
		assertEquals("123456789012345AB", reparacion2.getIdVehiculo());
		assertEquals("ReparaciÃ³n trnsmisiÃ³n", reparacion2.getDescripcion());
		assertEquals(445.1, reparacion2.getCoste(), 0.001);
		assertEquals(634.0, reparacion2.getPrecio(), 0.001);

		Date fechaIni2 = formatoFecha.parse("2023-04-11");
		Date fechaFin2 = formatoFecha.parse("2023-04-14");
		assertEquals(fechaIni2, reparacion2.getFechaIni());
		assertEquals(fechaFin2, reparacion2.getFechaFin());
		assertEquals(5, reparacion2.getIdMecanico());

		// Comprueba los detalles de la tercera reparación
		Reparacion reparacion3 = reparaciones.get(2);
		assertEquals(3, reparacion3.getIdReparacion());
		assertEquals("456789012345678EF", reparacion3.getIdVehiculo());
		assertEquals("Reemplazo de frenos", reparacion3.getDescripcion());
		assertEquals(300.0, reparacion3.getCoste(), 0.001);
		assertEquals(50.0, reparacion3.getPrecio(), 0.001);

		Date fechaIni3 = formatoFecha.parse("2023-04-12");
		Date fechaFin3 = formatoFecha.parse("2023-04-13");
		assertEquals(fechaIni3, reparacion3.getFechaIni());
		assertEquals(fechaFin3, reparacion3.getFechaFin());
		assertEquals(6, reparacion3.getIdMecanico());
	}

}
