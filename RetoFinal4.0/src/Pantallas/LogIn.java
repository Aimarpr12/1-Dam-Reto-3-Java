package Pantallas;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Clases.Empleado;
import Controller.Controller;



public class LogIn extends JPanel implements DocumentListener, ActionListener   {
	/**
	 * 
	 */
	private Controller controller = new Controller();
	private static final long serialVersionUID = 1L;
	private JTextField txtDni;
	private JPasswordField passwordField;
	private JButton buttonLogIn;
	private JButton buttonCreateAccount;
	public LogIn() {
		setLayout(null);
		
		labelDNI();
		
		JLabel labelContrasenaIncorrecta = labelContrasenaODniIncorrecto();
		
		labelContrasena();
		
		jTextField();
		
		passwordField();
		
		buttonLogIn(labelContrasenaIncorrecta, txtDni.getText(), String.valueOf(passwordField.getPassword()));
	
		buttonCreateAccount();		
	}
	
	private void buttonCreateAccount() {
		buttonCreateAccount = new JButton("Sign up");
		buttonCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
		        App app = (App) SwingUtilities.getRoot(component);
		        app.mostrarSignUp();
			}
		});
		buttonCreateAccount.setBounds(351, 11, 89, 23);
		add(buttonCreateAccount);
		
	}
	
	private void passwordField() {
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(48, 144, 109, 20);
		add(passwordField);
		passwordField.getDocument().addDocumentListener(this);
		
	}
	
	private void jTextField() {
		txtDni = new JTextField();
		txtDni.setBounds(48, 86, 109, 20);
		add(txtDni);
		txtDni.setColumns(10);
		txtDni.getDocument().addDocumentListener(this);
		
	}
	
	private void buttonLogIn(JLabel labelContrasenaIncorrecta, String text, String valueOf) {
		buttonLogIn = new JButton("LogIn");
		buttonLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empleado user = controller.getLogInCorrect(txtDni.getText(),String.valueOf(passwordField.getPassword()));
				if (user != null) {
					Component component = (Component) e.getSource();
					App app = (App) SwingUtilities.getRoot(component);
					//comprobar si es jefe total
					if (controller.comporbarSiEsJefe(user.getId())) {
	                    app.esJefe(user);
					}else if (controller.comprobarSiEsMecanico(user.getId())){
						app.esMecanico(user);
					}else {
						app.esVendedor(user);
					}
				}else {
					add(labelContrasenaIncorrecta);
					txtDni.setText("");
					passwordField.setText("");
				}
			}
		});
		buttonLogIn.setBounds(162, 215, 89, 23);
		add(buttonLogIn);
		buttonLogIn.setEnabled(false);
		buttonLogIn.addActionListener(this);
		
	}
	
	private void labelContrasena() {
		JLabel labelContraseña = new JLabel("Contraseña:");
		labelContraseña.setBounds(48, 119, 86, 14);
		add(labelContraseña);
		
	}
	
	private JLabel labelContrasenaODniIncorrecto() {
		JLabel labelContrasenaIncorrecta = new JLabel("DNI o Contraseña Incorrectas");
		labelContrasenaIncorrecta.setForeground(new Color(255, 0, 0));
		labelContrasenaIncorrecta.setBounds(225, 119, 200, 45);
		return labelContrasenaIncorrecta;
	}
	
	private void labelDNI() {
		JLabel labelUsuario = new JLabel("DNI:");
		labelUsuario.setBounds(48, 61, 86, 14);
		add(labelUsuario);
		
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
