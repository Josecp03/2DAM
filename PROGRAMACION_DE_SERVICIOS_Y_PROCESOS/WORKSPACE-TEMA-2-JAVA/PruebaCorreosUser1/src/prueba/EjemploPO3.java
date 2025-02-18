package prueba;

import org.apache.commons.net.pop3.POP3Client;
import org.apache.commons.net.pop3.POP3MessageInfo;
import java.io.IOException;

public class EjemploPO3 {
    public static void main(String[] args) {
        String server = "localhost";
        String username = "usu1";
        String password = "usu1";
        int puerto = 110;

        POP3Client pop3 = new POP3Client();
        try {
            // Conectamos al servidor POP3
            pop3.connect(server, puerto);
            System.out.println("Conexión realizada al servidor POP3");

            // Intentamos hacer login con usuario y contraseña
            if (pop3.login(username, password)) {
                System.out.println("Login exitoso");
            } else {
                System.out.println("Error al hacer login");
                // Si falla, podemos cerrar aquí o continuar la lógica de error
            }

            // Obtenemos la lista de mensajes
            POP3MessageInfo[] mensajes = pop3.listMessages();
            if (mensajes == null) {
                System.out.println("Imposible recuperar mensajes.");
            } else {
                System.out.println("Existen " + mensajes.length + " mensajes");
            }

            // Finalizamos sesión
            pop3.logout();
            pop3.disconnect();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Salimos del programa
        System.exit(0);
    }
}

