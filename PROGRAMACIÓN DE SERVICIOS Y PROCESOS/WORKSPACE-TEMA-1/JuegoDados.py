import random

puntuacion1 = random.randint(1,6)
puntuacion2 = random.randint(1,6)

print("JUEGO DE DADOS")
print(f"Alberto ha sacado un {puntuacion1}.")
print(f"Bárbara ha sacado un {puntuacion2}.")

if (puntuacion1 == puntuacion2):
    print("Han empatado.")
elif (puntuacion1 > puntuacion2):
    print("Alberto ha ganado.")
else:
    print("Bárbara ha ganado.")