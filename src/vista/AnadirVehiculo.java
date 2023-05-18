package vista;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import controller.Controller;
import modelo.Coche;
import modelo.Empleado;
import modelo.Mecanico;
import modelo.Moto;
import modelo.Motor;
import modelo.TipoDeVehiculo;
import modelo.Vehiculo;
import modelo.Vendedor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class AnadirVehiculo extends JPanel implements DocumentListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private Controller controller;
	private JTextField textFielMatricula;
	private JButton btnAnadirVehiculo;
	private JTextComponent textFieldBastidor;
	private JComboBox comboBoxMatricula;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	public AnadirVehiculo(Empleado user, Controller controller) {
		setBackground(new Color(255, 252, 244));
		this.controller = controller;
		setLayout(null);
		
		btnAnadirVehiculo(user);
		
		anadirMatricula(user);
		
		
		anadirPrecio();
		
		anadirCoste();	
		
		anadirMarca();
		
		anadirModelo();
		
		buttonLogOut();
		
		buttonAtras(user);
	}
	private void anadirModelo() {
		lableAnadirModelo();
		txtFieldoModelo();
		
	}
	private void anadirMarca() {
		lableAnadirMarca();
		txtFieldoMarca();
		
	}
	private void anadirCoste() {
		lableAnadirCoste();
		textFieldCoste();
	}
	
	private void anadirPrecio() {
		lblAnadirPrecio();
		textFieldPrecio();
		
	}
	
	private void anadirMatricula(Empleado user) {
		lblMatricula();
		jComboBoxMatricula(user);
		
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
		btnLogOut.setBounds(713, 49, 89, 23);
		add(btnLogOut);		
	}
	/**
	 * Boton que vuelve a la pantalla de inicio correspondiente dependiendo del tipo de empleado
	 */
	private void buttonAtras(Empleado user) {
		JButton btnAtras = new JButton("Atras");
		btnAtras.setForeground(Color.WHITE);
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
		btnAtras.setBounds(68, 49, 89, 23);
		add(btnAtras);
		
	}
	
	private void txtFieldoModelo() {
		textFieldModelo = new JTextField();
		textFieldModelo.setBounds(417, 345, 86, 20);
		textFieldModelo.getDocument().addDocumentListener(this);
		add(textFieldModelo);
		
	}
	private void lableAnadirModelo() {
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setForeground(new Color(255, 128, 0));
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModelo.setBounds(417, 295, 89, 14);
		add(lblModelo);
		
	}
	private void txtFieldoMarca() {
		textFieldMarca = new JTextField();
		textFieldMarca.setBounds(203, 345, 86, 20);
		textFieldMarca.getDocument().addDocumentListener(this);
		add(textFieldMarca);
		
	}
	private void lableAnadirMarca() {
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setForeground(new Color(255, 128, 0));
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMarca.setBounds(203, 295, 61, 14);
		add(lblMarca);
		
	}
	
	private void textFieldCoste() {
		textFielMatricula = new JTextField();
		textFielMatricula.setColumns(10);
		textFielMatricula.setBounds(496, 198, 86, 20);
		textFielMatricula.getDocument().addDocumentListener(this);
		add(textFielMatricula);				
	}
	
	private void lableAnadirCoste() {
		JLabel lablMatricula = new JLabel("Matricula:");
		lablMatricula.setForeground(new Color(255, 128, 0));
		lablMatricula.setFont(new Font("Tahoma", Font.BOLD, 15));
		lablMatricula.setBounds(496, 163, 103, 14);
		add(lablMatricula);		
	}
	
	private void textFieldPrecio() {
	    textFieldBastidor = new JTextField();
	    textFieldBastidor.setBounds(256, 198, 86, 20);
	    textFieldBastidor.getDocument().addDocumentListener(this);
	    add(textFieldBastidor);
	}

	private void lblAnadirPrecio() {
		JLabel lblBastidor = new JLabel("Bastidor:");
		lblBastidor.setForeground(new Color(255, 128, 0));
		lblBastidor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBastidor.setBounds(256, 163, 85, 14);
		add(lblBastidor);		
	}
	
	private void jComboBoxMatricula(Empleado user) {
		comboBoxMatricula = new JComboBox();
		comboBoxMatricula.setBounds(594, 343, 115, 22);
		int anoActual = 2023;
		for(int i = 0; i < 50; i++) {
			comboBoxMatricula.addItem(anoActual - i);
		}
		add(comboBoxMatricula);		
	}
	private void lblMatricula() {
		JLabel lblAno = new JLabel("Año:");
		lblAno.setForeground(new Color(255, 128, 0));
		lblAno.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAno.setBounds(594, 295, 115, 14);
		add(lblAno);
	}
	/**
	 * Recoge los datos de los campos
	 * hace comprobaciones de tamaño
	 * introduce en bbdd
	 */
	private void btnAnadirVehiculo(Empleado user) {
		btnAnadirVehiculo = new JButton("Añadir Vehiculo");
		btnAnadirVehiculo.setForeground(Color.WHITE);
		btnAnadirVehiculo.setBackground(SystemColor.textHighlight);
		btnAnadirVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnadirVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldBastidor.getText().length() == 17) {
					boolean seHaAnadido = anadirTipoDeVehiculo();
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
				}else {
					JOptionPane.showMessageDialog(
						    null,
						    "El bastidor no es correcto. Debe tener 17 caracteres.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		btnAnadirVehiculo.setBounds(399, 427, 139, 23);
		add(btnAnadirVehiculo);
		btnAnadirVehiculo.setEnabled(false);
		btnAnadirVehiculo.addActionListener(this);
	}
	
	protected boolean anadirTipoDeVehiculo() {
		String[] options = {"Moto", "Coche"};
		int selectedOption = JOptionPane.showOptionDialog(
		    null,
		    "¿Qué tipo de vehículo tienes?",
		    "Selecciona una opción",
		    JOptionPane.DEFAULT_OPTION,
		    JOptionPane.PLAIN_MESSAGE,
		    null,
		    options,
		    options[0]);

		if (selectedOption == 0) {
			Moto moto = new Moto(textFieldBastidor.getText().toString(), textFielMatricula.getText().toString(),
					textFieldMarca.getText().toString(), textFieldModelo.getText().toString(), 
					Integer.parseInt(comboBoxMatricula.getSelectedItem().toString()), TipoDeVehiculo.moto);
			boolean seHaInsertado = insertarCilindrada(moto);
		    if(seHaInsertado) {
				return true;
			}else {
				return false;
			}
		} else if (selectedOption == 1) {
			Coche coche = new Coche(textFieldBastidor.getText().toString(), textFielMatricula.getText().toString(),
					textFieldMarca.getText().toString(), textFieldModelo.getText().toString(), 
					Integer.parseInt(comboBoxMatricula.getSelectedItem().toString()), TipoDeVehiculo.coche);
			boolean seHaInsertado = insertarTipoDeMotor(coche);
			if(seHaInsertado) {
				return true;
			}else {
				return false;
			}
		} else {
			return false;
		}
	}
	/**
	 * Si es un coche, inserta el tipo de motor
	 * @param vehiculo
	 */
	private boolean insertarTipoDeMotor(Vehiculo vehiculo) {
		String[] options = {"Diesel", "Gasolina", "Electrico"};
		int selectedOption = JOptionPane.showOptionDialog(
		    null,
		    "¿Qué tipo de vehículo tienes?",
		    "Selecciona una opción",
		    JOptionPane.DEFAULT_OPTION,
		    JOptionPane.PLAIN_MESSAGE,
		    null,
		    options,
		    options[0]);

		Coche coche = (Coche) vehiculo;
		if (selectedOption == 0) {
		    coche.setMotor(Motor.Diesel);
		} else if (selectedOption == 1) {
			coche.setMotor(Motor.Gasolina);
		    // El usuario seleccionó "Coche"
		} else if (selectedOption == 2) {
			coche.setMotor(Motor.Electrico);
		    // El usuario seleccionó "Bicicleta"
		} else {
		    return false;
		}
		boolean seHaAnadidoEnBD = controller.anadirCoche(coche);
		if(seHaAnadidoEnBD) {
			controller.addCoche(coche);
			return true;
		}else {
			return false;			
		}
		
	}
	/**
	 * Si es una moto, inserta la cilindrada
	 * @param vehiculo
	 */
	private boolean insertarCilindrada(Vehiculo vehiculo) {
		int cilindrada = 0;
		boolean cilindradaValida = false;

		while (!cilindradaValida) {
		    String input = JOptionPane.showInputDialog(
		        null,
		        "Introduce la cilindrada de la moto:",
		        "Cilindrada",
		        JOptionPane.PLAIN_MESSAGE
		   );

		    try {
		        cilindrada = Integer.parseInt(input);
		        cilindradaValida = true;
		        Moto moto = new Moto(vehiculo, cilindrada);		        
				boolean seHaAnadidoEnBD = controller.anadirMoto(moto);
				if(seHaAnadidoEnBD) {
					controller.addMoto(moto);
					return true;
				}else {
					return false;			
				}
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(
		            null,
		            "La cilindrada debe ser un número entero.",
		            "Error",
		            JOptionPane.ERROR_MESSAGE);
		    }
		}
		return false;

		// Realiza la acción correspondiente con el valor de la cilindrada aquí

		
	}
	public void insertUpdate(DocumentEvent e) {
	    if (textFieldBastidor.getText().length() == 17 && textFielMatricula.getText().length() > 0 &&  textFieldMarca.getText().length() > 0 && textFieldModelo.getText().length() > 0) {
	    	btnAnadirVehiculo.setEnabled(true);
	    }
	  }
	
	@Override
	public void removeUpdate(DocumentEvent e) {
		if (textFieldBastidor.getText().length() > 17 || textFieldBastidor.getText().length() < 17 || textFielMatricula.getText().length() == 0 || textFieldMarca.getText().length() == 0 || textFieldModelo.getText().length() == 0) {
			btnAnadirVehiculo.setEnabled(false);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
