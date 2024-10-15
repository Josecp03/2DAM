package creacionProcesos;

import java.io.*;

public class Ejemplo7 {
	
    public static void main(String args[]) throws IOException {

        ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "DIR");
        File fout = new File("src\\creacionProcesos\\salida.txt");
        File ferr = new File("error.txt");

        pb.redirectOutput(fout);
        pb.redirectError(ferr);
        pb.start();
    }
}
