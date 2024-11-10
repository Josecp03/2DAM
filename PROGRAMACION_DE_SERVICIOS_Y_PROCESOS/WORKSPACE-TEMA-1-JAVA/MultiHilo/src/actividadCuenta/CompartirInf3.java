package actividadCuenta;

public class CompartirInf3 {

    public static void main(String[] args) {
        // Crear una cuenta con saldo inicial de 1000 y saldo máximo de 2000
        Cuenta cuentaCompartida = new Cuenta(500, 1500);

        // Crear varias personas que compartirán la misma cuenta
        Persona p1 = new Persona("María", cuentaCompartida);
        Persona p2 = new Persona("Iván", cuentaCompartida);
        Persona p3 = new Persona("Ana", cuentaCompartida);

        // Iniciar los hilos
        p1.start();
        p2.start();
        p3.start();
    }
}
