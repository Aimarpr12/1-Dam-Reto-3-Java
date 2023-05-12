package modelo;

import java.util.List;


public class Coche extends Vehiculo {

	protected Motor motor;

	public Coche(String bastidor, String matricula, String marca, String modelo, int año,
			TipoDeVehiculo tipoDeVehiculo, Motor motor) {
		super(bastidor, matricula, marca, modelo, año, tipoDeVehiculo);
		this.motor = motor;
	}
	
	public Coche(String bastidor, String matricula, String marca, String modelo, int año,
			TipoDeVehiculo tipoDeVehiculo) {
		super(bastidor, matricula, marca, modelo, año, tipoDeVehiculo);
	}
	
	public Coche() {
		
	}
	

	public Motor getMotor() {
		return motor;
	}



	public void setMotor(Motor motor) {
		this.motor = motor;
	}

	@Override
	public Vehiculo encontrarVehiculoEnLista(String matricula, List <Vehiculo> listDeVehiculos) {
		for(Vehiculo vehiculoActual : listDeVehiculos) {
			if(matricula.equals(vehiculoActual.getMatricula())){
				return vehiculoActual;
			}
		}
		return null;
	}

	
	
	
}
