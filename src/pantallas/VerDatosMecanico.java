package pantallas;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import clases.Mecanico;
import controller.Controller;

import java.awt.Font;
import java.awt.SystemColor;

public class VerDatosMecanico extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private Controller controller;
	public VerDatosMecanico(Mecanico user, Controller controller2) {
		setBackground(new Color(255, 252, 244));
		this.controller = controller2;
		/**
		 * Create the panel.
		 */
		setLayout(null);
		
		labelNombre(user);
		
		buttonVerSueldo(user);
				
		labelNombreJefe(user);
		
		labelIdJefe(user);
		
		labelJefe(user);
		
		labelFechaDeContratacion();
		
		labelFechaContraracionFecha(user);
		
		labelId();
		
		labelIdNum(user);
		
		buttonLogOut();
		
		buttonAtras(user);
		
		labelPuesto(user);
		
		labelNombrePuesto(user);
		
		labelRango(user);
		
		lableNombrePuesto();
	}
		
	private void lableNombrePuesto() {
		JLabel lblRango = new JLabel("Rango");
		lblRango.setForeground(new Color(255, 128, 0));
		lblRango.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRango.setBounds(189, 267, 80, 14);
		add(lblRango);
		
	}

	private void labelRango(Mecanico user) {
		JLabel labelRango = new JLabel(user.getRango());
		labelRango.setForeground(new Color(255, 128, 0));
		labelRango.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelRango.setBounds(412, 267, 316, 14);
		add(labelRango);
		
	}

	private void labelNombrePuesto(Mecanico user) {
		JLabel lbl = new JLabel("Puesto");
		lbl.setForeground(new Color(255, 128, 0));
		lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl.setBounds(189, 206, 80, 14);
		add(lbl);
		
	}

	private void labelPuesto(Mecanico user) {
		JLabel labelPuesto = new JLabel("Mecanico");
		labelPuesto.setForeground(new Color(255, 128, 0));
		labelPuesto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelPuesto.setBounds(412, 206, 316, 14);
		add(labelPuesto);
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
		btnLogOut.setBounds(710, 49, 89, 23);
		add(btnLogOut);		
	}

	private void buttonAtras(Mecanico user) {
		JButton btnAtras = new JButton("Atrás");
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
		btnAtras.setBounds(71, 49, 89, 23);
		add(btnAtras);
		
	}
	
	private void labelNombre(Mecanico user) {
		JLabel labelNombre = new JLabel(user.getNombre());
		labelNombre.setForeground(new Color(255, 128, 0));
		labelNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelNombre.setBounds(526, 53, 109, 14);
		add(labelNombre);
	}

	private void buttonVerSueldo(Mecanico user) {
		JButton btnNewButton = new JButton("Ver Sueldo");
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPasswordField passwordField = new JPasswordField();
        	    int option = JOptionPane.showConfirmDialog(null, passwordField, "Introduzca su contraseña:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        	    if (option == JOptionPane.OK_OPTION) {
        	        char[] password = passwordField.getPassword();
        	        String passwordString = new String(password);    
        	        if(controller.verificarQueLaContrasenaEsCorrecta(user.getDni(), passwordString)) {
        	        	JOptionPane.showMessageDialog(null, "Su salario es de: " + user.getSalario());
        	        }else {
        	        	UIManager.put("OptionPane.messageForeground", Color.RED);
        	            JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        	        }
        	    }
			}
		});
		btnNewButton.setBounds(393, 466, 128, 23);
		add(btnNewButton);
		
	}

	private void labelIdJefe(Mecanico user) {
		String idJefe = user.getJefe() + "";
		String nombreJefe = controller.getNombreJefe(user.getJefe());
		if(nombreJefe.equals("")) {
			idJefe = "No tiene jefe";
		}
		JLabel labelIdJefe = new JLabel("");
		labelIdJefe.setForeground(new Color(255, 128, 0));
		labelIdJefe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelIdJefe.setBounds(600, 376, 208, 14);
		labelIdJefe.setText("Id: " + idJefe);
		add(labelIdJefe);
		
	}

	private void labelNombreJefe(Mecanico user) {
		String nombreJefe = controller.getNombreJefe(user.getJefe());
		if(nombreJefe.equals("")) {
			nombreJefe = "No tiene jefe";
		}
		JLabel labelNombreJefe = new JLabel("");
		labelNombreJefe.setForeground(new Color(255, 128, 0));
		labelNombreJefe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelNombreJefe.setBounds(412, 376, 316, 14);
		labelNombreJefe.setText("Nombre: " + nombreJefe);
		add(labelNombreJefe);
		
	}

	private void labelJefe(Mecanico user) {
		JLabel lblJefe = new JLabel("Jefe:");
		lblJefe.setForeground(new Color(255, 128, 0));
		lblJefe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblJefe.setBounds(189, 376, 138, 14);
		add(lblJefe);
	}

	private void labelFechaDeContratacion() {
		JLabel lblFecha = new JLabel("Fecha De Contratación: ");
		lblFecha.setForeground(new Color(255, 128, 0));
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setBounds(189, 322, 182, 14);
		add(lblFecha);
		
	}

	private void labelFechaContraracionFecha(Mecanico user) {
		JLabel labelFechaContratacion = new JLabel("");
		labelFechaContratacion.setForeground(new Color(255, 128, 0));
		labelFechaContratacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelFechaContratacion.setBounds(412, 322, 316, 14);
		labelFechaContratacion.setText(user.getFechaContratacion() + "");
		add(labelFechaContratacion);
	}

	private void labelIdNum(Mecanico user) {
		JLabel labelIdNum = new JLabel("");
		labelIdNum.setForeground(new Color(255, 128, 0));
		labelIdNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelIdNum.setBounds(412, 157, 316, 14);
		labelIdNum.setText(user.getId() + "");
		add(labelIdNum);
		
		
	}

	private void labelId() {
		JLabel labelId = new JLabel("Id");
		labelId.setForeground(new Color(255, 128, 0));
		labelId.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelId.setBounds(189, 157, 138, 14);
		add(labelId);	
	}
}

