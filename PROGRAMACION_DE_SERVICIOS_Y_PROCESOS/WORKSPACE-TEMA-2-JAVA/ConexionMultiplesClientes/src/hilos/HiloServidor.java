package hilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread {
	
    BufferedReader fentrada;
    PrintWriter fsalida;
    Socket socket = null;

    public HiloServidor(Socket s) {
        socket = s;
        // Se crean flujos de entrada y salida con el cliente
        try {
            fsalida = new PrintWriter(socket.getOutputStream(), true);
            fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("ERROR DE E/S");
            e.printStackTrace();
        }
    }

    // Esta es la tarea que se va a realizar con el cliente
    public void run() {
        String cadena = "";
        while (!cadena.trim().equals("*")) {
            System.out.println("COMUNICO CON: " + socket.toString());
            try {
                cadena = fentrada.readLine();
                fsalida.println(cadena.trim().toUpperCase());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("FIN CON: " + socket.toString());
        try {
            fentrada.close();
            fsalida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
