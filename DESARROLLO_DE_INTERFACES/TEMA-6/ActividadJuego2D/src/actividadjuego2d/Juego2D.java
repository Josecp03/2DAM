package actividadjuego2d;

import java.applet.Applet;
import java.applet.AudioClip;
import javax.swing.*;
import java.awt.*;
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
        this.setIconImage(new ImageIcon(getClass().getResource("../imgs/iconoJuego.png")).getImage());
                
    }

    public static void main(String[] args) {
        Juego2D juego = new Juego2D();
        juego.setVisible(true);
    }
}

class JuegoPanel extends JPanel implements ActionListener {

    private Timer timer;
    private int x, y, velX, velY;
    private boolean enElSuelo;
    private Image iconoNormal, iconoGirado;
    private boolean mirandoIzquierda;
    private Image fondo;
    private Image moneda1, moneda2, moneda3;
    private boolean moneda1Activa, moneda2Activa, moneda3Activa;
    private Image monedaSinDesbloquear1, monedaSinDesbloquear2, monedaSinDesbloquear3;
    private Image monedaDesbloqueada1, monedaDesbloqueada2, monedaDesbloqueada3;
    private Image puertaFinal;

    // Parámetros del "suelo"
    private int sueloX, sueloY, sueloHeight;

    // Parámetros de los cuadrados
    private int cuadradoX, cuadradoY, cuadradoAncho, cuadradoAlto;
    private int cuadrado2X, cuadrado2Y, cuadrado2Ancho, cuadrado2Alto;
    private int cuadrado3X, cuadrado3Y, cuadrado3Ancho, cuadrado3Alto;

    // Parámetros para los pinchos
    private int pinchoX, pinchoY, pinchoAncho, pinchoAlto;
    private int pincho2X, pincho2Y, pincho2Ancho, pincho2Alto;

    AudioClip audioMusicaFondo;
    AudioClip audioMuerte;
    AudioClip audioMoneda;
    AudioClip audioVictoria;

    public JuegoPanel() {

        setFocusable(true);
        addKeyListener(new TAdapter());

        
        // Cargar los audios
        audioMusicaFondo = Applet.newAudioClip(getClass().getResource("../audio/musicafondo.wav"));
        audioMuerte = Applet.newAudioClip(getClass().getResource("../audio/sonidomuerte.wav"));
        audioMoneda = Applet.newAudioClip(getClass().getResource("../audio/sonidologro.wav"));
        audioVictoria = Applet.newAudioClip(getClass().getResource("../audio/sonidovictoria.wav"));

        // Reproducir la mísuica de fondo
        reproducirMusicaFondo();

        // Cargar las imágenes 
        ImageIcon imageIconNormal = new ImageIcon(getClass().getResource("/imgs/ic_personaje.png"));
        ImageIcon imageIconGirado = new ImageIcon(getClass().getResource("/imgs/ic_personaje_girado.png"));
        ImageIcon fondoIcon = new ImageIcon(getClass().getResource("/imgs/fondo.jpg"));
        ImageIcon monedaIcon = new ImageIcon(getClass().getResource("/imgs/moneda.png"));
        ImageIcon moneda2Icon = new ImageIcon(getClass().getResource("/imgs/moneda.png"));
        ImageIcon moneda3Icon = new ImageIcon(getClass().getResource("/imgs/moneda.png"));
        ImageIcon puertaFinalIcon = new ImageIcon(getClass().getResource("/imgs/puertafinal.png"));
        ImageIcon monedaSinDesbloquear1Icon = new ImageIcon(getClass().getResource("/imgs/moneda_sin_desbloquear.PNG"));
        ImageIcon monedaSinDesbloquear2Icon = new ImageIcon(getClass().getResource("/imgs/moneda_sin_desbloquear.PNG"));
        ImageIcon monedaSinDesbloquear3Icon = new ImageIcon(getClass().getResource("/imgs/moneda_sin_desbloquear.PNG"));
        ImageIcon monedaDesbloqueada1Icon = new ImageIcon(getClass().getResource("/imgs/moneda_desbloqueada.png"));
        ImageIcon monedaDesbloqueada2Icon = new ImageIcon(getClass().getResource("/imgs/moneda_desbloqueada.png"));
        ImageIcon monedaDesbloqueada3Icon = new ImageIcon(getClass().getResource("/imgs/moneda_desbloqueada.png"));

        // Asignar las imágenes
        iconoNormal = imageIconNormal.getImage();
        iconoGirado = imageIconGirado.getImage();
        fondo = fondoIcon.getImage();
        moneda1 = monedaIcon.getImage();
        moneda2 = moneda2Icon.getImage();
        moneda3 = moneda3Icon.getImage();
        puertaFinal = puertaFinalIcon.getImage();
        monedaSinDesbloquear1 = monedaSinDesbloquear1Icon.getImage();
        monedaSinDesbloquear2 = monedaSinDesbloquear2Icon.getImage();
        monedaSinDesbloquear3 = monedaSinDesbloquear3Icon.getImage();
        monedaDesbloqueada1 = monedaDesbloqueada1Icon.getImage();
        monedaDesbloqueada2 = monedaDesbloqueada2Icon.getImage();
        monedaDesbloqueada3 = monedaDesbloqueada3Icon.getImage();

        // Establecer posiciones iniciales
        x = 0;
        y = 490;
        velX = 0;
        velY = 0;
        enElSuelo = true;
        mirandoIzquierda = false;
        sueloX = 0;
        sueloY = 550;

        // Primer cuadrado
        cuadradoAncho = 50;
        cuadradoAlto = 100;
        cuadradoX = 200;
        cuadradoY = 447;

        // Segundo cuadrado
        cuadrado2Ancho = 50;
        cuadrado2Alto = 100;
        cuadrado2X = 335;
        cuadrado2Y = 447;

        // Tercer cuadrado
        cuadrado3Ancho = 50;
        cuadrado3Alto = 100;
        cuadrado3X = 470;
        cuadrado3Y = 447;

        // Primer pincho
        pinchoAncho = 83;
        pinchoAlto = 40;
        pinchoX = 251;
        pinchoY = 507;

        // Segundo pincho
        pincho2Ancho = 83;
        pincho2Alto = 40;
        pincho2X = 387;
        pincho2Y = 507;

        // Timer
        timer = new Timer(10, this);
        timer.start();

        // Inicializar estado de las monedas
        moneda1Activa = true;
        moneda2Activa = true;
        moneda3Activa = true;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujar(g);
    }

