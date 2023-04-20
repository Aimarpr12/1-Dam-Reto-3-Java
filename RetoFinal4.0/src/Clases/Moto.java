package Clases;


public class Moto extends Vehiculo{
	
	protected int cilindrada;
	
	public Moto(String bastidor, String matricula, String marca, String modelo, int año, int cilindrada) {
		super(bastidor, matricula, marca, modelo, año);
		this.cilindrada = cilindrada;
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moto other = (Moto) obj;
		return cilindrada == other.cilindrada;
	}

	@Override
	public boolean anadirVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaReparado(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaVendido(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
