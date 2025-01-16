package ejercicioEntrega;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP {

    public static void main(String[] args) {
        
    	// Puerto en el que el servidor va a escuchar
        final int PUERTO_SERVIDOR = 12345;

        try {
        	
            // Crear un socket UDP en el puerto 12345
            DatagramSocket socket = new DatagramSocket(PUERTO_SERVIDOR);
            System.out.println("Esperando Datagrama .......... ");

            // Buffer para recibir datos
            byte[] bufferRecibo = new byte[1024];

            // Recibir datagrama del cliente
            DatagramPacket datagramaRecibo = new DatagramPacket(bufferRecibo, bufferRecibo.length);
            socket.receive(datagramaRecibo);

            // Extraer información del datagrama
            int bytesRecibidos = datagramaRecibo.getLength();
            String textoRecibido = new String(datagramaRecibo.getData(), 0, bytesRecibidos);
            System.out.println("Texto recibido: " + textoRecibido);

            // Contar la cantidad de 'a' minúscula en el texto
            int contadorA = 0;
            for (char c : textoRecibido.toCharArray()) {
                if (c == 'a') {
                    contadorA++;
                }
            }
            
            // Preparar la respuesta con el número de apariciones
            String respuesta = String.valueOf(contadorA);
            byte[] bufferEnvio = respuesta.getBytes();

            // Obtener la IP y el puerto del cliente para enviar la respuesta
            InetAddress ipCliente = datagramaRecibo.getAddress();
            int puertoCliente = datagramaRecibo.getPort();

            // Construir el datagrama de respuesta
            DatagramPacket datagramaEnvio = new DatagramPacket(
                    bufferEnvio,
                    bufferEnvio.length,
                    ipCliente,
                    puertoCliente
            );

            // Enviar el datagrama con el número de 'a' al cliente
            socket.send(datagramaEnvio);
            System.out.println("Respuesta enviada al cliente. Número de 'a'= " + respuesta);

            // Cerrar el socket
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
