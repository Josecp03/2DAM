import sys

if len(sys.argv) == 3:
    suma = int(sys.argv[1]) + int(sys.argv[2])  # Convertir cada argumento a int antes de sumar
    print(suma)
else:
    print("Error, introduce los parámetros correctamente")
