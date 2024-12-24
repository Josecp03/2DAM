/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego2d;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class Juego2D extends JFrame {

    private JuegoPanel panel;

    public Juego2D() {

        panel = new JuegoPanel();
        add(panel);
        setTitle("Juego 2D");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

    }

    public static void main(String[] args) {

        Juego2D juego = new Juego2D();
        juego.setVisible(true);

    }

}

class JuegoPanel extends JPanel implements ActionListener {

    private Timer timer;
    private int x, y, velX, velY;

    public JuegoPanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new TAdapter());

        x = 50;
        y = 50;
        velX = 0;
        velY = 0;

        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujar(g);
    }

    private void dibujar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 30, 30); // Dibujamos un cuadrado
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mover();
        repaint();
    }

    private void mover() {
        x += velX;
        y += velY;

        // Colisión con el borde izquierdo
        if (x < 0) {
            x = 0;
        }

        // Colisión con el borde derecho
        if (x > getWidth() - 30) { // 30 es el ancho del cuadrado
            x = getWidth() - 30;
        }

        // Colisión con el borde superior
        if (y < 0) {
            y = 0;
        }

        // Colisión con el borde inferior
        if (y > getHeight() - 30) { // 30 es la altura del cuadrado
            y = getHeight() - 30;
        }

    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                velX = -2;
            }

            if (key == KeyEvent.VK_RIGHT) {
                velX = 2;
            }

            if (key == KeyEvent.VK_UP) {
                velY = -2;
            }

            if (key == KeyEvent.VK_DOWN) {
                velY = 2;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
                velX = 0;
            }

            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
                velY = 0;
            }
        }
    }

}
