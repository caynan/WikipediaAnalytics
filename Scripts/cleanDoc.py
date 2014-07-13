import os

files = [f for f in os.listdir('.') if os.path.isfile(f)]
for filePath in files:
	f = open(filePath,"r")

	lines = f.readlines()

	f.close()

	f = open(filePath,"w")

	for line in lines:
			continue
		else:
			f.write(line)

	f.close()
