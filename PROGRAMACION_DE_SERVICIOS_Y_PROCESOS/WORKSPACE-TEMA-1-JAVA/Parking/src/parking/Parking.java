package parking;

public class Parking {
    private int plazasTotales;
    private int plazasOcupadas = 0;

    public Parking(int plazasTotales) {
        this.plazasTotales = plazasTotales;
    }

    public synchronized boolean entrar(int cocheId) throws InterruptedException {
        while (plazasOcupadas == plazasTotales) {
            System.out.println("****** Coche " + cocheId + " intenta entrar pero no hay plazas libres ******");
            wait(); // Espera hasta que haya una plaza disponible
        }
        plazasOcupadas++;
        System.out.println("****** Coche " + cocheId + " entra en el parking. Plazas ocupadas: " + plazasOcupadas + " ******");
        return true;
    }

    public synchronized void salir(int cocheId) {
        plazasOcupadas--;
        System.out.println("****** Coche " + cocheId + " sale del parking. Plazas ocupadas: " + plazasOcupadas + " ******");
        notifyAll(); // Notifica a los coches esperando
    }
}
