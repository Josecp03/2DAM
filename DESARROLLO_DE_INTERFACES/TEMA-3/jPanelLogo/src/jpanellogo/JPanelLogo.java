/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanellogo;

import java.awt.Graphics;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author josec
 */
public class JPanelLogo extends JPanel{

    private File rutaImagen;
    
    public JPanelLogo() {
    
    }

    public File getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(File rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        
        if (rutaImagen != null && rutaImagen.exists()) {
            ImageIcon imageIcon = new ImageIcon(rutaImagen.getAbsolutePath());
            grphcs.drawImage(imageIcon.getImage(), 0, 0, null);
        }
        
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
