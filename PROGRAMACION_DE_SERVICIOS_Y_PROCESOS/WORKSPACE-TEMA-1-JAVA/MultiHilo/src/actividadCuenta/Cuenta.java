package actividadCuenta; 

public class Cuenta {
    private int saldo;
    private int saldoMaximo;

    // Constructor que inicializa el saldo actual y el saldo máximo
    public Cuenta(int saldoInicial, int saldoMaximo) {
        this.saldo = saldoInicial;
        this.saldoMaximo = saldoMaximo;
    }

    // Obtener el saldo actual.
    public synchronized int getSaldo() {
        return saldo;
    }

    // Método sincronizado para ingresar dinero
    public synchronized void ingresar(int cantidad, String nom) {
        if (saldo + cantidad > saldoMaximo) {
            System.out.println(nom + " no puede ingresar " + cantidad + " porque excede el saldo máximo (" + saldoMaximo + ")");
            System.exit(0);  // Finaliza el proceso si se supera el saldo máximo.
        } else {
            saldo += cantidad;
            System.out.println(nom + " ingresó " + cantidad + " ACTUAL(" + getSaldo() + ")");
        }
    }

    // Método sincronizado para retirar dinero
    public synchronized void reintegrar(int cantidad, String nom) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            System.out.println(nom + " retira " + cantidad + " ACTUAL(" + getSaldo() + ")");
        } else {
            System.out.println(nom + " no puede retirar " + cantidad + " porque no hay suficiente saldo (" + getSaldo() + ")");
            System.exit(0);  // Finaliza el proceso si no hay suficiente saldo.
        }
    }
}
