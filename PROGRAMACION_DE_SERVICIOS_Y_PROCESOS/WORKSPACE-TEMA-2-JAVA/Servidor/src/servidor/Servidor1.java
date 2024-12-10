package servidor;

import java.io.*;
import java.net.*;

public class Servidor1 {
    public static void main(String[] args) throws IOException {
        int numeroPuerto = 6001; // Puerto
        ServerSocket servidor = null;

        servidor = new ServerSocket(numeroPuerto);

        Socket clienteConectado = null;
        System.out.println("Esperando al cliente....");
        clienteConectado = servidor.accept();

        // CREO FLUJO DE SALIDA AL CLIENTE
        OutputStream salida = null;
        salida = clienteConectado.getOutputStream();
        DataOutputStream flujoSalida = new DataOutputStream(salida);

        // ENVIO UN SALUDO AL CLIENTE
        flujoSalida.writeUTF("Saludos al cliente del servidor");

        // CREO FLUJO DE ENTRADA DE CLIENTE
        InputStream entrada = null;
        entrada = clienteConectado.getInputStream();
        DataInputStream flujoEntrada = new DataInputStream(entrada);

        // EL CLIENTE ME ENVIA UN MENSAJE
        System.out.println("Recibiendo del CLIENTE: \n\t" + flujoEntrada.readUTF());

        // CERRAR STREAMS Y SOCKETS
        entrada.close();
        flujoEntrada.close();
        salida.close();
        flujoSalida.close();
        clienteConectado.close();
        servidor.close();
    }
}
