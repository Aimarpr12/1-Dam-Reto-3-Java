package repository;

import java.util.Date;
import java.util.List;

import modelo.Reparacion;

public interface IRepositorioReparacion {
	public boolean updateReparacion(int id, Date fechaFin);
	public boolean updateReparacion(Reparacion reparacion);
	public List <Reparacion> getListaDeReparaciones() ;
	public Reparacion createReparacion(Reparacion reparacion);
	boolean updateReparacionIdMecanico(Reparacion reparacion);
}
