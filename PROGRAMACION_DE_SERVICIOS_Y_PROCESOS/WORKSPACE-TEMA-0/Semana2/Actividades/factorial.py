# Inicializar la variable 
num = -1

# Pedir al usuario el número hasta que sea positivo
while num < 0:
    num = int(input("Introduzca un número entero mayor que 0: "))

# Inicializar las variables necesarias para calcular el factorial
aux = 1
resultado = 1

# Recorrer un bucle para ir calculando su factorial
while aux <= num:
    resultado *= aux
    aux += 1

# Imprimir el resultado
print(f"{num}! = {resultado}")