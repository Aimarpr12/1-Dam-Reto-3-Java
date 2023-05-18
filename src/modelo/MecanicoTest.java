package modelo;

import modelo.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MecanicoTest {

    @Test
    public void testEncontrarMecanicoEnLista() {
        // Crear lista de empleados de ejemplo
        List<Empleado> listaEmpleados = new ArrayList<>();
        Empleado empleado1 = new Empleado("12345678", "John", "Doe", 123456789, new Date(), "Dirección 1", "john.doe@example.com");
        empleado1.setTipoDeEmpleado(TipoDeEmpleado.mecanico);
        Empleado empleado2 = new Empleado("98765432", "Jane", "Smith", 987654321, new Date(), "Dirección 2", "jane.smith@example.com");
        empleado2.setTipoDeEmpleado(TipoDeEmpleado.vendedor);
        Empleado empleado3 = new Empleado("45678901", "Bob", "Johnson", 654321987, new Date(), "Dirección 3", "bob.johnson@example.com");
        empleado3.setTipoDeEmpleado(TipoDeEmpleado.mecanico);
        listaEmpleados.add(empleado1);
        listaEmpleados.add(empleado2);
        listaEmpleados.add(empleado3);

        // Crear instancia de Mecanico
        Mecanico mecanico = new Mecanico();

        // Buscar un mecanico en la lista por DNI
        Mecanico mecanicoEncontrado = mecanico.encontrarMecanicoEnLista("45678901", listaEmpleados);

        // Verificar que se encuentra el mecanico con el DNI buscado
        Assert.assertNotNull(mecanicoEncontrado);
        Assert.assertEquals("45678901", mecanicoEncontrado.getDni());
        Assert.assertEquals("Bob", mecanicoEncontrado.getNombre());
        Assert.assertEquals("Johnson", mecanicoEncontrado.getApellido());
    }

    @Test
    public void testAddDatosMecanico() {
        // Crear empleado de ejemplo
        Empleado empleado = new Empleado("12345678", "John", "Doe", 123456789, new Date(), "Dirección 1", "john.doe@example.com");
        empleado.setSalario(2000);
        empleado.setJefe(12345);

        // Crear instancia de Mecanico
        Mecanico mecanico = new Mecanico();

        // Agregar los datos del empleado al mecanico
        mecanico.addDatosMecanico(empleado);

        // Verificar que los datos se han agregado correctamente
        Assert.assertEquals("12345678", mecanico.getDni());
        Assert.assertEquals("John", mecanico.getNombre());
        Assert.assertEquals("Doe", mecanico.getApellido());
        Assert.assertEquals(2000, mecanico.getSalario());
        Assert.assertEquals(12345, mecanico.getJefe());
    }

    @Test
    public void testAnadirReparacionAListaDeReparacion() {
        // Crear instancia de Mecanico
        Mecanico mecanico = new Mecanico();
        
        // Crear reparacion de ejemplo
        Reparacion reparacion = new Reparacion();
        reparacion.setIdReparacion(1);
        reparacion.setDescripcion("Reparación de motor");
        // Añadir la reparacion a la lista de reparaciones del mecanico
        mecanico.anadirReparacionAListaDeReparacion(reparacion);
        
        // Verificar que la reparacion se ha añadido correctamente
        List<Reparacion> listaDeReparaciones = mecanico.getListaDeReparaciones();
        Assert.assertEquals(1, listaDeReparaciones.size());
        Assert.assertEquals(reparacion, listaDeReparaciones.get(0));
    }

    @Test
    public void testRemoveReparacionDeLaLista() {
        // Crear instancia de Mecanico
        Mecanico mecanico = new Mecanico();
        
        // Crear reparaciones de ejemplo
        Reparacion reparacion1 = new Reparacion();
        reparacion1.setIdReparacion(1);
        reparacion1.setDescripcion("Reparación de motor");
        Reparacion reparacion2 = new Reparacion();
        reparacion2.setIdReparacion(2);
        reparacion2.setDescripcion("Reparación de frenos");
        
        // Agregar las reparaciones a la lista de reparaciones del mecanico
        mecanico.anadirReparacionAListaDeReparacion(reparacion1);
        mecanico.anadirReparacionAListaDeReparacion(reparacion2);
        
        // Eliminar una reparacion de la lista por su ID
        mecanico.removeReparacionDeLaLista(1);
        
        // Verificar que la reparacion se ha eliminado correctamente
        List<Reparacion> listaDeReparaciones = mecanico.getListaDeReparaciones();
        Assert.assertEquals(1, listaDeReparaciones.size());
        Assert.assertEquals(reparacion2, listaDeReparaciones.get(0));
    }
}

