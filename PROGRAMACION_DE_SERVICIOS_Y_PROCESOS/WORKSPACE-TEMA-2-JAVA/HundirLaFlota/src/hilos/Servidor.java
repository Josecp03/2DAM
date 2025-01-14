package hilos;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(6000);
        System.out.println("Servidor iniciado en el puerto 6000...");
        
        Tablero tablero = new Tablero();

        while (true) {
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado: " + cliente.getInetAddress());

            BufferedReader fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter fsalida = new PrintWriter(cliente.getOutputStream(), true);

            int intentosRestantes = 20; // Límite de intentos
            String mensaje;
            while ((mensaje = fentrada.readLine()) != null) {
                if (mensaje.equals("*")) {
                    fsalida.println("Has terminado la partida manualmente. ¡Adiós!");
                    break;
                }

                try {
                    String[] partes = mensaje.split(",");
                    int fila = Integer.parseInt(partes[0]);
                    int columna = Integer.parseInt(partes[1]);

                    if (fila < 0 || fila >= 10 || columna < 0 || columna >= 10) {
                        fsalida.println("Coordenadas fuera de rango. Intenta de nuevo.");
                        continue;
                    }

                    if (intentosRestantes <= 0) {
                        fsalida.println("¡Has alcanzado el límite de intentos! Fin del juego.");
                        break;
                    }

                    String respuesta = tablero.verificarPosicion(fila, columna);
                    fsalida.println(respuesta);
                    intentosRestantes--;

                    if (!tablero.quedanBarcos()) {
                        fsalida.println("¡Has ganado!");
                        break;
                    }

                    fsalida.println("Intentos restantes: " + intentosRestantes);
                } catch (Exception e) {
                    fsalida.println("Error en el formato de la posición. Intenta de nuevo.");
                }
            }

            fentrada.close();
            fsalida.close();
            cliente.close();
            System.out.println("Cliente desconectado.");
        }
    }
}
