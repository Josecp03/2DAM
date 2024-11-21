package restaurante;

public class CocineroDurmiente {

	private int numClientes;
	private int numPersonasSalaEspera = 0;
	private boolean cocineroOcupado = false;
	private boolean finPlato = false;
	private boolean cocineroDormido = false;
	
	public CocineroDurmiente(int numClientes) {
		super();
		this.numClientes = numClientes;
	}
	
	public synchronized boolean entrar(int clienteId) throws InterruptedException {

		if (numPersonasSalaEspera == numClientes) {
			
			// Si no hay mesas libres, me voy sin comer
			System.out.println("****** El cliente " + clienteId + " intenta entrar pero no hay sitio******");
			return false;

		} else {

			// Entro esperando a ser atendido
			numPersonasSalaEspera++;
			System.out.println("****** El cliente " + clienteId + " espera en la sala de espera hasta que le atienden******");

			while (cocineroOcupado) {
				wait();
			}

			// Desocupo la sala de espera ya que entro
			numPersonasSalaEspera--;

			// El cocinero me atiende
			cocineroOcupado = true;

			// Si el cocinero está dormido lo despierto
			if (cocineroDormido) {
				System.out.println("****** El cliente " + clienteId + " despierta al cocinero ******");
				notifyAll();
			}

			// Espero a que me corte el pelo
			System.out.println("****** El cliente " + clienteId + " come la comida del cocinero ******");

			while (!finPlato) {
				wait();
			}

			cocineroOcupado = false;

			// Que pase el siguiente
			notifyAll();
			System.out.println("****** El cliente " + clienteId + " se va comido ******");

			return true;

		}
		
	}
	
	public synchronized void esperarCliente() throws InterruptedException { 
	    
		// El barbero espera a que llegue un cliente 
		// Se supone que le corta el pelo fuera del monitor
		cocineroDormido = true;
		while (!cocineroOcupado) {
			
			System.out.println(" 		Cocinero se duerme esperando clientes -_- ZZZZZZZZZZ");
			wait();
			
		}
		
		cocineroDormido = false;			
		System.out.println("		Cocinero cocinando XXXXXXXXXX");
                          
    }

	public synchronized void acabarPlato() {

		finPlato = true;
		System.out.println("		Cocinero termina de cocinar y llama al siguiente cliente......");
		notifyAll();

	}

	public static void main(String[] args) {

		int numMaxSalaEspera = 3;
		int numClientes = 7;
		CocineroDurmiente c = new CocineroDurmiente(numClientes);
		new Cocinero(c).start();
		for (int i = 0; i < numClientes; i++)
			new Cliente(c, i).start();

	}
	
	
	
}