    private void dibujar(Graphics g) {

        // Dibujar las imágenes
        g.drawImage(fondo, 0, 0, this);
        g.drawImage(monedaSinDesbloquear1, 700, 20, this);
        g.drawImage(monedaSinDesbloquear2, 720, 20, this);
        g.drawImage(monedaSinDesbloquear3, 740, 20, this);
        g.drawImage(puertaFinal, 670, 460, this);

        // Dibuja las monedas activas
        if (moneda1Activa) {
            g.drawImage(moneda1, 200, 250, this);
        }
        if (moneda2Activa) {
            g.drawImage(moneda2, 340, 250, this);
        }
        if (moneda3Activa) {
            g.drawImage(moneda3, 470, 250, this);
        }

        // Dibuja el personaje
        if (mirandoIzquierda) {
            g.drawImage(iconoGirado, x, y, this);
        } else {
            g.drawImage(iconoNormal, x, y, this);
        }

        // Dibuja los cuadrados
        g.setColor(new Color(0x265e9c));
        g.fillRect(cuadradoX, cuadradoY, cuadradoAncho, cuadradoAlto);
        g.setColor(Color.BLACK);
        g.drawRect(cuadradoX, cuadradoY, cuadradoAncho, cuadradoAlto);

        g.setColor(new Color(0x265e9c));
        g.fillRect(cuadrado2X, cuadrado2Y, cuadrado2Ancho, cuadrado2Alto);
        g.setColor(Color.BLACK);
        g.drawRect(cuadrado2X, cuadrado2Y, cuadrado2Ancho, cuadrado2Alto);

        g.setColor(new Color(0x265e9c));
        g.fillRect(cuadrado3X, cuadrado3Y, cuadrado3Ancho, cuadrado3Alto);
        g.setColor(Color.BLACK);
        g.drawRect(cuadrado3X, cuadrado3Y, cuadrado3Ancho, cuadrado3Alto);

        // Dibujar los pinchos
        g.setColor(new Color(0x000000));
        int[] xPoints = {pinchoX, pinchoX + pinchoAncho / 2, pinchoX + pinchoAncho};
        int[] yPoints = {pinchoY + pinchoAlto, pinchoY, pinchoY + pinchoAlto};
        g.fillPolygon(xPoints, yPoints, 3);
        g.setColor(Color.WHITE);
        g.drawPolygon(xPoints, yPoints, 3);

        // Dibujar el segundo pincho como triángulo
        g.setColor(new Color(0x000000));
        int[] x2Points = {pincho2X, pincho2X + pincho2Ancho / 2, pincho2X + pincho2Ancho};
        int[] y2Points = {pincho2Y + pincho2Alto, pincho2Y, pincho2Y + pincho2Alto};
        g.fillPolygon(x2Points, y2Points, 3);
        g.setColor(Color.WHITE);
        g.drawPolygon(x2Points, y2Points, 3);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mover();
        aplicarGravedad();
        detectarColisionConSuelo();
        detectarColisionConCuadrado();
        detectarColisionConCuadrado2();
        detectarColisionConCuadrado3();
        detectarColisionConMonedas();
        detectarColisionConPincho();
        detectarColisionConPincho2();
        detectarColisionPuertaFinal();
        repaint();
    }

