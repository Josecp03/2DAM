package hilos2;

public class CompartirInf1 {

	public static void main(String[] args) {
		Contador cont = new Contador(100);
		HiloA a = new HiloA("HiloA", cont);
		HiloA b = new HiloA("HiloB", cont);
		a.start();
		b.start();
	}
	
}
