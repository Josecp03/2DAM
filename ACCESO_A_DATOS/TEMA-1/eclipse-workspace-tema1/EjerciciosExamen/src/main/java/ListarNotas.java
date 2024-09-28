import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Set;

public class ListarNotas {

    static int LON = 48; // Tamaño del registro en Notas.dat

    public static void main(String[] args) throws IOException {

        // Fichero
        File fichero = new File(".\\Notas.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "r");

        Set<Integer> codigosAlumnos = new HashSet<>();

        // Primera pasada: obtener los códigos de alumno únicos
        long posicion = 0;
        while (posicion < file.length()) {
            file.seek(posicion);
            int codAlumno = file.readInt();
            codigosAlumnos.add(codAlumno);
            posicion += LON;
        }

        // Cerrar archivo después de la primera pasada
        file.close();

        // Segunda pasada: mostrar las notas de cada alumno
        System.out.printf("%9s %-20s %9s %n", "NUMALUM", "ASIGNATURA", "NOTA");
        System.out.printf("%9s %-20s %9s %n", "-------", "--------------------", "----");

        // Recorrer cada código de alumno y mostrar sus notas secuencialmente
        for (int codAlumno : codigosAlumnos) {
            file = new RandomAccessFile(fichero, "r");
            posicion = 0;

            while (posicion < file.length()) {
                file.seek(posicion);

                // Leer código del alumno
                int cod = file.readInt();

                // Leer nombre de la asignatura
                char asignatura[] = new char[20];
                for (int i = 0; i < asignatura.length; i++) {
                    asignatura[i] = file.readChar();
                }
                String asignaturaS = new String(asignatura).trim();

                // Leer nota de la asignatura
                float notaAsignatura = file.readFloat();

                // Imprimir las notas solo para el alumno actual
                if (cod == codAlumno) {
                    System.out.printf("%9d %-20s %9.2f %n", cod, asignaturaS, notaAsignatura);
                }

                // Avanzar a la siguiente posición
                posicion += LON;
            }

            // Cerrar archivo después de cada pasada
            file.close();
        }
    }
}
