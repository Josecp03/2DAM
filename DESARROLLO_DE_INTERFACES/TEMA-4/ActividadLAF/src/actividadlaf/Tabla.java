/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividadlaf;

import dto.Cliente;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import logica.LogicaNegocio;

/**
 *
 * @author josec
 */
public class Tabla extends javax.swing.JFrame {

    /**
     * Creates new form Tabla
     */
    public Tabla() {
        initComponents();
        refrescarTabla();
        // Incapacitar al usuario para redimensionar el tamaño de la interfaz
        setResizable(false);

        // Centrar la interfaz en medio de la pantalla
        setLocationRelativeTo(null);

        //  Establecer el icono de la interfaz
        this.setIconImage(new ImageIcon(getClass().getResource("/imgs/main_icon.png")).getImage());

        jTableClientes.setDefaultRenderer(Object.class, new CentrarColumnas());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClientes;
    // End of variables declaration//GEN-END:variables

    private void refrescarTabla() {

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(new String[]{"Nombre", "Apellidos", "NIF", "Correo", "Nacionalidad", "Recibir Noticias", "Preferencias"});

        List<Cliente> listaClientes = LogicaNegocio.getListaClientes();
        for (Cliente cliente : listaClientes) {
            dtm.addRow(cliente.toArrayString());
        }

        jTableClientes.setModel(dtm);

        // Aplicar el renderizador centrado a todas las columnas
        for (int i = 0; i < dtm.getColumnCount(); i++) {
            jTableClientes.getColumnModel().getColumn(i).setCellRenderer(new CentrarColumnas());
        }
    }
}