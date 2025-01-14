package hilos;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int puerto = 6000;

        Socket cliente = new Socket(host, puerto);
        BufferedReader fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        PrintWriter fsalida = new PrintWriter(cliente.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Conectado al servidor. Comienza el juego. Tienes 20 intentos. Escribe '*' para salir.");

        String respuesta;
        while (true) {
            String posicion;

            // Validación del formato de la entrada
            while (true) {
                System.out.print("Introduce posición (fila,columna o '*'): ");
                posicion = in.readLine();

                if (posicion.equals("*")) {
                    break; // El asterisco es válido
                }

                if (posicion.matches("\\d+,\\d+")) {
                    String[] partes = posicion.split(",");
                    int fila = Integer.parseInt(partes[0]);
                    int columna = Integer.parseInt(partes[1]);

                    if (fila >= 0 && fila < 10 && columna >= 0 && columna < 10) {
                        break; // Entrada válida
                    } else {
                        System.out.println("Las coordenadas deben estar entre 0 y 9. Intenta de nuevo.");
                    }
                } else {
                    System.out.println("Formato inválido. Usa 'fila,columna' (ejemplo: 3,4). Intenta de nuevo.");
                }
            }

            fsalida.println(posicion);

            if (posicion.equals("*")) {
                respuesta = fentrada.readLine();
                System.out.println(respuesta); // Mensaje del servidor
                break;
            }

            respuesta = fentrada.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

            if (respuesta.equals("¡Has alcanzado el límite de intentos! Fin del juego.") || 
                respuesta.equals("¡Has ganado!")) {
                break;
            }

            // Recibir intentos restantes
            respuesta = fentrada.readLine();
            System.out.println(respuesta);
        }

        fentrada.close();
        fsalida.close();
        cliente.close();
        System.out.println("Fin del juego.");
    }
}
