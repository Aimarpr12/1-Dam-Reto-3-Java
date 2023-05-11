package repository;

import java.util.List;

import modelo.EstadisticasDeVentas;

public interface IRepositorioEstadisticasVentas {
	public List <EstadisticasDeVentas> obtenerTopDosVendedores();
	
}
