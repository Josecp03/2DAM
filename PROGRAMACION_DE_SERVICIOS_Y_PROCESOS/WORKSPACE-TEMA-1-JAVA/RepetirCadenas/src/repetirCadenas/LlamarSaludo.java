package repetirCadenas;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LlamarSaludo {

	public static void main(String[] args) throws IOException {
		
		ProcessBuilder pb = new ProcessBuilder("java", "repetirCadenas.Saludo", "Hola Mundo");
		File directorio = new File(".\\bin");
		pb.directory(directorio);
		Process p = pb.start();
		
		try {
			
			InputStream is = p.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String linea;	
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			br.close();
			
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
