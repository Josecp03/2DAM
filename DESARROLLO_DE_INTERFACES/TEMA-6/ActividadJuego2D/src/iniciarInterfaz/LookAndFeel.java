package iniciarInterfaz;

import actividadjuego2d.Juego2D;
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;

public class LookAndFeel {
    
    public static void main(String[] args) {
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenSkin");
        Juego2D j = new Juego2D();
        j.setVisible(true);
        
    }
    
}
