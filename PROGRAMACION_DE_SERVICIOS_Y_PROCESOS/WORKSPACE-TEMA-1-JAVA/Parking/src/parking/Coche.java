package parking;

public class Coche extends Thread {
    private Parking parking;
    private int cocheId;

    public Coche(Parking parking, int cocheId) {
        this.parking = parking;
        this.cocheId = cocheId;
    }

    @Override
    public void run() {
        try {
            // Intenta entrar al parking
            parking.entrar(cocheId);

            // Simula la estancia en el parking
            Thread.sleep((int) (Math.random() * 5000) + 2000);

            // Sale del parking
            parking.salir(cocheId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
