package vista;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
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
import modelo.Empleado;
import modelo.Mecanico;
import modelo.Moto;
import modelo.Reparacion;
import modelo.TipoDeVehiculo;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class VerReparaciones extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 * @param user 
	 * @param controller 
	 */
	private Controller controller;
	private JTable table;
	private DefaultTableModel model;
	private List<Reparacion> listaDeReparaciones;
	public VerReparaciones(Empleado user, Controller controller2) {
		setBackground(new Color(255, 252, 244));
		this.controller = controller2;
		cargarTodasLasVentas();
		btnVerCliente(user);
		buttonLogOut();
		buttonAtras(user);
		btnVerVehiculo(user);
		btnVerMecanico(user);
	}

	private void buttonLogOut() {
		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogOut.setBackground(SystemColor.textHighlight);
		btnLogOut.setForeground(new Color(255, 255, 255));
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
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAtras.setBackground(SystemColor.textHighlight);
		btnAtras.setForeground(new Color(255, 255, 255));
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
		btnDatosDelVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDatosDelVehiculo.setBackground(SystemColor.textHighlight);
		btnDatosDelVehiculo.setForeground(new Color(255, 255, 255));
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
		btnDatosDelVehiculo.setBounds(346, 366, 160, 23);
		add(btnDatosDelVehiculo);
	} 

	private void verDatosDelCoche(String matricula, Empleado user) {
		try {
			TipoDeVehiculo tipoDeVehiculo = controller.averiguarTipoDeVehiculo(matricula);
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

	private void btnVerMecanico(Empleado user) { 
		JButton btnDatosDelVehiculo = new JButton("Ver Datos Mecanico");
		btnDatosDelVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDatosDelVehiculo.setBackground(SystemColor.textHighlight);
		btnDatosDelVehiculo.setForeground(new Color(255, 255, 255));
		btnDatosDelVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = null;
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada != -1) {
					try {
						dni = table.getValueAt(filaSeleccionada, 4).toString();						
						verDatosEmpleado(dni, user);							
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "El mecanico seleccionado ya no es mecanico", "Datos del mecanico", JOptionPane.INFORMATION_MESSAGE);
						// TODO: handle exception
					}
				} 
			}
		});
		btnDatosDelVehiculo.setBounds(662, 366, 181, 23);
		add(btnDatosDelVehiculo);
	} 

	private void verDatosEmpleado(String dni, Empleado user) {
		Mecanico mecanico = new Mecanico();
		mecanico = (Mecanico) mecanico.encontrarMecanicoEnLista(dni, controller.getAllEmpleado());
		JOptionPane.showMessageDialog(null, ""
				+ "Id: " + mecanico.getId() + "\n"
				+ "DNI: " + mecanico.getDni() + "\n"
				+ "Nombre: " +  mecanico.getNombre()+ "\n"
				+ "Apelldio: " + mecanico.getApellido()+ "\n"
				+ "Telefono: " + mecanico.getNumeroTelefono()+ "\n"
				+ "Años en la empresa: " + mecanico.calcularAntiguedad()+ "\n"
				+ "Edad: " + mecanico.calcularEdad()+ "\n"
				+ "Dir: " + mecanico.getDireccion() + "\n"
				+ "Correo: " + mecanico.getEmail() + "\n"
				+ "Salario: " + mecanico.getSalario() + "\n"
				+ "Rango: " + mecanico.getRango()
				, "Datos del mecanico", JOptionPane.INFORMATION_MESSAGE);

	}

	private void btnVerCliente(Empleado user) {
		JButton btnVerDatosDelCliente = new JButton("Datos del Cliente");
		btnVerDatosDelCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerDatosDelCliente.setBackground(SystemColor.textHighlight);
		btnVerDatosDelCliente.setForeground(new Color(255, 255, 255));
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
							+ "Telefono: " + cliente.getTelefono() + "\n"
							+ "Correo: " + cliente.getCorreo() + "\n"
							+ "Direccion: " + cliente.getDir()
							, "Datos del cliente", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnVerDatosDelCliente.setBounds(43, 366, 171, 23);
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

		model.addColumn("Id reparación");
		model.addColumn("Precio");
		model.addColumn("Coste");
		model.addColumn("Fecha");
		model.addColumn("DNI del Mecánico");
		model.addColumn("Matrícula");
		model.addColumn("DNI del Cliente");
		listaDeReparaciones = controller.getAllReparaciones();
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
			fila[3]= reparacion.getFechaFin();
			fila[4]= reparacion.conseguirElDniDelMecanico(controller.getAllEmpleado());
			fila[5] = reparacion.conseguirMatriculaDelCoche(controller.getAllVehiculos());
			fila[6] = reparacion.conseguiDniDelCliente(controller.getAllClienteVehiculos());

			model.addRow(fila); 
		}

	}

}
