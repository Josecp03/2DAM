package ejercicioEntregable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDPNumeros {
    
	public static void main(String[] args) {
        
    	int puerto = 9876;
        byte[] buffer = new byte[1024];
        
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            
        	System.out.println("Servidor UDP escuchando en el puerto " + puerto + "...");
            
            while (true) {
                
            	// Recibir datagrama
                DatagramPacket paqRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqRecibido);
                
                // Convertir bytes a objeto
                ByteArrayInputStream bais = new ByteArrayInputStream(paqRecibido.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Numeros nums = (Numeros) ois.readObject();
                ois.close();
                
                int numero = nums.getNumero();
                
                // Si el número <= 0, finalizamos servidor
                if (numero <= 0) {
                    System.out.println("Finalizando servidor UDP...");
                    break;
                }
                
                // Calculamos
                nums.setCuadrado((long) numero * numero);
                nums.setCubo((long) numero * numero * numero);
                System.out.println("Servidor UDP recibió " + numero 
                        + ", cuadrado=" + nums.getCuadrado() 
                        + ", cubo=" + nums.getCubo());
                
                // Empaquetamos objeto para enviar al cliente
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(nums);
                oos.close();
                
                byte[] envio = baos.toByteArray();
                
                // Dirección IP y puerto del cliente
                InetAddress ipCliente = paqRecibido.getAddress();
                int puertoCliente = paqRecibido.getPort();
                
                DatagramPacket paqEnvio = new DatagramPacket(envio, envio.length, ipCliente, puertoCliente);
                socket.send(paqEnvio);
                
            }
            
            System.out.println("Cerrando servidor UDP...");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
