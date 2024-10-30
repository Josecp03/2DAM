/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanelimagenn;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author josec
 */
public class JPanelImagenn extends JPanel {

    private ImagenFondo imagenFondo;

    public JPanelImagenn() {

    }

    public ImagenFondo getImagenFondo() {
        return imagenFondo;
    }

    public void setImagenFondo(ImagenFondo imagenFondo) {
        this.imagenFondo = imagenFondo;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.

        if (imagenFondo != null) {
            
            if (imagenFondo.getRutaimagen() != null && imagenFondo.getRutaimagen().exists()) {
                ImageIcon imageIcon = new ImageIcon(imagenFondo.getRutaimagen().getAbsolutePath());
                Graphics2D g2d = (Graphics2D) grphcs;
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, imagenFondo.getOpacidad()));
                g2d.drawImage(imageIcon.getImage(), 0, 0, null);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
            }
        
        }

    }

}
