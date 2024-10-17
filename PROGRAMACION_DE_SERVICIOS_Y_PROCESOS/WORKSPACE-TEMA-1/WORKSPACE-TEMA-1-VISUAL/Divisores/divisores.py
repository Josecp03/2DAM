# Importar lo necesario
import sys

# Función para econtrar los divisores del número
def encontrar_divisores(numero):
    divisores = []
    for i in range(1, numero + 1):
        if numero % i == 0:
            divisores.append(i)
    return divisores

# Comprobar la longitud de los argumentos (nombre del programa, más número que se le pide al usaurio)
if len(sys.argv) == 2:

    # Pedir al usuario un número
    numero = int(input("Introduce un número: "))

    # Obtener los divisores
    resultado = encontrar_divisores(numero)

    # Mostrar los divisores
    print(f"Los divisores de {numero} son: {resultado}")

else:

    # Mostrar mensaje de eror
    print("Error, introduce los parámetros correctamente")

