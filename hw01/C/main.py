import os
import text

command = ""
for i in range(1, 101):        
    command += text.get_command(i) + "\n"    
    
res = os.popen(command).read()
print(res)
