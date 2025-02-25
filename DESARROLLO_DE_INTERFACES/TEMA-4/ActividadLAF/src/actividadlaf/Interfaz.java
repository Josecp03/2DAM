/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividadlaf;

import dto.Cliente;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import logica.LogicaNegocio;

/**
 *
 * @author josec
 */
public class Interfaz extends javax.swing.JFrame {

    private Tabla tabla;

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {

        // Inicializar los componentes
        initComponents();

        // Establecer un tamaño determinado a la interfaz
        setSize(625, 512);

        // Incapacitar al usuario para redimensionar el tamaño de la interfaz
        setResizable(false);

        // Centrar la interfaz en medio de la pantalla
        setLocationRelativeTo(null);

        //  Establecer el icono de la interfaz
        this.setIconImage(new ImageIcon(getClass().getResource("/imgs/main_icon.png")).getImage());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanelImagenn3 = new jpanelimagenn.JPanelImagenn();
        jPanelImagenn4 = new jpanelimagenn.JPanelImagenn();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jRadioButtonNo = new javax.swing.JRadioButton();
        jRadioButtonSi = new javax.swing.JRadioButton();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldApellidos = new javax.swing.JTextField();
        jTextFieldNif = new javax.swing.JTextField();
        jTextFieldCorreo = new javax.swing.JTextField();
        jComboBoxNacionalidad = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jCheckBoxHistorica = new javax.swing.JCheckBox();
        jCheckBoxAventuras = new javax.swing.JCheckBox();
        jCheckBoxNegra = new javax.swing.JCheckBox();
        jCheckBoxJuvenil = new javax.swing.JCheckBox();
        jCheckBoxFantastica = new javax.swing.JCheckBox();
        jCheckBoxCienciaFiccion = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelImagenn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelImagenn3.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Users/josec/Documents/NetBeansProjects/TEMA-4/ActividadLAF/src/imgs/nuevo.png"), 1.0f));
        jPanelImagenn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelImagenn3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelImagenn3Layout = new javax.swing.GroupLayout(jPanelImagenn3);
        jPanelImagenn3.setLayout(jPanelImagenn3Layout);
        jPanelImagenn3Layout.setHorizontalGroup(
            jPanelImagenn3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanelImagenn3Layout.setVerticalGroup(
            jPanelImagenn3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2.add(jPanelImagenn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jPanelImagenn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelImagenn4.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Users/josec/Documents/NetBeansProjects/TEMA-4/ActividadLAF/src/imgs/guardar.png"), 1.0f));
        jPanelImagenn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelImagenn4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelImagenn4Layout = new javax.swing.GroupLayout(jPanelImagenn4);
        jPanelImagenn4.setLayout(jPanelImagenn4Layout);
        jPanelImagenn4Layout.setHorizontalGroup(
            jPanelImagenn4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanelImagenn4Layout.setVerticalGroup(
            jPanelImagenn4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2.add(jPanelImagenn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 40));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/foto.png"))); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, 410));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Socio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Nombre:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setText("Apellidos:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel4.setText("NIF:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel5.setText("E-mail:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel6.setText("Nacionalidad:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel7.setText("Desea recibir las últimas noticias en su correo:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        buttonGroup1.add(jRadioButtonNo);
        jRadioButtonNo.setText("No");
        jPanel3.add(jRadioButtonNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, -1, -1));

        buttonGroup1.add(jRadioButtonSi);
        jRadioButtonSi.setText("Sí");
        jPanel3.add(jRadioButtonSi, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, -1, -1));
        jPanel3.add(jTextFieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 280, -1));
        jPanel3.add(jTextFieldApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 280, -1));
        jPanel3.add(jTextFieldNif, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 280, -1));
        jPanel3.add(jTextFieldCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 280, -1));

        jComboBoxNacionalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "España", "Estados Unidos", "Bélgica", "Brasil" }));
        jPanel3.add(jComboBoxNacionalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 390, 280));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Preferencias", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCheckBoxHistorica.setText("Histórica");
        jPanel4.add(jCheckBoxHistorica, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        jCheckBoxAventuras.setText("Aventuras");
        jPanel4.add(jCheckBoxAventuras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jCheckBoxNegra.setText("Negra");
        jPanel4.add(jCheckBoxNegra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jCheckBoxJuvenil.setText("Juvenil");
        jPanel4.add(jCheckBoxJuvenil, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        jCheckBoxFantastica.setText("Fantástica");
        jPanel4.add(jCheckBoxFantastica, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        jCheckBoxCienciaFiccion.setText("Ciencia Ficción");
        jPanel4.add(jCheckBoxCienciaFiccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 390, 100));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 630, -1));

        jMenu1.setText("Archivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelImagenn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagenn3MouseClicked

        // Establecer los nombres
        String nombre = jTextFieldNombre.getText();
        String apellidos = jTextFieldApellidos.getText();
        String nif = jTextFieldNif.getText();
        String nacionalidad = (String) jComboBoxNacionalidad.getSelectedItem();
        String recibirNoticias = "";
        String preferencias = "";
        String correo = jTextFieldCorreo.getText();

        if (jRadioButtonSi.isSelected()) {
            recibirNoticias = "Sí"; 
        } else if (jRadioButtonNo.isSelected()) {
            recibirNoticias = "No"; 
        } 

        if (!jRadioButtonSi.isSelected() && !jRadioButtonNo.isSelected()) {
            JOptionPane.showMessageDialog(this, "Indica si quiere o no recibir noticias", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (jCheckBoxAventuras.isSelected()) {
            preferencias += "Aventuras ";
        }

        if (jCheckBoxNegra.isSelected()) {
            preferencias += "Negra ";
        }

        if (jCheckBoxFantastica.isSelected()) {
            preferencias += "Fantástica ";
        }

        if (jCheckBoxHistorica.isSelected()) {
            preferencias += "Historica ";
        }

        if (jCheckBoxCienciaFiccion.isSelected()) {
            preferencias += "Ciencia Ficción ";
        }

        if (jCheckBoxJuvenil.isSelected()) {
            preferencias += "Juvenil ";
        }

        if (esPalabra(nombre)) {

            if (esPalabra(apellidos)) {

                if (nifCorrecto(nif)) {

                    Cliente cliente = new Cliente(nombre, apellidos, nif, correo, nacionalidad, recibirNoticias, preferencias);
                    LogicaNegocio.anadirCliente(cliente);
                    JOptionPane.showMessageDialog(this, "Usuario registrado con éxito", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Los apellidos tienen que ser una palabra", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {

            JOptionPane.showMessageDialog(this, "El nombre tiene que ser una palabra", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_jPanelImagenn3MouseClicked

    private void jPanelImagenn4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagenn4MouseClicked
        dispose();
        Tabla tabla = new Tabla();
        tabla.setVisible(true);
    }//GEN-LAST:event_jPanelImagenn4MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox jCheckBoxAventuras;
    private javax.swing.JCheckBox jCheckBoxCienciaFiccion;
    private javax.swing.JCheckBox jCheckBoxFantastica;
    private javax.swing.JCheckBox jCheckBoxHistorica;
    private javax.swing.JCheckBox jCheckBoxJuvenil;
    private javax.swing.JCheckBox jCheckBoxNegra;
    private javax.swing.JComboBox<String> jComboBoxNacionalidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private jpanelimagenn.JPanelImagenn jPanelImagenn3;
    private jpanelimagenn.JPanelImagenn jPanelImagenn4;
    private javax.swing.JRadioButton jRadioButtonNo;
    private javax.swing.JRadioButton jRadioButtonSi;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextFieldApellidos;
    private javax.swing.JTextField jTextFieldCorreo;
    private javax.swing.JTextField jTextFieldNif;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables

    private boolean esPalabra(String texto) {
        return texto.matches("\\b[\\wáéíóúñüÁÉÍÓÚÑÜ]+\\b");
    }

    private boolean nifCorrecto(String nif) {
        return nif.matches("^[0-9]{8}[A-Za-z]$");
    }

}
