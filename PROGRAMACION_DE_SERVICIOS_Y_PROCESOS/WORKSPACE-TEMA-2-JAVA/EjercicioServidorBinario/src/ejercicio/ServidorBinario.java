package ejercicio;

import java.io.*;
import java.net.*;

public class ServidorBinario {
    public static void main(String[] args) {
        int puerto = 6000;
        ServerSocket servidor = null;
        Socket socketCliente = null;
        DataInputStream flujoEntrada = null;
        DataOutputStream flujoSalida = null;
        
        try {
            servidor = new ServerSocket(puerto);  // Crear el servidor y escuchar en el puerto 6000
            System.out.println("Servidor esperando conexión en el puerto " + puerto + "...");
            socketCliente = servidor.accept();  // Aceptar la conexión del cliente
            
            flujoEntrada = new DataInputStream(socketCliente.getInputStream());  // Flujo de entrada
            flujoSalida = new DataOutputStream(socketCliente.getOutputStream());  // Flujo de salida
            
            while (true) {
                // Leer el número recibido del cliente
                int numero = flujoEntrada.readInt();
                
                // Si el número es 0, salir del bucle
                if (numero == 0) {
                    System.out.println("Número recibido es 0, terminando la conexión.");
                    break;
                }
                
                // Convertir el número a binario
                String binario = Integer.toBinaryString(numero);
                
                // Enviar la representación binaria al cliente
                flujoSalida.writeUTF(binario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (flujoEntrada != null) flujoEntrada.close();
                if (flujoSalida != null) flujoSalida.close();
                if (socketCliente != null) socketCliente.close();
                if (servidor != null) servidor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
