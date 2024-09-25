# Importo la clase necesaria
import random

# Inicializar la variable que contiene el número de dados  
numeroDados = 0

# Pedir el número hasta que el número de jugadores sea positivo 
while numeroDados <= 0:
    
    # Imprimo el mensaje de introducción al juego
    print("PARES Y NONES")
    numeroDados = int(input("Número de dados: "))

    # Comprobar que sea un número válido
    if numeroDados <= 0:
        print("!Imposible!")


# Inicializar un array donde voy a ir metiendo la puntuación de los dados
listaTiradas = [0] * numeroDados

# Inicializar las puntuaciones
puntuacionPares = 0
puntuacionImpares = 0

# Recorre un for tantas veces como dados se tienen que tirar
for i in range(numeroDados):
    
    # Generar la tirada aleatoriamente
    tirada = random.randint(1,6)

    # Comprobar si es par o impar
    if tirada % 2 == 0:
        puntuacionPares += 1
    else:
        puntuacionImpares += 1

    # Meter la tirada en un array
    listaTiradas[i] = tirada

# Imprimir los dados
print(f"Dados: {listaTiradas}")

# Comprobar si han ganado los pares o impares para ver que jugador ha ganado
if puntuacionPares > puntuacionImpares:
    print("Ha ganado el jugador de los pares")
elif puntuacionImpares > puntuacionPares:
    print("Ha ganado el jugador de los impares")
else:
    print("Han empatado")