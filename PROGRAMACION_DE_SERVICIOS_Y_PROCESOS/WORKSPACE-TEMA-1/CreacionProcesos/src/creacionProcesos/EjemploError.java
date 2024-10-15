package creacionProcesos;

import java.io.*;

public class EjemploError {
	
    public static void main(String[] args) throws IOException{
    	
        // C ejecuta el cmd y luego finaliza
        Process p = new ProcessBuilder("CMD", "/C", "DIRR").start();

        // Mostramos carácter a carácter la salida generada por DIRR
        try {
        	
            // Leemos lo que el comando escribió en la consola
            InputStream is = p.getInputStream();
            int c;
            while ((c = is.read()) != -1)
                System.out.print((char) c);

            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Comprobación de error 0 bien y -1 mal
        int exitVal;
        try {
        	
            // Recoge la salida de System.exit()
            exitVal = p.waitFor();
            System.out.println("Valor de salida: " + exitVal);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
        	
            InputStream er = p.getErrorStream();
            BufferedReader brer = new BufferedReader(new InputStreamReader(er));
            String liner = null;

            while ((liner = brer.readLine()) != null)
                System.out.println("ERROR: " + liner);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
