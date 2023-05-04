package pantallas;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;

import clases.Empleado;
import clases.Mecanico;
import clases.Vendedor;
import controller.Controller;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;
	private Container contentPane;
	private Controller controller = new Controller();

	/**
	 * Launch the application.
	 */ 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = getContentPane();
		mostrarPanelAlEjecutarLaAplicacion();
	}

	private void mostrarPanelAlEjecutarLaAplicacion() {

		contentPane.removeAll();
		Bienvenido panelInicial = new Bienvenido();
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);	

	}

	public void logIn() {
		contentPane.removeAll();
		LogIn panelInicial = new LogIn(controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);	

	}

	public void mostrarSignUp() {
		contentPane.removeAll();
		CreateAcount panelInicial = new CreateAcount();
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);

	}

	public void esJefe(Empleado user) {
		contentPane.removeAll();
		PantallaInicioJefe panelInicial = new PantallaInicioJefe(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);	

	}

	public void esMecanico(Mecanico user) {
		contentPane.removeAll();
		PantallaInicioMecanico panelInicial = new PantallaInicioMecanico(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);	

	}

	public void esVendedor(Vendedor user) {
		contentPane.removeAll();
		PantallaInicioVendedor panelInicial = new PantallaInicioVendedor(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);
	}

	public void mostrarEditarDatosVendedor(Vendedor user) {
		contentPane.removeAll();
		EditarDatosVendedor panelInicial = new EditarDatosVendedor(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);

	}

	public void mostrarEditarDatosMecanico(Mecanico user) {
		contentPane.removeAll();
		EditarDatosMecanico panelInicial = new EditarDatosMecanico(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);

	}

	public void mostrarVerDatosMecanico(Mecanico user) {
		contentPane.removeAll();
		VerDatosMecanico panelInicial = new VerDatosMecanico(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);

	}

	public void mostrarVerDatosVendedor(Vendedor user) {
		contentPane.removeAll();
		VerDatosVendedor panelInicial = new VerDatosVendedor(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);

	}

	public void mostrarVerReparaciones(Empleado user) {
		contentPane.removeAll();
		VerReparaciones panelInicial = new VerReparaciones(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);
	}

	public void mostrarVerVentas(Empleado user) {
		contentPane.removeAll();
		VerVentas panelInicial = new VerVentas(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);
	}

	public void mostrarVerVehiculosDisponibles(Vendedor user) {
		contentPane.removeAll();
		VerVehiculosDisponibles panelInicial = new VerVehiculosDisponibles(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);
	}

	public void anadirReparacion(Mecanico user) {
		contentPane.removeAll();
		AnadirReparacion panelInicial = new AnadirReparacion(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);

	}

	public void anadirVenta(Vendedor user) {
		contentPane.removeAll();
		AnadirVenta panelInicial = new AnadirVenta(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);

	}

	public void anadirVehiculo(Empleado user) {
		contentPane.removeAll();
		AnadirVehiculo panelInicial = new AnadirVehiculo(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);

	}
	
	public void anadirCliente(Empleado user) {
		contentPane.removeAll();
		AnadirCliente panelInicial = new AnadirCliente(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);

	}
	
	public void asociarClienteVehiculo(Empleado user) {
		contentPane.removeAll();
		AsociarClienteVehiculo panelInicial = new AsociarClienteVehiculo(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);

	}
	
	public void mostrarVerificarUser(Empleado user) {
		contentPane.removeAll();
		VerificarUser panelInicial = new VerificarUser(user, controller);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);

	}
}
