package directorio;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

public class FTPRecursivo {

    public static void main(String[] args) {
        
    	// Inicialización variables
    	String server = "localhost";
        int port = 21;
        String user = "usuario1";  
        String pass = "dam";      

        FTPClient ftpClient = new FTPClient();

        try {
        	
            // Conexión al servidor
            ftpClient.connect(server, port);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Error en la conexión, servidor respondió con código: " + replyCode);
                return;
            }

            // Login
            boolean success = ftpClient.login(user, pass);
            if (!success) {
                System.out.println("No se pudo iniciar sesión en el servidor FTP con las credenciales proporcionadas.");
                return;
            }

            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // Directorio inicial en la cuenta del usuario.
            String directorioInicio = "/";

            System.out.println("Listado del contenido de " + user + directorioInicio + ":");
            listarRecursivo(ftpClient, directorioInicio, "");

            // Logout y desconexión
            ftpClient.logout();

        } catch (IOException ex) {
            System.out.println("Ocurrió un error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private static void listarRecursivo(FTPClient ftpClient, String ruta, String sangria) throws IOException {
        // Obtiene la lista de ficheros y directorios en la ruta
        FTPFile[] archivos = ftpClient.listFiles(ruta);

        for (FTPFile archivo : archivos) {
            // Construimos la ruta completa del objeto
            String nombre = archivo.getName();
            String pathCompleto = ruta.endsWith("/") ? ruta + nombre : ruta + "/" + nombre;

            // Imprimimos con una sangría para visualizar estructura
            System.out.println(sangria + (archivo.isDirectory() ? "[D] " : "[F] ") + nombre);

            // Si es un directorio y no son las rutas especiales "." o "..", bajamos recursivamente
            if (archivo.isDirectory() && !nombre.equals(".") && !nombre.equals("..")) {
                listarRecursivo(ftpClient, pathCompleto, sangria + "   ");
            }
        }
    }
}