    private void mover() {

        x += velX;
        y += velY;

        // Colisión con el borde izquierdo de la pantalla
        if (x < 0) {
            x = 0;
        }

        // Colisión con el borde derecho de la pantalla
        if (x > getWidth() - iconoNormal.getWidth(this)) {
            x = getWidth() - iconoNormal.getWidth(this);
        }

        // Colisión con el borde superior de la pantalla
        if (y < 0) {
            y = 0;
        }

    }

    private void aplicarGravedad() {
        if (!enElSuelo) {
            velY += 1;
        } else {
            velY = 0;
        }
    }

    // Detecta la colisión con el suelo
    private void detectarColisionConSuelo() {

        if (y + iconoNormal.getHeight(this) > sueloY && y < sueloY + sueloHeight) {
            if (velY > 0) {

                // Colocar al personaje encima del suelo
                y = sueloY - iconoNormal.getHeight(this);
                enElSuelo = true;
                velY = 0;

            }
        }
    }

    // Detecta la colisión con el primer cuadrado
    private void detectarColisionConCuadrado() {

        // Colisión cuando el personaje está cayendo y toca el cuadrado
        if (x + iconoNormal.getWidth(this) > cuadradoX && x < cuadradoX + cuadradoAncho) {
            if (y + iconoNormal.getHeight(this) > cuadradoY && y < cuadradoY + cuadradoAlto) {
                if (velY > 0) {

                    // Colocar al personaje justo encima del cuadrado
                    y = cuadradoY - iconoNormal.getHeight(this);
                    enElSuelo = true;
                    velY = 0;
                }
            }
        }

        // Colisión lateral izquierda con el cuadrado
        if (x < cuadradoX && x + iconoNormal.getWidth(this) > cuadradoX
                && y + iconoNormal.getHeight(this) > cuadradoY && y < cuadradoY + cuadradoAlto) {
            x = cuadradoX - iconoNormal.getWidth(this); // Colocar al personaje justo antes del cuadrado
        }

        // Colisión lateral derecha con el cuadrado
        if (x + iconoNormal.getWidth(this) > cuadradoX + cuadradoAncho && x < cuadradoX + cuadradoAncho
                && y + iconoNormal.getHeight(this) > cuadradoY && y < cuadradoY + cuadradoAlto) {
            x = cuadradoX + cuadradoAncho; // Colocar al personaje justo después del cuadrado
        }

        // Si el personaje está tocando la parte superior del cuadrado 
        if (y + iconoNormal.getHeight(this) <= cuadradoY && y + iconoNormal.getHeight(this) > cuadradoY - 5) {
            // Si está tocando el borde superior y se mueve hacia adelante, lo dejamos caer
            if (velX != 0) {
                enElSuelo = false;  // El personaje debe caer si sigue avanzando
            }
        }
    }

