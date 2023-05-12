package modelo;

import java.util.List;


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
	public Vehiculo encontrarVehiculoEnLista(String matricula, List <Vehiculo> listDeVehiculos) {
		for(Vehiculo vehiculoActual : listDeVehiculos) {
			if(matricula.equals(vehiculoActual.getMatricula())){
				return vehiculoActual;
			}
		}
		return null;
	}

	
	
	

}
