# Inicializar la variable a 0
numeroIntroducido = 0

# Pedir el número hasta que el número de jugadores sea positivo 
while numeroIntroducido <= 0:
    
    # Imprimo el mensaje de introducción al jueg
    numeroIntroducido = int(input("Dígame un número: "))

    # Comprobar que sea un número válido
    if numeroIntroducido <= 0:
        print("Introduzca un número positivo")

# Inicializar la lista
listaDivisores = []

# Inicalizar el contador
contadorDivisores = 0

for i in range(1, numeroIntroducido + 1):
    
    # Comprobar que sea divisor
    if numeroIntroducido % i == 0:
        listaDivisores.append(i)
        contadorDivisores += 1

# Imprimir el resultado
print(f"{numeroIntroducido} tiene {contadorDivisores} divisores: {listaDivisores}")