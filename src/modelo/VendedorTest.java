package modelo;

import modelo.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendedorTest {

    @Test
    public void testAddDatosVendedor() {
        // Crear empleado de ejemplo
        Empleado empleado = new Empleado("12345678", "John", "Doe", 123456789, new Date(), "Dirección 1", "john.doe@example.com");
        empleado.setSalario(2000);
        empleado.setJefe(12345);

        // Crear instancia de Vendedor
        Vendedor vendedor = new Vendedor();

        // Agregar los datos del empleado al vendedor
        vendedor.addDatosVendedor(empleado);

        // Verificar que los datos se han agregado correctamente
        Assert.assertEquals("12345678", vendedor.getDni());
        Assert.assertEquals("John", vendedor.getNombre());
        Assert.assertEquals("Doe", vendedor.getApellido());
        Assert.assertEquals(2000, vendedor.getSalario());
        Assert.assertEquals(12345, vendedor.getJefe());
    }

    @Test
    public void testEncontrarVendedorEnLista() {
        // Crear lista de empleados de ejemplo
        List<Empleado> listaEmpleados = new ArrayList<>();
        Empleado empleado1 = new Empleado("12345678", "John", "Doe", 123456789, new Date(), "Dirección 1", "john.doe@example.com");
        Empleado empleado2 = new Empleado("98765432", "Jane", "Smith", 987654321, new Date(), "Dirección 2", "jane.smith@example.com");
        Empleado empleado3 = new Empleado("56789012", "Michael", "Johnson", 567890123, new Date(), "Dirección 3", "michael.johnson@example.com");
        listaEmpleados.add(empleado1);
        listaEmpleados.add(empleado2);
        listaEmpleados.add(empleado3);
        // Crear instancia de Vendedor
        Vendedor vendedor = new Vendedor();
        
        // Encontrar un vendedor en la lista por su DNI
        Vendedor vendedorEncontrado = vendedor.encontrarVendedorEnLista("98765432", listaEmpleados);
        
        // Verificar que se ha encontrado el vendedor correcto
        Assert.assertNotNull(vendedorEncontrado);
        Assert.assertEquals("98765432", vendedorEncontrado.getDni());
        Assert.assertEquals("Jane", vendedorEncontrado.getNombre());
        Assert.assertEquals("Smith", vendedorEncontrado.getApellido());
    }
}
        