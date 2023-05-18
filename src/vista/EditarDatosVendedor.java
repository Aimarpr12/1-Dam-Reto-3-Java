package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.Controller;
import modelo.Vendedor;

public class EditarDatosVendedor extends JPanel  implements DocumentListener, ActionListener{

	/**
	 * Create the panel.
	 */
	private Controller controller;;
	private static final long serialVersionUID = 1L;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textTelefono;
	private JTextField textDireccion;
	private JTextField textCorreo;
	private JButton buttonLogIn;

	public EditarDatosVendedor(Vendedor user, Controller controller2) {
		setBackground(new Color(255, 252, 244));
		this.controller = controller2;
		labelNombre();
		jTextNombre(user);
		labelApellido();
		jTextApellido(user);
		labelTelefono();
		jTextTelefono(user);
		labelDireccion();
		jTextDireccion(user);
		labelCorreo();
		jTextCorreo(user);
		buttonActualizarDatos(user);
		buttonChangePass(user);
		buttonAtras(user);
		buttonLogOut();
	}

	private void buttonLogOut() {
		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setBackground(SystemColor.textHighlight);
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.logIn();
			}
		});
		btnLogOut.setBounds(724, 65, 89, 23);
		add(btnLogOut);		
	}
	/**
	 * Devuelve a la pantalla correspondiente segun el tipo de user
	 */
	private void buttonAtras(Vendedor user) {
		JButton btnAtras = new JButton("Atrás");
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(SystemColor.textHighlight);
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.esVendedor(user);
			}
		});
		btnAtras.setBounds(52, 65, 89, 23);
		add(btnAtras);
	}
	/**
	 * Pide pass antigua
	 * Pide pass nueva 2 veces, y deben coincidir
	 */
	private void buttonChangePass(Vendedor user) {
		JButton btnCambiarContrasena = new JButton("Cambiar contraseña");
		btnCambiarContrasena.setForeground(Color.WHITE);
		btnCambiarContrasena.setBackground(SystemColor.textHighlight);
		btnCambiarContrasena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCambiarContrasena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPasswordField passwordField = new JPasswordField();
				//se pide la contraseña vieja para comporbar que el user se sabe la contraseña vieja
				int option = JOptionPane.showConfirmDialog(null, passwordField, "Introduzca la contraseña vieja", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (option == JOptionPane.OK_OPTION) {
					char[] password = passwordField.getPassword();
					String passwordString = new String(password);    
					if(controller.verificarQueLaContrasenaEsCorrecta(user.getDni(), passwordString)) {
						// si la contraseña vieka coincide se pide la contraseña nueva 
						passwordField.setText("");
						int contraNueva = JOptionPane.showConfirmDialog(null, passwordField, "Introduzca la contraseña nueva", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						if (contraNueva == JOptionPane.OK_OPTION) {
							char[] newPassword = passwordField.getPassword();
							String newPasswordString = new String(newPassword);
							// se comprueba que la contraseña no sea la misma que la que estaba antes
							if(!controller.verificarQueLaContrasenaEsCorrecta(user.getDni(), newPasswordString)) {
								// se vuelve a pedir la contraseña nueva
								passwordField.setText("");
								int contraNuevaRepetir = JOptionPane.showConfirmDialog(null, passwordField, "Vuelva a introducir la nueva contraseña", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
								if (contraNuevaRepetir == JOptionPane.OK_OPTION) {
									char[] newPasswordRepeat = passwordField.getPassword();
									String newPasswordRepeatString = new String(newPasswordRepeat);
									//se comparan las 2 nuevas contraseñas y si son iguales se actualiza la contraseña en la base de datos
									if(newPasswordString.equals(newPasswordRepeatString)) {
										controller.changePassword(user.getDni(), newPasswordString);
									} else {
										UIManager.put("OptionPane.messageForeground", Color.RED);
										JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
									}
								}
							}else {
								UIManager.put("OptionPane.messageForeground", Color.RED);
								JOptionPane.showMessageDialog(null, "Contraseña introducida es la misma que la anterior", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}else {
						UIManager.put("OptionPane.messageForeground", Color.RED);
						JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnCambiarContrasena.setBounds(571, 459, 175, 23);
		add(btnCambiarContrasena);				
	}

	private void jTextCorreo(Vendedor user) {
		textCorreo = new JTextField();
		textCorreo.setForeground(new Color(255, 128, 0));
		textCorreo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textCorreo.setColumns(10);
		textCorreo.setBounds(469, 370, 236, 20);
		textCorreo.setText(user.getEmail());
		add(textCorreo);
		textCorreo.getDocument().addDocumentListener(this);

	}

	private void labelCorreo() {
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setForeground(new Color(255, 128, 0));
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCorreo.setBounds(261, 373, 141, 14);
		add(lblCorreo);

	}

	private void jTextDireccion(Vendedor user) {
		textDireccion = new JTextField();
		textDireccion.setForeground(new Color(255, 128, 0));
		textDireccion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textDireccion.setColumns(10);
		textDireccion.setBounds(469, 320, 236, 20);
		textDireccion.setText(user.getDireccion());
		add(textDireccion);
		textDireccion.getDocument().addDocumentListener(this);

	}

	private void labelDireccion() {
		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setForeground(new Color(255, 128, 0));
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDireccion.setBounds(261, 323, 141, 14);
		add(lblDireccion);

	}

	private void jTextTelefono(Vendedor user) {
		textTelefono = new JTextField();
		textTelefono.setForeground(new Color(255, 128, 0));
		textTelefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textTelefono.setColumns(10);
		textTelefono.setBounds(469, 267, 236, 20);
		textTelefono.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		textTelefono.setText(user.getNumeroTelefono() + "");
		textTelefono.getDocument().addDocumentListener(this);
		add(textTelefono);

	}

	private void labelTelefono() {
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setForeground(new Color(255, 128, 0));
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelefono.setBounds(261, 270, 141, 14);
		add(lblTelefono);

	}

	private void jTextApellido(Vendedor user) {
		textApellido = new JTextField();
		textApellido.setForeground(new Color(255, 128, 0));
		textApellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textApellido.setColumns(10);
		textApellido.setBounds(469, 211, 236, 20);
		textApellido.setText(user.getApellido());
		textApellido.getDocument().addDocumentListener(this);
		add(textApellido);

	}

	private void labelApellido() {
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setForeground(new Color(255, 128, 0));
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellido.setBounds(261, 214, 141, 14);
		add(lblApellido);

	}

	private void jTextNombre(Vendedor user) {
		textNombre = new JTextField();
		textNombre.setForeground(new Color(255, 128, 0));
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNombre.setColumns(10);
		textNombre.setBounds(469, 155, 236, 20);
		textNombre.setText(user.getNombre());
		textNombre.getDocument().addDocumentListener(this);
		add(textNombre);

	}

	private void labelNombre() {
		setLayout(null);
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(255, 128, 0));
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBounds(261, 158, 141, 14);
		add(lblNombre);
	}

	private void buttonActualizarDatos(Vendedor user) {
		buttonLogIn = new JButton("Actualizar Datos");
		buttonLogIn.setForeground(Color.WHITE);
		buttonLogIn.setBackground(SystemColor.textHighlight);
		buttonLogIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vendedor updateDelUser = new Vendedor(user.getDni(), textNombre.getText(), textApellido.getText(), Integer.parseInt(textTelefono.getText()), user.getFechaNacimiento(), textDireccion.getText(), textCorreo.getText());
				controller.actualizarUser(updateDelUser, user.getId());
				JOptionPane.showMessageDialog(null, "Se han actualizado los datos correctamente", "User Actualizado", JOptionPane.INFORMATION_MESSAGE);		
				Component component = (Component) e.getSource();
                App app = (App) SwingUtilities.getRoot(component);
                app.esVendedor(user);
			}
		});
		buttonLogIn.setBounds(163, 459, 175, 23);
		add(buttonLogIn);
		buttonLogIn.setEnabled(false);
		buttonLogIn.addActionListener(this);		
	}

	public void insertUpdate(DocumentEvent e) {
		if (textNombre.getText().length() > 0 &&  textApellido.getText().length() > 0 && textTelefono.getText().length() > 0 && textCorreo.getText().length() > 0 && textDireccion.getText().length() > 0) {
			buttonLogIn.setEnabled(true);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if (textNombre.getText().length() == 0 || textApellido.getText().length() == 0 || textTelefono.getText().length() == 0 || textDireccion.getText().length() == 0 || textCorreo.getText().length() == 0) {
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
