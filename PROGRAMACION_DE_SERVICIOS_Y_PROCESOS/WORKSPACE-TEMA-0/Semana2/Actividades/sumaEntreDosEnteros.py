# Inicializar los números
numero1 = int(input("Introduzca el primer número: "))
numero2 = int(input("Introduzca el segundo número: "))

# Inicializar la suma de los números
sumaNumeros = 0

# Bucle for para recorrer todos los números que hay del primer número hasta el segundo
for i in range(numero1 + 1 , numero2):
    
    # Ir sumando los números correspondientes (Sin incluir los dos números introducidos)
    sumaNumeros += i

# Imprimir por pantalla la suma de los números totales
print(f"La suma de los números entre {numero1} y {numero2} es de {sumaNumeros}")



