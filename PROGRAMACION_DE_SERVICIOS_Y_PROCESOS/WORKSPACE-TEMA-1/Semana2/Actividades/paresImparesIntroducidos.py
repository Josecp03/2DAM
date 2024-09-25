# Inicializar las variables
numeroDeNumeros = -1

while numeroDeNumeros < 0:

    # Pedir al usuario el número de números que se van a introducir
    numeroDeNumeros = int(input("¿Cuántos números se van a introducir? "))

# Inicializar los contadores de pares e impares
contadorPares = 0
contadorImpares = 0

for i in range (numeroDeNumeros):
        
    # Pedir al usuario que introduzca el número actual
    num = int(input(f"Introduzca el número { i + 1}: "))

    # Guardar el primer número que se introduce en una variable
    if num % 2 == 0:
        contadorPares += 1
    else:
        contadorImpares += 1

# Imprimir cúantos pares e impares se han introducido
print(f"Se han introducido {contadorPares} números pares y {contadorImpares} impares")