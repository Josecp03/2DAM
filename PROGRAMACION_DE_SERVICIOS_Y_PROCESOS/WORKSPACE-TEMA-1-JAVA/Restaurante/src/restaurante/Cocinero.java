package restaurante;


public class Cocinero extends Thread {
	
	CocineroDurmiente c;
	int num;

	
	public Cocinero(CocineroDurmiente c) {
		super();
		this.c = c;
	}

	public void run() {
		
		while (true) {
			
			try {
				
				c.esperarCliente();
				
				// Cocinar el plato
				Thread.sleep(5000);
				
				c.acabarPlato();
				
				// Decansa un poco
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {

				e.printStackTrace();
				
			}

		}

	}
}