/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author josec
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {

    private final int WIDTH = 800, HEIGHT = 600;
    private Thread gameThread;
    private boolean running;
    private Ball ball;
    private Paddle player1, player2;
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private Image backgroundImage;
    private Image iconImage;


    public GamePanel() throws IOException {

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        try {
            
            backgroundImage = ImageIO.read(getClass().getResource("/imgs/pongGameFondo.jpeg"));
            
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen de fondo");
            e.printStackTrace();
        }

        addKeyListener(this);
        setFocusable(true);
        ball = new Ball(WIDTH / 2, HEIGHT / 2, 20, 20, this);
        player1 = new Paddle(10, HEIGHT / 2 - 60, 10, 120);
        player2 = new Paddle(WIDTH - 20, HEIGHT / 2 - 60, 10, 120);
        startGame();
    }

    private void startGame() {

        running = true;
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        while (running) {

            update();
            repaint();
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void update() {

        ball.move();
        ball.checkCollision(player1, player2, WIDTH, HEIGHT);
        player1.move(HEIGHT);
        player2.move(HEIGHT);

    }

    public void incrementScorePlayer1() {
        scorePlayer1++;
        player1.reduceHeight();
        checkGameOver();
    }

    public void incrementScorePlayer2() {
        scorePlayer2++;
        player2.reduceHeight();
        checkGameOver();
    }

    private void checkGameOver() {
        if (scorePlayer1 == 5 || scorePlayer2 == 5) {
            String winner = (scorePlayer1 == 5) ? "Jugador 1" : "Jugador 2";
            int response = JOptionPane.showConfirmDialog(this, winner + " ha ganado. ¿Quieres volver a jugar?",
                    "Fin del Juego", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        }
    }

    private void resetGame() {
        
        // Reinicia las variables
        scorePlayer1 = 0;
        scorePlayer2 = 0;

        // Reinicia las posiciones de los jugadores
        player1 = new Paddle(10, HEIGHT / 2 - 60, 10, 120);
        player2 = new Paddle(WIDTH - 20, HEIGHT / 2 - 60, 10, 120);

        // Reinicia la pelota
        ball.resetPosition(WIDTH / 2, HEIGHT / 2);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, null);
        ball.draw(g);
        player1.draw(g);
        player2.draw(g);

        // Dibujar el marcador
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        String scoreText = scorePlayer1 + " : " + scorePlayer2;
        int textWidth = g.getFontMetrics().stringWidth(scoreText);
        g.drawString(scoreText, (WIDTH - textWidth) / 2, 50);

    }

    @Override
    public void keyPressed(KeyEvent ke) {

        int key = ke.getKeyCode();

        if (key == KeyEvent.VK_W) {
            player1.setDirection(-1);
        }

        if (key == KeyEvent.VK_S) {
            player1.setDirection(1);
        }

        if (key == KeyEvent.VK_UP) {
            player2.setDirection(-1);
        }

        if (key == KeyEvent.VK_DOWN) {
            player2.setDirection(1);
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {

        int key = ke.getKeyCode();

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
            player1.setDirection(0);
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            player2.setDirection(0);
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

}
