# Importar lo necesario
import subprocess

# Asignar un resultado
result = subprocess.run(["python", "divisores.py", "100"], capture_output=True, text= True)

# Imprimir el resultado
print(result.stdout)