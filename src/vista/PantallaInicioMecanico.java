package vista;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.Controller;
import error.VehiculoNoEncontradoException;
import modelo.Cliente;
import modelo.Coche;
import modelo.Mecanico;
import modelo.Moto;
import modelo.Reparacion;
import modelo.TipoDeVehiculo;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

public class PantallaInicioMecanico extends JPanel {

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
	private List<Reparacion> listaDeReparaciones;
	public PantallaInicioMecanico(Mecanico user, Controller controller2) {
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

		btnFinReparacion(user);
	
		btnAsociarClienteAVehiculo(user);
		
		btnAnadirCliente(user);
		
		btnAnadirVehiculo(user);
	}
	
	private void btnAsociarClienteAVehiculo(Mecanico user) {
		JButton btnAsociarClienteAVehiculo = new JButton("Asociar Cliente A Vehiculo");
		btnAsociarClienteAVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAsociarClienteAVehiculo.setForeground(new Color(255, 255, 255));
		btnAsociarClienteAVehiculo.setBackground(SystemColor.textHighlight);
		btnAsociarClienteAVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.asociarClienteVehiculo(user);
			}
		});
		btnAsociarClienteAVehiculo.setBounds(628, 470, 244, 23);
		add(btnAsociarClienteAVehiculo);
	}

	private void btnAnadirCliente(Mecanico user) {
		JButton btnAnadirCliente = new JButton("Añadir Cliente");
		btnAnadirCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnadirCliente.setForeground(new Color(255, 255, 255));
		btnAnadirCliente.setBackground(SystemColor.textHighlight);
		btnAnadirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.anadirCliente(user);
			}
		});
		btnAnadirCliente.setBounds(341, 470, 200, 23);
		add(btnAnadirCliente);
		
	}

	private void btnAnadirVehiculo(Mecanico user) {		
		JButton btnAnadirVehiculo = new JButton("Añadir Vehiculo");
		btnAnadirVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnadirVehiculo.setForeground(new Color(255, 255, 255));
		btnAnadirVehiculo.setBackground(SystemColor.textHighlight);
		btnAnadirVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.anadirVehiculo(user);
			}
		});
		btnAnadirVehiculo.setBounds(72, 470, 160, 23);
		add(btnAnadirVehiculo);
		
	}

	private void cargarTodasLasListas() {
		controller.cargarListaDeClientes();
		controller.cargarListaDeClienteVehiculos();
		controller.cargarListaDeVehiculos();
		controller.cargarListaDeReparaciones();

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


	/**
	 * Muestra pop up con todos los datos del vehiculo seleccionado en la tabla
	 */
	private void btnVerVehiculo(Mecanico user) { 
		JButton btnDatosDelVehiculo = new JButton("Datos del Vehículo");
		btnDatosDelVehiculo.setBackground(SystemColor.textHighlight);
		btnDatosDelVehiculo.setForeground(Color.WHITE);
		btnDatosDelVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDatosDelVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matricula = null;
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada != -1) {
					matricula = table.getValueAt(filaSeleccionada, 5).toString();
					verDatosDelCoche(matricula, user);	
				} 
			}
		});
		btnDatosDelVehiculo.setBounds(466, 409, 160, 23);
		add(btnDatosDelVehiculo);
	} 
	/**
	 * Obtiene los datos del vehiculo dependiendo del tipo
	 * @param matricula
	 * @param user
	 */
	private void verDatosDelCoche(String matricula, Mecanico user) {
		TipoDeVehiculo tipoDeVehiculo;
		try {
			tipoDeVehiculo = controller.averiguarTipoDeVehiculo(matricula);
			if(TipoDeVehiculo.coche.equals(tipoDeVehiculo)) {
				Coche coche = new Coche();
				coche = (Coche) coche.encontrarVehiculoEnLista(matricula, controller.getAllVehiculos());
				mostrarDatatosCoches(coche);
			}else if (TipoDeVehiculo.moto.equals(tipoDeVehiculo)){
				Moto moto = new Moto();
				moto = (Moto) moto.encontrarVehiculoEnLista(matricula, controller.getAllVehiculos());
				mostrarDatosMoto(moto);
			}    
		} catch (VehiculoNoEncontradoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
		}
		

	}

	private void mostrarDatosMoto(Moto moto) {
		JOptionPane.showMessageDialog(null, ""
				+ "Bastidor: " + moto.getBastidor() + "\n"
				+ "Matrícula: " + moto.getMatricula() + "\n"
				+ "Marca: " + moto.getMarca() + "\n"
				+ "Modelo: " + moto.getModelo() + "\n"
				+ "Año: " + moto.getAno() + "\n"
				+ "Cilindrada: " + moto.getCilindrada()
				, "Datos de la moto", JOptionPane.INFORMATION_MESSAGE);
	}

	private void mostrarDatatosCoches(Coche coche) {
		JOptionPane.showMessageDialog(null, ""
				+ "Bastidor: " + coche.getBastidor() + "\n"
				+ "Matrícula: " + coche.getMatricula() + "\n"
				+ "Marca: " + coche.getMarca() + "\n"
				+ "Modelo: " + coche.getModelo() + "\n"
				+ "Año: " + coche.getAno() + "\n"
				+ "Tipo de motor: " + coche.getMotor()
				, "Datos del coche", JOptionPane.INFORMATION_MESSAGE);		
	}

	private void btnFinReparacion(Mecanico user) { 
		JButton btnDatosDelVehiculo = new JButton("Finalizar Reparacion");
		btnDatosDelVehiculo.setBackground(SystemColor.textHighlight);
		btnDatosDelVehiculo.setForeground(Color.WHITE);
		btnDatosDelVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDatosDelVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada != -1) {
					try {
						table.getValueAt(filaSeleccionada, 4).toString();
						JOptionPane.showMessageDialog(null,"La reparación ya esta finalizada", "Error", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e2) {
						int idReparacion = Integer.parseInt(table.getValueAt(filaSeleccionada, 0).toString());
						finalizarReparacion(idReparacion, user, e);
					}
				} 
			}
		});
		btnDatosDelVehiculo.setBounds(672, 409, 200, 23);
		add(btnDatosDelVehiculo);

		JButton btnAnadirReparacion = new JButton("Añadir Reparación");
		btnAnadirReparacion.setBackground(SystemColor.textHighlight);
		btnAnadirReparacion.setForeground(Color.WHITE);
		btnAnadirReparacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnadirReparacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comprobarQueHayHuecoParaLaReparacion()) {
					Component component = (Component) e.getSource();
					App app = (App) SwingUtilities.getRoot(component);
					app.anadirReparacion(user);					
				}else {
					JOptionPane.showMessageDialog(null,"No se puede añadir la reparacion por que hay 3 reparaciones sin finalizar", 
							"Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnAnadirReparacion.setBounds(261, 409, 172, 23);
		add(btnAnadirReparacion);
	} 



	protected boolean comprobarQueHayHuecoParaLaReparacion() {
		int i = 0;
		for(Reparacion reparacion : controller.getAllReparaciones()) {
			if(reparacion.getFechaFin() == null) {
				i++;
			}
		}
		if(i < 3 ) {
			return true;
		}else {
			return false;
		}
	}

	protected void finalizarReparacion(int idReparacion, Mecanico user, ActionEvent e) {
		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");   
		String fechaActualFormateada = formatoFecha.format(new Date());
		Date fechaActual = new Date();
		try {
			fechaActual = formatoFecha.parse(fechaActualFormateada);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean seHaReparado = controller.finalizarReaparacion(fechaActual, idReparacion);
		if(seHaReparado) {			
			JOptionPane.showMessageDialog(null,"Actualizado la fecha de fin", "Correct update", JOptionPane.INFORMATION_MESSAGE);	
			Component component = (Component) e.getSource();
			App app = (App) SwingUtilities.getRoot(component);
			app.esMecanico(user);
		}else {
			JOptionPane.showMessageDialog(null,"No se ha podido actualizar", "Error", JOptionPane.INFORMATION_MESSAGE);	
		}
	}
	/**
	 * Muestra pop up con todos los datos del cliente seleccionado en la tabla
	 */
	private void btnVerCliente(Mecanico user) {
		JButton btnVerDatosDelCliente = new JButton("Datos del Cliente");
		btnVerDatosDelCliente.setBackground(SystemColor.textHighlight);
		btnVerDatosDelCliente.setForeground(Color.WHITE);
		btnVerDatosDelCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerDatosDelCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = null;
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada != -1) {
					dni = table.getValueAt(filaSeleccionada,6).toString();
					Cliente cliente = new Cliente();	
					cliente = cliente.getUserFromList(controller.getAllClientes(), dni);
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
		btnVerDatosDelCliente.setBounds(72, 409, 161, 23);
		add(btnVerDatosDelCliente);		
	}

	private void cargarTodasLasVentas(Mecanico user) {		
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 159, 800, 190);
		add(scrollPane);

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultTableModel();
		table.setModel(model);

		model.addColumn("Id reparación");
		model.addColumn("Precio");
		model.addColumn("Coste");
		model.addColumn("Fecha Inicio");
		model.addColumn("Fecha Fin");
		model.addColumn("Matrícula");
		model.addColumn("DNI del Cliente");
		listaDeReparaciones = user.getListaDeReparaciones();
		actualizarVentas();

		 // Agregar sorter a la tabla
	    TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
	    table.setRowSorter(sorter);

	    // Agregar MouseListener a los encabezados de las columnas
	    for (int i = 0; i < table.getColumnCount(); i++) {
	        final int columnIndex = i;
	        TableColumn column = table.getColumnModel().getColumn(i);
	        column.setHeaderRenderer(new ColumnHeaderRenderer());
	        column.setHeaderValue("<html><b>" + column.getHeaderValue() + "</b></html>");
	        column.setResizable(true);

	        JTableHeader tableHeader = table.getTableHeader();
	        tableHeader.addMouseListener(new MouseInputAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                // Obtener el índice de la columna seleccionada
	                int columnIndex = tableHeader.columnAtPoint(e.getPoint());

	                // Obtener la dirección de ordenamiento actual
	                SortOrder currentOrder = sorter.getSortKeys().isEmpty()
	                        ? SortOrder.UNSORTED
	                        : sorter.getSortKeys().get(0).getSortOrder();

	                // Establecer la nueva dirección de ordenamiento
	                if (currentOrder == SortOrder.ASCENDING || currentOrder == SortOrder.UNSORTED) {
	                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(columnIndex, SortOrder.DESCENDING)));
	                } else {
	                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING)));
	                }

	                // Ordenar la tabla
	                sorter.sort();
	            }
	        });
	    }
		
		scrollPane.setViewportView(table);
	}

	private void actualizarVentas() {

		for (Reparacion reparacion : listaDeReparaciones) {

			Object[] fila = new Object [7];
			fila[0]= reparacion.getIdReparacion();
			fila[1]= reparacion.getPrecio();
			fila[2]= reparacion.getCoste();
			fila[3]= reparacion.getFechaIni();
			fila[4]= reparacion.getFechaFin();
			fila[5] = reparacion.conseguirMatriculaDelCoche(controller.getAllVehiculos());
			fila[6] = reparacion.conseguiDniDelCliente(controller.getAllClienteVehiculos());

			model.addRow(fila); 
		}

	}

	private void labelNombre(Mecanico user) {
		JLabel labelNombre = new JLabel(user.getNombre());
		labelNombre.setForeground(new Color(255, 128, 0));
		labelNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelNombre.setBounds(561, 70, 109, 14);
		add(labelNombre);
	}

	private void buttonVerMisDatos(Mecanico user) {
		JButton btnVerMisDatos = new JButton("Ver Mis Datos");
		btnVerMisDatos.setBackground(SystemColor.textHighlight);
		btnVerMisDatos.setForeground(Color.WHITE);
		btnVerMisDatos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerMisDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.mostrarVerDatosMecanico(user);
			}
		});
		btnVerMisDatos.setBounds(72, 66, 131, 23);
		add(btnVerMisDatos);

	}
	/**
	 * Para acceder a modificar los datos hay que insertar password
	 */
	private void buttonEditarDatos(Mecanico user) {
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
						app.mostrarEditarDatosMecanico(user);
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
}
