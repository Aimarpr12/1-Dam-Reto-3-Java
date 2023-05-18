package repository;

import java.util.List;

import modelo.Venta;

public interface IRepositorioVenta {
	public List <Venta> getListaDeVentas();
	public Venta createVenta(Venta venta);
	public boolean updateVenta(Venta venta);
	boolean updateVentaIdVendedor(Venta venta);
}
