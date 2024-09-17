package PruebaNivel;

public class Nivel10 {

	public static void main(String[] args) {
		
		// Inicializar varibales
        int[]array={1,2,3,4,5};
        int i=array.length-1;
        recorrerArrayInversoRecursivo(array,i); // Llamada al método
		
	}
	
	// Método recursivo
    public static void recorrerArrayInversoRecursivo(int[] array, int i) {
        if (i>=0){
            System.out.println("Elemento en la posición "+i+": "+array[i]);
            recorrerArrayInversoRecursivo(array,i-1); 
        }
    }
	
}
