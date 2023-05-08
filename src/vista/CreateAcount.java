package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import controller.Controller;
import modelo.Empleado;
import modelo.Mecanico;

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
	private static JTextField textCorreo;
	private Date fechaDeNacimiento; 
	private boolean segundaParte = false;
	private String fechaFormateada;
	
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
		
		buttonLogOut();
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
				if(!segundaParte) {
					if (String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordFieldRepeat.getPassword())) && txtDni.getText().length() <= 9 && String.valueOf(passwordField.getPassword()).length() <=16) {
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
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
						passwordFieldRepeat.setText("");
						passwordField.setText("");
					}					
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
		buttonCreateAccont(labelAlgunCampoVacion);
	}
	
	private void buttonLogOut() {
		JButton btnLogOut = new JButton("Atras");
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

	private void buttonCreateAccont(JLabel labelAlgunCampoVacion) {
		buttonCreateAccount = new JButton("Rellenar datos");
		buttonCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordFieldRepeat.getPassword())) && comprobarQueLosDatosSeanCorrectos()) {
					//añadirUserBaseDeDatos
					Empleado empleadoNuevo = new Empleado(txtDni.getText(), textNombre.getText(), textApellido.getText(), Integer.parseInt(textTelefono.getText()), fechaDeNacimiento, textDireccion.getText(), textCorreo.getText() );
					controller.anadirUser(empleadoNuevo, String.valueOf(passwordFieldRepeat.getPassword()));
					JOptionPane.showMessageDialog(null, "User añadido con exito", "User Correcto", JOptionPane.INFORMATION_MESSAGE);
					Component component = (Component) e.getSource();
					App app = (App) SwingUtilities.getRoot(component);
					app.logIn();
				}else {
					JOptionPane.showMessageDialog(null, "Algun Campo Incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
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
	
	protected boolean comprobarQueLosDatosSeanCorrectos() {
		if(txtDni.getText().length() <= 9 && textNombre.getText().length() <=30 && textApellido.getText().length() <= 40 && textTelefono.getText().length() == 9 && comprobarFormatoFechaNacimiento() && textDireccion.getText().length() <= 200 && textCorreo.getText().length() <= 30 && comprobarFormatoCorreo()) {
			return true;
		}
		return false;
	}


	private boolean comprobarFormatoFechaNacimiento() {
		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        formatoFecha.setLenient(false);
        try {
            Date fechaFormateadaDate = formatoFecha.parse(fechaFormateada);
            return true;
        } catch (ParseException e) {
            return false;
        } catch (Exception e) {
			return false;
		}
	}

	public static boolean comprobarFormatoCorreo() {
	    int longitud = textCorreo.getText().length();
	    if (longitud < 3) {
	        return false; // el string no tiene suficientes caracteres para tener una arroba entre medias
	    }
	    
	    String correo = textCorreo.getText().substring(1, textCorreo.getText().length()-1);
	    if(correo.contains("@")) {
	    	return true;
	    }else {
	    	return false;
	    }
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
		textCorreo.revalidate();
		textCorreo.repaint();
	}

	private void labelCorreo() {
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(510, 119, 86, 14);
		add(lblCorreo);
		lblCorreo.revalidate();
		lblCorreo.repaint();
	}

	private void jTextDireccion() {
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(510, 86, 109, 20);
		textDireccion.getDocument().addDocumentListener(this);
		add(textDireccion);
		textDireccion.revalidate();
		textDireccion.repaint();
	}

	private void labelDireccion() {
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(510, 61, 86, 14);
		add(lblDireccion);
		lblDireccion.revalidate();
		lblDireccion.repaint();
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
	            Date fechaSeleccionada = calendar.getDate();
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(fechaSeleccionada);
	            fechaDeNacimiento = calendar.getTime();
	            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	            fechaFormateada = formato.format(fechaDeNacimiento);
	        }
	    });
	    calendarPanel.setVisible(true); 
	    calendarPanel.add(selectButton, BorderLayout.SOUTH);
	    calendarPanel.setBounds(719, 86, 200, 200);
	    add(calendarPanel);
	    calendarPanel.revalidate();
	    calendarPanel.repaint();
	}

	private void labelFechaDeNacimiento() {
		JLabel lblFechaDeNacimiento = new JLabel("Fecha De Nacimiento:");
		lblFechaDeNacimiento.setBounds(719, 61, 133, 14);
		add(lblFechaDeNacimiento);
		lblFechaDeNacimiento.revalidate();
		lblFechaDeNacimiento.repaint();
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
		textTelefono.revalidate();
		textTelefono.repaint();
		
	}

	private void labelTelefono() {
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(301, 177, 86, 14);
		add(lblTelefono);
		lblTelefono.revalidate();
		lblTelefono.repaint();
		
	}

	private void jTextApellido() {
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(301, 144, 109, 20);
		textApellido.getDocument().addDocumentListener(this);
		add(textApellido);
		textApellido.revalidate();
		textApellido.repaint();
		
	}

	private void labelApellido() {
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(301, 119, 86, 14);
		add(lblApellido);
		lblApellido.revalidate();
		lblApellido.repaint();
		
	}

	private void jTextNombre() {
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(301, 86, 109, 20);
		textNombre.getDocument().addDocumentListener(this);
		add(textNombre);
		textNombre.revalidate();
		textNombre.repaint();
		
	}

	private void labelNombre() {
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(301, 61, 86, 14);
		add(lblNombre);
		lblNombre.revalidate();
		lblNombre.repaint();
		
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