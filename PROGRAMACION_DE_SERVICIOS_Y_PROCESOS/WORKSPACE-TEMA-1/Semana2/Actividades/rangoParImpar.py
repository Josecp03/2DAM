# Imprimir el nombre del ejercicio
print("PARES E IMPARES")

# Inicializar las variables
num1 = 0
num2 = -1

while num2 < num1:
    
    # Pedir los números al usario
    num1 = int(input("Escriba un número entero: "))
    num2 = int(input(f"Escriba un número entero mayor o igual que {num1}: "))

    # Comprobar que el segundo número sea mayor o igual que el primero
    if num2 < num1:
        print(f"!Le he pedido un número entero mayor o igual que {num1}!")
    else:
        for i in range(num1, num2 + 1):
            if i % 2 == 0:
                print(f"El número {i} es par")
            else:
                print(f"El número {i} es impar")

        