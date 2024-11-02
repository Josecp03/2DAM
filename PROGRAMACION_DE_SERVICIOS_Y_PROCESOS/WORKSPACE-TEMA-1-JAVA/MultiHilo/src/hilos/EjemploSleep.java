package hilos;

public class EjemploSleep {

    public static void main(String[] args) {
        
        // Creación de 3 hilos para tortugas
        Thread tortuga1 = new Thread(new Tortuga("T1"));
        Thread tortuga2 = new Thread(new Tortuga("T2"));
        Thread tortuga3 = new Thread(new Tortuga("T3"));
        
        // Creación de 3 hilos para liebres
        Thread liebre1 = new Thread(new Liebre("L1"));
        Thread liebre2 = new Thread(new Liebre("L2"));
        Thread liebre3 = new Thread(new Liebre("L3"));
        
        // Inicio de todos los hilos
        tortuga1.start();
        tortuga2.start();
        tortuga3.start();
        
        liebre1.start();
        liebre2.start();
        liebre3.start();
    }
}