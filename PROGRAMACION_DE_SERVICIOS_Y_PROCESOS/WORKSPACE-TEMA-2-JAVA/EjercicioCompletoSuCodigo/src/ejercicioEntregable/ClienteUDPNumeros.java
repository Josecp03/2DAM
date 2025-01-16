package ejercicioEntregable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDPNumeros {
    public static void main(String[] args) {
        
    	int puertoServidor = 9876;
        String host = "localhost";
        
        try (DatagramSocket socket = new DatagramSocket()) {
            
        	Scanner sc = new Scanner(System.in);
            int numero;
            
            while (true) {
                
            	System.out.print("Introduce un número (<= 0 para terminar): ");
                numero = sc.nextInt();
                
                // Creamos el objeto Numeros
                Numeros nums = new Numeros();
                nums.setNumero(numero);
                
                // Serializar objeto
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(nums);
                oos.close();
                
                byte[] bufferEnvio = baos.toByteArray();
                
                // Enviamos al servidor
                InetAddress ipServidor = InetAddress.getByName(host);
                DatagramPacket paqEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, ipServidor, puertoServidor);
                socket.send(paqEnvio);
                
                // Si el número <= 0, salimos
                if (numero <= 0) {
                    System.out.println("Finalizando cliente UDP...");
                    break;
                }
                
                // Esperamos respuesta del servidor
                byte[] bufferRecibo = new byte[1024];
                DatagramPacket paqRecibo = new DatagramPacket(bufferRecibo, bufferRecibo.length);
                socket.receive(paqRecibo);
                
                // Deserializamos objeto de respuesta
                ByteArrayInputStream bais = new ByteArrayInputStream(bufferRecibo);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Numeros numsRecibidos = (Numeros) ois.readObject();
                ois.close();
                
                System.out.println("Servidor UDP calculó -> Cuadrado: " 
                        + numsRecibidos.getCuadrado() 
                        + ", Cubo: " 
                        + numsRecibidos.getCubo());
                
            }
            
            sc.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
