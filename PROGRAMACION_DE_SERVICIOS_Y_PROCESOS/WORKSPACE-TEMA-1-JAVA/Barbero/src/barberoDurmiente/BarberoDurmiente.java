package barberoDurmiente;

public class BarberoDurmiente {
	private int nSillasEspera;
	private int nSillasEsperaOcupadas = 0;
	private boolean sillaBarberoOcupada = false;
	private boolean finCorte = false;
	private boolean barberoDormido = false;

	public BarberoDurmiente(int nSillasEspera) {
		this.nSillasEspera = nSillasEspera;
	}

	public synchronized boolean entrar(int clienteId) throws InterruptedException {

		if (nSillasEsperaOcupadas == nSillasEspera) {
			// Si no hay sillas libres, me voy sin cortar el pelo
			System.out.println("****** El cliente " + clienteId + " intenta entrar pero no hay sitio******");
			return false;

		} else {

			// Me quedo esperando si la silla del barbero está ocupada
			nSillasEsperaOcupadas++;
			System.out.println("****** El cliente " + clienteId + " se sienta en una silla de la sala de espera******");

			while (sillaBarberoOcupada) {
				wait();
			}

			// Desocupo la silla de espera
			nSillasEsperaOcupadas--;

			// Me siento en la silla del barbero
			sillaBarberoOcupada = true;

			// Si el barbero está dormido lo despierto
			if (barberoDormido) {
				System.out.println("****** El cliente " + clienteId + " despierta al barbero ******");
				notifyAll();
			}

			// Espero a que me corte el pelo
			System.out.println("****** El cliente " + clienteId + " en la silla de barbero ******");

			while (!finCorte) {
				wait();
			}

			sillaBarberoOcupada = false;

			// Que pase el siguiente
			notifyAll();
			System.out.println("****** El cliente " + clienteId + " se va con el pelo cortado ******");

			return true;

		}
	}

	public synchronized void esperarCliente() throws InterruptedException { 
    
		// El barbero espera a que llegue un cliente 
		// Se supone que le corta el pelo fuera del monitor
		barberoDormido = true;
		while (!sillaBarberoOcupada) {
			
			System.out.println(" 		Barbero se duerme esperando clientes -_- ZZZZZZZZZZ");
			wait();
			
		}
		
		barberoDormido = false;			
		System.out.println("		Barbero cortando el pelo XXXXXXXXXX");
                          
    }

	public synchronized void acabarCorte() {

		finCorte = true;
		System.out.println("		Barbero termina de cortar el pelo y llama al siguiente......");
		notifyAll();

	}

	public static void main(String[] args) {

		int numSillas = 4;
		int numClientes = 6;
		BarberoDurmiente s = new BarberoDurmiente(numSillas);
		new BarberoB(s).start();
		for (int i = 0; i < numClientes; i++)
			new ClienteB(s, i).start();

	}

}