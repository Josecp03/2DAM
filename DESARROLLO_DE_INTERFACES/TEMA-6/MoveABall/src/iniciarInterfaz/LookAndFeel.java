package iniciarInterfaz;

import javax.swing.JFrame;
import moveaball.MoveABall;
import org.jvnet.substance.SubstanceLookAndFeel;

public class LookAndFeel {
    
    public static void main(String[] args) {
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenSkin");
        MoveABall i = new MoveABall();
        i.setVisible(true);
        
    }
    
}
