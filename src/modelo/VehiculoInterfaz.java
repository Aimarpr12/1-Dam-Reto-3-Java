package modelo;

import java.util.List;


public interface VehiculoInterfaz {
	Vehiculo encontrarVehiculoEnLista(String matricula, List <Vehiculo> listDeVehiculos);
}
