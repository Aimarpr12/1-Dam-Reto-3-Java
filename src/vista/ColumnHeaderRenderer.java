package vista;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColumnHeaderRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setHorizontalAlignment(JLabel.CENTER); // Alinea el texto en el centro
        label.setBorder(javax.swing.BorderFactory.createEtchedBorder()); // Agrega un borde a los encabezados de las columnas
        return label;
    }
}
