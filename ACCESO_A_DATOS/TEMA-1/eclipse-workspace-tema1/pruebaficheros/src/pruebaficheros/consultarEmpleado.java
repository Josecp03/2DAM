package pruebaficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class consultarEmpleado {

    public static void main(String[] args) throws IOException {
        
        File fichero = new File(".\\AleatorioEmpleUTF.dat");
        
        // Declarar el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero, "r");
        
        // Inicializar variables
        int id = 5;
        int dep;
        double salario;
        String apellido;
        
        // Calcular la posición del registro en el fichero
        int posicion = (id - 1) * 36; 
        
        // Comprobar si la posición es válida
        if (posicion >= file.length()) {
            System.out.println("El empleado con el identificador " + id + " no existe");
        } else {
            // Obtener los datos del empleado
            file.seek(posicion);
            int idLeido = file.readInt();
            apellido = file.readUTF();
            dep = file.readInt();
            salario = file.readDouble();
            
            // Mostrar los datos del empleado
            System.out.println("ID: " + idLeido + ", Apellido: " + apellido + ", Departamento: " + dep + ", Salario: " + salario + "€");
        }
        
        // Cerrar el fichero
        file.close();
    }
}
