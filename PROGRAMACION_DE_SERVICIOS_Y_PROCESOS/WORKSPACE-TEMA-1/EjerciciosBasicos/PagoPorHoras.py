
# Calcular el pago que tiene que realizar el jefe por horas trabajadas del empleado
horasTrabajadas = -1

while (horasTrabajadas < 0):
        horasTrabajadas = (int)(input("¿Cúantas horas ha trabjado el empleado?"))



precioHora = -1
while (precioHora < 0):
    precioHora = input("¿Cúanto cobra por hora?")
    if (precioHora < 0):
        print("Por favor introduzca un valor positivo")

print(f"Usted ha cobrado un total de {precioHora*horasTrabajadas} €")