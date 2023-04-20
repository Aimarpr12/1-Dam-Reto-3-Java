package Pantallas;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Bienvenido extends JPanel {

	/**
	 * Create the panel.
	 */
	public Bienvenido() {
		 setLayout(null);
		    addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            new SwingWorker<Void, Void>() {
		                @Override
		                protected Void doInBackground() throws Exception {
		                    ImageIcon icono = new ImageIcon(Bienvenido.class.getResource("/PantallasImgLoading/Loading.gif"));
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



		protected void eliminargift(JLabel labelGif) {
			remove(labelGif);
			
		}


		private JLabel labelFotoLogo1(String foto) {
		    ImageIcon icono = new ImageIcon(Bienvenido.class.getResource(foto));
		    JLabel labelGif = new JLabel(icono);
		    int x = (getWidth() - icono.getIconWidth()) / 2;
		    int y = (getHeight() - icono.getIconHeight()) / 2;
		    labelGif.setBounds(x, y, icono.getIconWidth(), icono.getIconHeight());
		    add(labelGif);
		    revalidate();
		    repaint();
		    return labelGif;
		    
		}
	

}
