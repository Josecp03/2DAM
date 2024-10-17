import subprocess
result = subprocess.run(["python", "my_python_file.py"], capture_output = True, text = True)
print(result.stdout)