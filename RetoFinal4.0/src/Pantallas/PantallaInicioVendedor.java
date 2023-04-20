package Pantallas;

import javax.swing.JPanel;

import Clases.Empleado;
import Clases.Vendedor;
import Controller.Controller;

public class PantallaInicioVendedor extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private Controller controller = new Controller();
	public PantallaInicioVendedor(Empleado user) {
		
		convertirUserEnVendedor(user);
	}
	private void convertirUserEnVendedor(Empleado user) {
		double comisionVenta = controller.conseguirDatosDeVendedor(user.getId());
		Vendedor vendedor = new Vendedor(user, comisionVenta);
	}

}
