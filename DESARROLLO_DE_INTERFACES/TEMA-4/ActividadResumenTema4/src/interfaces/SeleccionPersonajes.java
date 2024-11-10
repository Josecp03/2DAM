/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import actividadlaf.CentrarColumnas;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author josec
 */
public class SeleccionPersonajes extends javax.swing.JFrame {

    private String pokemonActual = "";
    private String evolucionActual = "";
    private int contadorClicker = 0;
    private final int MAX_CLICKS = 300;
    AudioClip audiobulbasur = Applet.newAudioClip(getClass().getResource("/audios/SonidoBulbasur.wav"));
    AudioClip audioCharmander = Applet.newAudioClip(getClass().getResource("/audios/SonidoCharmander.wav"));
    AudioClip audioSquirtle = Applet.newAudioClip(getClass().getResource("/audios/SonidoSquirtle.wav"));
    AudioClip audioEvolucion = Applet.newAudioClip(getClass().getResource("/audios/SonidoEvolucion.wav"));
    AudioClip audioClick = Applet.newAudioClip(getClass().getResource("/audios/SonidoClick.wav"));
    AudioClip audioIvysaur = Applet.newAudioClip(getClass().getResource("/audios/SonidoIvysaur.wav"));
    AudioClip audioVenusaur = Applet.newAudioClip(getClass().getResource("/audios/SonidoVenusaur.wav"));
    AudioClip audioCharmeleon = Applet.newAudioClip(getClass().getResource("/audios/SonidoCharmeleon.wav"));
    AudioClip audioCharizard = Applet.newAudioClip(getClass().getResource("/audios/SonidoCharizard.wav"));
    AudioClip audioWartortle = Applet.newAudioClip(getClass().getResource("/audios/SonidoWartortle.wav"));
    AudioClip audioBlastoise = Applet.newAudioClip(getClass().getResource("/audios/SonidoBlastoise.wav"));
    AudioClip audioVictoria = Applet.newAudioClip(getClass().getResource("/audios/SonidoVictoria.wav"));
    AudioClip audioLogro = Applet.newAudioClip(getClass().getResource("/audios/SonidoLogro.wav"));
    AudioClip audioMewtwo = Applet.newAudioClip(getClass().getResource("/audios/SonidoMewtwo.wav"));
    private int numClicksBulbasur = 0;
    private int numClicksCharmander = 0;
    private int numClicksSquirtle = 0;
    private int numClicksMewTwo = 0;
    private int numClicksIvysaur = 0;
    private int numClicksCharmeleon = 0;
    private int numClicksWartortle = 0;
    private int numClicksVenusaur = 0;
    private int numClicksCharizard = 0;
    private int numClicksBlastoise = 0;
    private String nick = "";
    private boolean estadisticasDisponibles = false;

    public boolean isEstadisticasDisponibles() {
        return estadisticasDisponibles;
    }

