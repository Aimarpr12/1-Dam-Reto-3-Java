package vista;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import error.VehiculoNoEncontradoException;
import modelo.Cliente;
import modelo.Coche;
import modelo.Empleado;
import modelo.Moto;
import modelo.TipoDeVehiculo;
import modelo.Venta;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class VerVentas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private List <Venta> listaDeVentas;
	private Controller controller;
	/**
	 * Create the panel.
	 * @param user 
	 * @param controller2 
	 */
	public VerVentas(Empleado user, Controller controller2) {
		setBackground(new Color(255, 252, 244));
		this.controller = controller2;
		cargarTodasLasVentas();

		btnVerCliente(user);

		btnVerVehiculo(user);

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
		btnLogOut.setBounds(754, 49, 89, 23);
		add(btnLogOut);		
	}

	private void buttonAtras(Empleado user) {
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBackground(SystemColor.textHighlight);
		btnAtras.setForeground(new Color(255, 255, 255));
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.esJefe(user);
			}
		});
		btnAtras.setBounds(43, 49, 89, 23);
		add(btnAtras);

	}

	private void btnVerVehiculo(Empleado user) { 
		JButton btnDatosDelVehiculo = new JButton("Datos del Vehiculo");
		btnDatosDelVehiculo.setBackground(SystemColor.textHighlight);
		btnDatosDelVehiculo.setForeground(new Color(255, 255, 255));
		btnDatosDelVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDatosDelVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matricula = null;
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada != -1) {
					matricula = table.getValueAt(filaSeleccionada, 4).toString();
					verDatosDelCoche(matricula, user);	
				} 
			}
		});
		btnDatosDelVehiculo.setBounds(683, 366, 160, 23);
		add(btnDatosDelVehiculo);
	} 

	private void verDatosDelCoche(String matricula, Empleado user) {
		try {
			TipoDeVehiculo tipoDeVehiculo = controller.averiguarTipoDeVehiculo(matricula);
			if(TipoDeVehiculo.coche.equals(tipoDeVehiculo)) {
				Coche coche = new Coche();
				coche = (Coche) coche.encontrarVehiculoEnLista(matricula, controller);
				mostrarDatatosCoches(coche);
			}else if (TipoDeVehiculo.moto.equals(tipoDeVehiculo)){
				Moto moto = new Moto();
				moto = (Moto) moto.encontrarVehiculoEnLista(matricula, controller);
				mostrarDatosMoto(moto);
			}    
			
		} catch (VehiculoNoEncontradoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void mostrarDatosMoto(Moto moto) {
		JOptionPane.showMessageDialog(null, ""
				+ "Bastidor: " + moto.getBastidor() + "\n"
				+ "Matricula: " + moto.getMatricula() + "\n"
				+ "Marca: " + moto.getMarca() + "\n"
				+ "Modelo: " + moto.getModelo() + "\n"
				+ "Año: " + moto.getAño() + "\n"
				+ "Cilindrada: " + moto.getCilindrada()
				, "Datos de la moto", JOptionPane.INFORMATION_MESSAGE);		
	}

	private void mostrarDatatosCoches(Coche coche) {
		JOptionPane.showMessageDialog(null, ""
				+ "Bastidor: " + coche.getBastidor() + "\n"
				+ "Matricula: " + coche.getMatricula() + "\n"
				+ "Marca: " + coche.getMarca() + "\n"
				+ "Modelo: " + coche.getModelo() + "\n"
				+ "Año: " + coche.getAño() + "\n"
				+ "Tipo de motor: " + coche.getMotor()
				, "Datos del coche", JOptionPane.INFORMATION_MESSAGE);
		
	}

	private void btnVerCliente(Empleado user) {
		JButton btnVerDatosDelCliente = new JButton("Datos del Cliente");
		btnVerDatosDelCliente.setBackground(SystemColor.textHighlight);
		btnVerDatosDelCliente.setForeground(new Color(255, 255, 255));
		btnVerDatosDelCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerDatosDelCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = null;
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada != -1) {
					dni = table.getValueAt(filaSeleccionada, 5).toString();
					Cliente cliente = new Cliente();	
					cliente = cliente.getUserFromList(controller, dni);
					JOptionPane.showMessageDialog(null, ""
							+ "DNI: " + cliente.getDni() + "\n"
							+ "Nombre: " + cliente.getNombre() + "\n"
							+ "Apellido: " + cliente.getApellido() + "\n"
							+ "Telefono: " + cliente.getTelefono() + "\n"
							+ "Correo: " + cliente.getCorreo() + "\n"
							+ "Direccion: " + cliente.getDir()
							, "Datos del cliente", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnVerDatosDelCliente.setBounds(43, 366, 167, 23);
		add(btnVerDatosDelCliente);		
	}



	private void cargarTodasLasVentas() {		
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 148, 800, 190);
		add(scrollPane);

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultTableModel();
		table.setModel(model);

		model.addColumn("Id venta");
		model.addColumn("Precio");
		model.addColumn("Fecha");
		model.addColumn("DNI del Vendedor");
		model.addColumn("Bastidor");
		model.addColumn("DNI del Cliente");
		listaDeVentas = controller.getAllVentas();
		actualizarVentas();

		scrollPane.setViewportView(table);
	}

	private void actualizarVentas() {

		for (Venta venta : listaDeVentas) {

			Object[] fila = new Object [6];
			fila[0]= venta.getIdVenta();
			fila[1]= venta.getPrecio();
			fila[2]= venta.getFecha();
			fila[3]= venta.getIdVendedor();
			fila[4]= venta.getBastidor();
			fila[5] = venta.getIdCliente();

			model.addRow(fila); 
		}

	}
}
