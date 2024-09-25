# Importo la clase necesaria
import random

# Inicializar la variable que contiene el número de dados que se van a lanzar
numeroDados = -1

# Pedir el número hasta que sea mayor que 0
while numeroDados <= 0:
    
    # Imprimo el mensaje de introducción al juego
    print("TIRADA DE DADOS")
    numeroDados = int(input("Número de dados: "))

    # Comprobar que sea un número válido
    if numeroDados <= 0:
        print("!Imposible!")

# Inicializar un array donde voy a ir metiendo la puntuación de los dados
listaTiradas = [0] * 5

# Recorrer un for el número de veces indicado 
for i in range(numeroDados):

    # Generar la tirada aleatoriamente
    tirada = random.randint(1,6)

    # Meter la tirada en un array
    listaTiradas[i] = tirada

# Imprimir las tiradas
print(f"Dados: {listaTiradas}")
