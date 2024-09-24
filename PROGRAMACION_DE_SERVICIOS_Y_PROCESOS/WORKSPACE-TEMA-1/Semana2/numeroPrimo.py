# Importar lo necesario
import math

# Definir una función para calcular que un número sea primo
def esPrimo (numero):
    
    # Ir comprobando sus divisores
    for i in range(2, int(math.sqrt(numero)) + 1):
        if numero % i == 0:
            return False

    # Devolver true si no se encontraron divisores
    return True    
    

# Inicializar las variables
numero = 1

while numero <= 1:

    # Pedir al usuario el número de números que se van a introducir
    numero = int(input("Introduzca un número entero mayor que 1: "))

# Mostrar un mensaje de si el número introduzido por el usuario es primo o no
if esPrimo(numero):
    print(f"{numero} es primo.")
else:
    print(f"{numero} no es primo.")
