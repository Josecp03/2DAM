/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iniciarInterfaz;

import interfaces.Acceso;
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;

/**
 *
 * @author josec
 */
public class LookAndFeel {
    
    public static void main(String[] args) {
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenSkin");
        Acceso a = new Acceso();
        a.setVisible(true);
        
    }
    
}
