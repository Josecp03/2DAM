package hilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	
    public static void main(String[] args) throws IOException {
        String Host = "localhost";
        int Puerto = 8001; // puerto remoto
        Socket Cliente = new Socket(Host, Puerto);

        // CREO FLUJO DE SALIDA AL SERVIDOR
        PrintWriter fsalida = new PrintWriter(Cliente.getOutputStream(), true);

        // CREO FLUJO DE ENTRADA AL SERVIDOR
        BufferedReader fentrada = new BufferedReader(new InputStreamReader(Cliente.getInputStream()));

        // FLUJO PARA ENTRADA ESTÁNDAR
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String cadena, eco = "";

        System.out.print("Introduce cadena: ");
        cadena = in.readLine();
        while (cadena != null) {
            fsalida.println(cadena);
            eco = fentrada.readLine();
            System.out.println(" -> ECO: " + eco);
            System.out.print("Introduce cadena: ");
            cadena = in.readLine();
        }

        fsalida.close();
        fentrada.close();
        System.out.println("Fin del envío...");
        Cliente.close();
    }
}

