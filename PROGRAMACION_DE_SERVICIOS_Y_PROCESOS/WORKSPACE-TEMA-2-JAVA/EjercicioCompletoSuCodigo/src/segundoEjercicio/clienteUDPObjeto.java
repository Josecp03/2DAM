package segundoEjercicio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;

public class clienteUDPObjeto {
	
	public static void main(String[] args) throws Exception {
		
		DatagramSocket clientSocket = new DatagramSocket();
		byte[] recibidos = new byte[1024];
		
		InetAddress IPServidor = InetAddress.getLocalHost();
		int puerto = 9876;
		
		Persona per = new Persona("Maria",22);
		
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bs);
		os.writeObject(per);
		os.close();
		byte[] bytes = bs.toByteArray();
		
		// ENVIANDO AL SERVIDOR
		System.out.println("Enviando " + bytes.length + " bytes al servidor");
		DatagramPacket envio = new DatagramPacket(bytes, bytes.length);
		System.out.println("Esperando datagram.... ");
		clientSocket.receive(envio);
		
		// RECIBIENDO DEL SERVIDOR
		DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
		System.out.println("Esperando datagrama...");
		clientSocket.receive(recibo);
			
		// CONVERTIMOS bytes a objeto
		ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
		ObjectInputStream in = new ObjectInputStream(bais);
		Persona persona = (Persona) in.readObject();
		in.close();
		
		// Visualizo los datos
		InetAddress IPOrigen = recibo.getAddress();
		int puertoOrigen = recibo.getPort();
		System.out.println("\tPROCEDENTE DE : "+IPOrigen+" : "+puertoOrigen);
		System.out.println("\tDatos: "+persona.getNombre()+"*"+persona.getEdad());
		clientSocket.close();
		
	}
}