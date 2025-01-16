package ejercicioEntregable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCPNumeros {
    
	public static void main(String[] args) {
        
		int puerto = 5000;
        
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            
        	System.out.println("Servidor TCP escuchando en el puerto " + puerto + "...");
            
            // Aceptamos la conexión del cliente
            try (Socket socket = servidor.accept()) {
                
            	System.out.println("Cliente conectado: " + socket.getInetAddress() + ":" + socket.getPort());
                
                // Streams de entrada y salida de objetos
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                
                while (true) {
                    
                	// Recibimos el objeto Números
                    Numeros nums = (Numeros) ois.readObject();
                    
                    // Si el número <= 0, fin del servidor
                    if (nums.getNumero() <= 0) {
                        System.out.println("Finalizando servidor...");
                        break;
                    }
                    
                    // Calcular cuadrado y cubo
                    int numero = nums.getNumero();
                    nums.setCuadrado((long) numero * numero);
                    nums.setCubo((long) numero * numero * numero);
                    
                    System.out.println("Servidor ha recibido " + numero 
                            + ", cuadrado=" + nums.getCuadrado() 
                            + ", cubo=" + nums.getCubo());
                    
                    // Enviamos el objeto de vuelta al cliente
                    oos.writeObject(nums);
                    oos.flush();
                    
                }
                
                // Cerramos los streams
                ois.close();
                oos.close();
                
            }
            
            System.out.println("Servidor cerrado.");
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
