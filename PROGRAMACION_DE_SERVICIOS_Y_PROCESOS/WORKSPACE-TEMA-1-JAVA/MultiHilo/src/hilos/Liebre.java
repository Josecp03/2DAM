package hilos;

public class Liebre implements Runnable {
    
    private String nombre;

    public Liebre(String nombre) {
        this.nombre = nombre;
    }

    public void run() {
        int i = 0;
        System.out.println("Empieza la " + nombre);
        while (i < 5) {
            try {
                Thread.sleep(2000);
                System.out.print(nombre + " ");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            i++;
        }
        System.out.println(nombre + " llega a la meta");
    }
}