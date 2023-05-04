package pantallas;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import clases.Empleado;
import clases.Mecanico;
import clases.Vendedor;
import controller.Controller;

import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;



public class LogIn extends JPanel implements DocumentListener, ActionListener   {
	/**
	 * 
	 */
	private Controller controller;
	private static final long serialVersionUID = 1L;
	private JTextField txtDni;
	private JPasswordField passwordField;
	private JButton buttonLogIn;
	private JButton buttonCreateAccount;
	public LogIn(Controller controller) {
		this.controller = controller;
		setBackground(new Color(255, 252, 244));
		setLayout(null);
		
		cargarEmpleadosNoVerificados();
		
		labelDNI();

		labelContrasena();

		jTextField();

		passwordField();

		logoEmpresa();

		imagenNeumatico();

		buttonLogIn(txtDni.getText(), String.valueOf(passwordField.getPassword()));

		buttonCreateAccount();		
	}

	private void cargarEmpleadosNoVerificados() {
		controller.empleadosAVerificar();
		
	}

	private void buttonCreateAccount() {
		buttonCreateAccount = new JButton("Sign up");
		buttonCreateAccount.setBackground(SystemColor.textHighlight);
		buttonCreateAccount.setForeground(new Color(255, 255, 255));
		buttonCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.mostrarSignUp();
			}
		});
		buttonCreateAccount.setBounds(284, 415, 89, 23);
		add(buttonCreateAccount);

		JLabel lblTitulo = new JLabel("Bienvenid@ a RYU");
		lblTitulo.setForeground(new Color(255, 128, 0));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitulo.setBounds(320, 112, 288, 37);
		add(lblTitulo);

	}

	private void passwordField() {
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(499, 286, 109, 20);
		add(passwordField);
		passwordField.getDocument().addDocumentListener(this);

	}

	private void jTextField() {
		txtDni = new JTextField();
		txtDni.setBounds(284, 286, 109, 20);
		add(txtDni);
		txtDni.setColumns(10);
		txtDni.getDocument().addDocumentListener(this);

	}

	private void buttonLogIn(String text, String valueOf) {
		buttonLogIn = new JButton("LogIn");
		buttonLogIn.setBackground(SystemColor.textHighlight);
		buttonLogIn.setForeground(new Color(255, 255, 255));
		buttonLogIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empleado user = controller.getLogInCorrect(txtDni.getText(),String.valueOf(passwordField.getPassword()));
				if(controller.comprobarSiEstanVerificados(txtDni.getText())) {
					if (user != null) {
						Component component = (Component) e.getSource();
						App app = (App) SwingUtilities.getRoot(component);
						//comprobar si es jefe total
						if (controller.comporbarSiEsJefe(user.getId())) {
							app.esJefe(user);
						}else if (controller.comprobarSiEsMecanico(user.getId())){
							String rango = controller.conseguirDatosDeMecanico(user.getId());
							Mecanico mecanico = new Mecanico (user, rango);
							app.esMecanico(mecanico);
						}else {
							double comisionVenta = controller.conseguirDatosDeVendedor(user.getId());
							Vendedor vendedor = new Vendedor(user, comisionVenta);
							app.esVendedor(vendedor); 
						}
					}else {
						JOptionPane.showMessageDialog(
								null,
								"Usuario o contraseña incorrectos.",
								"Error",
								JOptionPane.ERROR_MESSAGE);
						txtDni.setText("");
						passwordField.setText("");
					}		
				}else {
					JOptionPane.showMessageDialog(
							null,
							"No esta verificado.",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonLogIn.setBounds(519, 415, 89, 23);
		add(buttonLogIn);
		buttonLogIn.setEnabled(false);
		buttonLogIn.addActionListener(this);

	}

	private void labelContrasena() {
		JLabel labelContraseña = new JLabel("Contraseña:");
		labelContraseña.setForeground(new Color(255, 128, 0));
		labelContraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelContraseña.setBounds(499, 261, 86, 14);
		add(labelContraseña);
	}
		
	private void labelDNI() {
		JLabel labelUsuario = new JLabel("DNI:");
		labelUsuario.setForeground(new Color(255, 128, 0));
		labelUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelUsuario.setBounds(284, 261, 86, 14);
		add(labelUsuario);

	}
	private void logoEmpresa() {
		ImageIcon icon = new ImageIcon(Bienvenido.class.getResource("/PantallasImg/icono.png"));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(180, 157, Image.SCALE_SMOOTH);
		JLabel lblIcono = new JLabel(new ImageIcon(scaledImage));
		lblIcono.setBounds(29, 10, 246, 188);

		JPanel panel = new JPanel();
		panel.setLayout(null); 
		panel.setBounds(0, 0, 800, 600);

		add(lblIcono); 

		setVisible(true);
	}

	private void imagenNeumatico() {
		ImageIcon icon = new ImageIcon(Bienvenido.class.getResource("/PantallasImg/neumatico.png"));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(120, 107, Image.SCALE_SMOOTH);
		JLabel lblNeumatico = new JLabel(new ImageIcon(scaledImage));
		lblNeumatico.setBounds(620, 10, 240, 206);

		JPanel panel = new JPanel();
		panel.setLayout(null); 
		panel.setBounds(0, 0, 800, 600);

		add(lblNeumatico); 

		setVisible(true);
	}

	public void insertUpdate(DocumentEvent e) {
		if (txtDni.getText().length() > 0 && String.valueOf(passwordField.getPassword()).length() > 0) {
			buttonLogIn.setEnabled(true);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if (txtDni.getText().length() == 0 || String.valueOf(passwordField.getPassword()).length() == 0) {
			buttonLogIn.setEnabled(false);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
