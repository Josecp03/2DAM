/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

/**
 *
 * @author josec
 */
public class Bingo {

    static int carton[][][] = new int[4][5][5];
    
    public static void main(String[] args) {
        
        int inicio;
        int num;
        
        // Llenando los cartones
        for (int numCarton = 0; numCarton < 4; numCarton++) {
            
            inicio = 1;
            
            for (int fila = 0; fila < 5; fila++) {
                for (int col = 0; col < 5; col++) {
                    
                    // Genera un número aleatorio entre inicio e inicio + 14
                    num = (int) (Math.random() * 15) + inicio;
                    
                    // Verificar que el número no se repita en el cartón
                    while (buscaNum(numCarton, num)) {                        
                        num = (int) (Math.random() * 15) + inicio;
                    }
                    
                    // Aisgna el número al cartón en la posición correspondiente
                    carton[numCarton][fila][col] = num;
                    
                }
                
                // Aumenta el rango de números en 15 para la siguiente columna
                inicio = inicio + 15;
                      
            }
            
            // La posición central del cartón es un espaicio libre (0)
            carton[numCarton][2][2] = 0;
            
        }
        
        // Muestra los cartones de bingo generados
        mostrarCartones();
        
    }
    
    // Método que verifica si un número ya está en el cartón paa evitar repeticiones
    public static boolean buscaNum(int numCarton, int valor) {
        
        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 5; col++) {
                
                if (valor == carton[numCarton][fila][col]) {
                    return true;
                }
                
            }
            
            
        }
        
        return false;
        
    }
    
    // Muestra todos los cartones de bingo generados en el arreglo multidimensional
    public static void mostrarCartones() {
        
        for (int numCarton = 0; numCarton < 4; numCarton++) {
            System.out.println("Cartón: " + numCarton);
            for (int fila = 0; fila < 5; fila++) {
                for (int col = 0; col < 5; col++) {
                    System.out.print(carton[numCarton][fila][col] + "\t");
                }
                System.out.println();
            }
            System.out.println();
                        
        }
        
    }
    
}
