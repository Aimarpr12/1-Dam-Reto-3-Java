package vista;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import controller.Controller;
import error.VehiculoNoEncontradoException;
import modelo.Cliente;
import modelo.Empleado;
import modelo.IntegerOnlyDocument;
import modelo.Mecanico;
import modelo.Vendedor;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class AnadirCliente extends JPanel implements DocumentListener, ActionListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private Controller controller;
	private JTextField textFieldDir;
	private JTextField textFielTelefono;
	private JTextField textFielApellido;
	private JTextComponent textFieldNombre;
	private AbstractButton btnAnadirCliente;
	private JLabel lablCorreo;
	private JTextField textFieldCorreo;
	private JTextField textFieldDni;
	public AnadirCliente(Empleado user, Controller controller)  {
		setBackground(new Color(255, 252, 244));
		this.controller = controller;
		setLayout(null);
		
		
		btnAnadirCliente(user);

		
		anadirDni(user);
		
		
		anadirNombre();
		
		anadirApellido();	
		
		anadirTelefono();
		
		anadirDireccion();
		
		anadirCorreo();
		
		buttonLogOut();
		
		buttonAtras(user);
	}
	private void anadirCorreo() {
		lableAnadirCorreo();
		txtFieldoCorreo();
		
	}
	
	private void anadirDireccion() {
		lableDireccion();
		txtFieldDireccion();
		
	}
	private void anadirTelefono() {
		lableTelefono();
		txtFieldTelefono();
		
	}
	private void anadirApellido() {
		lableApellido();
		textFieldApellido();
	}
	
	private void anadirNombre() {
		lblNombre();
		textNombre();
		
	}
	
	private void anadirDni(Empleado user) {
		lblDni();
		textFieldDni(user);
		
	}
	
	private void buttonLogOut() {
		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.setBackground(SystemColor.textHighlight);
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
                App app = (App) SwingUtilities.getRoot(component);
                app.logIn();
			}
		});
		btnLogOut.setBounds(623, 49, 89, 23);
		add(btnLogOut);		
	}

	private void buttonAtras(Empleado user) {
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBackground(SystemColor.textHighlight);
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
		btnAtras.setBounds(80, 49, 89, 23);
		add(btnAtras);
		
	}
	
	private void txtFieldoCorreo() {
		textFieldCorreo = new JTextField();
		textFieldCorreo.setColumns(10);
		textFieldCorreo.setBounds(506, 288, 86, 20);
		textFieldCorreo.getDocument().addDocumentListener(this);
		add(textFieldCorreo);
		
	}
	private void lableAnadirCorreo() {
		lablCorreo = new JLabel("Correo:");
		lablCorreo.setForeground(new Color(255, 128, 0));
		lablCorreo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lablCorreo.setBounds(506, 248, 73, 14);
		add(lablCorreo);
		
	}
	
	private void textFieldDni(Empleado user) {
		textFieldDni = new JTextField();
		textFieldDni.setBounds(506, 157, 86, 20);
		textFieldDni.getDocument().addDocumentListener(this);
		add(textFieldDni);
		textFieldDni.setColumns(10);		
	}
	private void txtFieldDireccion() {
		textFieldDir = new JTextField();
		textFieldDir.setBounds(345, 288, 86, 20);
		textFieldDir.getDocument().addDocumentListener(this);
		add(textFieldDir);		
	}
	private void lableDireccion() {
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setForeground(new Color(255, 128, 0));
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDireccion.setBounds(342, 248, 89, 14);
		add(lblDireccion);
		
	}
	private void txtFieldTelefono() {
		textFielTelefono = new JTextField();
		textFielTelefono.setBounds(163, 288, 86, 20);
		textFielTelefono.setDocument(new IntegerOnlyDocument());
		textFielTelefono.getDocument().addDocumentListener(this);
		add(textFielTelefono);
		
	}
	private void lableTelefono() {
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setForeground(new Color(255, 128, 0));
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelefono.setBounds(163, 248, 86, 14);
		add(lblTelefono);
		
	}
	
	private void textFieldApellido() {
		textFielApellido = new JTextField();
		textFielApellido.setColumns(10);
		textFielApellido.setBounds(345, 157, 86, 20);
		textFielApellido.getDocument().addDocumentListener(this);
		add(textFielApellido);				
	}
	
	private void lableApellido() {
		JLabel lablApellido = new JLabel("Apellido:");
		lablApellido.setForeground(new Color(255, 128, 0));
		lablApellido.setFont(new Font("Tahoma", Font.BOLD, 15));
		lablApellido.setBounds(345, 126, 73, 14);
		add(lablApellido);		
	}
	
	private void textNombre() {
	    textFieldNombre = new JTextField();
	    textFieldNombre.setBounds(163, 157, 86, 20);
	    textFieldNombre.getDocument().addDocumentListener(this);
	    add(textFieldNombre);
	}

	private void lblNombre() {
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(255, 128, 0));
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBounds(163, 126, 86, 14);
		add(lblNombre);		
	}
	
	
	private void lblDni() {
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setForeground(new Color(255, 128, 0));
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDni.setBounds(506, 126, 115, 14);
		add(lblDni);
		
	}
	private void btnAnadirCliente(Empleado user) {
		btnAnadirCliente = new JButton("Añadir Cliente");
		btnAnadirCliente.setBackground(SystemColor.textHighlight);
		btnAnadirCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnadirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldNombre.getText().length() < 30  && textFielApellido.getText().length() < 40 &&  
						textFielTelefono.getText().length() == 9 && textFieldDir.getText().length() < 200 && 
						textFieldCorreo.getText().length() < 30 && textFieldDni.getText().length() < 9 && 
						tieneArrobaEntreMedias(textFieldCorreo.getText())) {
					Cliente cliente = new Cliente(textFieldDni.getText(), 
							textFieldNombre.getText(), textFielApellido.getText(), 
							Integer.parseInt(textFielTelefono.getText()), textFieldDir.getText(), textFieldCorreo.getText());
					boolean seHaAñadido = cliente.addCliente(cliente, controller);
					if(seHaAñadido) {
						JOptionPane.showMessageDialog(
								null,
								"Se ha añadido correctamente",
								"Añadido correcto",
								JOptionPane.ERROR_MESSAGE);	
						Component component = (Component) e.getSource();
						App app = (App) SwingUtilities.getRoot(component);
						/*
						try {
						    Mecanico mecanico = (Mecanico) user;
						    app.esMecanico(mecanico);
						} catch (ClassCastException e1) {
						    try {
						        Vendedor vendedor = (Vendedor) user;
						        app.esVendedor(vendedor);								
						    } catch (ClassCastException e2) {
						    	throw new UsuarioNoValidoException("El usuario no es un Mecanico ni un Vendedor válido");
						    }
						}
						*/
						
						if (user instanceof Mecanico) {
							Mecanico mecanico = (Mecanico) user;
							app.esMecanico(mecanico);
						} else if (user instanceof Vendedor) {
							Vendedor vendedor = (Vendedor) user;
					        app.esVendedor(vendedor);
						}
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
		btnAnadirCliente.setBounds(358, 371, 139, 23);
		add(btnAnadirCliente);
		btnAnadirCliente.setEnabled(false);
		btnAnadirCliente.addActionListener(this);
	}
	
	public static boolean tieneArrobaEntreMedias(String str) {
	    int longitud = str.length();
	    if (longitud < 3) {
	        return false; // el string no tiene suficientes caracteres para tener una arroba entre medias
	    }
	    
	    String correo = str.substring(1, str.length()-1);
	    if(correo.contains("@")) {
	    	return true;
	    }else {
	    	return false;
	    }
	}

	public void insertUpdate(DocumentEvent e) {
	    if (textFieldNombre.getText().length() >0 && textFielApellido.getText().length() > 0 &&  textFielTelefono.getText().length() > 0 && textFieldDir.getText().length() > 0 && textFieldCorreo.getText().length() > 0 && textFieldDni.getText().length() > 0) {
	    	btnAnadirCliente.setEnabled(true);
	    }
	  }
	
	@Override
	public void removeUpdate(DocumentEvent e) {
		if (textFieldNombre.getText().length() > 17 || textFieldNombre.getText().length() < 17 || textFielApellido.getText().length() == 0 || textFielTelefono.getText().length() == 0 || textFieldDir.getText().length() == 0 || textFieldCorreo.getText().length() == 0 || textFieldDni.getText().length() == 0) {
			btnAnadirCliente.setEnabled(false);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
