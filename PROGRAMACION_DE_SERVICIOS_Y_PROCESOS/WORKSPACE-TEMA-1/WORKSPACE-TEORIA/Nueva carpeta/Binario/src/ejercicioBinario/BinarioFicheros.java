package ejercicioBinario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BinarioFicheros {
    public static void main(String[] args) {
        String numeroFile = "numero.txt";
        String errorFile = "error.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(numeroFile));
            String linea = reader.readLine();
            reader.close();
            
            if (linea != null) {
                try {
                    int numero = Integer.parseInt(linea);
                    String binario = Integer.toBinaryString(numero);
                    System.out.println("El número en binario es: " + binario);
                } catch (NumberFormatException e) {
                    FileWriter fw = new FileWriter(errorFile);
                    fw.write("Error: " + linea + " no es un número válido.");
                    fw.close();
                }
            }
        } catch (IOException e) {
            try {
                FileWriter fw = new FileWriter(errorFile);
                fw.write("Error al leer el archivo: " + e.getMessage());
                fw.close();
            } catch (IOException ioException) {
                System.out.println("Error al escribir en el archivo de errores.");
            }
        }
    }
}
