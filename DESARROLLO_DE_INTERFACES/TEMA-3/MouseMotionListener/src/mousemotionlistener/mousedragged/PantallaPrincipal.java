/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousemotionlistener.mousedragged;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 *
 * @author josec
 */
public class PantallaPrincipal extends javax.swing.JFrame {

    // Atributos
    Color colorPizarra = new Color(0x2f4f4f); 
    Color c = Color.BLACK;
    Graphics g;
    int w = 7;
    int h = 7;

    /**
     * Creates new form PantallaPrincipal
     */
    public PantallaPrincipal() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/mousemotionlistener/imgs/LOGO.png")).getImage());
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelPizarra = new javax.swing.JPanel();
        jButtonPincel = new javax.swing.JButton();
        jButtonBorrarPizarra = new javax.swing.JButton();
        jButtonBorrador = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(28, 28, 28));

        jLabel1.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DIBUJILLO 2.0");

        jPanelPizarra.setBackground(new java.awt.Color(47, 79, 79));
        jPanelPizarra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(108, 59, 42), 10));
        jPanelPizarra.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jPanelPizarra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanelPizarraMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout jPanelPizarraLayout = new javax.swing.GroupLayout(jPanelPizarra);
        jPanelPizarra.setLayout(jPanelPizarraLayout);
        jPanelPizarraLayout.setHorizontalGroup(
            jPanelPizarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 454, Short.MAX_VALUE)
        );
        jPanelPizarraLayout.setVerticalGroup(
            jPanelPizarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );

        jButtonPincel.setBackground(new java.awt.Color(108, 59, 42));
        jButtonPincel.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        jButtonPincel.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPincel.setText("PINCEL");
        jButtonPincel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonPincel.setFocusable(false);
        jButtonPincel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPincelActionPerformed(evt);
            }
        });

        jButtonBorrarPizarra.setBackground(new java.awt.Color(108, 59, 42));
        jButtonBorrarPizarra.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        jButtonBorrarPizarra.setForeground(new java.awt.Color(255, 255, 255));
        jButtonBorrarPizarra.setText("BORRAR PIZARRA");
        jButtonBorrarPizarra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonBorrarPizarra.setFocusable(false);
        jButtonBorrarPizarra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarPizarraActionPerformed(evt);
            }
        });

        jButtonBorrador.setBackground(new java.awt.Color(108, 59, 42));
        jButtonBorrador.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        jButtonBorrador.setForeground(new java.awt.Color(255, 255, 255));
        jButtonBorrador.setText("BORRADOR");
        jButtonBorrador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonBorrador.setFocusable(false);
        jButtonBorrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorradorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelPizarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButtonPincel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jButtonBorrador, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonBorrarPizarra, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(jLabel1)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanelPizarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBorrarPizarra, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBorrador, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPincel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPincelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPincelActionPerformed
        
        // Desactivar el borde del otro botón
        jButtonBorrador.setBorder(null);
        
        // Asignar el tamaño del pincel un poco más pequeño
        w = 7;
        h = 7;

        // Crear un borde con un color rojo y un grosor de 3 píxeles
        LineBorder border = new LineBorder(Color.red, 3);

        // Asignar el borde al botón
        jButtonPincel.setBorder(border);
        
        // Establecer el color de la pizarra
        c = Color.black;
        
    }//GEN-LAST:event_jButtonPincelActionPerformed

    private void jPanelPizarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelPizarraMouseDragged
        g = jPanelPizarra.getGraphics();
        g.setColor(c);
        g.fillOval(evt.getX(), evt.getY(), w, h);
    }//GEN-LAST:event_jPanelPizarraMouseDragged

    private void jButtonBorrarPizarraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarPizarraActionPerformed
        repaint();  
    }//GEN-LAST:event_jButtonBorrarPizarraActionPerformed

    private void jButtonBorradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorradorActionPerformed
        
        // Desactivar el borde del otro botón
        jButtonPincel.setBorder(null);
        
        // Asignar el tamaño del borrador un poco más grande
        w = 15;
        h = 15;

        // Crear un borde con un color como rojo y un grosor de 3 píxeles
        LineBorder border = new LineBorder(Color.red, 3);

        // Asignar el borde al botón
        jButtonBorrador.setBorder(border);
        
        // Establecer el color de la pizarra
        c = colorPizarra;
        
    }//GEN-LAST:event_jButtonBorradorActionPerformed

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
    private javax.swing.JButton jButtonBorrador;
    private javax.swing.JButton jButtonBorrarPizarra;
    private javax.swing.JButton jButtonPincel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelPizarra;
    // End of variables declaration//GEN-END:variables
}