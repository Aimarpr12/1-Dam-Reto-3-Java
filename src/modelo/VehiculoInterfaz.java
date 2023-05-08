package modelo;

import controller.Controller;

public interface VehiculoInterfaz {
	boolean anadirVehiculo(Vehiculo vehiculo, Controller controller);
	boolean estaReparado(Vehiculo vehiculo);
	boolean estaVendido(Vehiculo vehiculo);
	Vehiculo encontrarVehiculoEnLista(String matricula, Controller controller);
}
