/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividadvideojuego.interfaz;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author josec
 */
public class PantallaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form PantallaPrincipal
     */
    public PantallaPrincipal() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/actividadvideojuego/imgs/logo.png")).getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelImagennFondo = new jpanelimagenn.JPanelImagenn();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelEdad = new javax.swing.JLabel();
        jLabelNick = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jTextFieldNick = new javax.swing.JTextField();
        jTextFieldEdad = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jButtonEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelImagennFondo.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Users/josec/Documents/NetBeansProjects/TEMA-3/ActividadVideojuego/src/actividadvideojuego/imgs/FondoRegistro.jpg"), 1.0f));

        jLabelTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/actividadvideojuego/imgs/TituloJuego.png"))); // NOI18N

        jLabelEdad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/actividadvideojuego/imgs/edad.png"))); // NOI18N

        jLabelNick.setIcon(new javax.swing.ImageIcon(getClass().getResource("/actividadvideojuego/imgs/Nick.png"))); // NOI18N

        jLabelNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/actividadvideojuego/imgs/Nombre.png"))); // NOI18N

        jLabelPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/actividadvideojuego/imgs/Password.png"))); // NOI18N

        jTextFieldNick.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldNick.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jTextFieldNick.setForeground(new java.awt.Color(255, 128, 0));

        jTextFieldEdad.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldEdad.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jTextFieldEdad.setForeground(new java.awt.Color(255, 128, 0));

        jTextFieldNombre.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldNombre.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jTextFieldNombre.setForeground(new java.awt.Color(255, 128, 0));

        jPasswordFieldPassword.setBackground(new java.awt.Color(255, 255, 255));
        jPasswordFieldPassword.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jPasswordFieldPassword.setForeground(new java.awt.Color(255, 128, 0));

        jButtonEnviar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonEnviar.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        jButtonEnviar.setForeground(new java.awt.Color(255, 128, 0));
        jButtonEnviar.setText("Enviar");
        jButtonEnviar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelImagennFondoLayout = new javax.swing.GroupLayout(jPanelImagennFondo);
        jPanelImagennFondo.setLayout(jPanelImagennFondoLayout);
        jPanelImagennFondoLayout.setHorizontalGroup(
            jPanelImagennFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImagennFondoLayout.createSequentialGroup()
                .addGroup(jPanelImagennFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelImagennFondoLayout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(jLabelTitulo))
                    .addGroup(jPanelImagennFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanelImagennFondoLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jButtonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelImagennFondoLayout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addGroup(jPanelImagennFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelNombre)
                                .addComponent(jLabelPassword)
                                .addComponent(jLabelNick)
                                .addComponent(jLabelEdad))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanelImagennFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldNick)
                                .addComponent(jTextFieldEdad)
                                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(258, Short.MAX_VALUE))
        );
        jPanelImagennFondoLayout.setVerticalGroup(
            jPanelImagennFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImagennFondoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabelTitulo)
                .addGap(33, 33, 33)
                .addGroup(jPanelImagennFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelNick)
                    .addComponent(jTextFieldNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelImagennFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelImagennFondoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabelNombre))
                    .addGroup(jPanelImagennFondoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelImagennFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelEdad)
                    .addComponent(jTextFieldEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelImagennFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelImagennFondoLayout.createSequentialGroup()
                        .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(jButtonEnviar)
                        .addGap(44, 44, 44))
                    .addGroup(jPanelImagennFondoLayout.createSequentialGroup()
                        .addComponent(jLabelPassword)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImagennFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImagennFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed

        String edad = jTextFieldEdad.getText();
        String nick = jTextFieldNick.getText();
        String nombre = jTextFieldNombre.getText();
        char[] passwordArray = jPasswordFieldPassword.getPassword();
        String password = new String(passwordArray);

        if (camposVacios(edad, nick, nombre, password)) {
            JOptionPane.showMessageDialog(this,
                    "Los campos están vacíos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {

            if (!soloNumeros(edad)) {
                JOptionPane.showMessageDialog(this,
                        "No se pueden poner letras en la edad",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {

                if (!edadCorrecta(Integer.parseInt(edad))) {
                    JOptionPane.showMessageDialog(this,
                            "Es un juego para mayores de 18 años",
                            "PG-18",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    
                    dispose();
                    SeleccionPersonajes seleccionPersonajes = new SeleccionPersonajes();
                    seleccionPersonajes.setVisible(true);
                    
                }

            }

        }

    }//GEN-LAST:event_jButtonEnviarActionPerformed

    public static boolean camposVacios(String edad, String nick, String nombre, String password) {

        boolean vacio = false;

        if (edad.isEmpty() || nick.isEmpty() || nombre.isEmpty() || password.isEmpty()) {
            vacio = true;
        }

        return vacio;

    }

    public static boolean soloNumeros(String edad) {

        // Comprobar que solo sean números
        return edad.matches("^[0-9]+$");

    }

    public static boolean edadCorrecta(int edad) {

        // Inicializar una variable booleana
        boolean correcto = false;

        // Comprobar que sea mayor de edad
        if (edad >= 18) {
            correcto = true;
        }

        // Devolver la expresión booleana
        return correcto;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JLabel jLabelEdad;
    private javax.swing.JLabel jLabelNick;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelTitulo;
    private jpanelimagenn.JPanelImagenn jPanelImagennFondo;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JTextField jTextFieldEdad;
    private javax.swing.JTextField jTextFieldNick;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}