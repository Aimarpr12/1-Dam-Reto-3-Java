package modelo;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import controller.Controller;

public class Venta {
	protected int idVenta;
	protected int precio;
	protected Date fecha;
	protected String bastidor;
	protected String idCliente;
	private int idVendedor;
	
	
	public Venta(int idVenta, int precio, Date fecha, String bastidor, String idCliente, int idVendedor) {
		super();
		this.idVenta = idVenta;
		this.precio = precio;
		this.fecha = fecha;
		this.bastidor = bastidor;
		this.idCliente = idCliente;
		this.idVendedor = idVendedor;
	}
	public Venta(int precio, Date fecha, String bastidor, String idCliente, int idVendedor) {
		super();
		this.precio = precio;
		this.fecha = fecha;
		this.bastidor = bastidor;
		this.idCliente = idCliente;
		this.idVendedor = idVendedor;
	}
	
	@Override
	public String toString() {
		return "Venta [idVenta=" + idVenta + ", precio=" + precio + ", fecha=" + fecha + ", bastidor=" + bastidor
				+ ", idCliente=" + idCliente + ", idVendedor=" + idVendedor + "]";
	}
	public Venta() {
		// TODO Auto-generated constructor stub
	}
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getBastidor() {
		return bastidor;
	}
	public void setBastidor(String bastidor) {
		this.bastidor = bastidor;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}
	
	public String conseguiDniDelCliente(Controller controller) {
		List <ClienteVehiculo> listCliente = controller.getAllClienteVehiculos();
		for(ClienteVehiculo cliente : listCliente){
			if(cliente.getBastidor().equals(getBastidor())){
				return cliente.getIdCliente();
			}
		}
		return null;
	}

	
	public String conseguirElDniDelVendedor(Controller controller) {
		List <Empleado> listEmpleados =controller.getAllEmpleado();
		for(Empleado empleado: listEmpleados){
			if(empleado.getId() == getIdVendedor()) {
				return empleado.getDni();
			}
		}
		return null;
	}

	public String conseguirMatriculaDelCoche(Controller controller) {
		List <Vehiculo> listVehiculos =controller.getAllVehiculos();
		for(Vehiculo vehiculo: listVehiculos){
			if(vehiculo.getBastidor().equals(getBastidor())) {
				return vehiculo.getMatricula();
			}
		}
		return null;
	}
	
	public boolean anadirVenta(Venta venta, Controller controller) {
		venta = controller.anadirVenta(venta);	
		if(0 != venta.getIdVenta()) {
			controller.addVenta(venta);
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		Venta venta = (Venta) obj;
		if(this.idVenta == venta.getIdVenta() && this.precio == venta.getPrecio() &&
				this.fecha.equals(venta.getFecha()) && this.idVendedor == venta.getIdVendedor()
				&& this.bastidor.equals(venta.getBastidor()) && this.idCliente.equals(venta.getIdCliente())) {
			return true;
		}
		return false;
	}
	
}
