package modelo;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class CocheTest {
    
    @Test
    public void testEncontrarVehiculoEnLista() {
        // Crear una lista de vehículos
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        Coche coche1 = new Coche("1234", "ABC123", "Ford", "Focus", 2020, TipoDeVehiculo.coche);
        Coche coche2 = new Coche("5678", "DEF456", "Toyota", "Corolla", 2019, TipoDeVehiculo.coche);
        Moto moto1 = new Moto("4321", "XYZ789", "Honda", "CBR500", 2021, TipoDeVehiculo.moto);
        listaVehiculos.add(coche1);
        listaVehiculos.add(coche2);
        listaVehiculos.add(moto1);
        
        // Encontrar un coche existente por su matrícula
        Vehiculo resultadoCocheExistente = coche1.encontrarVehiculoEnLista("ABC123", listaVehiculos);
        Assert.assertEquals(coche1, resultadoCocheExistente);
        
        // Encontrar una moto existente por su matrícula
        Vehiculo resultadoMotoExistente = coche1.encontrarVehiculoEnLista("XYZ789", listaVehiculos);
        Assert.assertEquals(moto1, resultadoMotoExistente);
        
        // Encontrar un vehículo no existente por su matrícula
        Vehiculo resultadoNoExistente = coche1.encontrarVehiculoEnLista("GHI789", listaVehiculos);
        Assert.assertNull(resultadoNoExistente);
    }
}
