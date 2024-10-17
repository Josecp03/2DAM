import subprocess

result = subprocess.run(['python', 'suma.py', '5', '7'], capture_output=True, text= True)
print(result.stdout)