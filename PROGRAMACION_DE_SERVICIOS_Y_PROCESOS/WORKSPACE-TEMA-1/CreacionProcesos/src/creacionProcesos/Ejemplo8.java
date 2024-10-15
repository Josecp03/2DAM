package creacionProcesos;

import java.io.*;

public class Ejemplo8 {

    public static void main(String args[]) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("CMD");
        File fBat = new File("fichero.bat");
        File fout = new File("salida.txt");
        File ferr = new File("error.txt");

        pb.redirectInput(fBat);
        pb.redirectOutput(fout);
        pb.redirectError(ferr);
        pb.start();
    }
}
