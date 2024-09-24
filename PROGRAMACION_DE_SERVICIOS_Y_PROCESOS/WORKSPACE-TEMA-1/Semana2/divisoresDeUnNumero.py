
# Inicializar la variable n
n = -1

# Entrar en el bucle siempre que se introduzca un valor negativo
while n < 0:

    # Pedir el número
    n = int(input("Introduzca un número mayor entero mayor que cero: "))
    
    # Calcular los divisores
    for i in range(1, n + 1):
        if n % i == 0:
            if i == n:
                print(i)
            else:
                print(f"{i}, ", end = "")