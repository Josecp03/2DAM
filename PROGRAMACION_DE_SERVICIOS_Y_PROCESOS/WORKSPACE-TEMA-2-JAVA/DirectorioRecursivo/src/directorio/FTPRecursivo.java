package directorio;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;

public class FTPRecursivo {
    private static final String SERVER = "localhost";
    private static final int PORT = 21;
    private static final String USER = "usuario1";
    private static final String PASSWORD = "dam";
    
    public static void main(String[] args) {
        
    	FTPClient ftpClient = new FTPClient();
        try {
            // Conexión y autenticación en el servidor FTP
            ftpClient.connect(SERVER, PORT);
            ftpClient.login(USER, PASSWORD);
            ftpClient.enterLocalPassiveMode();
            
            System.out.println("Conectado al servidor FTP");
            
            // Inicia el listado recursivo desde la raíz o la carpeta que desees.
            listarArchivos(ftpClient, "/");
            
            ftpClient.logout();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Método recursivo que lista archivos y directorios en la ruta dada.
     * @param ftpClient instancia conectada del cliente FTP.
     * @param ruta ruta en el servidor FTP desde donde comenzar el listado.
     * @throws IOException
     */
    public static void listarArchivos(FTPClient ftpClient, String ruta) throws IOException {
        FTPFile[] archivos = ftpClient.listFiles(ruta);
        for (FTPFile archivo : archivos) {
            String rutaCompleta = ruta + "/" + archivo.getName();
            System.out.println((archivo.isDirectory() ? "[D] " : "[F] ") + rutaCompleta);
            
            // Evita entrar en los directorios especiales "." y ".."
            if (archivo.isDirectory() && !archivo.getName().equals(".") && !archivo.getName().equals("..")) {
                listarArchivos(ftpClient, rutaCompleta);
            }
        }
    }
}
