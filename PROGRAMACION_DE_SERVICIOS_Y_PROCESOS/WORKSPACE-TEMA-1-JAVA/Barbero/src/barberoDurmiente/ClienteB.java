package barberoDurmiente;

public class ClienteB extends Thread {

    BarberoDurmiente b;
    int num;

    public ClienteB(BarberoDurmiente b, int num) {
        this.b = b;
        this.num = num;
    }

    public void run() {
        boolean t = false;

        while (true) {
            try {
                Thread.sleep(2000);
                t = b.entrar(num);

                // Espera para entrar otra vez porque se lo han cortado
                if (t) {
                    Thread.sleep(200000);
                } else { // Se da una vuelta e intenta entrar de nuevo
                    Thread.sleep(4000);
                }

            } catch (InterruptedException e) {
                // Manejo de excepciones
                e.printStackTrace();
            }
        }
    }
}
