A = [4, 0, 8, 6, 9, 9, 2, 7]

i = 0
j = 7
v = A[0]
while True:
	while True:
		i = i + 1
		if (A[i] >= v):
			break
	while True:
		j = j - 1
		if (A[j] <= v):
			break
	if i < j:
		temp = A[i]
		A[i] = A[j]
		A[j] = temp
	if (i >= j):
		break
temp = A[0]
A[0] = A[j]
A[j] = temp
print(A)