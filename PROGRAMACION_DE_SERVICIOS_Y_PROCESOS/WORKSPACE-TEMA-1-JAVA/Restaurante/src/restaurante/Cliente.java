package restaurante;


public class Cliente extends Thread {

	CocineroDurmiente c;
	int num;

	
    public Cliente(CocineroDurmiente c, int num) {
		super();
		this.c = c;
		this.num = num;
	}


	public void run() {
        boolean t = false;

        while (true) {
            try {
                Thread.sleep(2000);
                t = c.entrar(num);

                // Espera para entrar otra vez porque otra persona esta comiendo
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
