package clases;

import java.util.List;

import controller.Controller;

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
	public boolean anadirVehiculo(Vehiculo vehiculo, Controller controller) {
		Coche coche = (Coche) vehiculo;
		boolean seHaAnadidoEnBD = controller.anadirCoche(coche);
		if(seHaAnadidoEnBD) {
			controller.addCoche(coche);
			return true;
		}else {
			return false;			
		}
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

	@Override
	public Vehiculo encontrarVehiculoEnLista(String matricula, Controller controller) {
		List <Vehiculo> listDeVehiculos = controller.getAllVehiculos();
		for(Vehiculo vehiculoActual : listDeVehiculos) {
			if(matricula.equals(vehiculoActual.getMatricula())){
				return vehiculoActual;
			}
		}
		return null;
	}

	
	
	
}
