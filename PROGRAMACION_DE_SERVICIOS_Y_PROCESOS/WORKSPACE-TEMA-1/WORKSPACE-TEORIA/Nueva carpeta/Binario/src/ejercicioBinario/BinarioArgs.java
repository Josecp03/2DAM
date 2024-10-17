package ejercicioBinario;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BinarioArgs {
    public static void main(String[] args) {
        try {
            // Redirige la salida a un flujo de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            
            // Ejecuta el programa Binario
            Binario.main(new String[]{"10"}); // Puedes cambiar el número según sea necesario
            
            // Imprime la salida en pantalla
            System.out.println(baos.toString());
        } catch (Exception e) {
            System.out.println("Error al ejecutar el programa: " + e.getMessage());
        }
    }
}
