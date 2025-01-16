package ejercicioEntregable;

import java.io.Serializable;

public class Numeros implements Serializable {
    
	// Atributos
	private static final long serialVersionUID = 1L;
    private int numero;
    private long cuadrado;
    private long cubo;
    
    // Constructores
    public Numeros(int numero, long cuadrado, long cubo) {
        this.numero = numero;
        this.cuadrado = cuadrado;
        this.cubo = cubo;
    }
    
    public Numeros() {
    	
    }

    // Getters y Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }
    
}
