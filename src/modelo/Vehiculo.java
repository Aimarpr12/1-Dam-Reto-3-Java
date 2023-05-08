package modelo;


public abstract class Vehiculo implements VehiculoInterfaz{
	protected String bastidor;
	protected String matricula;
	protected String marca;
	protected String modelo;
	protected int año;
	protected TipoDeVehiculo tipoDeVehiculo;
	

	
	public Vehiculo() {
		
	}
	
	public Vehiculo(String bastidor, String matricula, String marca, String modelo, int año) {
		super();
		this.bastidor = bastidor;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.año = año;
	}
	
	public Vehiculo(String bastidor, String matricula, String marca, String modelo, int año,
			TipoDeVehiculo tipoDeVehiculo) {
		super();
		this.bastidor = bastidor;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.año = año;
		this.tipoDeVehiculo = tipoDeVehiculo;
	}


	public TipoDeVehiculo getTipoDeVehiculo() {
		return tipoDeVehiculo;
	}


	public void setTipoDeVehiculo(TipoDeVehiculo tipoDeVehiculo) {
		this.tipoDeVehiculo = tipoDeVehiculo;
	}


	public String getBastidor() {
		return bastidor;
	}


	public void setBastidor(String bastidor) {
		this.bastidor = bastidor;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public int getAño() {
		return año;
	}


	public void setAño(int año) {
		this.año = año;
	}

	
	
	
}
