package repository;

import java.util.List;

import modelo.Cliente;

public interface IRepositorioCliente {
	public boolean insertarCliente(Cliente cliente);
	public List <Cliente> getListaDeClientes();
}
