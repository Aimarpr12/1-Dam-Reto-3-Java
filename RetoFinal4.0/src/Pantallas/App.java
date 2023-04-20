package Pantallas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Empleado;

public class App extends JFrame {

	private JPanel contentPane;

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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
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
		LogIn panelInicial = new LogIn();
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
		Jefe panelInicial = new Jefe(user);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);	
		
	}

	public void esMecanico(Empleado user) {
		contentPane.removeAll();
		PantallaInicioMecanico panelInicial = new PantallaInicioMecanico(user);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);	
		
	}

	public void esVendedor(Empleado user) {
		contentPane.removeAll();
		PantallaInicioVendedor panelInicial = new PantallaInicioVendedor(user);
		contentPane.add(panelInicial);
		panelInicial.setLayout(null);
		validate();
		setVisible(true);
	}

}
