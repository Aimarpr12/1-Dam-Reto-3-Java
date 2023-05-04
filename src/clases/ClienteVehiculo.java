package clases;

import controller.Controller;

public class ClienteVehiculo {
	protected String bastidor;
	protected String idCliente;
	
	public ClienteVehiculo(String bastidor, String idCliente) {
		super();
		this.bastidor = bastidor;
		this.idCliente = idCliente;
	}

	public String getBastidor() {
		return bastidor;
	}

	public void setBastidor(String bastidor) {
		this.bastidor = bastidor;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public boolean addClienteVehiculo(ClienteVehiculo clienteVehiculo, Controller controller) {
		boolean seHaAnadido = controller.anadirClienteVehiculoBD(clienteVehiculo);
		if(seHaAnadido) {
			controller.addClienteVehiculo(clienteVehiculo);
			return true;
		}else {
			return false;			
		}
	}
	
	
}
