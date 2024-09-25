# Inicializar la variable numeroDeNumeros
numeroDeNumeros = -1

# Pedir al usuario el número hasta que sea positivo
while numeroDeNumeros < 0:

    # Pedir al usuario el número de números que se van a introducir
    numeroDeNumeros = int(input("¿Cuántos números se van a introducir? "))

# Inicializar la suma a 0
sumaNumeros = 0

# Bucle for para pedir tantos números como el usuario haya introducido
for i in range (numeroDeNumeros):
        
    # Pedir al usuario que introduzca el número actual
    num = float(input(f"Introduzca el número { i + 1}: "))

    # Ir realizando la suma de los números
    sumaNumeros += num

# Imprimir la suma total de los números
print(f"La suma total de los números introducidos es de {round(sumaNumeros, 2)}")

   