package clases;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class CocheTest {

    @Test
    public void testEquals() {
        Coche coche1 = new Coche("B1", "1234ABC", "Renault", "Clio", 2019, TipoDeVehiculo.coche, Motor.Gasolina);
        Coche coche2 = new Coche("B1", "1234ABC", "Renault", "Clio", 2019, TipoDeVehiculo.coche, Motor.Gasolina);
        assertTrue(coche1.equals(coche2));
    }
    
    @Test
    public void testGetTipoDeMotor() {
        Coche coche = new Coche("B4", "9999XYZ", "Toyota", "Corolla", 2023, TipoDeVehiculo.coche, Motor.Electrico);
        assertEquals(Motor.Electrico, coche.getMotor());
    }
    
    @Test
    public void testSetTipoDeMotor() {
        Coche coche = new Coche("B5", "5555CCC", "Volkswagen", "Golf", 2024, TipoDeVehiculo.coche, Motor.Gasolina);
        coche.setMotor(Motor.Diesel);
        assertEquals(Motor.Diesel, coche.getMotor());
    }
}
