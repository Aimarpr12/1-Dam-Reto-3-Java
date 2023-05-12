package vista;

import javax.swing.JPanel;

import controller.Controller;
import modelo.ComboBoxMatriculaAnadirReparacion;
import modelo.IntegerOnlyDocument;
import modelo.Mecanico;
import modelo.Reparacion;
import modelo.Vehiculo;
import modelo.Vendedor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;

public class AnadirReparacion extends JPanel implements DocumentListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private Controller controller;
	private JTextField textFieldPrecio;
	private JTextField textFieldCoste;
	private JTextPane textPaneDescripcion;
	private JComboBox<ComboBoxMatriculaAnadirReparacion> comboBoxMatricula;
	private JButton btnAnadirReparcion;
	public AnadirReparacion(Mecanico user, Controller controller) {
		setBackground(new Color(255, 252, 244));
		this.controller = controller;
		setLayout(null);

		btnAnadirreparcion(user);
		
		buttonAnadirVehiculo(user);

		anadirMatricula(user);

		anadirdescripcion();

		anadirPrecio();

		anadirCoste();	
		
		buttonLogOut();
		
		buttonAtras(user);
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
		btnLogOut.setBounds(720, 83, 107, 23);
		add(btnLogOut);		
	}
	
	private void buttonAtras(Mecanico user) {
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBackground(SystemColor.textHighlight);
		btnAtras.setForeground(new Color(255, 255, 255));
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
                App app = (App) SwingUtilities.getRoot(component);
                app.esMecanico(user);
			}
		});
		btnAtras.setBounds(85, 83, 89, 23);
		add(btnAtras);
	}
	private void buttonAnadirVehiculo(Mecanico user) {
		JButton btnLogOut = new JButton("Añadir vehículo");
		btnLogOut.setBackground(SystemColor.textHighlight);
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
                App app = (App) SwingUtilities.getRoot(component);
                app.anadirVehiculo(user);
			}
		});
		btnLogOut.setBounds(567, 438, 152, 23);
		add(btnLogOut);		
	}
	private void anadirCoste() {
		lableAnadirCoste();
		textFieldCoste();
	}

	private void anadirPrecio() {
		lblAnadirPrecio();
		textFieldPrecio();

	}

	private void anadirdescripcion() {
		lblAnadirDescripcion();
		textPaneDescripcion();

	}

	private void anadirMatricula(Mecanico user) {
		lblMatricula();
		jComboBoxMatricula(user);

	}

	private void textFieldCoste() {
		textFieldCoste = new JTextField();
		textFieldCoste.setDocument(new IntegerOnlyDocument());
		textFieldCoste.setColumns(10);
		textFieldCoste.setBounds(388, 304, 116, 20);
		textFieldCoste.getDocument().addDocumentListener(this);
		add(textFieldCoste);		
	}
	private void lableAnadirCoste() {
		JLabel lablCoste = new JLabel("Coste:");
		lablCoste.setForeground(new Color(255, 128, 0));
		lablCoste.setFont(new Font("Tahoma", Font.BOLD, 15));
		lablCoste.setBounds(179, 308, 86, 14);
		add(lablCoste);		
	}

	private void textFieldPrecio() {
		textFieldPrecio = new JTextField();
		textFieldPrecio.setDocument(new IntegerOnlyDocument());
		textFieldPrecio.setBounds(388, 237, 115, 20);
		add(textFieldPrecio);
		textFieldPrecio.setColumns(10);    
		textFieldPrecio.getDocument().addDocumentListener(this);
	}

	private void lblAnadirPrecio() {
		JLabel lablPrecio = new JLabel("Precio:");
		lablPrecio.setForeground(new Color(255, 128, 0));
		lablPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lablPrecio.setBounds(180, 241, 85, 14);
		add(lablPrecio);		
	}


	private void textPaneDescripcion() {
		textPaneDescripcion = new JTextPane();
		textPaneDescripcion.setBounds(616, 243, 205, 81);
		textPaneDescripcion.getDocument().addDocumentListener(this);
		add(textPaneDescripcion);		
	}
	private void lblAnadirDescripcion() {
		JLabel lblNewLabel = new JLabel("Descripcion De La Reparacion:");
		lblNewLabel.setForeground(new Color(255, 128, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(598, 181, 287, 14);
		add(lblNewLabel);

	}

	private void jComboBoxMatricula(Mecanico user) {
		comboBoxMatricula = new JComboBox<ComboBoxMatriculaAnadirReparacion>();
		comboBoxMatricula.setBounds(388, 179, 115, 22);
		for(Vehiculo vehiculoActual : controller.getAllVehiculos()) {
			comboBoxMatricula.addItem(new ComboBoxMatriculaAnadirReparacion(vehiculoActual.getBastidor(), vehiculoActual.getMatricula() ));
		}
		add(comboBoxMatricula);		
	}
	private void lblMatricula() {
		JLabel lblMatricula = new JLabel("Matricula Vehiculo:");
		lblMatricula.setForeground(new Color(255, 128, 0));
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMatricula.setBounds(179, 179, 229, 14);
		add(lblMatricula);

	}
	private void btnAnadirreparcion(Mecanico user) {
		btnAnadirReparcion = new JButton("Añadir Reparacion");
		btnAnadirReparcion.setForeground(Color.WHITE);
		btnAnadirReparcion.setBackground(SystemColor.textHighlight);
		btnAnadirReparcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnadirReparcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboBoxMatriculaAnadirReparacion comboSeleccionado = (ComboBoxMatriculaAnadirReparacion) comboBoxMatricula.getSelectedItem();
				Date fechaActual = new Date();
				SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
				String fechaActualFormateada = formatoFecha.format(fechaActual);
				try {
					fechaActual = formatoFecha.parse(fechaActualFormateada);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				Reparacion reparacion = new Reparacion(comboSeleccionado.getValue(), textPaneDescripcion.getText(), Integer.parseInt(textFieldCoste.getText()), Integer.parseInt(textFieldPrecio.getText()), fechaActual,user.getId());
				boolean seHaInsetado = controller.anadirReparacionFuncion(reparacion);
				if(seHaInsetado) {
					JOptionPane.showMessageDialog(
							null,
							"Se ha añadido correctamente",
							"Añadido correcto",
							JOptionPane.ERROR_MESSAGE);	
					Component component = (Component) e.getSource();
					App app = (App) SwingUtilities.getRoot(component);
					app.esMecanico(user);
				}else {
					JOptionPane.showMessageDialog(
						    null,
						    "No se ha añadido.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAnadirReparcion.setBounds(179, 438, 180, 23);
		add(btnAnadirReparcion);
		btnAnadirReparcion.setEnabled(false);
		btnAnadirReparcion.addActionListener(this);
	}

	public void insertUpdate(DocumentEvent e) {
		if (textFieldPrecio.getText().length() > 0 && textFieldCoste.getText().length() > 0 && textPaneDescripcion.getText().length() > 0) {
			btnAnadirReparcion.setEnabled(true);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if (textFieldPrecio.getText().length() == 0 || textFieldCoste.getText().length() == 0 || textPaneDescripcion.getText().length() == 0) {
			btnAnadirReparcion.setEnabled(false);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
