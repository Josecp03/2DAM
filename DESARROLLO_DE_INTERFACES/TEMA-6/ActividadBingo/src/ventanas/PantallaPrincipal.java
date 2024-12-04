/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author josec
 */
public class PantallaPrincipal extends javax.swing.JFrame {

    static int matriz[][] = new int[5][5];
    private ArrayList<Integer> numerosGenerados = new ArrayList<>();

    /**
     * Creates new form PantallaPrincipal
     */
    public PantallaPrincipal() {

        // Inicializar los componentes
        initComponents();

        // Establecer un tamaño determinado a la interfaz
        setSize(639, 358);

        // Incapacitar al usuario para redimensionar el tamaño de la interfaz
        setResizable(false);

        // Centrar la interfaz en medio de la pantalla
        setLocationRelativeTo(null);

        // Establecer imagen fondo
        String relativePath = "src/imgs/FondoVerde.jpg";
        jPanelImagenn1.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File(relativePath), 1.0f));
        
        // Establecer el icono de la interfaz
        this.setIconImage(new ImageIcon(getClass().getResource("/imgs/bingo.png")).getImage());
        
        // Crear la matriz
        crearMatriz();

        // Mostrar la matriz en el GridLayout
        mostrarMatriz();

        jButtonNuevoNumero.setFont(new Font("Futura", Font.BOLD, 15));
        jLabelNumero.setFont(new Font("Futura", Font.BOLD, 20));
        jLabelMensajeVictoria.setFont(new Font("Futura", Font.BOLD, 20));

    }

    private void crearMatriz() {

        int inicio = 1;
        int num = 0;

        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 5; col++) {

                // Genera un número aleatorio entre inicio e inicio + 14
                num = (int) (Math.random() * 15) + inicio;

                // Verificar que el número no se repita en el cartón
                while (buscaNum(num)) {
                    num = (int) (Math.random() * 15) + inicio;
                }

                // Aisgna el número al cartón en la posición correspondiente
                matriz[fila][col] = num;

            }

            // Aumenta el rango de números en 15 para la siguiente columna
            inicio = inicio + 15;

        }

        // La posición central del cartón es un espaicio libre (0)
        matriz[2][2] = 0;

    }

    // Método que verifica si un número ya está en el cartón paa evitar repeticiones
    public static boolean buscaNum(int valor) {

        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 5; col++) {

                if (valor == matriz[fila][col]) {
                    return true;
                }

            }

        }

        return false;

    }

    private void mostrarMatriz() {
        // Primero creamos los números en el cartón
        crearMatriz();

        // Limpiar el panel antes de agregar los nuevos JLabel
        jPanelCarton.removeAll();

        // Crear un borde con el color personalizado
        javax.swing.border.Border bordeVerde = BorderFactory.createLineBorder(Color.decode("#021707"), 3);

        // Aplicar el borde al jPanelCarton
        jPanelCarton.setBorder(bordeVerde);

        // Crear un JLabel para cada número y agregarlo al GridLayout
        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 5; col++) {
                JLabel label = new JLabel();
                String texto;

                if (matriz[fila][col] == 0) {
                    label.setIcon(new ImageIcon(getClass().getResource("/imgs/bingo.png"))); // Aquí puedes poner una imagen especial para la casilla libre
                    label.setBackground(Color.decode("#021707")); // Color para la casilla libre
                } else {
                    texto = String.valueOf(matriz[fila][col]);
                    label.setText(texto);
                    label.setBackground(Color.decode("#2D572C"));
                }

                // Si es la posición [2][2], marcar como siempre roja
                if (fila == 2 && col == 2) {
                    label.setBackground(Color.RED);  // Siempre marcado
                }

                // Configurar el estilo del JLabel
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setFont(new Font("Futura", Font.BOLD, 20));
                label.setOpaque(true);
                label.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));

                // Agregar el JLabel al GridLayout
                jPanelCarton.add(label);
            }
        }

        // Actualizar el panel para reflejar los cambios
        jPanelCarton.revalidate();
        jPanelCarton.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelImagenn1 = new jpanelimagenn.JPanelImagenn();
        jPanelCarton = new javax.swing.JPanel();
        jButtonNuevoNumero = new javax.swing.JButton();
        jLabelNumero = new javax.swing.JLabel();
        jLabelMensajeVictoria = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelImagenn1.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Program Files/NetBeans 8.2"), 0.5f));
        jPanelImagenn1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelCarton.setLayout(new java.awt.GridLayout(5, 5));
        jPanelImagenn1.add(jPanelCarton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 390, 230));

        jButtonNuevoNumero.setBackground(new java.awt.Color(45, 87, 44));
        jButtonNuevoNumero.setForeground(new java.awt.Color(255, 255, 255));
        jButtonNuevoNumero.setText("NUEVO NÚMERO");
        jButtonNuevoNumero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonNuevoNumero.setFocusPainted(false);
        jButtonNuevoNumero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonNuevoNumeroMouseClicked(evt);
            }
        });
        jPanelImagenn1.add(jButtonNuevoNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 190, 50));

        jLabelNumero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanelImagenn1.add(jLabelNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 180, 50));

        jLabelMensajeVictoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanelImagenn1.add(jLabelMensajeVictoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 160, 90));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImagenn1, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImagenn1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoNumeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNuevoNumeroMouseClicked

        int nuevoNumero;

        // Generar un nuevo número único que no esté en la lista
        do {
            nuevoNumero = (int) (Math.random() * 75) + 1;
        } while (numerosGenerados.contains(nuevoNumero));  // Si el número ya ha sido generado, generar otro número

        jLabelNumero.setText(String.valueOf(nuevoNumero));

        // Almacenar el nuevo número en la lista de números generados
        numerosGenerados.add(nuevoNumero);

        System.out.println("Número generado: " + nuevoNumero);

        boolean encontrado = false;

        // Recorremos la matriz para encontrar el número
        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 5; col++) {
                if (matriz[fila][col] == nuevoNumero) {
                    // Cambiar el fondo a rojo
                    JLabel label = (JLabel) jPanelCarton.getComponent(fila * 5 + col);
                    label.setBackground(Color.RED);
                    label.setText(String.valueOf(nuevoNumero));  // Actualiza el texto si es necesario
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {
                break;
            }
        }

        // Verificar victoria
        if (verificarVictoria()) {
            jLabelMensajeVictoria.setText("Has ganado!");

            // Mostrar un JOptionPane para informar de la victoria
            JOptionPane.showMessageDialog(this, "¡Has ganado!", "Victoria", JOptionPane.INFORMATION_MESSAGE);

            dispose();
        }


    }//GEN-LAST:event_jButtonNuevoNumeroMouseClicked

    private boolean verificarVictoria() {
        // Verificar filas
        for (int fila = 0; fila < 5; fila++) {
            boolean filaCompleta = true;
            for (int col = 0; col < 5; col++) {
                // Si estamos en la casilla central (2,2), la contamos como marcada
                if ((fila == 2 && col == 2) || ((JLabel) jPanelCarton.getComponent(fila * 5 + col)).getBackground() == Color.RED) {
                    continue;
                } else {
                    filaCompleta = false;
                    break;
                }
            }
            if (filaCompleta) {
                return true;
            }
        }

        // Verificar columnas
        for (int col = 0; col < 5; col++) {
            boolean columnaCompleta = true;
            for (int fila = 0; fila < 5; fila++) {
                // Si estamos en la casilla central (2,2), la contamos como marcada
                if ((fila == 2 && col == 2) || ((JLabel) jPanelCarton.getComponent(fila * 5 + col)).getBackground() == Color.RED) {
                    continue;
                } else {
                    columnaCompleta = false;
                    break;
                }
            }
            if (columnaCompleta) {
                return true;
            }
        }

        // Verificar diagonal principal
        boolean diagonalPrincipalCompleta = true;
        for (int i = 0; i < 5; i++) {
            // Si estamos en la casilla central (2,2), la contamos como marcada
            if ((i == 2 && i == 2) || ((JLabel) jPanelCarton.getComponent(i * 5 + i)).getBackground() == Color.RED) {
                continue;
            } else {
                diagonalPrincipalCompleta = false;
                break;
            }
        }
        if (diagonalPrincipalCompleta) {
            return true;
        }

        // Verificar diagonal secundaria
        boolean diagonalSecundariaCompleta = true;
        for (int i = 0; i < 5; i++) {
            // Si estamos en la casilla central (2,2), la contamos como marcada
            if ((i == 2 && (4 - i) == 2) || ((JLabel) jPanelCarton.getComponent(i * 5 + (4 - i))).getBackground() == Color.RED) {
                continue;
            } else {
                diagonalSecundariaCompleta = false;
                break;
            }
        }
        if (diagonalSecundariaCompleta) {
            return true;
        }

        // Si no se ha completado ninguna fila, columna ni diagonal
        return false;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNuevoNumero;
    private javax.swing.JLabel jLabelMensajeVictoria;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JPanel jPanelCarton;
    private jpanelimagenn.JPanelImagenn jPanelImagenn1;
    // End of variables declaration//GEN-END:variables
}
