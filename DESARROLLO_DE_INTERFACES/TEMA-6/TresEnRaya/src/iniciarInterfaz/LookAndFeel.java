package iniciarInterfaz;

import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;
import tresenraya.TresEnRaya;

public class LookAndFeel {
    
    public static void main(String[] args) {
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenSkin");
        TresEnRaya i = new TresEnRaya();
        i.setVisible(true);
        
    }
    
}
