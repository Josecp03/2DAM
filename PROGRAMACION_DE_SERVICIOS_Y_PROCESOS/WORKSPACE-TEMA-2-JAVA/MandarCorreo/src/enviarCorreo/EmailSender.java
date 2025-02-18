package enviarCorreo;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    
	public static void main(String[] args) {
        
		// Definir los valores fijos
        String origen = "josecorrochanop2003@gmail.com"; 
        String destino = "josecorrochanop2003@gmail.com"; 
        String asunto = "Prueba de SMTPClient";
        String mensaje = "Hola. \n ¿Cómo estás?\n Adiós.";

        // Establecer conexión SMTP
        SMTPConnection smtpConnection = new SMTPConnection();

        try {
            // Crear el mensaje
            Message message = new MimeMessage(smtpConnection.getSession());
            message.setFrom(new InternetAddress(origen));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
            message.setSubject(asunto);
            message.setText(mensaje);

            // Autenticación y envío
            Transport transport = smtpConnection.getSession().getTransport("smtp");
            transport.connect(smtpConnection.getHost(), smtpConnection.getUser(), smtpConnection.getPassword());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("Correo enviado correctamente a " + destino);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo.");
        }
    }
}
