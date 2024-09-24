import random

puntuacion1Carmen = random.randint(1,6)
puntuacion2Carmen = random.randint(1,6)
puntuacion1David = random.randint(1,6)
puntuacion2David = random.randint(1,6)

print("JUEGO DE DADOS (2)")
print(f"Carmen ha sacado un {puntuacion1Carmen} y un {puntuacion2Carmen}.")
print(f"David ha sacado un {puntuacion1David} y un {puntuacion2David}.")

puntuacionCarmen = puntuacion1Carmen + puntuacion2Carmen
puntuacionDavid = puntuacion1David + puntuacion2David 

if puntuacionCarmen == puntuacionDavid: 
    
    # Comprobar el valor más alto que ha sacado Carmen
    if puntuacion1Carmen > puntuacion2Carmen:
        puntuacionMayorCarmen = puntuacion1Carmen
    else:
        puntuacionMayorCarmen = puntuacion2Carmen

    #  Comprobar el valor más alto que ha sacado David
    if puntuacion1David > puntuacion2David:
        puntuacionMayorDavid = puntuacion1David
    else:
        puntuacionMayorDavid = puntuacion2David

    # Comprobar que uno de los dos haya tenido un valor más alto que el otro para desempatar
    if puntuacionMayorDavid != puntuacionMayorCarmen:
        
        # Comprobar cual de los dos es mayor
        if puntuacionMayorCarmen > puntuacionMayorDavid:
            print("Ha ganado Carmmen")
        else:
            print("Ha ganado David")
    else:
        print("Han empatado.")
        
elif puntuacionCarmen > puntuacionDavid:
    print("Carmen ha ganado.")
else:
    print("David ha ganado.")