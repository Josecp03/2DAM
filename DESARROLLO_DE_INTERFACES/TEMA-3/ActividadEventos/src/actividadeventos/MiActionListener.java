/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividadeventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author josec
 */
public class MiActionListener implements ActionListener, ListSelectionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Se ha pulsado el elemento");
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
         if (!lse.getValueIsAdjusting()) { // Solo cuando la selección ha terminado
            System.out.println("Se ha seleccionado un elemento en la lista");
        }
    }
    
}
