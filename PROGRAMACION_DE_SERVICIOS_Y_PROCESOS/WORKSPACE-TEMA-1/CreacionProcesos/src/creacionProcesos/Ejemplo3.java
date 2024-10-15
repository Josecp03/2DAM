package creacionProcesos; 

import java.io.*; 

public class Ejemplo3 {
	
    public static void main(String[] args) throws IOException {
    	
        // En primer lugar mostramos donde nos encontramos.
        String w = System.getProperty("user.dir"); // Obtiene el directorio de trabajo actual.
        System.out.println("Mi ruta es: " + w);

        // Creamos el objeto File con el directorio donde está Ejemplo2.class.
        File directorio = new File(".\\bin"); // Se define el directorio de trabajo, apuntando a la carpeta "bin".

        // Si hay un paquete, el nombre del .class es package.clase.
        ProcessBuilder pb = new ProcessBuilder("java", "pspunit1.Ejemplo2");
        pb.directory(directorio); // Establece el directorio de trabajo para el proceso.

        System.out.printf("El directorio de trabajo es: %s%n", pb.directory());

        // Inicia el proceso que ejecuta la clase `pspunit1.Ejemplo2`.
        Process p = pb.start(); 
        try {
        	
            // Leemos la salida devuelta por el proceso.
            InputStream is = p.getInputStream();
            
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c); // Imprime cada carácter de la salida del proceso.
            }
            is.close();
            
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
