/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividadvideojuego.interfaz;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author josec
 */
public class SeleccionPersonajes extends javax.swing.JFrame {

    /**
     * Creates new form SeleccionPersonajes
     */
    public SeleccionPersonajes() {
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
        jPanel1 = new javax.swing.JPanel();
        jPanelImagennProphet = new jpanelimagenn.JPanelImagenn();
        jPanelImagennBaterry = new jpanelimagenn.JPanelImagenn();
        jPanelImagennFirebreak = new jpanelimagenn.JPanelImagenn();
        jPanelImagennNomand = new jpanelimagenn.JPanelImagenn();
        jPanelImagennOutrider = new jpanelimagenn.JPanelImagenn();
        jPanelImagennReaper = new jpanelimagenn.JPanelImagenn();
        jPanelImagennSpectre = new jpanelimagenn.JPanelImagenn();
        jPanelImagennSeraph = new jpanelimagenn.JPanelImagenn();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelImagennFondo.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Users/josec/Documents/NetBeansProjects/TEMA-3/ActividadVideojuego/src/actividadvideojuego/imgs/FondoFinal.png"), 1.0f));

        jPanel1.setLayout(new java.awt.GridLayout(2, 4));

        jPanelImagennProphet.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelImagennProphet.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Users/josec/Documents/NetBeansProjects/TEMA-3/ActividadVideojuego/src/actividadvideojuego/imgs/Prophet.jpg"), 1.0f));
        jPanelImagennProphet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelImagennProphetMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelImagennProphetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelImagennProphetMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelImagennProphetLayout = new javax.swing.GroupLayout(jPanelImagennProphet);
        jPanelImagennProphet.setLayout(jPanelImagennProphetLayout);
        jPanelImagennProphetLayout.setHorizontalGroup(
            jPanelImagennProphetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanelImagennProphetLayout.setVerticalGroup(
            jPanelImagennProphetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelImagennProphet);

        jPanelImagennBaterry.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelImagennBaterry.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Users/josec/Documents/NetBeansProjects/TEMA-3/ActividadVideojuego/src/actividadvideojuego/imgs/Battery.jpg"), 1.0f));
        jPanelImagennBaterry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelImagennBaterryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelImagennBaterryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelImagennBaterryMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelImagennBaterryLayout = new javax.swing.GroupLayout(jPanelImagennBaterry);
        jPanelImagennBaterry.setLayout(jPanelImagennBaterryLayout);
        jPanelImagennBaterryLayout.setHorizontalGroup(
            jPanelImagennBaterryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanelImagennBaterryLayout.setVerticalGroup(
            jPanelImagennBaterryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelImagennBaterry);

        jPanelImagennFirebreak.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelImagennFirebreak.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Users/josec/Documents/NetBeansProjects/TEMA-3/ActividadVideojuego/src/actividadvideojuego/imgs/FireBreak.jpg"), 1.0f));
        jPanelImagennFirebreak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelImagennFirebreakMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelImagennFirebreakMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelImagennFirebreakMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelImagennFirebreakLayout = new javax.swing.GroupLayout(jPanelImagennFirebreak);
        jPanelImagennFirebreak.setLayout(jPanelImagennFirebreakLayout);
        jPanelImagennFirebreakLayout.setHorizontalGroup(
            jPanelImagennFirebreakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanelImagennFirebreakLayout.setVerticalGroup(
            jPanelImagennFirebreakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelImagennFirebreak);

        jPanelImagennNomand.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelImagennNomand.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Users/josec/Documents/NetBeansProjects/TEMA-3/ActividadVideojuego/src/actividadvideojuego/imgs/Nomand.jpg"), 1.0f));
        jPanelImagennNomand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelImagennNomandMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelImagennNomandMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelImagennNomandMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelImagennNomandLayout = new javax.swing.GroupLayout(jPanelImagennNomand);
        jPanelImagennNomand.setLayout(jPanelImagennNomandLayout);
        jPanelImagennNomandLayout.setHorizontalGroup(
            jPanelImagennNomandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanelImagennNomandLayout.setVerticalGroup(
            jPanelImagennNomandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelImagennNomand);

        jPanelImagennOutrider.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelImagennOutrider.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Users/josec/Documents/NetBeansProjects/TEMA-3/ActividadVideojuego/src/actividadvideojuego/imgs/Outrider.jpg"), 1.0f));
        jPanelImagennOutrider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelImagennOutriderMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelImagennOutriderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelImagennOutriderMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelImagennOutriderLayout = new javax.swing.GroupLayout(jPanelImagennOutrider);
        jPanelImagennOutrider.setLayout(jPanelImagennOutriderLayout);
        jPanelImagennOutriderLayout.setHorizontalGroup(
            jPanelImagennOutriderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanelImagennOutriderLayout.setVerticalGroup(
            jPanelImagennOutriderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelImagennOutrider);

        jPanelImagennReaper.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelImagennReaper.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Users/josec/Documents/NetBeansProjects/TEMA-3/ActividadVideojuego/src/actividadvideojuego/imgs/Reaper.jpg"), 1.0f));
        jPanelImagennReaper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelImagennReaperMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelImagennReaperMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelImagennReaperMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelImagennReaperLayout = new javax.swing.GroupLayout(jPanelImagennReaper);
        jPanelImagennReaper.setLayout(jPanelImagennReaperLayout);
        jPanelImagennReaperLayout.setHorizontalGroup(
            jPanelImagennReaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanelImagennReaperLayout.setVerticalGroup(
            jPanelImagennReaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelImagennReaper);

        jPanelImagennSpectre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelImagennSpectre.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Users/josec/Documents/NetBeansProjects/TEMA-3/ActividadVideojuego/src/actividadvideojuego/imgs/Spectre.jpg"), 1.0f));
        jPanelImagennSpectre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelImagennSpectreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelImagennSpectreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelImagennSpectreMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelImagennSpectreLayout = new javax.swing.GroupLayout(jPanelImagennSpectre);
        jPanelImagennSpectre.setLayout(jPanelImagennSpectreLayout);
        jPanelImagennSpectreLayout.setHorizontalGroup(
            jPanelImagennSpectreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanelImagennSpectreLayout.setVerticalGroup(
            jPanelImagennSpectreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelImagennSpectre);

        jPanelImagennSeraph.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelImagennSeraph.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Users/josec/Documents/NetBeansProjects/TEMA-3/ActividadVideojuego/src/actividadvideojuego/imgs/Seraph.jpg"), 1.0f));
        jPanelImagennSeraph.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelImagennSeraphMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelImagennSeraphMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelImagennSeraphMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelImagennSeraphLayout = new javax.swing.GroupLayout(jPanelImagennSeraph);
        jPanelImagennSeraph.setLayout(jPanelImagennSeraphLayout);
        jPanelImagennSeraphLayout.setHorizontalGroup(
            jPanelImagennSeraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanelImagennSeraphLayout.setVerticalGroup(
            jPanelImagennSeraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelImagennSeraph);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/actividadvideojuego/imgs/TituloSelccionaPersonaje.png"))); // NOI18N

        javax.swing.GroupLayout jPanelImagennFondoLayout = new javax.swing.GroupLayout(jPanelImagennFondo);
        jPanelImagennFondo.setLayout(jPanelImagennFondoLayout);
        jPanelImagennFondoLayout.setHorizontalGroup(
            jPanelImagennFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImagennFondoLayout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addGroup(jPanelImagennFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(305, Short.MAX_VALUE))
        );
        jPanelImagennFondoLayout.setVerticalGroup(
            jPanelImagennFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImagennFondoLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel1)
                .addGap(60, 60, 60)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImagennFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImagennFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void cambiarBorde(JPanel imagen) {
        LineBorder bordeBlanco = new LineBorder(Color.WHITE, 4);
        imagen.setBorder(bordeBlanco);
    }

    public static void borrarBorde(JPanel imagen) {
        imagen.setBorder(null);
    }

    public static void mensajeEspecialista(Component parent, String especialista) {
        JOptionPane.showMessageDialog(parent,
                "Has elegido a "+especialista,
                especialista,
                JOptionPane.INFORMATION_MESSAGE);
    }


    private void jPanelImagennProphetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennProphetMouseEntered
        cambiarBorde(jPanelImagennProphet);
    }//GEN-LAST:event_jPanelImagennProphetMouseEntered

    private void jPanelImagennProphetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennProphetMouseExited
        borrarBorde(jPanelImagennProphet);
    }//GEN-LAST:event_jPanelImagennProphetMouseExited

    private void jPanelImagennBaterryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennBaterryMouseEntered
        cambiarBorde(jPanelImagennBaterry);
    }//GEN-LAST:event_jPanelImagennBaterryMouseEntered

    private void jPanelImagennBaterryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennBaterryMouseExited
        borrarBorde(jPanelImagennBaterry);
    }//GEN-LAST:event_jPanelImagennBaterryMouseExited

    private void jPanelImagennFirebreakMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennFirebreakMouseEntered
        cambiarBorde(jPanelImagennFirebreak);
    }//GEN-LAST:event_jPanelImagennFirebreakMouseEntered

    private void jPanelImagennFirebreakMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennFirebreakMouseExited
        borrarBorde(jPanelImagennFirebreak);
    }//GEN-LAST:event_jPanelImagennFirebreakMouseExited

    private void jPanelImagennNomandMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennNomandMouseEntered
        cambiarBorde(jPanelImagennNomand);
    }//GEN-LAST:event_jPanelImagennNomandMouseEntered

    private void jPanelImagennNomandMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennNomandMouseExited
        borrarBorde(jPanelImagennNomand);
    }//GEN-LAST:event_jPanelImagennNomandMouseExited

    private void jPanelImagennOutriderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennOutriderMouseEntered
        cambiarBorde(jPanelImagennOutrider);
    }//GEN-LAST:event_jPanelImagennOutriderMouseEntered

