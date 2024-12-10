package servidor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente0 {
	
	public static void main(String[] args) {
		String Host = "localhost";
		int puerto = 6000;
		Socket Cliente;
		
		try {
			
				Cliente = new Socket(Host,puerto);
			 
			InetAddress ip = Cliente.getInetAddress();
			System.out.println("Puerta local: "+Cliente.getLocalPort());
			System.out.println("Puerto remoto: "+Cliente.getPort());
			System.out.println("Host remoto: "+ip.getHostName());
			System.out.println("IP host remoto: "+ip.getHostAddress());
			
			Cliente.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

		
