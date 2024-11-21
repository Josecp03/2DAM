package barberoDurmiente;

public class BarberoB extends Thread {
	
	BarberoDurmiente b;
	int num;

	public BarberoB(BarberoDurmiente b) {
		this.b = b;
	}

	public void run() {
		
		while (true) {
			
			try {
				
				b.esperarCliente();
				
				// Cortar pelo
				Thread.sleep(5000);
				
				b.acabarCorte();
				
				// Decansa un poco
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {

				e.printStackTrace();
				
			}

		}

	}
}