    private void jPanelImagennOutriderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennOutriderMouseExited
        borrarBorde(jPanelImagennOutrider);
    }//GEN-LAST:event_jPanelImagennOutriderMouseExited

    private void jPanelImagennReaperMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennReaperMouseEntered
        cambiarBorde(jPanelImagennReaper);
    }//GEN-LAST:event_jPanelImagennReaperMouseEntered

    private void jPanelImagennReaperMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennReaperMouseExited
        borrarBorde(jPanelImagennReaper);
    }//GEN-LAST:event_jPanelImagennReaperMouseExited

    private void jPanelImagennSpectreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennSpectreMouseEntered
        cambiarBorde(jPanelImagennSpectre);
    }//GEN-LAST:event_jPanelImagennSpectreMouseEntered

    private void jPanelImagennSpectreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennSpectreMouseExited
        borrarBorde(jPanelImagennSpectre);
    }//GEN-LAST:event_jPanelImagennSpectreMouseExited

    private void jPanelImagennSeraphMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennSeraphMouseEntered
        cambiarBorde(jPanelImagennSeraph);
    }//GEN-LAST:event_jPanelImagennSeraphMouseEntered

    private void jPanelImagennSeraphMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennSeraphMouseExited
        borrarBorde(jPanelImagennSeraph);
    }//GEN-LAST:event_jPanelImagennSeraphMouseExited

    private void jPanelImagennProphetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennProphetMouseClicked
        mensajeEspecialista(this, "Prophet");
    }//GEN-LAST:event_jPanelImagennProphetMouseClicked

    private void jPanelImagennBaterryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennBaterryMouseClicked
        mensajeEspecialista(this, "Battery");
    }//GEN-LAST:event_jPanelImagennBaterryMouseClicked

    private void jPanelImagennFirebreakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennFirebreakMouseClicked
        mensajeEspecialista(this, "Firebreak");
    }//GEN-LAST:event_jPanelImagennFirebreakMouseClicked

    private void jPanelImagennNomandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennNomandMouseClicked
        mensajeEspecialista(this, "Nomand");
    }//GEN-LAST:event_jPanelImagennNomandMouseClicked

    private void jPanelImagennOutriderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennOutriderMouseClicked
        mensajeEspecialista(this, "Outrider");
    }//GEN-LAST:event_jPanelImagennOutriderMouseClicked

    private void jPanelImagennReaperMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennReaperMouseClicked
        mensajeEspecialista(this, "Reaper");
    }//GEN-LAST:event_jPanelImagennReaperMouseClicked

    private void jPanelImagennSpectreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennSpectreMouseClicked
        mensajeEspecialista(this, "Spectre");
    }//GEN-LAST:event_jPanelImagennSpectreMouseClicked

    private void jPanelImagennSeraphMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelImagennSeraphMouseClicked
        mensajeEspecialista(this, "Seraph");
    }//GEN-LAST:event_jPanelImagennSeraphMouseClicked

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
            java.util.logging.Logger.getLogger(SeleccionPersonajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionPersonajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionPersonajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionPersonajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeleccionPersonajes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private jpanelimagenn.JPanelImagenn jPanelImagennBaterry;
    private jpanelimagenn.JPanelImagenn jPanelImagennFirebreak;
    private jpanelimagenn.JPanelImagenn jPanelImagennFondo;
    private jpanelimagenn.JPanelImagenn jPanelImagennNomand;
    private jpanelimagenn.JPanelImagenn jPanelImagennOutrider;
    private jpanelimagenn.JPanelImagenn jPanelImagennProphet;
    private jpanelimagenn.JPanelImagenn jPanelImagennReaper;
    private jpanelimagenn.JPanelImagenn jPanelImagennSeraph;
    private jpanelimagenn.JPanelImagenn jPanelImagennSpectre;
    // End of variables declaration//GEN-END:variables
}