package segundoEjercicio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDPObjeto {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// Asocio el socket al puerto 12345
		DatagramSocket serverSocket = new DatagramSocket(9876);
		System.out.println("Esperando Datagrama .......... ");

		byte[] recibidos = new byte[1024];

		DatagramPacket paqRecibo = new DatagramPacket(recibidos, recibidos.length);

		serverSocket.receive(paqRecibo);// recibo datagrama

		// CONVERTIMOS bytes a objeto
		ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
		ObjectInputStream in = new ObjectInputStream(bais);
		Persona persona = (Persona) in.readObject();
		in.close();
		
        // Obtener la IP y el puerto del cliente para enviar la respuesta
        InetAddress ipOrigen = paqRecibo.getAddress();
        int puerto = paqRecibo.getPort();
		System.out.println("\tPROCEDENTE DE : "+ipOrigen+" : "+puerto);
		System.out.println("\tDatos: "+persona.getNombre()+"*"+persona.getEdad());
        
        // Cambiamos los datos
		persona.setNombre("Maroa Dolores");
		persona.setEdad(34);
		
		//convertimos objeto a bytes
		ByteArrayOutputStream bs= new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream (bs);
		os.writeObject(persona); // this es de tipo DatoUdp
		os.close();
		byte[] bytes = bs.toByteArray(); // devuelve byte[]
		
		System.out.println("Enviando " + bytes.length + " bytes al cliente");
		DatagramPacket envio = new DatagramPacket(bytes, bytes.length,ipOrigen,puerto);
		serverSocket.send(envio);
		serverSocket.close();
		System.out.println("Socket cerrado...");
		



	}

}
