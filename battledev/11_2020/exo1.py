s = 0
for _ in range(int(input())):
	s += input()[-5:].isdigit()
print(s)

# alternatively: print(sum(input()[-5:].isdigit() for _ in range(int(input()))))
