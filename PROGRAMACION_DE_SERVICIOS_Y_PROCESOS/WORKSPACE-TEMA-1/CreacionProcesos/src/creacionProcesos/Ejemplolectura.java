package creacionProcesos;

import java.io.*;

//Lee una cadena desde la entrada estándar y la visualiza
public class Ejemplolectura {
	
    public static void main(String args[]) {
    	
        //Bytes
        InputStreamReader in = new InputStreamReader(System.in);
        
        //Caracteres
        BufferedReader br = new BufferedReader(in);
        String texto;

        try {
            System.out.println("Introduce una cadena");
            texto = br.readLine();
            System.out.println("La cadena escrita es: " + texto);
            in.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
