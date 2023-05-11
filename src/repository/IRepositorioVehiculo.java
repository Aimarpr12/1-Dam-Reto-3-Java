package repository;

import java.util.List;

import modelo.Vehiculo;

public interface IRepositorioVehiculo {
	public List <Vehiculo> getListaVehiculos();
	public boolean insertarVehiculo(Vehiculo vehiculo);
}
