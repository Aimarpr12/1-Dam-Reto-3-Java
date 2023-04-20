package Pantallas;

import javax.swing.JPanel;

import Clases.Empleado;
import Clases.Mecanico;
import Controller.Controller;

public class PantallaInicioMecanico extends JPanel {

	/**
	 * Create the panel.
	 */
	private Controller controller = new Controller();
	
	public PantallaInicioMecanico(Empleado user) {
		convertirUserEnMecanico(user);
	}

	private void convertirUserEnMecanico(Empleado user) {
		String rango = controller.conseguirDatosDeMecanico(user.getId());
		Mecanico mecanico = new Mecanico (user, rango);
		
	}

}
