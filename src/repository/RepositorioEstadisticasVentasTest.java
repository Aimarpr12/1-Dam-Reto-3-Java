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
        assertEquals(2, estadisticas.size());

        EstadisticasDeVentas estadisticasVendedor1 = estadisticas.get(0);
        assertNotNull(estadisticasVendedor1);
        // Verificar los valores esperados para el primer vendedor
        assertEquals("Nombre Vendedor 1", estadisticasVendedor1.getNombreCompleto());
        assertEquals("Total Comision 1", estadisticasVendedor1.getComisionTotal());

        EstadisticasDeVentas estadisticasVendedor2 = estadisticas.get(1);
        assertNotNull(estadisticasVendedor2);
        // Verificar los valores esperados para el segundo vendedor
        assertEquals("Nombre Vendedor 2", estadisticasVendedor2.getNombreCompleto());
        assertEquals("Total Comision 2", estadisticasVendedor2.getComisionTotal());
    }
}
