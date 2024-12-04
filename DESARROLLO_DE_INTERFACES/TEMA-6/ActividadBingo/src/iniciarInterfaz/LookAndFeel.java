package iniciarInterfaz;

import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;
import ventanas.PantallaPrincipal;

public class LookAndFeel {
    
    public static void main(String[] args) {
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenSkin");
        PantallaPrincipal p = new PantallaPrincipal();
        p.setVisible(true);
        
    }
    
}
