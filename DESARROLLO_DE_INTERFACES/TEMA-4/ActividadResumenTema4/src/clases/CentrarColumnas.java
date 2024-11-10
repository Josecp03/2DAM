package actividadlaf;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CentrarColumnas extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Llama al método padre para obtener el componente de celda
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Centra el texto en todas las columnas
        setHorizontalAlignment(SwingConstants.CENTER);
        
        return cell;
    }
}
