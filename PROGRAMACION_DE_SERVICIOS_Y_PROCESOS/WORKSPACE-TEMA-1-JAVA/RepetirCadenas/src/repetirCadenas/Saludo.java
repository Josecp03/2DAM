package repetirCadenas;

public class Saludo {

	public static void main(String[] args) {

		// Comprobar que se pasan correctamente los argumentos
		if (args.length < 1) {
			System.out.println("SE NECESITA UN SALUDO");
			System.exit(1);
		}

		// Mostrar 5 veces el saludo 
		for (int i = 0; i < 5; i++) {
			System.out.println(i + 1 + ". " + args[0]);

		}

	}

}
