package creacionProcesos; 

import java.io.*; 

public class EjemploNotepad {
	
	public static void main(String[] args) throws IOException {
				
		// Se crea el objeto ProcessBuilder con "NOTEPAD".
		ProcessBuilder pb = new ProcessBuilder("NOTEPAD"); 
		
		// Se inicia el proceso y se guarda en la variable p.
		Process p = pb.start(); 
		
		// Esta línea está comentada. Sirve para crear y ejecutar el proceso en una sola línea
		// Process pb = new ProcessBuilder("NOTEPAD").start(); 
		
	}
}
