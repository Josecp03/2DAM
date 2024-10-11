# Inicializar la variable a 0
numeroPalabras = 0

# Pedir el número hasta que el número de jugadores sea positivo 
while numeroPalabras <= 0:
    
    # Imprimo el mensaje de introducción al jueg
    numeroPalabras = int(input("Dígame cuántas palabras tiene la lista: "))

    # Comprobar que sea un número válido
    if numeroPalabras <= 0:
        print("Introduzca un número positivo")

# Inicializar la lista
listaPalabras = [] * numeroPalabras

# Pedir todas las palabras
for i in range(numeroPalabras):
    palabraActual = input(f"Dígame la palabra {i + 1}: ")
    listaPalabras.append(palabraActual)

# Imprimir todas las palabras que se han introducido
print(listaPalabras)

# Pedir la palabra a buscar
palabraBuscada = input("Dígame la palabra a buscar: ")

# Inicializar contador
contador = 0

# Buscar cúantas veces se repite la palabra
for elemento in listaPalabras:

    # Comprobar que el elemento actual de la lista sea igual a la palabra que buscamos
    if elemento == palabraBuscada:
        contador += 1

# Imprimir el resultado
print(f"La palabra '{palabraBuscada}' aparece {contador} veces en la lista")


    

