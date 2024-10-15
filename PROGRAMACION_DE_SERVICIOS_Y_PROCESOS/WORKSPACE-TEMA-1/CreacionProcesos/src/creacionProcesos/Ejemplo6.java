package creacionProcesos;

import java.io.*;
import java.util.*;

public class Ejemplo6 {

    public static void main(String args[]) {
        ProcessBuilder test = new ProcessBuilder();
        // environment devuelve las variables de entorno
        Map entorno = test.environment();
        System.out.println("Variables de entorno: ");
        System.out.println(entorno);

        test = new ProcessBuilder("java", "Hola", "Maria");
        // command devuelve los argumentos del proceso test
        List l = test.command();
        Iterator iter = l.iterator();
        System.out.println("Los argumentos del comando son: ");
        while (iter.hasNext())
            System.out.println(iter.next());

        // command con parámetros define un nuevo proceso y sus argumentos
        test.command("CMD", "/C", "DIR");
        try {
            Process p = test.start();

            // Leemos lo que el comando escribió en la consola
            InputStream is = p.getInputStream();
            int c;
            while ((c = is.read()) != -1)
                System.out.print((char) c);

            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
