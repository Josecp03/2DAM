package iniciarInterfaz;

import javax.swing.JFrame;
import movealine.MoveLine;
import org.jvnet.substance.SubstanceLookAndFeel;

public class LookAndFeel {
    
    public static void main(String[] args) {
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenSkin");
        MoveLine i = new MoveLine();
        i.setVisible(true);
        
    }
    
}
