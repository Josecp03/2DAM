/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iniciarInterfaz;

import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;
import ponggame.GamePanel;

public class FeelAndLook {

    public static void main(String[] args) throws IOException {
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenSkin");
        JFrame frame = new JFrame("Pong Game");
        GamePanel gamePanel = new GamePanel();

        // Establecer el ícono de la ventana principal
        frame.setIconImage(new ImageIcon(FeelAndLook.class.getResource("/imgs/iconoPong.png")).getImage());
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
