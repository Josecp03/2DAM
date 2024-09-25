# Importo la clase necesaria
import random

# Inicializar la variable que contiene el número de dados  
numeroDados = 0


# Inicializar la variable que contiene el valor buscado  
valorBuscado = 0

# Pedir el número hasta que el número de jugadores sea positivo 
while numeroDados <= 0:
    
    # Imprimo el mensaje de introducción al juego
    print("OBTENER VALOR (2)")
    numeroDados = int(input("Número de dados: "))

    # Comprobar que sea un número válido
    if numeroDados <= 0:
        print("!Imposible!")

# Pedir el número hasta que el valor buscado esté entre 1 y 6 
while valorBuscado < 1 or valorBuscado > 6:
    
    # Pido al usuario el valor a conseguir en la tirada del dado
    valorBuscado = int(input("Valor a conseguir: "))

        
    # Comprobar que sea un número válido
    if valorBuscado < 1 or valorBuscado > 6:
        print(f"!Imposible conseguir un {valorBuscado}!")

# Inicializar un array donde voy a ir metiendo la puntuación de los dados
listaTiradas = [0] * numeroDados

# Inicializar la variable booleana
victoria = False

# Recorre un for tantas veces como jugadores haya
for i in range(numeroDados):
    
    # Generar la tirada aleatoriamente
    tirada = random.randint(1,6)

    # Comprobar que la tirada actual sea igual al valor buscado
    if tirada == valorBuscado:
        victoria = True

    # Meter la tirada en un array
    listaTiradas[i] = tirada


# Imprimir las tiradas
print(f"Dados: {listaTiradas}")

# Comprobar si el jugador ha ganado
if victoria:
    print("El jugador ha ganado")
else:
    print("El jugador ha perdido")

