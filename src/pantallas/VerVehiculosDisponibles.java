package pantallas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import clases.Vehiculo;
import clases.Vendedor;
import controller.Controller;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class VerVehiculosDisponibles extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private Controller controller;
	private JTable table;
	private DefaultTableModel model;
	private List <Vehiculo> listaDeVehiculos;
	public VerVehiculosDisponibles(Vendedor user, Controller controller) {
		setBackground(new Color(255, 252, 244));
		setLayout(null);
		
		this.controller = controller;
		
		cargarTodasLasVentas();
		
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
		btnLogOut.setBounds(753, 83, 89, 23);
		add(btnLogOut);		
	}

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
		btnAtras.setBounds(43, 83, 89, 23);
		add(btnAtras);
		
	}

	private void cargarTodasLasVentas() {		
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 217, 800, 190);
		add(scrollPane);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultTableModel();
		table.setModel(model);
		 
		model.addColumn("Bastidor");
		model.addColumn("Matricula");
		model.addColumn("Marca");
		model.addColumn("Modelo");
		model.addColumn("Año");
		listaDeVehiculos = controller.getAllVehiculosDisponibles();
		actualizarVentas();
		
		scrollPane.setViewportView(table);
	}

	private void actualizarVentas() {
		
		for (Vehiculo vehiculo: listaDeVehiculos) {
	
			Object[] fila = new Object [5];
			fila[0]= vehiculo.getBastidor();
			fila[1]= vehiculo.getMatricula();
			fila[2]= vehiculo.getMarca();
			fila[3]= vehiculo.getModelo();
			fila[4]= vehiculo.getAño();
			
			model.addRow(fila); 
		}
	}
}
