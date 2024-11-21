package parking;

public class Main {
    public static void main(String[] args) {
        int plazas = 5; // Número de plazas en el parking
        int numCoches = 10; // Número total de coches

        Parking parking = new Parking(plazas);

        for (int i = 0; i < numCoches; i++) {
            new Coche(parking, i).start();
        }
    }
}
