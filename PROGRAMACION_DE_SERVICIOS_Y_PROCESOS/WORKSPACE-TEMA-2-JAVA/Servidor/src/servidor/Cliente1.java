package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente1 {
	public static void main(String[] args) throws  IOException {
		String host = "localhost";
		int puerto = 6001;
		
		System.out.println("PROGRAMA CLIENTE INICIADO...");
		Socket cliente = new Socket(host,puerto);
		
		DataOutputStream flujosalida = new DataOutputStream(cliente.getOutputStream());
		DataInputStream flujoentrada = new DataInputStream(cliente.getInputStream());
		
		String cadena = flujoentrada.readUTF();
		System.out.println("Recibiendo del servidor: \n\t"+cadena);
		
		flujosalida.writeUTF(cadena);
		
		flujoentrada.close();
		flujosalida.close();
		cliente.close();
	}
}