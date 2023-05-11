package repository;

import java.util.List;

import modelo.ClienteVehiculo;

public interface IRepositorioClienteVehiculo {
	public boolean insertarClienteVehiculo(ClienteVehiculo clienteVehiculo);
	public List <ClienteVehiculo> getListaDeClienteVehiculos();
}
