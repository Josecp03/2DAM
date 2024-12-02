/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author josec
 */
public class CentrarColumnas extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, 
            Object value, 
            boolean isSelected, 
            boolean hasFocus, 
            int row, 
            int column) {
        
        // Llama al método padre para obtener el componente de celda
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Centra el texto en todas las columnas
        setHorizontalAlignment(SwingConstants.CENTER);

        return cell;
        
    }

}
