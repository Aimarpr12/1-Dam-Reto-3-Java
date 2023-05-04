package clases;

import java.util.List;

import controller.Controller;

public class Moto extends Vehiculo{
	
	protected int cilindrada;

	public Moto(String bastidor, String matricula, String marca, String modelo, int año,
			TipoDeVehiculo tipoDeVehiculo, int cilindrada) {
		super(bastidor, matricula, marca, modelo, año, tipoDeVehiculo);
		// TODO Auto-generated constructor stub
		this.cilindrada = cilindrada;
	}
	
	public Moto(Vehiculo vehiculo, int cilindrada) {
		super(vehiculo.getBastidor(), vehiculo.getMatricula(), vehiculo.getMarca(), vehiculo.getModelo(), vehiculo.getAño(), vehiculo.getTipoDeVehiculo());
		// TODO Auto-generated constructor stub
		this.cilindrada = cilindrada;
	}
	
	public Moto(String bastidor, String matricula, String marca, String modelo, int año,
			TipoDeVehiculo tipoDeVehiculo) {
		super(bastidor, matricula, marca, modelo, año, tipoDeVehiculo);
	}
	
	public Moto() {
		
	}

	public int getCilindrada() {
		return cilindrada;
	}



	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}



	@Override
	public boolean anadirVehiculo(Vehiculo vehiculo, Controller controller) {
		Moto moto = (Moto) vehiculo;
		boolean seHaAnadidoEnBD = controller.anadirMoto(moto);
		if(seHaAnadidoEnBD) {
			controller.addMoto(moto);
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
