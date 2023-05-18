package repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import modelo.EstadisticasDeVentas;
import org.junit.BeforeClass;
import org.junit.Test;


public class RepositorioEstadisticasVentasTest {

    private static IRepositorioEstadisticasVentas repositorio;

    @BeforeClass
    public static void setUp() {
        repositorio = new RepositorioEstadisticasVentas();
    }

    @Test
    public void testObtenerTopDosVendedores() {
        List<EstadisticasDeVentas> estadisticas = repositorio.obtenerTopDosVendedores();
        assertNotNull(estadisticas);
        assertEquals(1, estadisticas.size());

        EstadisticasDeVentas estadisticasVendedor1 = estadisticas.get(0);
        assertNotNull(estadisticasVendedor1);
        // Verificar los valores esperados para el primer vendedor
        assertEquals("Juan Pérez", estadisticasVendedor1.getNombreCompleto());
        assertEquals("2,000.00", estadisticasVendedor1.getComisionTotal());

    }
}
