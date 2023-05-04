package pantallas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import clases.Empleado;
import controller.Controller;


public class VerificarUser extends JPanel {

	/**
	 * Create the panel.
	 */
	private JTable table;
	private DefaultTableModel model;
	private Controller controller;
	private JScrollPane scrollPane;
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
				controller.verificarCuenteSeleccionada((String) table.getValueAt(selectedRow, 0));
				if (table != null) {
					model.setNumRows(0); 
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

}
