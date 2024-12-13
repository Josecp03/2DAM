package ejercicio;

import java.io.*;
import java.net.*;

public class ClienteBinario {
    public static void main(String[] args) {
        String host = "localhost";  // El servidor se ejecuta en el mismo equipo (localhost)
        int puerto = 6000;          // El puerto en el que el servidor está escuchando
        Socket socket = null;
        DataOutputStream flujoSalida = null;
        DataInputStream flujoEntrada = null;
        
        try {
            // Conectar al servidor en localhost y puerto 6000
            socket = new Socket(host, puerto);
            flujoSalida = new DataOutputStream(socket.getOutputStream());  // Flujo para enviar datos
            flujoEntrada = new DataInputStream(socket.getInputStream());    // Flujo para recibir datos
            
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));  // Para leer la entrada del usuario
            int numero;
            
            while (true) {
                // Solicitar número al usuario
                System.out.print("Introduce un número (0 para salir): ");
                numero = Integer.parseInt(teclado.readLine());  // Leer el número desde el teclado
                
                // Enviar número al servidor
                flujoSalida.writeInt(numero);
                
                // Si el número es 0, terminar
                if (numero == 0) {
                    System.out.println("Finalizando la conexión...");
                    break;
                }
                
                // Recibir la representación binaria del número
                String binario = flujoEntrada.readUTF();
                System.out.println("Número en binario recibido: " + binario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (flujoSalida != null) flujoSalida.close();  // Cerrar flujo de salida
                if (flujoEntrada != null) flujoEntrada.close();  // Cerrar flujo de entrada
                if (socket != null) socket.close();  // Cerrar el socket
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
