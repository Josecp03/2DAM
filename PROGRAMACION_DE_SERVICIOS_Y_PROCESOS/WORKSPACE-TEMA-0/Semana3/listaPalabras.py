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