    // Detecta la colisión con el segundo cuadrado
    private void detectarColisionConCuadrado2() {

        // Colisión cuando el personaje está cayendo y toca el cuadrado
        if (x + iconoNormal.getWidth(this) > cuadrado2X && x < cuadrado2X + cuadrado2Ancho) {
            if (y + iconoNormal.getHeight(this) > cuadrado2Y && y < cuadrado2Y + cuadrado2Alto) {
                if (velY > 0) {
                    y = cuadrado2Y - iconoNormal.getHeight(this); // Colocar al personaje justo encima del cuadrado
                    enElSuelo = true;
                    velY = 0;
                }
            }
        }

        // Colisión lateral izquierda con el cuadrado
        if (x < cuadrado2X && x + iconoNormal.getWidth(this) > cuadrado2X
                && y + iconoNormal.getHeight(this) > cuadrado2Y && y < cuadrado2Y + cuadrado2Alto) {
            x = cuadrado2X - iconoNormal.getWidth(this); // Colocar al personaje justo antes del cuadrado
        }

        // Colisión lateral derecha con el cuadrado
        if (x + iconoNormal.getWidth(this) > cuadrado2X + cuadrado2Ancho && x < cuadrado2X + cuadrado2Ancho
                && y + iconoNormal.getHeight(this) > cuadrado2Y && y < cuadrado2Y + cuadrado2Alto) {
            x = cuadrado2X + cuadrado2Ancho; // Colocar al personaje justo después del cuadrado
        }

        // Si el personaje está tocando la parte superior del cuadrado 
        if (y + iconoNormal.getHeight(this) <= cuadrado2Y && y + iconoNormal.getHeight(this) > cuadrado2Y - 5) {
            // Si está tocando el borde superior y se mueve hacia adelante, lo dejamos caer
            if (velX != 0) {
                enElSuelo = false;  // El personaje debe caer si sigue avanzando
            }
        }
    }

    // Detecta la colisión con el segundo cuadrado
    private void detectarColisionConCuadrado3() {

        // Colisión cuando el personaje está cayendo y toca el cuadrado
        if (x + iconoNormal.getWidth(this) > cuadrado3X && x < cuadrado3X + cuadrado3Ancho) {
            if (y + iconoNormal.getHeight(this) > cuadrado3Y && y < cuadrado3Y + cuadrado3Alto) {
                if (velY > 0) {
                    y = cuadrado2Y - iconoNormal.getHeight(this); // Colocar al personaje justo encima del cuadrado
                    enElSuelo = true;
                    velY = 0;
                }
            }
        }

        // Colisión lateral izquierda con el cuadrado
        if (x < cuadrado3X && x + iconoNormal.getWidth(this) > cuadrado3X
                && y + iconoNormal.getHeight(this) > cuadrado2Y && y < cuadrado3Y + cuadrado3Alto) {
            x = cuadrado3X - iconoNormal.getWidth(this); // Colocar al personaje justo antes del cuadrado
        }

        // Colisión lateral derecha con el cuadrado
        if (x + iconoNormal.getWidth(this) > cuadrado3X + cuadrado3Ancho && x < cuadrado3X + cuadrado3Ancho
                && y + iconoNormal.getHeight(this) > cuadrado3Y && y < cuadrado3Y + cuadrado3Alto) {
            x = cuadrado3X + cuadrado3Ancho; // Colocar al personaje justo después del cuadrado
        }

        // Si el personaje está tocando la parte superior del cuadrado 
        if (y + iconoNormal.getHeight(this) <= cuadrado3Y && y + iconoNormal.getHeight(this) > cuadrado3Y - 5) {
            // Si está tocando el borde superior y se mueve hacia adelante, lo dejamos caer
            if (velX != 0) {
                enElSuelo = false;  // El personaje debe caer si sigue avanzando
            }
        }
    }

