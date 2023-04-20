package Clases;

import java.util.Objects;

public abstract class Vehiculo implements VehiculoInterfaz{
	protected String bastidor;
	protected String matricula;
	protected String marca;
	protected String modelo;
	protected int año;
	
	
	public Vehiculo(String bastidor, String matricula, String marca, String modelo, int año) {
		super();
		this.bastidor = bastidor;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.año = año;
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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		return año == other.año && Objects.equals(bastidor, other.bastidor) && Objects.equals(marca, other.marca)
				&& Objects.equals(matricula, other.matricula) && Objects.equals(modelo, other.modelo);
	}
	
	
}
