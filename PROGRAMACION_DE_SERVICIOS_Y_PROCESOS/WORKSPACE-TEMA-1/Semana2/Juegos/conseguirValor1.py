# Importo la clase necesaria
import random

# Inicializar la variable que contiene el número de jugadores  
numeroJugadores = 0


# Inicializar la variable que contiene el valor buscado  
valorBuscado = 0

# Pedir el número hasta que el número de jugadores sea positivo 
while numeroJugadores <= 0:
    
    # Imprimo el mensaje de introducción al juego
    print("OBTENER VALOR (1)")
    numeroJugadores = int(input("Número de jugadores: "))

    # Comprobar que sea un número válido
    if numeroJugadores <= 0:
        print("!Imposible!")

# Pedir el número hasta que el valor buscado esté entre 1 y 6 
while valorBuscado < 1 or valorBuscado > 6:
    
    # Pido al usuario el valor a conseguir en la tirada del dado
    valorBuscado = int(input("Valor a conseguir: "))

        
    # Comprobar que sea un número válido
    if valorBuscado < 1 or valorBuscado > 6:
        print(f"!Imposible conseguir un {valorBuscado}!")

# Recorre un for tantas veces como jugadores haya
for i in range(numeroJugadores):
    
    # Lanzar el dado
    tirada = random.randint(1,6)

    # Comprobar que la tirada sea igual que el valor buscado
    if tirada == valorBuscado:
        resultado = "CONSEGUIDO"
    else:
        resultado = tirada

    # Imprimir el resultado de la tirada
    print(f"Jugador {i+1}: {resultado}")


