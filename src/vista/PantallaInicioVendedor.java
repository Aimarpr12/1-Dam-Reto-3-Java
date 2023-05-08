package vista;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import error.VehiculoNoEncontradoException;
import modelo.Cliente;
import modelo.ClienteVehiculo;
import modelo.Coche;
import modelo.Empleado;
import modelo.Mecanico;
import modelo.Moto;
import modelo.TipoDeVehiculo;
import modelo.Vehiculo;
import modelo.Vendedor;
import modelo.Venta;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;

public class PantallaInicioVendedor extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */

	private Controller controller;
	private JButton btnEditarDatos;
	private JTable table;
	private DefaultTableModel model;
	private List<Venta> listaDeVentas;

	public PantallaInicioVendedor(Vendedor user, Controller controller2) {
		setBackground(new Color(255, 252, 244));
		setLayout(null);
		this.controller = controller2; 
		cargarTodasLasListas();

		cargarTodasLasVentas(user);

		btnVerCliente(user);

		buttonLogOut();

		btnVerVehiculo(user);

		buttonEditarDatos(user);

		buttonVerMisDatos(user);

		labelNombre(user);
		
		buttonAnnadirVenta(user);
		
		buttonVerVehiculosDisponibles(user);
		
		btnAsociarClienteAVehiculo(user);
		
		btnAnadirCliente(user);
		
		btnAnadirVehiculo(user);
	}
	
	private void btnAsociarClienteAVehiculo(Vendedor user) {
	}

	private void btnAnadirCliente(Vendedor user) {
		JButton btnAnadirCliente = new JButton("Añadir Cliente");
		btnAnadirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.anadirCliente(user);
			}
		});
		btnAnadirCliente.setBounds(664, 49, 200, 23);
		add(btnAnadirCliente);
		
	}

	private void btnAnadirVehiculo(Vendedor user) {		
		JButton btnAnadirVehiculo = new JButton("Añadir Vehiculo");
		btnAnadirVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.anadirVehiculo(user);
			}
		});
		btnAnadirVehiculo.setBounds(475, 49, 160, 23);
		add(btnAnadirVehiculo);
		
	}

	private void cargarTodasLasListas() {
		controller.cargarListaDeClientes();
		controller.cargarListaDeClienteVehiculos();
		controller.cargarListaDeVehiculos();
		controller.cargarListaDeVentas();

	}

	private void buttonLogOut() {
		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.setBackground(SystemColor.textHighlight);
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.logIn();
			}
		});
		btnLogOut.setBounds(783, 66, 89, 23);
		add(btnLogOut);		
	}

	private void buttonVerVehiculosDisponibles(Vendedor user) {
		JButton btnVehiculosDisponibles = new JButton("Ver Vehiculos Disponibles");
		btnVehiculosDisponibles.setBackground(SystemColor.textHighlight);
		btnVehiculosDisponibles.setForeground(Color.WHITE);
		btnVehiculosDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVehiculosDisponibles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.mostrarVerVehiculosDisponibles(user);
			}
		});
		btnVehiculosDisponibles.setBounds(270, 409, 204, 23);
		add(btnVehiculosDisponibles);		
	}

	private void btnVerVehiculo(Vendedor user) { 
		JButton btnDatosDelVehiculo = new JButton("Datos del Vehiculo");
		btnDatosDelVehiculo.setBackground(SystemColor.textHighlight);
		btnDatosDelVehiculo.setForeground(Color.WHITE);
		btnDatosDelVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDatosDelVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matricula = null;
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada != -1) {
					matricula = table.getValueAt(filaSeleccionada, 3).toString();
					System.out.println(table.getValueAt(filaSeleccionada, 3).toString());
					verDatosDelCoche(matricula, user);	
				} 
			}
		});
		btnDatosDelVehiculo.setBounds(484, 409, 160, 23);
		add(btnDatosDelVehiculo);
	} 

	private void verDatosDelCoche(String matricula, Empleado user) {
		try {
			TipoDeVehiculo tipoDeVehiculo = controller.averiguarTipoDeVehiculo(matricula);
			if(TipoDeVehiculo.coche.equals(tipoDeVehiculo)) {
				System.out.println("coche");
				Coche coche = new Coche();
				coche = (Coche) coche.encontrarVehiculoEnLista(matricula, controller);
				mostrarDatatosCoches(coche);
			}else if (TipoDeVehiculo.moto.equals(tipoDeVehiculo)){
				System.out.println("moto");
				Moto moto = new Moto();
				moto = (Moto) moto.encontrarVehiculoEnLista(matricula, controller);
				mostrarDatosMoto(moto);
			}    		
		} catch (VehiculoNoEncontradoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void mostrarDatatosCoches(Coche coche) {
		JOptionPane.showMessageDialog(null, ""
				+ "Bastidor: " + coche.getBastidor() + "\n"
				+ "Matrícula: " + coche.getMatricula() + "\n"
				+ "Marca: " + coche.getMarca() + "\n"
				+ "Modelo: " + coche.getModelo() + "\n"
				+ "Año: " + coche.getAño() + "\n"
				+ "Tipo de motor: " + coche.getMotor()
				, "Datos del coche", JOptionPane.INFORMATION_MESSAGE);
	
		
	}

	private void mostrarDatosMoto(Moto moto) {
		JOptionPane.showMessageDialog(null, ""
				+ "Bastidor: " + moto.getBastidor() + "\n"
				+ "Matrícula: " + moto.getMatricula() + "\n"
				+ "Marca: " + moto.getMarca() + "\n"
				+ "Modelo: " + moto.getModelo() + "\n"
				+ "Año: " + moto.getAño() + "\n"
				+ "Cilindrada: " + moto.getCilindrada()
				, "Datos de la moto", JOptionPane.INFORMATION_MESSAGE);		
	}

	private void btnVerCliente(Vendedor user) {
		JButton btnVerDatosDelCliente = new JButton("Datos del Cliente");
		btnVerDatosDelCliente.setBackground(SystemColor.textHighlight);
		btnVerDatosDelCliente.setForeground(Color.WHITE);
		btnVerDatosDelCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerDatosDelCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = null;
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada != -1) {
					dni = table.getValueAt(filaSeleccionada,4).toString();
					Cliente cliente = new Cliente();	
					cliente = cliente.getUserFromList(controller, dni);
					JOptionPane.showMessageDialog(null, ""
							+ "DNI: " + cliente.getDni() + "\n"
							+ "Nombre: " + cliente.getNombre() + "\n"
							+ "Apellido: " + cliente.getApellido() + "\n"
							+ "Teléfono: " + cliente.getTelefono() + "\n"
							+ "Correo: " + cliente.getCorreo() + "\n"
							+ "Dirección: " + cliente.getDir()
							, "Datos del cliente", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnVerDatosDelCliente.setBounds(72, 409, 177, 23);
		add(btnVerDatosDelCliente);		
	}

	private void cargarTodasLasVentas(Empleado user) {		
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 159, 800, 190);
		add(scrollPane);

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultTableModel();
		table.setModel(model);

		model.addColumn("Id venta");
		model.addColumn("Precio");
		model.addColumn("Fecha");
		model.addColumn("Matrícula");
		model.addColumn("DNI del Cliente");
		listaDeVentas = getAllVentasPorVendedor(user.getId());
		actualizarVentas();

		scrollPane.setViewportView(table);
	}
	
	

	private List<Venta> getAllVentasPorVendedor(int id) {
		List <Venta> listDeTodasLasVentas = controller.getAllVentas();
		List <Venta> listDeVentasPorVendedor = new ArrayList <Venta>();
		for(Venta venta : listDeTodasLasVentas) {
			if(id == venta.getIdVendedor()){
				listDeVentasPorVendedor.add(venta);
			}
		}
		return listDeVentasPorVendedor;
	}

	private void actualizarVentas() {

		for (Venta venta : listaDeVentas) {

			Object[] fila = new Object [5];
			fila[0]= venta.getIdVenta();
			fila[1]= venta.getPrecio();
			fila[2]= venta.getFecha();
			fila[3]= venta.conseguirMatriculaDelCoche(controller);
			fila[4]= venta.getIdCliente();

			model.addRow(fila); 
		}

	}

	private void labelNombre(Empleado user) {
		JLabel labelNombre = new JLabel(user.getNombre());
		labelNombre.setForeground(new Color(255, 128, 0));
		labelNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelNombre.setBounds(561, 70, 109, 14);
		add(labelNombre);
	}

	private void buttonVerMisDatos(Vendedor user) {
		JButton btnVerMisDatos = new JButton("Ver Mis Datos");
		btnVerMisDatos.setBackground(SystemColor.textHighlight);
		btnVerMisDatos.setForeground(Color.WHITE);
		btnVerMisDatos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerMisDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.mostrarVerDatosVendedor(user);
			}
		});
		btnVerMisDatos.setBounds(72, 66, 131, 23);
		add(btnVerMisDatos);

	}

	private void buttonEditarDatos(Vendedor user) {
		btnEditarDatos = new JButton("Editar Datos");
		btnEditarDatos.setBackground(SystemColor.textHighlight);
		btnEditarDatos.setForeground(Color.WHITE);
		btnEditarDatos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPasswordField passwordField = new JPasswordField();
				int option = JOptionPane.showConfirmDialog(null, passwordField, "Introduzca su contraseña:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (option == JOptionPane.OK_OPTION) {
					char[] password = passwordField.getPassword();
					String passwordString = new String(password);    
					if(controller.verificarQueLaContrasenaEsCorrecta(user.getDni(), passwordString)) {
						Component component = (Component) e.getSource();
						App app = (App) SwingUtilities.getRoot(component);
						app.mostrarEditarDatosVendedor(user);
					}else {
						UIManager.put("OptionPane.messageForeground", Color.RED);
						JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnEditarDatos.setBounds(321, 66, 131, 23);
		add(btnEditarDatos);

	}

	private void buttonAnnadirVenta(Vendedor user) { 
		JButton btnAnnadirVenta = new JButton("Añadir Venta");
		btnAnnadirVenta.setBackground(SystemColor.textHighlight);
		btnAnnadirVenta.setForeground(Color.WHITE);
		btnAnnadirVenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnnadirVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comprobarQueHayMasde2Vehiculos()) {
					Component component = (Component) e.getSource();
					App app = (App) SwingUtilities.getRoot(component);
					app.anadirVenta(user);					
				}else {
					JOptionPane.showMessageDialog(null, "Hay menos de 2 vehiculos no se puede ralizar la venta",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAnnadirVenta.setBounds(672, 409, 200, 23);
		add(btnAnnadirVenta);
	}

	protected boolean comprobarQueHayMasde2Vehiculos() {
		int i = 0;
		for(Vehiculo vehiculo : controller.getAllVehiculos()) {
			boolean hayCoche = false;
			for(ClienteVehiculo clienteVehiculo : controller.getAllClienteVehiculos()) {
				if(vehiculo.getBastidor().equals(clienteVehiculo.getBastidor())) {
					hayCoche = true;
				}
			}
			if(!hayCoche) {
				i++;
			}
		}
		if(i > 2) {
			return true;
		}
		return false;
	} 

}