package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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
import modelo.Vendedor;
import modelo.Venta;

public class VerVentasDeVendedor extends JPanel {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JTable table;
	private DefaultTableModel model;
	public VerVentasDeVendedor(Empleado user, Vendedor vendedor, Controller controller2) {
		setBackground(new Color(255, 252, 244));
		this.controller = controller2;
		cargarTodasLasVentas(vendedor);
		btnVerCliente(user);
		buttonLogOut();
		buttonAtras(user);
		btnVerVehiculo(user);
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
	/**
	 * Muestra pop up con todos los datos del vehiculo seleccionado en la tabla
	 */
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
					matricula = table.getValueAt(filaSeleccionada, 4).toString();
					verDatosDelCoche(matricula, user);	
				} 
			}
		});
		btnDatosDelVehiculo.setBounds(517, 366, 255, 23);
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
				+ "Año: " + moto.getAno() + "\n"
				+ "Cilindrada: " + moto.getCilindrada()
				, "Datos de la moto", JOptionPane.INFORMATION_MESSAGE);
	
	}

	private void mostrarDatatosCoches(Coche coche) {
		JOptionPane.showMessageDialog(null, ""
				+ "Bastidor: " + coche.getBastidor() + "\n"
				+ "Matricula: " + coche.getMatricula() + "\n"
				+ "Marca: " + coche.getMarca() + "\n"
				+ "Modelo: " + coche.getModelo() + "\n"
				+ "Año: " + coche.getAno() + "\n"
				+ "Tipo de motor: " + coche.getMotor()
				, "Datos del coche", JOptionPane.INFORMATION_MESSAGE);	
	}
	/**
	 * Muestra pop up con todos los datos del cliente seleccionado en la tabla
	 */
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
					dni = table.getValueAt(filaSeleccionada,5).toString();
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
		btnVerDatosDelCliente.setBounds(144, 366, 255, 23);
		add(btnVerDatosDelCliente);		
	}

	private void cargarTodasLasVentas(Vendedor vendedor) {		
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
		model.addColumn("Matrícula");
		model.addColumn("DNI del Cliente");
		actualizarVentas(vendedor);

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

	private void actualizarVentas(Vendedor vendedor) {
		System.out.println("a");
		for (Venta venta: vendedor.getListaDeVentas()) {

			Object[] fila = new Object [6];
			fila[0]= venta.getIdVenta();
			fila[1]= venta.getPrecio();
			Date fecha = venta.getFecha();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			fila[2]= df;
			fila[3]= venta.conseguirElDniDelVendedor(controller.getAllEmpleado());
			fila[4] = venta.conseguirMatriculaDelCoche(controller.getAllVehiculos());
			fila[5] = venta.conseguiDniDelCliente(controller.getAllClienteVehiculos());

			model.addRow(fila); 
		}

	}

}
