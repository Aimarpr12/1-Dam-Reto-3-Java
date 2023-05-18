package modelo;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmpleadoTest {
    
    @Test
    public void testGetAllEmpleadosMecanico() {
        // Crear lista de empleados de ejemplo
        List<Empleado> todosLosEmpleados = new ArrayList<>();
        Empleado empleado1 = new Empleado("12345678", "John", "Doe", 123456789, new Date(), "Dirección 1", "john.doe@example.com");
        empleado1.setTipoDeEmpleado(TipoDeEmpleado.mecanico);
        Empleado empleado2 = new Empleado("98765432", "Jane", "Smith", 987654321, new Date(), "Dirección 2", "jane.smith@example.com");
        empleado2.setTipoDeEmpleado(TipoDeEmpleado.vendedor);
        Empleado empleado3 = new Empleado("45678901", "Bob", "Johnson", 654321987, new Date(), "Dirección 3", "bob.johnson@example.com");
        empleado3.setTipoDeEmpleado(TipoDeEmpleado.mecanico);
        todosLosEmpleados.add(empleado1);
        todosLosEmpleados.add(empleado2);
        todosLosEmpleados.add(empleado3);
        
        // Obtener todos los empleados mecánicos
        Empleado empleadoMecanico = new Empleado();
        List<Empleado> empleadosMecanicos = empleadoMecanico.getAllEmpeladosMecanico(todosLosEmpleados);
        
        // Verificar que solo se obtienen los empleados mecánicos
        Assert.assertEquals(2, empleadosMecanicos.size());
        Assert.assertTrue(empleadosMecanicos.contains(empleado1));
        Assert.assertTrue(empleadosMecanicos.contains(empleado3));
        Assert.assertFalse(empleadosMecanicos.contains(empleado2));
    }
    
    @Test
    public void testGetAllEmpleadosVendedor() {
        // Crear lista de empleados de ejemplo
        List<Empleado> todosLosEmpleados = new ArrayList<>();
        Empleado empleado1 = new Empleado("12345678", "John", "Doe", 123456789, new Date(), "Dirección 1", "john.doe@example.com");
        empleado1.setTipoDeEmpleado(TipoDeEmpleado.mecanico);
        Empleado empleado2 = new Empleado("98765432", "Jane", "Smith", 987654321, new Date(), "Dirección 2", "jane.smith@example.com");
        empleado2.setTipoDeEmpleado(TipoDeEmpleado.vendedor);
        Empleado empleado3 = new Empleado("45678901", "Bob", "Johnson", 654321987, new Date(), "Dirección 3", "bob.johnson@example.com");
        empleado3.setTipoDeEmpleado(TipoDeEmpleado.vendedor);
        todosLosEmpleados.add(empleado1);
        todosLosEmpleados.add(empleado2);
        todosLosEmpleados.add(empleado3);
        
        // Obtener todos los empleados vendedores
        Empleado empleadoVendedor = new Empleado();
        List<Empleado> empleadosVendedores = empleadoVendedor.getAllEmpeladosVendedor(todosLosEmpleados);
        
        // Verificarque solo se obtienen los empleados vendedores
        Assert.assertEquals(2, empleadosVendedores.size());
        Assert.assertTrue(empleadosVendedores.contains(empleado2));
        Assert.assertTrue(empleadosVendedores.contains(empleado3));
        Assert.assertFalse(empleadosVendedores.contains(empleado1));
        }
    
    @Test
    public void testGetEmpleadoConDni() {
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
        
        // Obtener empleado con DNI específico
        Empleado empleado = new Empleado();
        Empleado empleadoEncontrado = empleado.getEmpleadoConDni("98765432", listaEmpleados);
        
        // Verificar que se encuentra el empleado con el DNI buscado
        Assert.assertNotNull(empleadoEncontrado);
        Assert.assertEquals("98765432", empleadoEncontrado.getDni());
        Assert.assertEquals("Jane", empleadoEncontrado.getNombre());
        Assert.assertEquals("Smith", empleadoEncontrado.getApellido());
    }

    @Test
    public void testCalcularAntiguedad() {
        // Crear empleado con fecha de contratación hace 5 años
        Empleado empleado = new Empleado();
        LocalDate fechaContratacion = LocalDate.now().minusYears(5);
        Date fechaContratacionDate = java.sql.Date.valueOf(fechaContratacion);
        empleado.setFechaContratacion(fechaContratacionDate);
        
        // Calcular antigüedad
        int antiguedad = empleado.calcularAntiguedad();
        
        // Verificar que la antigüedad es 5 años
        Assert.assertEquals(5, antiguedad);
    }

    @Test
    public void testCalcularEdad() {
        // Crear empleado con fecha de nacimiento hace 30 años
        Empleado empleado = new Empleado();
        LocalDate fechaNacimiento = LocalDate.of(1993, Month.JANUARY, 1);
        Date fechaNacimientoDate = java.sql.Date.valueOf(fechaNacimiento);
        empleado.setFechaNacimiento(fechaNacimientoDate);
        
        // Calcular edad
        int edad = empleado.calcularEdad();
        
        // Verificar que la edad es 30 años
        Assert.assertEquals(30, edad);
    }
}
