package centrosProfesores;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Principal {

	public static void main(String[] args) {
		
		// Llamada al método para crear el xml
		crearXML();
		
	}

	// Método para crear el MXL
	private static void crearXML() {
		
		// PRIMER CENTRO
		
		// Creo el director del centro
		Profesor director1 = new Profesor(1234, "Andrés García", 1800.25);
		
		// Creo el centro del primer centro
		Centro c1 = new Centro("1", "IES RIBERA DEL TAJO", "AVDA REAL FABRICA DE SEDAS S/N", director1);
		
		// Creo 3 profesores
		Profesor p1 = new Profesor(1240, "Antonio García", 1400.25);
		Profesor p2 = new Profesor(1223, "Alicia Ramos", 1555.55);
		Profesor p3 = new Profesor(3422, "Julían Salmero", 1678.25);
		
		// Creo la lista de profesores
		ArrayList<Profesor> listaProfesores1 = new ArrayList<Profesor>();
		listaProfesores1.add(p1);
		listaProfesores1.add(p2);
		listaProfesores1.add(p3);
		
		// Creo el centro1
		UnCentro centro1 = new UnCentro(c1, listaProfesores1);
		
		
		// SEGUNDO CENTRO		
		// Creo el centro2 con los mismos datos pro probar
		UnCentro centro2 = new UnCentro(c1, listaProfesores1);
		
		// Creo la lista de centros y los añado
		ArrayList<UnCentro> listaCentros = new ArrayList<UnCentro>();
		listaCentros.add(centro1);
		listaCentros.add(centro2);
		
		DatosCentro datosCentro = new DatosCentro(listaCentros);
		
		// CREAR XML
		// Creamos el contexto indicando la clase raíz
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(DatosCentro.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(datosCentro, System.out);
			m.marshal(datosCentro, new File("DatosCentro.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
