/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import java.awt.Color;
import java.awt.Graphics;

public class Ball {

    private int x, y, width, height;
    private final int initialXSpeed = 3;
    private final int initialYSpeed = 3;
    private int xSpeed = initialXSpeed;
    private int ySpeed = initialYSpeed;
    private GamePanel gamePanel;

    public Ball(int x, int y, int width, int height, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gamePanel = gamePanel;
    }

    public void move() {

        x += xSpeed;
        y += ySpeed;

    }

    public void checkCollision(Paddle p1, Paddle p2, int screenWidth, int screenHeight) {

        if (y <= 0 || y + height >= screenHeight) {
            ySpeed *= -1;
        }

        if (x <= p1.getX() + p1.getWidth() && y + height >= p1.getY() && y <= p1.getY() + p1.getHeight()) {

            xSpeed *= -1;
            increaseSpeed();

        }

        if (x + width >= p2.getX() && y + height >= p2.getY() && y <= p2.getY() + p2.getHeight()) {

            xSpeed *= -1;
            increaseSpeed();

        }

        // Puntos para los jugadores
        if (x <= 0) {
            gamePanel.incrementScorePlayer2();
            resetPosition(screenWidth / 2, screenHeight / 2);
        } else if (x + width >= screenWidth) {
            gamePanel.incrementScorePlayer1();
            resetPosition(screenWidth / 2, screenHeight / 2);
        }

    }

    public void resetPosition(int centerX, int centerY) {
        x = centerX - width / 2;
        y = centerY - height / 2;

        if (xSpeed > 0) {
            xSpeed = initialXSpeed;
        } else {
            xSpeed = -initialXSpeed;
        }

        if (ySpeed > 0) {
            ySpeed = initialYSpeed;
        } else {
            ySpeed = -initialYSpeed;
        }

    }

    private void increaseSpeed() {
        xSpeed += (xSpeed > 0) ? 1 : -1;
        ySpeed += (ySpeed > 0) ? 1 : -1;

        // Limitar velocidad máxima
        xSpeed = Math.min(xSpeed, 10);
        ySpeed = Math.min(ySpeed, 10);
    }

    public void draw(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);

    }

}
