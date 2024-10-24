package repetirCadenas;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AlmacenarSaludoTxt {

	public static void main(String[] args) throws IOException {
		
		ProcessBuilder pb = new ProcessBuilder("java", "repetirCadenas.Saludo", "Hola Jose");
		File directorio = new File(".\\bin");
		pb.directory(directorio);
		Process p = null;
		
		File salida = new File("FicheroSaludo.txt");
		
//		// Si le pasamos le nombre del fichero como argumento:
//		if (args.length < 1) {
//			System.out.println("SE NECESITA UN NOMBRE DE FICHERO...");
//			System.exit(1);
//		}
//		
//		String nombre = args[0];
//		File salida = new File(nombre);
		
		
		try {
			pb.redirectOutput(salida);
			p = pb.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// CONTROL DE LOS MENSAJES DE ERROR
		try {
			InputStream er = p.getErrorStream();
			BufferedReader brer = new BufferedReader(new InputStreamReader(er));
			String liner = null;
			
			while ((liner = brer.readLine()) != null) {
				System.out.println("ERROR > " + liner);
			}
			
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		
		// COMPROBACIÓN DE ERROR - 0 bien -1 mal
		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
