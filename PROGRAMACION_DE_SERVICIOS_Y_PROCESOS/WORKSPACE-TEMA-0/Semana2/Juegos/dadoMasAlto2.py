# Importo la clase necesaria
import random

# Inicializar la variable que contiene el número de dados  
numeroDados = 0


# Pedir el número hasta que el número de jugadores sea positivo 
while numeroDados <= 0:
    
    # Imprimo el mensaje de introducción al juego
    print("EL DADO MÁS ALTO (1)")
    numeroDados = int(input("Número de dados: "))

    # Comprobar que sea un número válido
    if numeroDados <= 0:
        print("!Imposible!")

# Hacer lo mismo para cada jugador
for i in range(2):

    # Inicializar un array donde voy a ir metiendo la puntuación de los dados
    listaTiradas = [0] * numeroDados

    # Inicializar la variable mayor
    mayor = 0

    # Recorre un for tantas veces como jugadores haya
    for j in range(numeroDados):
        
        # Generar la tirada aleatoriamente
        tirada = random.randint(1,6)

        # Comprobar que la tirada actual sea mayor que la anterior
        if tirada > mayor:
            mayor = tirada

        # Meter la tirada en un array
        listaTiradas[j] = tirada
        
        # Mostrar el array cuando se haya completado
        if j == numeroDados - 1:
            # Imprimir las tiradas
            print(f"Jugador {i+1}: {listaTiradas}")

        # Asignar el mayor de cada jugador
        if i == 0:
            mayor1 = mayor
        else:
            mayor2 = mayor

        
# Comprobar cual de los dos tiene mayor puntuación
if mayor1 > mayor2:
    print("Ha ganado el jugador 1")
elif mayor2 > mayor1:
    print("Ha ganado el jugador 2")
else:
    print("Han empatado")
