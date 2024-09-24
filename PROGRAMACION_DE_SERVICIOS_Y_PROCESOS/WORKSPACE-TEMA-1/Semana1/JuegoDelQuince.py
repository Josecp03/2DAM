import random

# Definir la función para sacar cartas y sumar la puntuación
def sacar_cartas(nombreJugador, numeroCartas=3):
    puntuacionTotal = 0
    print(f"{nombreJugador} ha sacado un ", end="")
    
    # Sacar las cartas
    for i in range(numeroCartas):
        cartaActual = random.randint(1, 10)  # Generar un número aleatorio del 1 al 10
        if i == numeroCartas - 1:  # Si es la última carta
            print(f"y un {cartaActual}.")
        else:
            print(f"{cartaActual}, un ", end="")
        # Sumar la carta actual
        puntuacionTotal += cartaActual  
    
    # Devolver la puntuación total 
    return puntuacionTotal

# Inicializar los nombres de los participantes
nombrePersona1 = "Gloria"
nombrePersona2 = "Héctor"

# Llamar a la función para cada jugador y obtener sus puntuaciones
puntuacion1 = sacar_cartas(nombrePersona1)
puntuacion2 = sacar_cartas(nombrePersona2)

# Establecer la puntuación límite
puntuacionLimite = 15

# Comprobar quién ha ganado, considerando el límite
if puntuacion1 > puntuacionLimite and puntuacion2 > puntuacionLimite:
    print("Ambos se han pasado del límite. No hay ganador.")
elif puntuacion1 > puntuacionLimite:
    print(f"{nombrePersona2} ha ganado porque {nombrePersona1} se pasó del límite.")
elif puntuacion2 > puntuacionLimite:
    print(f"{nombrePersona1} ha ganado porque {nombrePersona2} se pasó del límite.")
elif puntuacion1 > puntuacion2:
    print(f"Ha ganado {nombrePersona1} con {puntuacion1} puntos.")
elif puntuacion2 > puntuacion1:
    print(f"Ha ganado {nombrePersona2} con {puntuacion2} puntos.")
else:
    print(f"Han empatado con {puntuacion1} puntos.")
