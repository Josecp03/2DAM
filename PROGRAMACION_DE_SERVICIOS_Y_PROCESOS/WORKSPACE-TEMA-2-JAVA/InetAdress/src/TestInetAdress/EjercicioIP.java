package TestInetAdress;

import java.net.*;

public class EjercicioIP {

    public static void main(String args[]) {
        String maquina = "";
        InetAddress dir = null;
        try {
            maquina = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ES NECESARIO INTRODUCIR UN ARGUMENTO");
            System.exit(0);
        }

        // Mostrar datos
        try {
            dir = InetAddress.getByName(maquina);
            pruebaMetodos(dir);
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        // Array de tipo InetAddress con todas las direcciones IP asignadas
        try {
            InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
            for (int i = 0; i < direcciones.length; i++) {
                System.out.println(direcciones[i].toString());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void pruebaMetodos(InetAddress dir) {
        System.out.println("\tMétodo getByName(): " + dir);
        InetAddress dir2;
        try {
            dir2 = InetAddress.getLocalHost();
            System.out.println("\tMétodo getLocalHost(): " + dir2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // Usamos métodos de la clase
        System.out.println("\tMétodo getHostName(): " + dir.getHostName());
        System.out.println("\tMétodo getHostAddress(): " + dir.getHostAddress());
        System.out.println("\tMétodo toString(): " + dir.toString());
        System.out.println("\tMétodo getCanonicalHostName(): " + dir.getCanonicalHostName());
    }
}
