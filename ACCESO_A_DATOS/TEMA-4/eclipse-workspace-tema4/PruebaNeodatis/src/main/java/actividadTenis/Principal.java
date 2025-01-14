package actividadTenis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import prueba.Jugadores;

public class Principal {

    public static void main(String[] args) {

        ODB odb = ODBFactory.open("neodatis.test");

        // Consulta jugadores de tenis y muestra también su país
        consultarJugadoresDeTenis(odb);

        // Consulta jugadores por país específico
        String paisBuscado = "España"; 
        consultarJugadoresPorPais(odb, paisBuscado);

        odb.close();
    }

    public static void consultarJugadoresDeTenis(ODB odb) {
        IQuery query = new CriteriaQuery(Jugadores.class, Where.equal("deporte", "tenis"));
        query.orderByAsc("nombre, edad");

        Objects<Jugadores> jugadores = odb.getObjects(query);

        int i = 0;
        for (Jugadores jug : jugadores) {
            System.out.println((i++) + "\t: " + jug.getNombre() + "*" + jug.getDeporte() + "*" + jug.getCiudad() + "*"
                    + jug.getEdad() + "*" + jug.getPais());
        }

        if (jugadores.isEmpty()) {
            System.out.println("No se encontraron jugadores de tenis.");
        }
    }

    public static void consultarJugadoresPorPais(ODB odb, String pais) {
        IQuery query = new CriteriaQuery(Jugadores.class, Where.equal("pais", pais));
        query.orderByAsc("nombre");

        Objects<Jugadores> jugadores = odb.getObjects(query);

        if (jugadores.isEmpty()) {
            System.out.println("No se encontraron jugadores en el país: " + pais);
        } else {
            System.out.println("Jugadores del país " + pais + ":");
            for (Jugadores jug : jugadores) {
                System.out.println("Nombre: " + jug.getNombre() + ", Edad: " + jug.getEdad() + ", Deporte: " + jug.getDeporte());
            }
        }
    }
}