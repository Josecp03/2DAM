package ejercicioEntregable;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCPNumeros {
    
	public static void main(String[] args) {
        
		String host = "localhost";
        int puerto = 5000;
        
        try (Socket socket = new Socket(host, puerto)) {
            
        	System.out.println("Conectado al servidor TCP " + host + ":" + puerto);
            
            // Creamos streams de objetos
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            Scanner sc = new Scanner(System.in);
            int numero;
            
            while (true) {
                
            	System.out.print("Introduce un número (<= 0 para terminar): ");
                numero = sc.nextInt();
                
                // Creamos el objeto Numeros
                Numeros nums = new Numeros();
                nums.setNumero(numero);
                nums.setCuadrado(0);
                nums.setCubo(0);
                
                // Enviamos el objeto al servidor
                oos.writeObject(nums);
                oos.flush();
                
                if (numero <= 0) {
                    System.out.println("Finalizando cliente...");
                    break;
                }
                
                // Recibimos el objeto modificado
                try {
                    Numeros numsRecibidos = (Numeros) ois.readObject();
                    System.out.println("Servidor ha calculado -> Cuadrado: " 
                            + numsRecibidos.getCuadrado() 
                            + ", Cubo: " 
                            + numsRecibidos.getCubo());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            
            sc.close();
            ois.close();
            oos.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
