package ejercicioEntrega;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {

    public static void main(String[] args) {
        
    	// Puerto en el que está escuchando el servidor
        final int PUERTO_SERVIDOR = 12345;
        
        try {
        	
            // Leer texto por teclado
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce un texto: ");
            String texto = sc.nextLine();
            sc.close();
            
            // Crear socket UDP 
            DatagramSocket socket = new DatagramSocket();
            
            // Preparar el mensaje a enviar
            byte[] bufferEnvio = texto.getBytes();
            
            // Construir el datagrama con el mensaje, hacia la IP local y puerto del servidor
            InetAddress ipServidor = InetAddress.getByName("localhost");
            DatagramPacket datagramaEnvio = new DatagramPacket(
                    bufferEnvio,
                    bufferEnvio.length,
                    ipServidor,
                    PUERTO_SERVIDOR
            );

            // Enviar el datagrama al servidor
            System.out.println("Enviando texto al servidor...");
            socket.send(datagramaEnvio);

            // Preparar el buffer para recibir la respuesta del servidor
            byte[] bufferRecibo = new byte[1024];
            DatagramPacket datagramaRecibo = new DatagramPacket(bufferRecibo, bufferRecibo.length);

            // Esperar la respuesta
            socket.receive(datagramaRecibo);
            String respuesta = new String(datagramaRecibo.getData()).trim();
            System.out.println("Número de 'a' recibido del servidor: " + respuesta);
            
            // Cerrar el socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
