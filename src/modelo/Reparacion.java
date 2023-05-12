package modelo;

import java.util.Date;
import java.util.List;

public class Reparacion {
	protected int idReparacion;
	protected String idVehiculo;
	protected String descripcion;
	protected double coste;
	protected double precio;
	protected Date fechaIni;
	protected Date fechaFin;
	protected int idMecanico;
	
	public Reparacion(int idReparacion, String idVehiculo, String descripcion, double coste, double precio, Date fechaIni,
			Date fechaFin, int idMecanico) {
		super();
		this.idReparacion = idReparacion;
		this.idVehiculo = idVehiculo;
		this.descripcion = descripcion;
		this.coste = coste;
		this.precio = precio;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.idMecanico = idMecanico;
	}

	@Override
	public boolean equals(Object obj) {
		Reparacion reparacion = (Reparacion) obj;
		if(this.idReparacion == reparacion.getIdReparacion() && this.idVehiculo.equals(reparacion.getIdVehiculo()) &&
				this.descripcion.equals(reparacion.getDescripcion()) && this.coste == reparacion.getCoste() &&
				this.precio == reparacion.getPrecio() && this.fechaFin.equals(reparacion.getFechaFin()) &&
				this.fechaIni.equals(reparacion.getFechaIni()) && this.idMecanico == reparacion.getIdMecanico()
				) {
			return true;
		}
		return false;
	}

	public Reparacion() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Venta [idReparacion=" + idReparacion + ", descripcion=" + descripcion + ", coste=" + coste + ", precio="
				+ precio + ", fechaIni=" + fechaIni + ", fechaFin=" + fechaFin
				+ ", idMecanico=" + idMecanico + "]";
	}

	public int getIdReparacion() {
		return idReparacion;
	}

	public void setIdReparacion(int idReparacion) {
		this.idReparacion = idReparacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	
	public String getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(String idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCoste() {
		return coste;
	}

	public void setCoste(double coste) {
		this.coste = coste;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getIdMecanico() {
		return idMecanico;
	}

	public void setIdMecanico(int idMecanico) {
		this.idMecanico = idMecanico;
	}
	
	public Reparacion(String idVehiculo, String descripcion, double coste, double precio, Date fechaIni,
			int idMecanico) {
		super();
		this.idVehiculo = idVehiculo;
		this.descripcion = descripcion;
		this.coste = coste;
		this.precio = precio;
		this.fechaIni = fechaIni;
		this.idMecanico = idMecanico;
	}

	public String conseguirElDniDelMecanico(List <Empleado> listEmpleados) {
		for(Empleado empleado: listEmpleados){
			if(empleado.getId() == getIdMecanico()) {
				return empleado.getDni();
			}
		}
		return null;
	}

	public String conseguirMatriculaDelCoche(List <Vehiculo> listVehiculos) {
		for(Vehiculo vehiculo: listVehiculos){
			if(vehiculo.getBastidor().equals(getIdVehiculo())) {
				return vehiculo.getMatricula();
			}
		}
		return null;
	}

	public String conseguiDniDelCliente(List <ClienteVehiculo> listCliente) {
		for(ClienteVehiculo cliente : listCliente){
			if(cliente.getBastidor().equals(getIdVehiculo())){
				return cliente.getIdCliente();
			}
		}
		return null;
	}
}
