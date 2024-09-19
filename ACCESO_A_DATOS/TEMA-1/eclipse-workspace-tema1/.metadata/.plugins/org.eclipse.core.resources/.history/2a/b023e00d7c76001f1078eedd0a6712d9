package ejercicioResumen;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class actividadDepartamentos {

    // Tamaño del registro: 4 (int COD_DEP) + 30 (15 chars NOMBRE) + 30 (15 chars LOCALIDAD) + 4 (int NUME_EMPLEADOS) + 4 (float MEDIA_SALARIO_DEP)
    private static final int TAM_REGISTRO = 72;

    public static void creafichero(String nombreFichero) throws IOException {
        File fichero = new File(nombreFichero);
        if (fichero.exists()) {
            System.out.println("EL FICHERO YA EXISTE");
        } else {
            RandomAccessFile file = new RandomAccessFile(fichero, "rw");
            file.close();
            System.out.println("EL FICHERO SE HA CREADO CORRECTAMENTE");
        }
    }

    public static boolean consultaregistro(RandomAccessFile file, int codDep) throws IOException {
        long posicion = (codDep - 1) * TAM_REGISTRO;
        if (posicion >= file.length()) {
            System.out.println("DEPARTAMENTO NO EXISTE");
            return false;
        }
        file.seek(posicion);
        int codigo = file.readInt();
        if (codigo == 0) {
            System.out.println("DEPARTAMENTO NO EXISTE");
            return false;
        } else {
            System.out.println("DEPARTAMENTO SI EXISTE");
            return true;
        }
    }

    public static void insertaregistro(String nombreFichero, int codDep, String nombre, String localidad, int numEmpleados, float salarioMedio) throws IOException {
        if (codDep < 1 || codDep > 100) {
            System.out.println("ERROR EL DEPARTAMENTO DEBE ESTAR ENTRE 1 Y 100");
            return;
        }
        RandomAccessFile file = new RandomAccessFile(nombreFichero, "rw");
        if (consultaregistro(file, codDep)) {
            System.out.println("ERROR EL DEPARTAMENTO YA EXISTE NO SE PUEDE INSERTAR");
        } else {
            long posicion = (codDep - 1) * TAM_REGISTRO;
            file.seek(posicion);
            file.writeInt(codDep);
            writeString(file, nombre, 15);
            writeString(file, localidad, 15);
            file.writeInt(numEmpleados);
            file.writeFloat(salarioMedio);
            System.out.println("REGISTRO INSERTADO");
        }
        file.close();
    }

    public static boolean visualizaregistro(String nombreFichero, int codDep) throws IOException {
        RandomAccessFile file = new RandomAccessFile(nombreFichero, "r");
        if (consultaregistro(file, codDep)) {
            long posicion = (codDep - 1) * TAM_REGISTRO;
            file.seek(posicion);
            int codigo = file.readInt();
            String nombre = readString(file, 15);
            String localidad = readString(file, 15);
            int numEmpleados = file.readInt();
            float salarioMedio = file.readFloat();
            System.out.println("Código: " + codigo + ", Nombre: " + nombre + ", Localidad: " + localidad + ", Nº Empleados: " + numEmpleados + ", Salario Medio: " + salarioMedio);
            file.close();
            return true;
        } else {
            file.close();
            return false;
        }
    }

    public static void modificarregistro(String nombreFichero, int codDep, String nuevaLocalidad, float nuevoSalario) throws IOException {
        RandomAccessFile file = new RandomAccessFile(nombreFichero, "rw");
        if (consultaregistro(file, codDep)) {
            long posicion = (codDep - 1) * TAM_REGISTRO + 4 + 30; // Saltar COD_DEP + NOMBRE
            file.seek(posicion);
            writeString(file, nuevaLocalidad, 15);
            file.writeFloat(nuevoSalario);
            System.out.println("REGISTRO MODIFICADO");
        } else {
            System.out.println("REGISTRO NO SE PUEDE MODIFICAR");
        }
        file.close();
    }

    public static void borrarregistro(String nombreFichero, int codDep) throws IOException {
        RandomAccessFile file = new RandomAccessFile(nombreFichero, "rw");
        if (consultaregistro(file, codDep)) {
            long posicion = (codDep - 1) * TAM_REGISTRO;
            file.seek(posicion);
            file.writeInt(0); // Borrar lógicamente poniendo el código a 0
            writeString(file, "", 15); // Vaciar nombre
            writeString(file, "", 15); // Vaciar localidad
            file.writeInt(0); // Vaciar número de empleados
            file.writeFloat(0); // Vaciar salario medio
            System.out.println("REGISTRO BORRADO");
        } else {
            System.out.println("REGISTRO NO EXISTE NO SE PUEDE BORRAR");
        }
        file.close();
    }

    // Métodos auxiliares para escribir y leer cadenas fijas
    private static void writeString(RandomAccessFile file, String text, int length) throws IOException {
        StringBuffer buffer = new StringBuffer(text);
        buffer.setLength(length);
        file.writeChars(buffer.toString());
    }

    private static String readString(RandomAccessFile file, int length) throws IOException {
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = file.readChar();
        }
        return new String(chars).trim();
    }

    public static void main(String[] args) throws IOException {
        String nombreFichero = "Departamentos.dat";
        creafichero(nombreFichero);
        insertaregistro(nombreFichero, 1, "Marketing", "Madrid", 50, 3500.5f);
        visualizaregistro(nombreFichero, 1);
        modificarregistro(nombreFichero, 1, "Barcelona", 4000.0f);
        visualizaregistro(nombreFichero, 1);
        borrarregistro(nombreFichero, 1);
        visualizaregistro(nombreFichero, 1);
    }
}
