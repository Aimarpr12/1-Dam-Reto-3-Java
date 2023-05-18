package vista;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.Controller;
import modelo.Cliente;
import modelo.ClienteVehiculo;
import modelo.ComboBoxMatriculaAnadirReparacion;
import modelo.Empleado;
import modelo.Mecanico;
import modelo.Vehiculo;
import modelo.Vendedor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class AsociarClienteVehiculo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private Controller controller;
	private JComboBox comboBoxDni;
	private JComboBox<ComboBoxMatriculaAnadirReparacion> comboBoxVehiculo;
	public AsociarClienteVehiculo(Empleado user, Controller controller) {
		setBackground(new Color(255, 252, 244));
		this.controller = controller;
		setLayout(null);
		
		dniCliente(user);
		
		matriculaVehiculo(user);
		
		btnConfirmar(user);	
		
		buttonLogOut();
		
		buttonAtras(user);
	}
	/**
	 * Inserta en la bbdd la relacion entre un vehiculo y un cliente
	 * @param user
	 */
	private void btnConfirmar(Empleado user) {
		JButton btnNewButton = new JButton("Asociar Cliente A Vehiculo");
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboBoxMatriculaAnadirReparacion comboSeleccionado = (ComboBoxMatriculaAnadirReparacion) comboBoxVehiculo.getSelectedItem();
				comboSeleccionado.getValue();
				ClienteVehiculo clienteVehiculo = new ClienteVehiculo(comboSeleccionado.getValue(), comboBoxDni.getSelectedItem().toString());
				boolean seHaAnadido = controller.anadirClienteVehiculoBD(clienteVehiculo);
				if(seHaAnadido) {
					controller.addClienteVehiculo(clienteVehiculo);
				}else {
					seHaAnadido = false;			
				}
				if(seHaAnadido) {
					JOptionPane.showMessageDialog(
							null,
							"Se ha añadido correctamente",
							"Añadido correcto",
							JOptionPane.ERROR_MESSAGE);	
					Component component = (Component) e.getSource();
					App app = (App) SwingUtilities.getRoot(component);
					try {
					    Mecanico mecanico = (Mecanico) user;
					    app.esMecanico(mecanico);
					} catch (Exception e1) {
					    try {
					        Vendedor vendedor = (Vendedor) user;
					        app.esVendedor(vendedor);								
					    } catch (Exception e2) {
					        System.out.println(e2.getMessage());
					    }
					}
					
				}else {
					JOptionPane.showMessageDialog(
						    null,
						    "No se ha añadido.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(287, 403, 242, 23);
		add(btnNewButton);
		
	}
	private void matriculaVehiculo(Empleado user) {
		labelMatricula();
		comboBoxMatricula();
		
	}
	
	private void dniCliente(Empleado user) {
		labelDni();
		comboBoxDni();
		
	}
	/**
	 * Devuelve al empleado a la pantalla de inicio correspondiente segun su tipo
	 */
	private void buttonLogOut() {
		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.setBackground(SystemColor.textHighlight);
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
                App app = (App) SwingUtilities.getRoot(component);
                app.logIn();
			}
		});
		btnLogOut.setBounds(678, 49, 89, 23);
		add(btnLogOut);		
	}

	private void buttonAtras(Empleado user) {
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBackground(SystemColor.textHighlight);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				try {
				    Mecanico mecanico = (Mecanico) user;
				    app.esMecanico(mecanico);
				} catch (Exception e1) {
				    try {
				        Vendedor vendedor = (Vendedor) user;
				        app.esVendedor(vendedor);								
				    } catch (Exception e2) {
				        System.out.println(e2.getMessage());
				    }
				}
			}
		});
		btnAtras.setBounds(65, 49, 89, 23);
		add(btnAtras);
		
	}
	
	private void comboBoxDni() {
		comboBoxDni = new JComboBox();
		comboBoxDni.setBackground(Color.WHITE);
		comboBoxDni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxDni.setBounds(159, 258, 129, 22);
		add(comboBoxDni);	
		List<Cliente> listDeClientes = controller.getAllClientes();
		for(Cliente cliente : listDeClientes) {
			comboBoxDni.addItem(cliente.getDni());
		}
	}
	private void labelDni() {
		JLabel lblDniCliente = new JLabel("Dni Cliente:");
		lblDniCliente.setForeground(new Color(255, 128, 0));
		lblDniCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDniCliente.setBounds(159, 207, 541, 14);
		add(lblDniCliente);		
	}
	private void comboBoxMatricula() {
		comboBoxVehiculo = new JComboBox<ComboBoxMatriculaAnadirReparacion>();
		comboBoxVehiculo.setBackground(Color.WHITE);
		comboBoxVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxVehiculo.setBounds(511, 258, 159, 22);
		add(comboBoxVehiculo);
		List<Vehiculo> listDeVehiculos = controller.getAllVehiculos();
		for(Vehiculo vehiculo : listDeVehiculos) {
			if(controller.averiguarSiElVehiculoNoTieneDueno(vehiculo.getBastidor())) {
				comboBoxVehiculo.addItem(new ComboBoxMatriculaAnadirReparacion(vehiculo.getBastidor(), vehiculo.getMatricula() ));
			}
		}
		
	}
	private void labelMatricula() {
		JLabel lblMatriculaCoche = new JLabel("Matricula Vehiculo:");
		lblMatriculaCoche.setForeground(new Color(255, 128, 0));
		lblMatriculaCoche.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMatriculaCoche.setBounds(511, 207, 159, 14);
		add(lblMatriculaCoche);
		
	}
}
