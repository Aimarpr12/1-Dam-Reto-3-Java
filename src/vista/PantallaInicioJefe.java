package vista;

import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;



import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import controller.Controller;
import modelo.Empleado;
import modelo.EstadisticasDeVentas;
import modelo.Mecanico;
import modelo.Vendedor;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class PantallaInicioJefe extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private Controller controller;
	private List <Empleado> listaDeEmpleados;
	private JTable table;
	private DefaultTableModel model;
	private JComboBox comboBoxEmpleo;
	private JComboBox comboBoxElementoAModificar;
	private boolean primeraVez = true;
	public PantallaInicioJefe(Empleado user, Controller controller2) {
		setBackground(new Color(255, 252, 244));
		setLayout(null); 
		this.controller = controller2;

		labelNombre(user);

		filtrarDatosTabla(user);

		ponerTodosLosUsersALaLista(); 

		btnVerVentas(user);

		btnVerReparaciones(user);

		buttonLogOut();
		
		buttonVerificarEmpleados(user);
		
		buttonImportarDatos(user);
		
		buttonExportarDatos(user);
	
		btnVerEstadisticas(user);
		
		btnVerVentasOReparacionesDelEmpleado(user);
	}
	/**
	 * Muestra ventas o reparaciones del user seleccionado en la tabla
	 */
	private void btnVerVentasOReparacionesDelEmpleado(Empleado user) {
		JButton btnVerVentasO = new JButton("Ver Ventas o Reparaciones Del Empleado");
		btnVerVentasO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada != -1) {
					String dni = table.getValueAt(filaSeleccionada, 0).toString();
					Empleado empleado = user.getEmpleadoConDni(dni, controller.getAllEmpleado());
					if(empleado instanceof Mecanico) {
						Mecanico mecanico = (Mecanico) empleado;
						app.verReparacionesPorMecanico(user, mecanico);
					}else if(empleado instanceof Vendedor) {
						Vendedor vendedor = (Vendedor) empleado;
						app.verReparacionesPorMecanico(user, vendedor);
					}
				}	
			}
		});
		btnVerVentasO.setForeground(Color.WHITE);
		btnVerVentasO.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerVentasO.setBackground(SystemColor.textHighlight);
		btnVerVentasO.setBounds(62, 481, 407, 27);
		add(btnVerVentasO);
	}
	/**
	 * Muestra los mejores vendedores del mes pasado
	 */
	private void btnVerEstadisticas(Empleado user) {
		JButton btnVerEstadisticas = new JButton("Ver Estadistica");
		btnVerEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<EstadisticasDeVentas> listDeEstadisticasDeVentas = controller.getEstadisticasTop2Vendedores();
				if(listDeEstadisticasDeVentas.size() != 0) {
					String estadisticas = "";
					for(EstadisticasDeVentas estadistica : listDeEstadisticasDeVentas) {
						estadisticas += "Nombre del empleado: " + estadistica.getNombreCompleto() +  " Comision total del empledo: " + estadistica.getComisionTotal() + "\n" ;
					}
					JOptionPane.showMessageDialog(null,	estadisticas, "Ver estadisticas", JOptionPane.INFORMATION_MESSAGE);
					
				}else {
					JOptionPane.showMessageDialog(null,	"no hay estadisticas que mostrar", "Ver estadisticas", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVerEstadisticas.setForeground(Color.WHITE);
		btnVerEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerEstadisticas.setBackground(SystemColor.textHighlight);
		btnVerEstadisticas.setBounds(672, 484, 241, 22);
		add(btnVerEstadisticas);
		
	}
	/**
	 * Exporta datos de memoria a xml
	 */
	private void buttonExportarDatos(Empleado user) {		
		JButton btnExportarDatos = new JButton("Exportar Datos");
		btnExportarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.exportarDatos();
			}
		});
		btnExportarDatos.setForeground(Color.WHITE);
		btnExportarDatos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExportarDatos.setBackground(SystemColor.textHighlight);
		btnExportarDatos.setBounds(672, 214, 241, 23);
		add(btnExportarDatos);
		
		
	}
	/**
	 * Importa datos de xml a memoria y actualiza bbdd
	 */
	private void buttonImportarDatos(Empleado user) {
		JButton btnImportarDatos = new JButton("Importar Datos");
		btnImportarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.importarDatos();
			}
		});
		btnImportarDatos.setForeground(Color.WHITE);
		btnImportarDatos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnImportarDatos.setBackground(SystemColor.textHighlight);
		btnImportarDatos.setBounds(672, 270, 241, 23);
		add(btnImportarDatos);
	}
	/**
	 * Verifica empleados que no tienen acceso a login
	 */
	private void buttonVerificarEmpleados(Empleado user) {
		JButton btnVerificarEmpleados = new JButton("Verificar Empleados");
		btnVerificarEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.mostrarVerificarUser(user);
			}
		});
		btnVerificarEmpleados.setForeground(Color.WHITE);
		btnVerificarEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerificarEmpleados.setBackground(SystemColor.textHighlight);
		btnVerificarEmpleados.setBounds(672, 428, 241, 23);
		add(btnVerificarEmpleados);
	}

	private void buttonLogOut() {
		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.setBackground(SystemColor.textHighlight);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.logIn();
			}
		});
		btnLogOut.setBounds(706, 97, 89, 23);
		add(btnLogOut);		
	}

	private void btnVerReparaciones(Empleado user) {
		JButton btnVerTodasLasReparaciones = new JButton("Ver Reparaciones");
		btnVerTodasLasReparaciones.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerTodasLasReparaciones.setForeground(new Color(255, 255, 255));
		btnVerTodasLasReparaciones.setBackground(SystemColor.textHighlight);
		btnVerTodasLasReparaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.mostrarVerReparaciones(user);
			}
		});
		btnVerTodasLasReparaciones.setBounds(672, 324, 241, 23);
		add(btnVerTodasLasReparaciones);
	}

	private void btnVerVentas(Empleado user) {
		JButton btnVerTodasLasVentas = new JButton("Ver Ventas");
		btnVerTodasLasVentas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerTodasLasVentas.setForeground(new Color(255, 255, 255));
		btnVerTodasLasVentas.setBackground(SystemColor.textHighlight);
		btnVerTodasLasVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
				app.mostrarVerVentas(user);
			}
		});
		btnVerTodasLasVentas.setBounds(672, 373, 241, 23);
		add(btnVerTodasLasVentas);

	}


	private void ponerTodosLosUsersALaLista() {
		if (primeraVez) {
			primeraVez = false;
			
			cargarLosDatosNecesarios();

			listaDeEmpleados =  controller.getAllEmpleado();
			mostrarTablaDeUsers();
		}
	}

	private void cargarLosDatosNecesarios() {
		controller.cargarListaDeEmpleados();
		controller.cargarListaDeReparaciones();
		controller.cargarListaDeVentas(); 
		controller.cargarListaDeClientes();
		controller.cargarListaDeVehiculos();
		controller.cargarListaDeClienteVehiculos();
		controller.cargarListaDeEstadisticasDeVentas();
		
	}

	private void mostrarTablaDeUsers() {

	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(62, 250, 546, 190);
	    add(scrollPane);

	    table = new JTable();
	    table.setDefaultEditor(Object.class, null);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    model = new DefaultTableModel();
	    table.setModel(model);

	    model.addColumn("DNI");
	    model.addColumn("Nombre");
	    model.addColumn("Apellido");
	    model.addColumn("Salario");

	    anadirempleadosALaLista();

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
	    table.setVisible(true);
	}


	private void anadirempleadosALaLista() {
		for (Empleado empleado : listaDeEmpleados) {
			Object[] fila = new Object [4];
			fila[0]= empleado.getDni();
			fila[1]= empleado.getNombre();
			fila[2]= empleado.getApellido();
			fila[3] = empleado.getSalario();

			model.addRow(fila); 
		}
	}

	private void filtrarDatosTabla(Empleado user) {
		filtrarPorTipoDeEpleado(user);
		filtrarPorSueldo();
		buttonAplicar(user);

	}

	private void buttonAplicar(Empleado user) {
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAplicar.setForeground(new Color(255, 255, 255));
		btnAplicar.setBackground(SystemColor.textHighlight);
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 

				String empleoSelecciona = (String) comboBoxEmpleo.getSelectedItem();
				if (table != null) {
					model.setNumRows(0); 
				}
				if (empleoSelecciona == "vendedor") {
					listaDeEmpleados = user.getAllEmpeladosVendedor(controller.getAllEmpleado());
					anadirempleadosALaLista();
				}else if(empleoSelecciona == "mecanico"){
					listaDeEmpleados = user.getAllEmpeladosMecanico(controller.getAllEmpleado());
					anadirempleadosALaLista();
				}else {
					listaDeEmpleados =  controller.getAllEmpleado();
					anadirempleadosALaLista();
				}
			}
		});
		btnAplicar.setBounds(379, 195, 89, 23);
		add(btnAplicar);
	}

	private void filtrarPorSueldo() {
		labelSueldo();
	}

	private void labelSueldo() {
	}

	private void filtrarPorTipoDeEpleado(Empleado user) {
		labelDepartamento();
		JComboboxEmpleo();
	}

	private void JComboboxEmpleo() {
		comboBoxEmpleo = new JComboBox<>();
		comboBoxEmpleo.setBackground(new Color(255, 255, 255));
		comboBoxEmpleo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxEmpleo.setBounds(62, 195, 307, 22);
		comboBoxEmpleo.addItem("seleciona un empleo");
		comboBoxEmpleo.addItem("vendedor");
		comboBoxEmpleo.addItem("mecanico");
		add(comboBoxEmpleo);
	}

	private void labelDepartamento() {		
		JLabel labelDepartameto = new JLabel("Departamento");
		labelDepartameto.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelDepartameto.setForeground(new Color(255, 128, 0));
		labelDepartameto.setBounds(62, 170, 130, 14);
		add(labelDepartameto);

	}

	private void labelNombre(Empleado user) {
		JLabel labelNombre = new JLabel(user.getNombre());
		labelNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelNombre.setForeground(new Color(255, 128, 0));
		labelNombre.setBounds(494, 101, 176, 14);
		add(labelNombre);
	}
}

