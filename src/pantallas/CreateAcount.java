package pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JCalendar;

import clases.Empleado;
import controller.Controller;


public class CreateAcount extends JPanel implements DocumentListener, ActionListener   {
	/**
	 * 
	 */
	private Controller controller = new Controller();
	private static final long serialVersionUID = 1L;
	private JTextField txtDni;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldRepeat;
	private JButton buttonLogIn;
	private JButton buttonCreateAccount;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textTelefono;
	private JTextField textDireccion;
	private JTextField textCorreo;
	private Date fechaDeNacimiento; 
	private boolean segundaParte = false;
	
	public CreateAcount() {
		setLayout(null);
		
		labelDNI();
		
		JLabel labelContrasenaNoCoincide = labelContrasenaNoCoincide();
		
		labelContrasena();
		
		labelContrasenaRepeat();
		
		jTextField();
		
		passwordField();
		
		passwordFieldRepeat();
		
		buttonCreateAccount(labelContrasenaNoCoincide, txtDni.getText(), String.valueOf(passwordField.getPassword()));
		
	}	

	private void passwordFieldRepeat() {
		passwordFieldRepeat = new JPasswordField();
		passwordFieldRepeat.setToolTipText("");
		passwordFieldRepeat.setBounds(48, 202, 109, 20);
		add(passwordFieldRepeat);
		passwordFieldRepeat.getDocument().addDocumentListener(this);
		
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
	
	private void buttonCreateAccount(JLabel labelContrasenaNoCoincide, String text, String valueOf) {
		buttonLogIn = new JButton("Create Password");
		buttonLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(labelContrasenaNoCoincide);  
				if (String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordFieldRepeat.getPassword()))) {
					if(controller.comprobarUser(txtDni.getText())) {
						txtDni.setEnabled(false);
						passwordField.setEnabled(false);
						passwordFieldRepeat.setEnabled(false);
						buttonLogIn.setEnabled(false);
						anadirRestoDeCampos();						
					}else {
						JOptionPane.showMessageDialog(null, "El user introducido esta registrado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						Component component = (Component) e.getSource();
	                    App app = (App) SwingUtilities.getRoot(component);
	                    app.logIn();
					}
				}else {
					add(labelContrasenaNoCoincide);
					passwordFieldRepeat.setText("");
					passwordField.setText("");
				}
			}
		});
		buttonLogIn.setBounds(118, 250, 133, 23);
		add(buttonLogIn);
		buttonLogIn.setEnabled(false);
		buttonLogIn.addActionListener(this);
		
	}
	
	protected void anadirRestoDeCampos() {
		labelNombre();
		jTextNombre();
		labelApellido();
		jTextApellido();
		labelTelefono();
		jTextTelefono();
		labelFechaDeNacimiento();
		fechaDeNacimiento();
		labelDireccion();
		jTextDireccion();
		labelCorreo();
		jTextCorreo();
		JLabel labelAlgunCampoVacion = labelAlgunCampoVacion();
		segundaParte = true;
		System.out.println(segundaParte);
		buttonCreateAccont(labelAlgunCampoVacion);
	}

	private void buttonCreateAccont(JLabel labelAlgunCampoVacion) {
		buttonCreateAccount = new JButton("Reñenar datos");
		buttonCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordFieldRepeat.getPassword()))) {
					//añadirUserBaseDeDatos
					Empleado empleadoNuevo = new Empleado(txtDni.getText(), textNombre.getText(), textApellido.getText(), Integer.parseInt(textTelefono.getText()), fechaDeNacimiento, textDireccion.getText(), textCorreo.getText() );
					System.out.println(empleadoNuevo.toString());
					controller.anadirUser(empleadoNuevo, String.valueOf(passwordFieldRepeat.getPassword()));
				}else {
					add(labelAlgunCampoVacion);
					passwordFieldRepeat.setText("");
					passwordField.setText("");
				} 
			}
		});
		buttonCreateAccount.setBounds(415, 250, 133, 23);
		add(buttonCreateAccount);
		buttonCreateAccount.setEnabled(false);
		buttonCreateAccount.addActionListener(this);
		
	}
	
	private JLabel labelAlgunCampoVacion() {
		JLabel labelContrasenaNoCoincide = new JLabel("Algun dato esta vacio");
		labelContrasenaNoCoincide.setForeground(new Color(255, 0, 0));
		labelContrasenaNoCoincide.setBounds(225, 119, 200, 45);
		return labelContrasenaNoCoincide;
	}

	private void jTextCorreo() {
		textCorreo = new JTextField();
		textCorreo.setColumns(10);
		textCorreo.setBounds(510, 144, 109, 20);
		textCorreo.getDocument().addDocumentListener(this);
		add(textCorreo);
		
	}

	private void labelCorreo() {
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(510, 119, 86, 14);
		add(lblCorreo);
		
	}

	private void jTextDireccion() {
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(510, 86, 109, 20);
		textDireccion.getDocument().addDocumentListener(this);
		add(textDireccion);
		
	}

	private void labelDireccion() {
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(510, 61, 86, 14);
		add(lblDireccion);
		
	}
	
	private void fechaDeNacimiento() {
	    JPanel calendarPanel = new JPanel();
	    calendarPanel.setLayout(new BorderLayout());
	    JCalendar calendar = new JCalendar();
	    calendar.setDate(Calendar.getInstance().getTime());
	    calendarPanel.add(calendar, BorderLayout.CENTER);
	    JButton selectButton = new JButton("Seleccionar");
	    selectButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(calendar.getTime());
	            int year = calendar.get(Calendar.YEAR);
	            int month = calendar.get(Calendar.MONTH);
	            int day = calendar.get(Calendar.DAY_OF_MONTH);
	            calendar.clear();
	            calendar.set(Calendar.YEAR, year);
	            calendar.set(Calendar.MONTH, month);
	            calendar.set(Calendar.DAY_OF_MONTH, day);
	            fechaDeNacimiento = calendar.getTime();
	        }
	    });
	    calendarPanel.setVisible(true); 
	    calendarPanel.add(selectButton, BorderLayout.SOUTH);
	    calendarPanel.setBounds(719, 86, 200, 200);
	    add(calendarPanel);
	}

	private void labelFechaDeNacimiento() {
		JLabel lblFechaDeNacimiento = new JLabel("Fecha De Nacimiento:");
		lblFechaDeNacimiento.setBounds(719, 61, 133, 14);
		add(lblFechaDeNacimiento);
		
	}

	private void jTextTelefono() {
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(301, 202, 109, 20);
		textTelefono.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
		            e.consume();
		        }
		    }
		});
		textTelefono.getDocument().addDocumentListener(this);
		add(textTelefono);
		
	}

	private void labelTelefono() {
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(301, 177, 86, 14);
		add(lblTelefono);
		
	}

	private void jTextApellido() {
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(301, 144, 109, 20);
		textApellido.getDocument().addDocumentListener(this);
		add(textApellido);
		
	}

	private void labelApellido() {
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(301, 119, 86, 14);
		add(lblApellido);
		
	}

	private void jTextNombre() {
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(301, 86, 109, 20);
		textNombre.getDocument().addDocumentListener(this);
		add(textNombre);
		
	}

	private void labelNombre() {
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(301, 61, 86, 14);
		add(lblNombre);
		
	}

	private void labelContrasena() {
		JLabel labelContraseña = new JLabel("Contraseña:");
		labelContraseña.setBounds(48, 119, 86, 14);
		add(labelContraseña);
		
	}
	
	private void labelContrasenaRepeat() {
		JLabel labelContraseña = new JLabel("Repite La Contraseña:");
		labelContraseña.setBounds(48, 177, 200, 14);
		add(labelContraseña);
		
	}
	
	
	private JLabel labelContrasenaNoCoincide() {
		JLabel labelContrasenaNoCoincide = new JLabel("ContrasenaNoCoincide");
		labelContrasenaNoCoincide.setForeground(new Color(255, 0, 0));
		labelContrasenaNoCoincide.setBounds(225, 119, 200, 45);
		return labelContrasenaNoCoincide;
	}
	private void labelDNI() {
		JLabel labelUsuario = new JLabel("DNI:");
		labelUsuario.setBounds(48, 61, 86, 14);
		add(labelUsuario);
		
	}
	
	public void insertUpdate(DocumentEvent e) {
	    if (txtDni.getText().length() > 0 && String.valueOf(passwordField.getPassword()).length() > 0 && String.valueOf(passwordFieldRepeat.getPassword()).length() > 0) {
	    	buttonLogIn.setEnabled(true);
	    }
	    if(segundaParte && textNombre.getText().length() > 0 && textApellido.getText().length() > 0 && textTelefono.getText().length() > 0 && textDireccion.getText().length() > 0 && textCorreo.getText().length() > 0) {
	    	buttonCreateAccount.setEnabled(true);
	    }
	  }

	@Override
	public void removeUpdate(DocumentEvent e) {
		if (txtDni.getText().length() == 0 || String.valueOf(passwordField.getPassword()).length() == 0 || String.valueOf(passwordFieldRepeat.getPassword()).length() > 0) {
			buttonLogIn.setEnabled(false);
		}
		if(segundaParte && (textNombre.getText().length() == 0 || textApellido.getText().length() == 0 || textTelefono.getText().length() == 0 || textDireccion.getText().length() == 0 || textCorreo.getText().length() == 0)) {
	    	buttonCreateAccount.setEnabled(false);
	    }
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}