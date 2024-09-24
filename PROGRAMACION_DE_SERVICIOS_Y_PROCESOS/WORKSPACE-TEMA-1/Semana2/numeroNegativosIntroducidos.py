# Inicializar las variables
numeroDeNumeros = -1

while numeroDeNumeros < 0:

    # Pedir al usuario el número de números que se van a introducir
    numeroDeNumeros = int(input("¿Cuántos números se van a introducir? "))

# Inicializar el contador de números negativos
contadorNegativos = 0

for i in range (numeroDeNumeros):
        
    # Pedir al usuario que introduzca el número actual
    num = int(input(f"Introduzca el número { i + 1}: "))

    # Sumar al contador cuando el número sea negativo
    if num < 0:
        contadorNegativos += 1


# Imprimir cuántos números negativos se han introducido
print(f"Se han introducido {contadorNegativos} números negativos")