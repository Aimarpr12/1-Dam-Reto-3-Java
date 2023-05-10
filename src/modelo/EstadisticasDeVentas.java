package modelo;

public class EstadisticasDeVentas {
	private String nombreCompleto;
	private String comisionTotal;
	public EstadisticasDeVentas(String nombreCompleto, String comisionTotal) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.comisionTotal = comisionTotal;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getComisionTotal() {
		return comisionTotal;
	}
	public void setComisionTotal(String comisionTotal) {
		this.comisionTotal = comisionTotal;
	}
	
	
}
