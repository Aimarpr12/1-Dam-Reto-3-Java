package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.Controller;
import modelo.Cliente;
import modelo.ClienteVehiculo;
import modelo.ComboBoxMatriculaAnadirReparacion;
import modelo.IntegerOnlyDocument;
import modelo.Vehiculo;
import modelo.Vendedor;
import modelo.Venta;

public class AnadirVenta extends JPanel implements DocumentListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private Controller controller;
	private JTextField textFieldPrecio;
	private JComboBox comboBoxDni;
	private JComboBox<ComboBoxMatriculaAnadirReparacion> comboBoxVehiculo;
	private JButton btnAnadirVenta;
	public AnadirVenta(Vendedor user, Controller controller) {
		setBackground(new Color(255, 252, 244));
		this.controller = controller;
		setLayout(null);
		
		buttonLogOut();

		buttonAtras(user);
		
		btnAnadirVenta(user);

		anadirMatricula(user);

		anadirPrecio();

		anadirCoste();	
		
		comboBoxDni();
	}
	
	private void buttonLogOut() {
		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.setBackground(SystemColor.textHighlight);
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
                App app = (App) SwingUtilities.getRoot(component);
                app.logIn();
			}
		});
		btnLogOut.setBounds(753, 83, 89, 23);
		add(btnLogOut);		
	}
	/**
	 * Boton que vuelve a la pantalla de inicio correspondiente dependiendo del tipo de empleado
	 */
	private void buttonAtras(Vendedor user) {
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBackground(SystemColor.textHighlight);
		btnAtras.setForeground(new Color(255, 255, 255));
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
                App app = (App) SwingUtilities.getRoot(component);
                app.esVendedor(user);
			}
		});
		btnAtras.setBounds(85, 83, 89, 23);
		add(btnAtras);
	}
	
	private void anadirCoste() {
		lableAnadirCoste();
		textFieldCoste();
	}

	private void anadirPrecio() {
		lblAnadirPrecio();
		textFieldPrecio();

	}

	

	private void anadirMatricula(Vendedor user) {
		lblMatricula();
		comboBoxMatricula();

	}

	private void textFieldCoste() {
	}
	private void lableAnadirCoste() {
		JLabel lablCoste = new JLabel("Cliente:");
		lablCoste.setForeground(new Color(255, 128, 0));
		lablCoste.setFont(new Font("Tahoma", Font.BOLD, 15));
		lablCoste.setBounds(179, 317, 86, 14);
		add(lablCoste);		
	}

	private void textFieldPrecio() {
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBackground(new Color(255, 255, 255));
		textFieldPrecio.setDocument(new IntegerOnlyDocument());
		textFieldPrecio.setBounds(402, 255, 138, 20);
		add(textFieldPrecio);
		textFieldPrecio.setColumns(10);    
		textFieldPrecio.getDocument().addDocumentListener(this);
	}

	private void lblAnadirPrecio() {
		JLabel lablPrecio = new JLabel("Precio:");
		lablPrecio.setForeground(new Color(255, 128, 0));
		lablPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lablPrecio.setBounds(179, 255, 86, 14);
		add(lablPrecio);		
	}


	private void comboBoxDni() {
		comboBoxDni = new JComboBox();
		comboBoxDni.setBackground(Color.WHITE);
		comboBoxDni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxDni.setBounds(402, 313, 138, 22);
		add(comboBoxDni);	
		List<Cliente> listDeClientes = controller.getAllClientes();
		for(Cliente cliente : listDeClientes) {
			comboBoxDni.addItem(cliente.getDni());
		}
	}

	private void comboBoxMatricula() {
		comboBoxVehiculo = new JComboBox<ComboBoxMatriculaAnadirReparacion>();
		comboBoxVehiculo.setBackground(Color.WHITE);
		comboBoxVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxVehiculo.setBounds(402, 191, 138, 22);
		add(comboBoxVehiculo);
		List<Vehiculo> listDeVehiculos = controller.getAllVehiculos();
		for(Vehiculo vehiculo : listDeVehiculos) {
			if(controller.averiguarSiElVehiculoNoTieneDueno(vehiculo.getBastidor())) {
				comboBoxVehiculo.addItem(new ComboBoxMatriculaAnadirReparacion(vehiculo.getBastidor(), vehiculo.getMatricula() ));
			}
		}
		
	}
	private void lblMatricula() {
		JLabel lblMatricula = new JLabel("Matricula Vehiculo:");
		lblMatricula.setForeground(new Color(255, 128, 0));
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMatricula.setBounds(179, 195, 162, 14);
		add(lblMatricula);
	}
	/**
	 * Annade la venta en bbdd y en la tabla clienteVehiculo
	 */
	private void btnAnadirVenta(Vendedor user) {
		btnAnadirVenta = new JButton("Añadir Venta");
		btnAnadirVenta.setBackground(SystemColor.textHighlight);
		btnAnadirVenta.setForeground(new Color(255, 255, 255));
		btnAnadirVenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnadirVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(textFieldPrecio.getText())> 100 && comboBoxVehiculo.getItemCount() > 0) {
					ComboBoxMatriculaAnadirReparacion comboSeleccionado = (ComboBoxMatriculaAnadirReparacion) comboBoxVehiculo.getSelectedItem();
					Date fechaActual = new Date();
					SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
					String fechaActualFormateada = formatoFecha.format(fechaActual);
					try {
						fechaActual = formatoFecha.parse(fechaActualFormateada);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					Venta venta = new Venta(Integer.parseInt(textFieldPrecio.getText()), fechaActual, comboSeleccionado.getValue(), comboBoxDni.getSelectedItem().toString(), user.getId());
					boolean seHaInsertado = controller.anadirVentaFuncion(venta);
					user.anadirVentaAListaDeVentas(venta);
					ClienteVehiculo clienteVehiculo = new ClienteVehiculo(comboSeleccionado.getValue(), comboBoxDni.getSelectedItem().toString());
					if(seHaInsertado) {
						JOptionPane.showMessageDialog(
								null,
								"Se ha añadido correctamente",
								"Añadido correcto",
								JOptionPane.ERROR_MESSAGE);	
						Component component = (Component) e.getSource();
						App app = (App) SwingUtilities.getRoot(component);
						app.esVendedor(user);
					}else {
						JOptionPane.showMessageDialog(
							    null,
							    "No se ha añadido.",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAnadirVenta.setBounds(379, 431, 161, 23);
		add(btnAnadirVenta);
		btnAnadirVenta.setEnabled(false);
		btnAnadirVenta.addActionListener(this);
	}

	public void insertUpdate(DocumentEvent e) {
		if (textFieldPrecio.getText().length() > 0) {
			btnAnadirVenta.setEnabled(true);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if (textFieldPrecio.getText().length() == 0) {
			btnAnadirVenta.setEnabled(false);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
