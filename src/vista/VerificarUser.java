package vista;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import modelo.Coche;
import modelo.Empleado;
import modelo.Mecanico;
import modelo.Moto;
import modelo.Motor;
import modelo.TipoDeEmpleado;
import modelo.TipoDeVehiculo;
import modelo.Vehiculo;
import modelo.Vendedor;


public class VerificarUser extends JPanel {

	/**
	 * Create the panel.
	 */
	private JTable table;
	private DefaultTableModel model;
	private Controller controller;
	private JScrollPane scrollPane;
	private Empleado empleado;
    public VerificarUser(Empleado user, Controller controller) {
    	this.controller = controller;
        setLayout(null);

		mostrarEmpleadosPorVerificar();
		
		buttonAtras(user);
		
		buttonVerificar();
		
		buttonExit();
		
		labelNombre(user);
		
    }
    
    
    private void labelNombre(Empleado user) {
    	JLabel labelNombre = new JLabel(user.getNombre());
		labelNombre.setBounds(357, 24, 109, 14);
		add(labelNombre);
	}


	private void buttonExit() {
    	JButton buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttonExit.setBounds(377, 324, 89, 23);
		add(buttonExit);
	}


	private void buttonVerificar() {
    	JButton buttonVerificar = new JButton("Verificar");
		buttonVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				String dni = (String) table.getValueAt(selectedRow, 0);
				if (table != null) {
					model.setNumRows(0); 
				}
				boolean seHaAnadido = anadirTipoDeEmpleado(dni);
				controller.verificarCuenteSeleccionada(dni);
				if(seHaAnadido) {
					JOptionPane.showMessageDialog(
							null,
							"Se ha añadido correctamente",
							"Añadido correcto",
							JOptionPane.ERROR_MESSAGE);	
				}else {
					JOptionPane.showMessageDialog(
						    null,
						    "No se ha añadido.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				cargarDatosTabla(controller.getAllEmpleadosNoVerificados());
			}
		});
		buttonVerificar.setBounds(100, 324, 89, 23);
		add(buttonVerificar);
		
	}


	private void buttonAtras(Empleado user) {
    	JButton buttonVolverARH = new JButton("Volver Atras");
		buttonVolverARH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				App app = (App) SwingUtilities.getRoot(component);
                app.esJefe(user);
			}
		});
		buttonVolverARH.setBounds(215, 324, 137, 23);
		add(buttonVolverARH);
	}


	private void mostrarEmpleadosPorVerificar() {
    	List<Empleado> listDeEmpleadosPorVerificar = controller.getAllEmpleadosNoVerificados();
    	scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 366, 184);
		add(scrollPane);
		
		
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultTableModel();
		table.setModel(model);
		 
		model.addColumn("DNI");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		cargarDatosTabla(listDeEmpleadosPorVerificar);
		
		
	}


	private void cargarDatosTabla(List<Empleado> listDeEmpleadosPorVerificar) {
		for (Empleado empleado : listDeEmpleadosPorVerificar) {
			Object[] fila = new Object [3];
			fila[0]= empleado.getDni();
			fila[1]= empleado.getNombre();
			fila[2]= empleado.getApellido();
			 
			model.addRow(fila);
		}
		scrollPane.setViewportView(table);
		table.setVisible(true);
		
	}
	
	protected boolean anadirTipoDeEmpleado(String dni) {
		String[] options = {"Vendedor", "Mecanico"};
		int selectedOption = JOptionPane.showOptionDialog(
		    null,
		    "¿Que empleado es?",
		    "Selecciona una opción",
		    JOptionPane.DEFAULT_OPTION,
		    JOptionPane.PLAIN_MESSAGE,
		    null,
		    options,
		    options[0]);

		empleado = new Empleado();
		empleado = empleado.getEmpleadoConDni(dni, controller.getAllEmpleado());
		if (selectedOption == 0) {
			empleado.setTipoDeEmpleado(TipoDeEmpleado.vendedor);
			System.out.println(empleado.toString());
			boolean seHaInsertado = insertarComision(empleado);
		    if(seHaInsertado) {
				return true;
			}else {
				return false;
			}
		} else if (selectedOption == 1) {
			empleado.setTipoDeEmpleado(TipoDeEmpleado.mecanico);
			System.out.println(empleado.toString());
			boolean seHaInsertado = insertarRango(empleado);
			if(seHaInsertado) {
				return true;
			}else {
				return false;
			}
		} else {
			return false;
		}

		
	}
	private boolean insertarRango(Empleado emple) {
		String[] options = {"Jefe de taller", "Mecánico senior", "Mecánico junior"};
		int selectedOption = JOptionPane.showOptionDialog(
		    null,
		    "¿Qué tipo de rango es?",
		    "Selecciona una opción",
		    JOptionPane.DEFAULT_OPTION,
		    JOptionPane.PLAIN_MESSAGE,
		    null,
		    options,
		    options[0]);

		Mecanico mecanico = new Mecanico();
		mecanico.addDatosMecanico(emple);
		if (selectedOption == 0) {
		    mecanico.setRango("Jefe de taller");
		} else if (selectedOption == 1) {
			mecanico.setRango("Mecánico senior");
		} else if (selectedOption == 2) {
			mecanico.setRango("Mecánico junior");
		} else {
		    return false;
		}
		boolean seHaAnadido = setRango(mecanico);
		
		if(seHaAnadido) {
			return true;
		}
		return false;
		
	}
	
	public boolean setRango(Mecanico mecanico) {
		boolean seHaInsertado = controller.updateRango(mecanico);
		if(seHaInsertado) {
			return controller.updateRangoList(mecanico);
		}else {
			return false;
		}
	}
	private boolean insertarComision(Empleado empleado) {
		Vendedor vendedor = new Vendedor();
		vendedor.addDatosVendedor(empleado);
		Double comision = 0.0;
		boolean comisionValida = false;

		while (!comisionValida) {
		    String input = JOptionPane.showInputDialog(
		        null,
		        "La comision del vendedor:",
		        "Comision",
		        JOptionPane.PLAIN_MESSAGE);

		    try {
		    	comision = Double.parseDouble(input);
		        comisionValida = true;
		        vendedor.setComision(comision);
		        boolean seHaAnadido = controller.setComisionFuncion(vendedor);
		        if(seHaAnadido) {
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

}
