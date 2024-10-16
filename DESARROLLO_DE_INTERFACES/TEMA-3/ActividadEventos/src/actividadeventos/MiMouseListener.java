/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividadeventos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author josec
 */
public class MiMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("El ratón ha sido clicado sobre el elemento");
    }

    @Override
    public void mousePressed(MouseEvent me) {
        System.out.println("El botón del ratón ha sido presionado sobre el elemento");
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        System.out.println("El botón del ratón ha sido soltado sobre el elemento");
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        System.out.println("El ratón ha entrado en el área del elemento");
    }

    @Override
    public void mouseExited(MouseEvent me) {
        System.out.println("El ratón ha salido del área del elemento");
    }
    
}
