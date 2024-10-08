/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.tablemodels;

import beans.Cliente;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author josec
 */
public class ClientesTableModel extends AbstractTableModel{
    
    private List<Cliente> listCliente;
    private String[] columnas = {"Nombre", "Apellidos", "ChekIn", "CheckOut", "NumeroHabitacion", "Tipo", "Dni", "Nacionalidad", "Fecha Nacimiento"};
    
    public ClientesTableModel(List<Cliente> listClientes) {
        this.listCliente = listClientes;
    }
    
    @Override
    public int getRowCount() {
        return listCliente.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch (i1) {
        
            case 0:
                return listCliente.get(i).getNombre();
            case 1:
                return listCliente.get(i).getApellidos();
            case 2:
                return listCliente.get(i).getChekin();
            case 3:
                return listCliente.get(i).getChekout();
            case 4:
                return listCliente.get(i).getNumeroHabitacion();
            case 5:
                return listCliente.get(i).getTipo();
            case 6:
                return listCliente.get(i).getDni();
            case 7:
                return listCliente.get(i).getNacionalidad();
            case 8:
                return listCliente.get(i).getFechaNacimiento();
                
        }
        
        return null;
    }
    
    @Override
    public String getColumnName(int i) {
        return columnas [i];
    }
    
}
