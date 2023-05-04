package pantallas;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class Bienvenido extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Bienvenido() {
		setBackground(Color.WHITE);
		setLayout(null);
		
	
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 new SwingWorker<Void, Void>() {
		                @Override
		                protected Void doInBackground() throws Exception {
		                    ImageIcon icono = new ImageIcon(Bienvenido.class.getResource("/PantallasImg/Loading.gif"));
		                    JLabel labelGif = new JLabel(icono);
		                    int x = (getWidth() - icono.getIconWidth()) / 2;
		                    int y = (getHeight() - icono.getIconHeight()) / 2;
		                    labelGif.setBounds(x, y, icono.getIconWidth(), icono.getIconHeight());
		                    add(labelGif);
		                    revalidate();
		                    repaint();
		                    try {
		                    	Thread.sleep(3000);
							} catch (Exception e2) {
							}
		                    return null;
		                }

		                @Override
		                protected void done() {
		                    Component component = (Component) e.getSource();
		                    App app = (App) SwingUtilities.getRoot(component);
		                    app.logIn();
		                }
		            }.execute();
		        
			}
		});
	    
	}

	
}