    public void setEstadisticasDisponibles(boolean estadisticasDisponibles) {
        this.estadisticasDisponibles = estadisticasDisponibles;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEvolucionActual() {
        return evolucionActual;
    }

    public void setEvolucionActual(String evolucionActual) {
        this.evolucionActual = evolucionActual;
    }

    public String getPokemonActual() {
        return pokemonActual;
    }

    public void setPokemonActual(String pokemonEscogido) {
        this.pokemonActual = pokemonEscogido;
    }

    public int getContadorClicker() {
        return contadorClicker;
    }

    public void setContadorClicker(int contadorClicker) {
        this.contadorClicker = contadorClicker;
    }

    /**
     * Creates new form SeleccionPersonajes
     */
    public SeleccionPersonajes() {

        // Inicializar los componentes
        initComponents();

        // Establecer un tamaño determinado a la interfaz
        setSize(1000, 575);

        // Incapacitar al usuario para redimensionar el tamaño de la interfaz
        setResizable(false);

        // Centrar la interfaz en medio de la pantalla
        setLocationRelativeTo(null);

        //  Establecer el icono de la interfaz
        this.setIconImage(new ImageIcon(getClass().getResource("/imgs/Logo.png")).getImage());

        if (contadorClicker < 100) {
            jButtonMewtwo.setEnabled(false);
        }

        // Configurar Progress Bar
        jProgressBar1.setMinimum(0);
        jProgressBar1.setMaximum(MAX_CLICKS);
        jProgressBar1.setStringPainted(true);

        // Hacer transparente el JPanel donde me salen las advertencias
        jPanelMensaje.setOpaque(false);

        // Borrar botón Salir
        jButtonSalir.setVisible(false);

        // Configuración columnas tabla
        jTableEstadisticas.setDefaultRenderer(Object.class, new CentrarColumnas());

        // Desactivar el boton de clicker hasta que se escoja el personaje
        jButtonClicker.setEnabled(false);

        // Cambiar el mensaje de ayuda
        jLabelAyuda.setText("Elige primero al personaje!");

        // Borrar componentes
        jProgressBar1.setVisible(false);
        jLabelMultiplicador.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelSeleccionPersonajes = new javax.swing.JPanel();
        jPanelImagenn3 = new jpanelimagenn.JPanelImagenn();
        jLabelNick = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButtonBulbasur = new javax.swing.JButton();
        jButtonCharmander = new javax.swing.JButton();
        jButtonSquirtle = new javax.swing.JButton();
        jButtonMewtwo = new javax.swing.JButton();
        jPanelClicker = new javax.swing.JPanel();
        jPanelImagenn4 = new jpanelimagenn.JPanelImagenn();
        jPanelImagenn5 = new jpanelimagenn.JPanelImagenn();
        jButtonClicker = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanelMensaje = new javax.swing.JPanel();
        jLabelMensajeVictoria = new javax.swing.JLabel();
        jButtonSalir = new javax.swing.JButton();
        jLabelAyuda = new javax.swing.JLabel();
        jLabelMultiplicador = new javax.swing.JLabel();
        jPanelEstadisticas = new javax.swing.JPanel();
        jPanelImagenn2 = new jpanelimagenn.JPanelImagenn();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEstadisticas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanelSeleccionPersonajes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelSeleccionPersonajesMouseClicked(evt);
            }
        });

        jPanelImagenn3.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Users/josec/Documents/NetBeansProjects/TEMA-4/ActividadResumenTema4/src/imgs/FondoCielo.png"), 1.0f));
        jPanelImagenn3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNick.setFont(new java.awt.Font("Showcard Gothic", 1, 28)); // NOI18N
        jLabelNick.setForeground(new java.awt.Color(251, 203, 4));
        jLabelNick.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanelImagenn3.add(jLabelNick, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 40, 990, -1));

        jPanel4.setLayout(new java.awt.GridLayout(2, 2, 3, 5));

        jButtonBulbasur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Bulbasur.png"))); // NOI18N
        jButtonBulbasur.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonBulbasur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBulbasurActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonBulbasur);

        jButtonCharmander.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Charmander.png"))); // NOI18N
        jButtonCharmander.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCharmander.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCharmanderActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonCharmander);

        jButtonSquirtle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Squirtle.png"))); // NOI18N
        jButtonSquirtle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSquirtle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSquirtleActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonSquirtle);

        jButtonMewtwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/MewTwo.png"))); // NOI18N
        jButtonMewtwo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonMewtwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMewtwoActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonMewtwo);

        jPanelImagenn3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 670, 330));

        javax.swing.GroupLayout jPanelSeleccionPersonajesLayout = new javax.swing.GroupLayout(jPanelSeleccionPersonajes);
        jPanelSeleccionPersonajes.setLayout(jPanelSeleccionPersonajesLayout);
        jPanelSeleccionPersonajesLayout.setHorizontalGroup(
            jPanelSeleccionPersonajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImagenn3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelSeleccionPersonajesLayout.setVerticalGroup(
            jPanelSeleccionPersonajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImagenn3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Personajes", jPanelSeleccionPersonajes);

        jPanelImagenn5.setImagenFondo(new jpanelimagenn.ImagenFondo(new java.io.File("C:/Users/josec/Documents/NetBeansProjects/TEMA-4/ActividadResumenTema4/src/imgs/FondoClicker.png"), 1.0f));
        jPanelImagenn5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonClicker.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonClicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClickerActionPerformed(evt);
            }
        });
        jPanelImagenn5.add(jButtonClicker, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 140, 110));
        jPanelImagenn5.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 400, 290, 40));

        javax.swing.GroupLayout jPanelMensajeLayout = new javax.swing.GroupLayout(jPanelMensaje);
        jPanelMensaje.setLayout(jPanelMensajeLayout);
        jPanelMensajeLayout.setHorizontalGroup(
            jPanelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanelMensajeLayout.setVerticalGroup(
            jPanelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jPanelImagenn5.add(jPanelMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 300, 150));

        jLabelMensajeVictoria.setFont(new java.awt.Font("Showcard Gothic", 1, 28)); // NOI18N
        jLabelMensajeVictoria.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMensajeVictoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanelImagenn5.add(jLabelMensajeVictoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 40, 1000, -1));

        jButtonSalir.setFont(new java.awt.Font("Showcard Gothic", 1, 28)); // NOI18N
        jButtonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Salir.png"))); // NOI18N
        jButtonSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        jPanelImagenn5.add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, -1, -1));

        jLabelAyuda.setFont(new java.awt.Font("Showcard Gothic", 1, 28)); // NOI18N
        jLabelAyuda.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAyuda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAyuda.setText("Haz click en el botón para ver el progreso! ");
        jPanelImagenn5.add(jLabelAyuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1000, -1));

        jLabelMultiplicador.setFont(new java.awt.Font("Showcard Gothic", 1, 28)); // NOI18N
        jLabelMultiplicador.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMultiplicador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMultiplicador.setText("X1");
        jPanelImagenn5.add(jLabelMultiplicador, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 450, 80, -1));

        javax.swing.GroupLayout jPanelImagenn4Layout = new javax.swing.GroupLayout(jPanelImagenn4);
        jPanelImagenn4.setLayout(jPanelImagenn4Layout);
        jPanelImagenn4Layout.setHorizontalGroup(
            jPanelImagenn4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImagenn4Layout.createSequentialGroup()
                .addComponent(jPanelImagenn5, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelImagenn4Layout.setVerticalGroup(
            jPanelImagenn4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImagenn5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelClickerLayout = new javax.swing.GroupLayout(jPanelClicker);
        jPanelClicker.setLayout(jPanelClickerLayout);
        jPanelClickerLayout.setHorizontalGroup(
            jPanelClickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImagenn4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelClickerLayout.setVerticalGroup(
            jPanelClickerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImagenn4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Clicker", jPanelClicker);

        jPanelEstadisticas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelEstadisticasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelImagenn2Layout = new javax.swing.GroupLayout(jPanelImagenn2);
        jPanelImagenn2.setLayout(jPanelImagenn2Layout);
        jPanelImagenn2Layout.setHorizontalGroup(
            jPanelImagenn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanelImagenn2Layout.setVerticalGroup(
            jPanelImagenn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTableEstadisticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableEstadisticas);

        javax.swing.GroupLayout jPanelEstadisticasLayout = new javax.swing.GroupLayout(jPanelEstadisticas);
        jPanelEstadisticas.setLayout(jPanelEstadisticasLayout);
        jPanelEstadisticasLayout.setHorizontalGroup(
            jPanelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEstadisticasLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1001, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelImagenn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelEstadisticasLayout.setVerticalGroup(
            jPanelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEstadisticasLayout.createSequentialGroup()
                .addComponent(jPanelImagenn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Estadisticas", jPanelEstadisticas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1002, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

        if (contadorClicker > 0 && !estadisticasDisponibles) {
            JOptionPane.showMessageDialog(
                null,
                "No puedes cambiar de pestaña ahora",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );

            jTabbedPane1.setSelectedIndex(1);
        }

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanelEstadisticasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEstadisticasMouseClicked

    }//GEN-LAST:event_jPanelEstadisticasMouseClicked

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed

        // Mostrar un cuadro de diálogo de confirmación
        int eleccion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de que quiere salir del juego?",
            "Confirmación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        // Verificar la respuesta del usuario
        if (eleccion == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonClickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClickerActionPerformed

        // Reproducir sonido cada vez que hago click
        audioClick.play();

        // Comprobar que el contador sea menor a la meta final
        if (contadorClicker < MAX_CLICKS) {

            if (pokemonActual.equals("MewTwo")) {

                // Incrementar el contador
                contadorClicker += 10;

            } else if (evolucionActual.equals("SegundaEvolucion")) {

                // Incrementar el contador
                contadorClicker += 3;

            } else if (evolucionActual.equals("TerceraEvolucion")) {

                // Incrementar el contador
                contadorClicker += 5;

            } else {

                // Incrementar el contador
                contadorClicker++;
            }

            // Actualizar la barra de progreso
            jProgressBar1.setValue(contadorClicker);
        }

        /*
        Evoluciones Bulbasur
        */
        if (contadorClicker == 50 && pokemonActual.equals("Bulbasur")) {

            // Asignar al Clicker la imagen correspondiente
            jButtonClicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Ivysaur.png")));

            // Reproducir el sonidos
            audioEvolucion.play();
            audioIvysaur.play();

            // Mostrar mensaje de que el pokemon ha evolucionado
            JOptionPane.showMessageDialog(jPanelMensaje, "Has conseguido evolucionar tu Bulbasur a un Ivysaur", "Enhorabuena!", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar el pokemon actual
            setEvolucionActual("SegundaEvolucion");

            // Actualizar multiplicador
            jLabelMultiplicador.setText("X3");
        }

        if (contadorClicker == 101 && pokemonActual.equals("Bulbasur")) {

            // Asignar al Clicker la imagen correspondiente
            jButtonClicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Venusaur.png")));

            // Reproducir el sonidos
            audioEvolucion.play();
            audioVenusaur.play();

            // Mostrar mensaje de que el pokemon ha evolucionado
            JOptionPane.showMessageDialog(jPanelMensaje, "Has conseguido evolucionar tu Ivysaur a un Venusaur", "Enhorabuena!", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar la evolución actual
            setEvolucionActual("TerceraEvolucion");

            // Actualizar multiplicador
            jLabelMultiplicador.setText("X5");

        }

        if (contadorClicker == 151) {

            // Reproducir el sonidos
            audioLogro.play();

            // Mostrar mensaje de que el pokemon has desbloqueado un nuevo pokemon
            JOptionPane.showMessageDialog(jPanelMensaje, "Has conseguido un nuevo pokemon", "Enhorabuena!", JOptionPane.INFORMATION_MESSAGE);

            // Debloquear el nuevo personaje
            jButtonMewtwo.setEnabled(true);

            // Mostrar un cuadro de diálogo de confirmación
            int eleccion = JOptionPane.showConfirmDialog(
                jPanelMensaje,
                "¿Quieres ir a seleccionarlo?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            // Verificar la respuesta del usuario
            if (eleccion == JOptionPane.YES_OPTION) {

                // Pasar a la pestaña de Seleccion de Personajes
                jTabbedPane1.setSelectedIndex(0);

                // Desactivar los otros pokemons
                jButtonBulbasur.setEnabled(false);
                jButtonCharmander.setEnabled(false);
                jButtonSquirtle.setEnabled(false);
                setPokemonActual("MewTwo");

            }

            // Actualizar el pokemon actual
            setPokemonActual("TerceraEvolucion");

            // Actualizar multiplicador
            jLabelMultiplicador.setText("X5");

        }

        /*
        Evoluciones Charmander
        */
        if (contadorClicker == 50 && pokemonActual.equals("Charmander")) {

            // Asignar al Clicker la imagen correspondiente
            jButtonClicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Charmeleon.png")));

            // Reproducir el sonidos
            audioEvolucion.play();
            audioCharmeleon.play();

            // Mostrar mensaje de que el pokemon ha evolucionado
            JOptionPane.showMessageDialog(jPanelMensaje, "Has conseguido evolucionar tu Charmander a un Charmeleon", "Enhorabuena!", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar el pokemon actual
            setEvolucionActual("SegundaEvolucion");

            // Actualizar multiplicador
            jLabelMultiplicador.setText("X3");

        }

        if (contadorClicker == 101 && pokemonActual.equals("Charmander")) {

            // Asignar al Clicker la imagen correspondiente
            jButtonClicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Charizard.png")));

            // Reproducir el sonidos
            audioEvolucion.play();
            audioCharizard.play();

            // Mostrar mensaje de que el pokemon ha evolucionado
            JOptionPane.showMessageDialog(jPanelMensaje, "Has conseguido evolucionar tu Charmeleon a un Charizard", "Enhorabuena!", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar la evolución actual
            setEvolucionActual("TerceraEvolucion");

            // Actualizar multiplicador
            jLabelMultiplicador.setText("X5");

        }

        /*
        Evoluciones Squirtle
        */
        if (contadorClicker == 50 && pokemonActual.equals("Squirtle")) {

            // Asignar al Clicker la imagen correspondiente
            jButtonClicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Wartortle.png")));

            // Reproducir el sonidos
            audioEvolucion.play();
            audioWartortle.play();

            // Mostrar mensaje de que el pokemon ha evolucionado
            JOptionPane.showMessageDialog(jPanelMensaje, "Has conseguido evolucionar tu Squirtle a un Wartortle", "Enhorabuena!", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar el pokemon actual
            setEvolucionActual("SegundaEvolucion");

            // Actualizar multiplicador
            jLabelMultiplicador.setText("X3");

        }

        if (contadorClicker == 101 && pokemonActual.equals("Squirtle")) {

            // Asignar al Clicker la imagen correspondiente
            jButtonClicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Blastoise.png")));

            // Reproducir el sonidos
            audioEvolucion.play();
            audioBlastoise.play();

            // Mostrar mensaje de que el pokemon ha evolucionado
            JOptionPane.showMessageDialog(jPanelMensaje, "Has conseguido evolucionar tu Waartortle a un Blastoise", "Enhorabuena!", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar la evolución actual
            setEvolucionActual("TerceraEvolucion");

            // Actualizar multiplicador
            jLabelMultiplicador.setText("X5");

        }

        /*
        FIN DEL JUEGO
        */
        if (contadorClicker >= MAX_CLICKS) {

            // Reproducir sonidos
            audioVictoria.play();

            // Hacer desaparecer el botón
            jButtonClicker.setVisible(false);

            // Hacer aparecer el botón de salir
            jButtonSalir.setVisible(true);

            // Borrar la barra de progreso
            jProgressBar1.setVisible(false);

            // Mostrar un mensaje que confirme que has acabado el juego
            jLabelMensajeVictoria.setText("Completaste el juego. Enhorabuena!");

            // Borrar el mensaje de ayuda
            jLabelAyuda.setVisible(false);

            // Borrar el mensaje de multiplicador
            jLabelMultiplicador.setVisible(false);

            // Poder ver las estadísticas
            setEstadisticasDisponibles(true);

            // Mostrar un cuadro de diálogo de confirmación
            int eleccion = JOptionPane.showConfirmDialog(
                jPanelMensaje,
                "¿Quieres ver las estadísticas?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            // Verificar la respuesta del usuario
            if (eleccion == JOptionPane.YES_OPTION) {
                jTabbedPane1.setSelectedIndex(2);
            }

            // Confugurar Tabla
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(new String[]{"Usuario", "Clicks Charmander", "Clicks Charmeleon", "Clicks Charizard", "Clicks Bulbasur", "Clicks Ivysaur", "Clicks Venusaur", "Clicks Squirtle", "Clicks Wartortle", "Clicks Blastoise", "Clicks MewTwo"});
            dtm.addRow(new Object[]{getNick(), numClicksCharmander, numClicksCharmeleon, numClicksCharizard, numClicksBulbasur, numClicksIvysaur, numClicksVenusaur, numClicksSquirtle, numClicksWartortle, numClicksBlastoise, numClicksMewTwo});
            jTableEstadisticas.setModel(dtm);

            // Aplicar el renderizador centrado a todas las columnas
            for (int i = 0; i < dtm.getColumnCount(); i++) {
                jTableEstadisticas.getColumnModel().getColumn(i).setCellRenderer(new CentrarColumnas());
            }

        }

        /*
        GUARDAR ESTADÍSTICAS
        */
        if (pokemonActual.equals("Bulbasur") && contadorClicker < 50) {
            numClicksBulbasur++;
        }

        if (pokemonActual.equals("Charmander") && contadorClicker < 50) {
            numClicksCharmander++;
        }

        if (pokemonActual.equals("Squirtle") && contadorClicker < 50) {
            numClicksSquirtle++;
        }

        if (contadorClicker >= 50 && pokemonActual.equals("Bulbasur")) {
            numClicksIvysaur++;
        }

        if (contadorClicker >= 101 && pokemonActual.equals("Bulbasur")) {
            numClicksVenusaur++;
        }

        if (contadorClicker >= 50 && pokemonActual.equals("Charmander")) {
            numClicksCharmeleon++;
        }

        if (contadorClicker >= 101 && pokemonActual.equals("Charmander")) {
            numClicksCharizard++;
        }

        if (contadorClicker >= 50 && pokemonActual.equals("Squirtle")) {
            numClicksWartortle++;
        }

        if (contadorClicker >= 101 && pokemonActual.equals("Squirtle")) {
            numClicksBlastoise++;
        }

        if (pokemonActual.equals("MewTwo")) {
            numClicksMewTwo++;
        }
    }//GEN-LAST:event_jButtonClickerActionPerformed

    private void jPanelSeleccionPersonajesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSeleccionPersonajesMouseClicked

    }//GEN-LAST:event_jPanelSeleccionPersonajesMouseClicked

    private void jButtonMewtwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMewtwoActionPerformed

        // Mostrar un cuadro de diálogo de confirmación
        int eleccion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de escoger a MewTwo?",
            "Confirmación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        // Verificar la respuesta del usuario
        if (eleccion == JOptionPane.YES_OPTION) {
            setPokemonActual("MewTwo");
            jButtonClicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/MewtwoClicker.png")));
            audioMewtwo.play();
            jTabbedPane1.setSelectedIndex(1);
        }

        // Actualizar multiplicador
        jLabelMultiplicador.setText("X10");

    }//GEN-LAST:event_jButtonMewtwoActionPerformed

    private void jButtonSquirtleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSquirtleActionPerformed

        // Mostrar un cuadro de diálogo de confirmación
        int eleccion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de escoger a Squirtle?",
            "Confirmación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        // Verificar la respuesta del usuario
        if (eleccion == JOptionPane.YES_OPTION) {
            setPokemonActual("Squirtle");
            jButtonClicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Squirtle.png")));
            audioSquirtle.play();
            jTabbedPane1.setSelectedIndex(1);

            // Activar el boton de clicker hasta que se escoja el personaje
            jButtonClicker.setEnabled(true);

            // Cambiar el mensaje de ayuda
            jLabelAyuda.setText("Haz click en el botón para ver el progreso!");

            // Borrar componentes
            jProgressBar1.setVisible(true);
            jLabelMultiplicador.setVisible(true);

        }
    }//GEN-LAST:event_jButtonSquirtleActionPerformed

    private void jButtonCharmanderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCharmanderActionPerformed

        // Mostrar un cuadro de diálogo de confirmación
        int eleccion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de escoger a Charmander?",
            "Confirmación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        // Verificar la respuesta del usuario
        if (eleccion == JOptionPane.YES_OPTION) {
            setPokemonActual("Charmander");
            jButtonClicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Charmander.png")));
            audioCharmander.play();
            jTabbedPane1.setSelectedIndex(1);

            // Activar el boton de clicker hasta que se escoja el personaje
            jButtonClicker.setEnabled(true);

            // Cambiar el mensaje de ayuda
            jLabelAyuda.setText("Haz click en el botón para ver el progreso!");

            // Borrar componentes
            jProgressBar1.setVisible(true);
            jLabelMultiplicador.setVisible(true);

        }
    }//GEN-LAST:event_jButtonCharmanderActionPerformed

    private void jButtonBulbasurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBulbasurActionPerformed

        // Mostrar un cuadro de diálogo de confirmación
        int eleccion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de escoger a Bulbasaur?",
            "Confirmación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        // Verificar la respuesta del usuario
        if (eleccion == JOptionPane.YES_OPTION) {
            setPokemonActual("Bulbasur");
            jButtonClicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Bulbasur.png")));
            audiobulbasur.play();
            jTabbedPane1.setSelectedIndex(1);

            // Activar el boton de clicker hasta que se escoja el personaje
            jButtonClicker.setEnabled(true);

            // Cambiar el mensaje de ayuda
            jLabelAyuda.setText("Haz click en el botón para ver el progreso!");

            // Borrar componentes
            jProgressBar1.setVisible(true);
            jLabelMultiplicador.setVisible(true);

        }

    }//GEN-LAST:event_jButtonBulbasurActionPerformed

    public void establecerNick(String nick) {
        setNick(nick);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBulbasur;
    private javax.swing.JButton jButtonCharmander;
    private javax.swing.JButton jButtonClicker;
    private javax.swing.JButton jButtonMewtwo;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonSquirtle;
    private javax.swing.JLabel jLabelAyuda;
    private javax.swing.JLabel jLabelMensajeVictoria;
    private javax.swing.JLabel jLabelMultiplicador;
    private javax.swing.JLabel jLabelNick;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelClicker;
    private javax.swing.JPanel jPanelEstadisticas;
    private jpanelimagenn.JPanelImagenn jPanelImagenn2;
    private jpanelimagenn.JPanelImagenn jPanelImagenn3;
    private jpanelimagenn.JPanelImagenn jPanelImagenn4;
    private jpanelimagenn.JPanelImagenn jPanelImagenn5;
    private javax.swing.JPanel jPanelMensaje;
    private javax.swing.JPanel jPanelSeleccionPersonajes;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableEstadisticas;
    // End of variables declaration//GEN-END:variables
}
