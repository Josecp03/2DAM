# Inicializar las variables
numeroDeNumeros = -1

while numeroDeNumeros < 0:

    # Pedir al usuario el número de números que se van a introducir
    numeroDeNumeros = int(input("¿Cuántos números se van a introducir?"))

# Inicializar la variable que contiene al primer número
primerNumero = None

for i in range (numeroDeNumeros):
        
    # Pedir al usuario que introduzca el número actual
    num = int(input(f"Introduzca el número { i + 1}: "))

    # Guardar el primer número que se introduce en una variable
    if i == 0:
        primerNumero = num

    # Comprobar que el número sea menor que el primero
    if num < primerNumero:
        print("El número no es mayor que el primero")

# Imprimir un mensaje cuando el programa haya terminado
print("Programa terminado")