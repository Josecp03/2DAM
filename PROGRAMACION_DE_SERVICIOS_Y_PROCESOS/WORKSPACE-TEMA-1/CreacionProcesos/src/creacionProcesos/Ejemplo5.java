package creacionProcesos;

import java.io.*;

public class Ejemplo5 {

    public static void main(String args[]) throws IOException {

        File directorio = new File(".\\bin");
        ProcessBuilder pb = new ProcessBuilder("java", "creacionProcesos.Ejemplolectura");
        pb.directory(directorio);
        String w = System.getProperty("user.dir");
        System.out.println("Mi ruta es: " + w);
        Process erp = pb.start();

        //Escritura
        OutputStream os = erp.getOutputStream();
        os.write("Hola Maria\n".getBytes());
        os.flush();

        //LEER STREAM DE ERRORES
        try {
            InputStream erpErr = erp.getErrorStream();
            BufferedReader brer = new BufferedReader(new InputStreamReader(erpErr));
            String liner = null;

            while ((liner = brer.readLine()) != null) {
                System.out.println("ERROR> " + liner);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        //LEER STREAM DE ENTRADA
        try {
            InputStream erpGet = erp.getInputStream();
            BufferedReader brer = new BufferedReader(new InputStreamReader(erpGet));
            String liner = null;

            while ((liner = brer.readLine()) != null) {
                System.out.println("BIEEEEEEEN!> " + liner);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
