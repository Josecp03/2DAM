package ejercicio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {

	public static void main(String[] args) throws IOException {
		
		InetAddress destino = InetAddress.getLocalHost();
		int port = 12345;
		byte[] mensaje = new byte[1024];
		
		String Saludo = "Enviando Saludos !!";
		mensaje = Saludo.getBytes();
		
		// Construyo el diagrama que se va a enviar
		DatagramPacket envio = new DatagramPacket
				(mensaje, mensaje.length, destino, port);
		DatagramSocket socket = new DatagramSocket(34567);
		
		System.out.println("Enviando Datagrama de longitud: "+mensaje.length);
		System.out.println("Host destino: "+destino.getHostName());
		System.out.println("IP Destino : "+destino.getHostAddress());
		System.out.println("Puerto local del Socket: "+socket.getLocalPort());
		System.out.println("Puerto al que envío: "+envio.getPort());
		
		socket.send(envio);
		socket.close();
	}
	
}
