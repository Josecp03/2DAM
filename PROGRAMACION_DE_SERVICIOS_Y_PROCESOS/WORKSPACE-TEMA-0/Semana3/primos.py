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


# Inicializar la variable a 0
numeroIntroducido = 0

# Pedir el número hasta que el número de jugadores sea positivo 
while numeroIntroducido <= 0:
    
    # Imprimo el mensaje de introducción al jueg
    numeroIntroducido = int(input("Dígame un número: "))

    # Comprobar que sea un número válido
    if numeroIntroducido <= 0:
        print("Introduzca un número positivo")

# Inicializar la lista
listaPrimos = []


for i in range(1, numeroIntroducido):
    
    # Comprobar que sea divisor
    if esPrimo(i):
        listaPrimos.append(i)

# Imprimir el resultado
print(f"Primos hasta {numeroIntroducido}: {listaPrimos}")