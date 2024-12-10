package TestInetAdress;

import java.net.*;

public class TestInetAdress {
    public static void main(String[] args) {
        InetAddress dir = null;

        System.out.println("================================================");
        System.out.println("SALIDA PARA LOCALHOST:");
        try {
            // LOCALHOST
            dir = InetAddress.getByName("localhost");
            pruebaMetodos(dir);

            // URL
            System.out.println("================================================");
            System.out.println("SALIDA PARA UNA URL:");
            dir = InetAddress.getByName("www.google.es");
            pruebaMetodos(dir);

            // Array de tipo InetAddress con todas las direcciones IP asignadas a google.es
            System.out.println("\tDIRECCIONES IP PARA: " + dir.getHostName());
            InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
            for (int i = 0; i < direcciones.length; i++) {
                System.out.println("\t\t" + direcciones[i].toString());
            }

            System.out.println("================================================");
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
    }

    private static void pruebaMetodos(InetAddress dir) {
        System.out.println("\tMétodo getByName(): " + dir);
        InetAddress dir2 = null;
        try {
            // dir2 tendrá la dirección IP de la máquina donde se está ejecutando
            dir2 = InetAddress.getLocalHost();
            System.out.println("\tMétodo getLocalHost(): " + dir2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // USAMOS MÉTODOS DE LA CLASE
        // String con el nombre del host
        System.out.println("\tMétodo getHostName(): " + dir.getHostName());
        // String con la dirección IP del host
        System.out.println("\tMétodo getHostAddress(): " + dir.getHostAddress());
        // String con toda la información del host
        System.out.println("\tMétodo toString(): " + dir.toString());
        // Nombre del dominio completo
        System.out.println("\tMétodo getCanonicalHostName(): " + dir.getCanonicalHostName());
    }
}
