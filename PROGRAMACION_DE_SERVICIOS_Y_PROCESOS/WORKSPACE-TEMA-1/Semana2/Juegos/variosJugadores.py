# Importo la clase necesaria
import random

# Inicializar la variable que contiene el número de jugadores 
numeroJugadores = 0

# Pedir el número hasta que sea mayor que 1
while numeroJugadores <= 1:
    
    # Imprimo el mensaje de introducción al juego
    print("TIRADA DE DADOS")
    numeroJugadores = int(input("Número de jugadores: "))

    # Comprobar que sea un número válido
    if numeroJugadores <= 1:
        print("!Imposible!")

# Recorrer un for para completar todos los jugadores indicados
for i in range(numeroJugadores):
    tirada = random.randint(1,6)
    print(f"Jugador {i+1}: {tirada}")

