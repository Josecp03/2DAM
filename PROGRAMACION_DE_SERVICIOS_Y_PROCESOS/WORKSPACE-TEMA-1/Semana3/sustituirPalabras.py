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

# Pedir la palabra a sustituir
palabraSustituida = input("Sustituir la palabra: ")

# Pedir la palabra nueva 
palabraNueva = input("por la palabra: ")

# Buscar la palabra a sustituir
for i in range(len(listaPalabras)):

    # Comprobar que el elemento actual de la lista sea igual a la palabra que buscamos
    if listaPalabras[i] == palabraSustituida:
        listaPalabras[i] = palabraNueva

# Imprimir el resultado
print(f"La lista es ahora: {listaPalabras}")


    

