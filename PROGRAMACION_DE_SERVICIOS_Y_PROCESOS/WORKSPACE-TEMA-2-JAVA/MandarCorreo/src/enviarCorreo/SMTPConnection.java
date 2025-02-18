package enviarCorreo;

import java.util.Properties;

import javax.mail.Session;


public class SMTPConnection {
    private String host;
    private int port;
    private String user;
    private String password;
    private Properties properties;
    private Session session;

    public SMTPConnection() {
        this.host = "smtp.gmail.com";
        this.port = 587;
        this.user = "josecorrochanop2003@gmail.com"; 
        this.password = "ewcx vfwx ohde gfgw";  
        configurarSMTP();
    }

    private void configurarSMTP() {
        properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", String.valueOf(port));
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        session = Session.getInstance(properties);
    }

    public Session getSession() {
        return session;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
