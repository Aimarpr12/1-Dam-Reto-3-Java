package modelo;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class ClienteTest {
    
    @Test
    public void testGetUserFromList() {
        // Crear una lista de clientes
        List<Cliente> listaClientes = new ArrayList<>();
        Cliente cliente1 = new Cliente("12345678", "John", "Doe", 123456789, "Dirección 1", "john@example.com");
        Cliente cliente2 = new Cliente("98765432", "Jane", "Smith", 987654321, "Dirección 2", "jane@example.com");
        listaClientes.add(cliente1);
        listaClientes.add(cliente2);
        
        // Obtener un cliente existente por su DNI
        Cliente resultadoExistente = cliente1.getUserFromList(listaClientes, "12345678");
        Assert.assertEquals(cliente1, resultadoExistente);
        
        // Obtener un cliente no existente por su DNI
        Cliente resultadoNoExistente = cliente1.getUserFromList(listaClientes, "99999999");
        Assert.assertNull(resultadoNoExistente);
    }
}