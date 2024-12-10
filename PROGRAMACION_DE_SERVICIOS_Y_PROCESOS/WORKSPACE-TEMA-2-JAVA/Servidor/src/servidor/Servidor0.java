package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor0 {
	public static void main(String[] args) {
		int puerto = 6000;
		String host = "localhost";
		ServerSocket servidor;
		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Escuchando en "+servidor.getLocalPort());
			Socket cliente1 = servidor.accept();
			Socket cliente2 = servidor.accept();
			servidor.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
