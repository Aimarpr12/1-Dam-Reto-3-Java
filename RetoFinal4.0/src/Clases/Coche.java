package Clases;

public class Coche extends Vehiculo {

	protected Motor motor;
	
	public Coche(String bastidor, String matricula, String marca, String modelo, int año, Motor motor) {
		super(bastidor, matricula, marca, modelo, año);
		this.motor = motor;
	}

	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		return motor == other.motor;
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
