package prueba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.net.pop3.POP3MessageInfo;
import org.apache.commons.net.pop3.POP3SClient;

public class EjemploPOP3 {

    public static void main(String[] args) {
        String server   = "localhost";
        String username = "usu1";
        String password = "usu1";
        int puerto      = 110;

        POP3SClient pop3 = new POP3SClient();

        try {
            pop3.connect(server, puerto);
            System.out.println("Conexión realizada al servidor POP3: " + server);

            // nos logueamos
            if (!pop3.login(username, password)) {
                System.err.println("Error al hacer login");
            } else {
                POP3MessageInfo[] men = pop3.listMessages();
                if (men == null) {
                    System.out.println("Imposible recuperar mensajes.");
                } else {
                    System.out.println("Nº de mensajes: " + men.length);
                    if (men.length > 0) {
                        Recuperarmensajes(men, pop3);
                    }
                }
                // finalizar sesión
                pop3.logout();
            }
            // nos desconectamos
            pop3.disconnect();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }

    private static void Recuperarmensajes(POP3MessageInfo[] men, POP3SClient pop3) throws IOException {
        for (int i = 0; i < men.length; i++) {
            System.out.println("Mensaje: " + (i + 1));
            POP3MessageInfo msginfo = men[i];
            System.out.println("[Identificador: " + msginfo.identifier
                    + ", Número: " + msginfo.number
                    + ", Tamaño: " + msginfo.size + "]");

            System.out.println("Prueba de listUniqueIdentifier:");
            POP3MessageInfo pmi = pop3.listUniqueIdentifier(i + 1);
            System.out.println("\tIdentificador: " + pmi.identifier
                    + ", Número: " + pmi.number
                    + ", Tamaño: " + pmi.size);

            // solo recupera cabecera
            System.out.println("Cabecera del mensaje:");
            BufferedReader reader = (BufferedReader) pop3.retrieveMessageTop(msginfo.number, 0);
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

            // recupera todo el mensaje
            BufferedReader reader2 = (BufferedReader) pop3.retrieveMessage(msginfo.number);
            String linea;
            while ((linea = reader2.readLine()) != null) {
                System.out.println(linea);
            }
            reader2.close();
        }
    }
}
