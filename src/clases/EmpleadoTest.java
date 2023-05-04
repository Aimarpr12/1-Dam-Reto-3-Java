package clases;


import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;



public class EmpleadoTest {

    @Test
    public void testEquals() {
        Empleado e1 = new Empleado(1, "12345678A", "Juan", "Pérez", new Date(2000-1-1), new Date(2000-1-1),
                123456789, "Calle Falsa 123", "juan.perez@empresa.com", 1000, 0);
        Empleado e2 = new Empleado(1, "12345678A", "Juan", "Pérez", new Date(2000-1-1), new Date(2000-1-1),
                123456789, "Calle Falsa 123", "juan.perez@empresa.com", 1000, 0);
        Empleado e3 = new Empleado(2, "23456789B", "María", "García", new Date(2000-1-1), new Date(2000-1-1),
                987654321, "Avenida Falsa 456", "maria.garcia@empresa.com", 1500, 1);
        
        assertTrue(e1.equals(e2));
        assertFalse(e1.equals(e3));
    }
    
    @Test
    public void testSetId() {
        Empleado e = new Empleado(1, "12345678A", "Juan", "Pérez", new Date(2000-1-1), new Date(2000-1-1),
                123456789, "Calle Falsa 123", "juan.perez@empresa.com", 1000, 0);
        
        e.setId(2);
        assertEquals(2, e.getId());
    }
    
    @Test
    public void testSetEmail() {
        Empleado e = new Empleado(1, "12345678A", "Juan", "Pérez", new Date(2000-1-1), new Date(2000-1-1),
                123456789, "Calle Falsa 123", "juan.perez@empresa.com", 1000, 0);
        
        
        e.setEmail("juan.perez@gmail.com");
        assertNotNull(e.getEmail());
        assertEquals("juan.perez@gmail.com", e.getEmail());
        e.setEmail(null);
        assertNull(e.getEmail());
    }
    
    @Test
    public void testConstructor() {
        Empleado e = new Empleado(1, "12345678A", "Juan", "Pérez", new Date(2000-1-1), new Date(2000-1-1),
                123456789, "Calle Falsa 123", "juan.perez@empresa.com", 1000, 0);
        
        assertNotNull(e);
        assertEquals(1, e.getId());
        assertEquals("12345678A", e.getDni());
        assertEquals("Juan", e.getNombre());
        assertEquals("Pérez", e.getApellido());
        assertNotNull(e.getFechaNacimiento());
        assertNotNull(e.getFechaContratacion());
        assertEquals(123456789, e.getNumeroTelefono());
        assertEquals("Calle Falsa 123", e.getDireccion());
        assertEquals("juan.perez@empresa.com", e.getEmail());
        assertEquals(1000, e.getSalario());
        assertEquals(0, e.getJefe());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidId() {
        Empleado e = new Empleado(0, "12345678A", "Juan", "Pérez", new Date(), new Date(),
                123456789, "Calle Falsa 123", "juan.perez@empresa.com", 1000, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIdWithInvalidValue() {
        Empleado e = new Empleado(1, "12345678A", "Juan", "Pérez", new Date(), new Date(),
                123456789, "Calle Falsa 123", "juan.perez@empresa.com", 1000, 0);
        e.setId(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSalarioWithInvalidValue() {
        Empleado e = new Empleado(1, "12345678A", "Juan", "Pérez", new Date(), new Date(),
                123456789, "Calle Falsa 123", "juan.perez@empresa.com", 1000, 0);
        e.setSalario(-100);
    }

}
