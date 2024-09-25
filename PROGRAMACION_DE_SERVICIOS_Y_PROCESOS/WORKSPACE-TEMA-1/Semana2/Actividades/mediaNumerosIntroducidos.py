# Inicializar la variable numeroDeNumeros
numeroDeNumeros = -1

# Pedir al usuario el número hasta que sea positivo
while numeroDeNumeros < 0:

    # Pedir al usuario el número de números que se van a introducir
    numeroDeNumeros = int(input("¿Cuántos números se van a introducir? "))

# Inicializar las variables correspondientes antes del bucle for
sumaNumeros = 0
max = 0
min = 2**31 - 1 # Valor cmúnmente utilizado como máximo

# Bucle for para pedir tantos números como el usuario haya introducido
for i in range (numeroDeNumeros):
        
    # Pedir al usuario que introduzca el número actual
    num = int(input(f"Introduzca el número { i + 1}: "))

    # Ir realizando la suma de los números
    sumaNumeros += num

    # Comprobar si el número actual es mayor que el mayor de los anteriores
    if num > max:
        max = num
    
    # Comprobar si el número actual es menor que el menor de los anteriores
    if num < min:
        min = num

# Imprimir los resultados finales
print(f"El mayor número introducido es {max}")
print(f"El menor número introducido es {min}")
print(f"La media de los números introducidos es {float(round((sumaNumeros / numeroDeNumeros), 2))}")