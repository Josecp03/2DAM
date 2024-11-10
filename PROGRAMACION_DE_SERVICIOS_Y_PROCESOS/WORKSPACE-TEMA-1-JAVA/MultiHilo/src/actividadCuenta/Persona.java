package actividadCuenta;

public class Persona extends Thread {
    private Cuenta cuenta;

    public Persona(String nombre, Cuenta cuenta) {
        super(nombre);
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        try {
            // Primer ingreso
            int ingreso1 = (int) (Math.random() * 500 + 1);
            cuenta.ingresar(ingreso1, getName());
            Thread.sleep(500);

            // Primer reintegro
            int reintegro1 = (int) (Math.random() * 500 + 1);
            cuenta.reintegrar(reintegro1, getName());
            Thread.sleep(500);

            // Segundo ingreso
            int ingreso2 = (int) (Math.random() * 500 + 1);
            cuenta.ingresar(ingreso2, getName());
            Thread.sleep(500);

            // Segundo reintegro
            int reintegro2 = (int) (Math.random() * 500 + 1);
            cuenta.reintegrar(reintegro2, getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