    // Detecta la colisión con el segundo cuadrado
    private void detectarColisionConPincho() {
        // Colisión cuando el personaje está cayendo y toca el cuadrado
        if (x + iconoNormal.getWidth(this) > pinchoX && x < pinchoX + pinchoAncho) {
            if (y + iconoNormal.getHeight(this) > pinchoY && y < pinchoY + pinchoAlto) {
                if (velY > 0) {
                    x = 0;
                    y = 490;
                    velY = 0;
                    reproducirSonidoMuerte();
                    JOptionPane.showMessageDialog(this, "Has muerto! Cuidado con los pinchos", "Cuidado!", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

    }

    // Detecta la colisión con el segundo cuadrado
    private void detectarColisionConPincho2() {
        // Colisión cuando el personaje está cayendo y toca el cuadrado
        if (x + iconoNormal.getWidth(this) > pincho2X && x < pincho2X + pincho2Ancho) {
            if (y + iconoNormal.getHeight(this) > pincho2Y && y < pincho2Y + pincho2Alto) {
                if (velY > 0) {
                    x = 0;
                    y = 490;
                    velY = 0;
                    reproducirSonidoMuerte();
                    JOptionPane.showMessageDialog(this, "Has muerto! Cuidado con los pinchos", "Cuidado!", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

    }

    private void detectarColisionConMonedas() {
        // Detectar colisión con la primera moneda
        if (moneda1Activa && x + iconoNormal.getWidth(this) > 200 && x < 200 + 50
                && y + iconoNormal.getHeight(this) > 250 && y < 250 + 50) {
            moneda1Activa = false;
            monedaSinDesbloquear1 = monedaDesbloqueada1; // Cambiar la imagen de la moneda 1 a desbloqueada
            reproducirSonidoLogro();
        }

        // Detectar colisión con la segunda moneda
        if (moneda2Activa && x + iconoNormal.getWidth(this) > 340 && x < 340 + 50
                && y + iconoNormal.getHeight(this) > 250 && y < 250 + 50) {
            moneda2Activa = false;
            monedaSinDesbloquear2 = monedaDesbloqueada2; // Cambiar la imagen de la moneda 2 a desbloqueada
            reproducirSonidoLogro();

        }

        // Detectar colisión con la tercera moneda
        if (moneda3Activa && x + iconoNormal.getWidth(this) > 470 && x < 470 + 50
                && y + iconoNormal.getHeight(this) > 250 && y < 250 + 50) {
            moneda3Activa = false;
            monedaSinDesbloquear3 = monedaDesbloqueada3; // Cambiar la imagen de la moneda 3 a desbloqueada
            reproducirSonidoLogro();

        }
    }

    private void detectarColisionPuertaFinal() {
        // Coordenadas y dimensiones de la puerta final
        int puertaX = 750;
        int puertaY = 460;
        int puertaAncho = puertaFinal.getWidth(this);
        int puertaAlto = puertaFinal.getHeight(this);

        // Detectar colisión con la puerta
        if (x + iconoNormal.getWidth(this) > puertaX && x < puertaX + puertaAncho
                && y + iconoNormal.getHeight(this) > puertaY && y < puertaY + puertaAlto) {

            reproducirSonidoVictoria();
            detenerMusicaFondo();

            // Preguntar si desea volver a jugar
            int opcion = JOptionPane.showConfirmDialog(this, "Has ganado! ¿Deseas volver a jugar?", "Volver a jugar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (opcion == JOptionPane.YES_OPTION) {
                reproducirMusicaFondo();

                x = 0;
                y = 490;
                velY = 0;

                // Reiniciar estado de las monedas
                moneda1Activa = true;
                moneda2Activa = true;
                moneda3Activa = true;

                // Cambiar las imágenes de las monedas a "sin desbloquear"
                monedaSinDesbloquear1 = new ImageIcon(getClass().getResource("/imgs/moneda_sin_desbloquear.PNG")).getImage();
                monedaSinDesbloquear2 = new ImageIcon(getClass().getResource("/imgs/moneda_sin_desbloquear.PNG")).getImage();
                monedaSinDesbloquear3 = new ImageIcon(getClass().getResource("/imgs/moneda_sin_desbloquear.PNG")).getImage();

            } else {
                // Preguntar si está seguro de salir
                int confirmarSalir = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas salir?", "Confirmar salida",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (confirmarSalir == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                velX = -2;  // Mueve hacia la izquierda
                mirandoIzquierda = true;  // Cambia la dirección a la izquierda
            }

            if (key == KeyEvent.VK_RIGHT) {
                velX = 2;   // Mueve hacia la derecha
                mirandoIzquierda = false;  // Cambia la dirección a la derecha
            }

            // Salto (solo se puede saltar si está en el suelo)
            if (key == KeyEvent.VK_UP && enElSuelo) {
                velY = -15;  // Fuerza del salto
                enElSuelo = false; // El personaje ya no está en el suelo
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

    public void reproducirMusicaFondo() {
        audioMusicaFondo.loop();
    }

    public void detenerMusicaFondo() {
        audioMusicaFondo.stop();
    }

    public void reproducirSonidoMuerte() {
        audioMuerte.play();
    }

    public void reproducirSonidoLogro() {
        audioMoneda.play();
    }

    public void reproducirSonidoVictoria() {
        audioVictoria.play();
    }

}
