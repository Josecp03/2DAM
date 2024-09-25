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



# Inicializar un array donde voy a ir metiendo la puntuación de los dados
listaTiradas = [0] * numeroDados

# Inicializar la variable mayor
mayor = 0

# Recorre un for tantas veces como jugadores haya
for i in range(numeroDados):
    
    # Generar la tirada aleatoriamente
    tirada = random.randint(1,6)

    # Comprobar que la tirada actual sea mayor que la anterior
    if tirada > mayor:
        mayor = tirada

    # Meter la tirada en un array
    listaTiradas[i] = tirada


# Imprimir las tiradas
print(f"Dados: {listaTiradas}")

# Imprimir la tirada más alta
print(f"El dado más alto es {mayor}")