#c1560749
#Last Edited: 3/12/2017
#Stream Cipher Attacker

#=FUNCTIONS===============================================================================================
#Basic Xor function.
#Input: Plain/Cipher texts, based on 'type' argument.
#TYPE => 'c' = cipher input. 'p' = plaintext input.
#Output: XOR'd pair as ASCII
def xor(str1, str2, type):
	res = ""
	if type == 'p':
		for c1, c2 in zip(str1,str2):
			ans = ord(c1) ^ ord(c2)
			res += chr(ans)
	elif type == 'c':
		for c1, c2 in zip(str1,str2):
			ans = c1 ^ c2
			res += chr(ans)
	return res

#Assumptions:
#Inputs: XORd ciphertext and sample text
#Assumes both inputs are in ASCII.
def cribdrag(str1, sample):
	slide = 0		#Slide counter, determines starting index
	count = slide 	#Count, points to the next character to be compared
	current_res = ""	#Prints current result
	res = ""		#Holds the XOR'd chars as ASCII

	sample_length = len(sample)	#Length is used to check if sample can still be slid.
	while sample_length <= len(str1):				
		for char in sample:
			ans = ord(str1[count]) ^ ord(char)
			res += chr(ans)	
			current_res += chr(ans)			
			count += 1
		print("Slide " + str(slide) + " => " + str(current_res))
		slide += 1						#Slide the sample text to the right
		count = slide 					#Resets the 'next' pointer to the new starting index.
		sample_length += 1				#If this == str1 length, stop sliding.
		current_res = ""
	return res

#Finding the two reseeded Cipher texts
#Input: .txt file containing the ciphertexts
def identify(cipher_list):
	with open(cipher_list) as f:
		#Read all ciphers from file and put in list
		ciphers = f.read().split()
		cipher_ints = []	#Holds all integer form ciphers

		#Change all ciphers from hexa into integers
		for line in ciphers:
			cipher_ints.append(bytes.fromhex(line))

		#XOR each cipher pair
		count = 0	#Used to point to the next element (Next pointer)
		index = 1 	#Represents the current element. E.g: Ciphertext 3
		found = False	#If 'True', stop looking for a plaintext^plaintext pair.
		for cipher in cipher_ints:
			while count != (len(cipher_ints) - 1): #While not end of list
				result = xor(cipher, cipher_ints[count + 1], 'c') #xor current cipher with the next cipher
				if result == ''.join(i for i in result if ord(i)<128):	#Removes all non-ascii characters. If result still matches, XOR pair is plaintext ^ plaintext.
					print("**Ciphertext " + str(index) + " and Ciphertext " + str((count + 2)) + " is a plaintext^plaintext pair!")
					print(result)
					found = True
					break 	#break inner while loop
				count += 1 	#Next pointer now points to the next element
			if found == True:	#Breaks the outer for loop
				break
			else:	#Current cipher has no plaintext^plaintext pairs. Continue search.
				count = index	#Start next loop from the next element, ignore the previous element since search is done for it.
				print("Ciphertext " + str(index) + " has no plaintext^plaintext pairs.")
				index += 1
		if found == False:	#No pairs were found.
			print("no match found")

#=MAIN=====================================================================================================

#NOTE: Comment out either step to run only one function.
#Only comment entire steps, do NOT comment out anything individually.
#This is to ensure the program runs properly.
#Be careful not to uncomment comments!

#BEFORE DOING STEP 1, ENSURE 'ciphertexts.txt' IS IN THE SAME FOLDER AS THIS .PY FILE!
#Identifier
#==STEP 1: IDENTIFY THE TWO CIPHERTEXTS
identify('ciphertexts.txt')


#Cribdrag Function
#STEP 2: CRIBDRAG THE XORd TEXTS
#c1 (cipher 4) & c2 (cipher 10) are hardcoded in as I have already identified the correct ciphertexts.
c1 = "595db447860f4eca0eded126ce3523fa5598ddba7b58a6c6ce346da32e65b6ba907317171b135297fd023c9f291d9b34a739d381eb688e52d45fd94b38ff8d1ad5353653401332f9463bf98d43cf9d7edfc1f0c46385d9daf7267a319482142813b9f386"
c2 = "7940a05a870e40db0edad030807305fd5dd7dbab3e17b68ff8267ebe2268bbbaac6c440a10184ec5f91068976603da30aa2e9c83e724c272d317d55c38f98d5fc463644a14192ef96836f19607decf48d6d4f3cf629f8a8ef0212e7c8892043e14fce3d4"
c1 = bytes.fromhex(c1)
c2 = bytes.fromhex(c2)
c3 = xor(c1,c2,'c')
sample_text = " the "
print('\n' + cribdrag(c3,sample_text))


# #XOR Comparison
# #Optional Step: Comparing Ciphertext XORs & Plaintext XORs
# a1 = "Goodness, good job"
# a2 = "Wowie, great phone"
# b1 = "cd09042d392fe3991f3963fa7acdf6523b5b32f7233bc9c1351935f76d28ace611aae6e58cf764af943a71ecd991ea145eb6c8bef0cea8ec4beaded50c7b0bd84aeb526b119e2eb5b980bf2fb2ce54f378b3dfbb23d354a61433b00212a9705ff4858a90"
# b1 = bytes.fromhex(b1)
# b2 = "5a8ea0ee9700b655f206d3060d9a2dafe807e854583a32c2474ccccc3f09c20dfc740a7a8c642dcc6edb49fa2bd5d8326df4c88cc5df0d18594598d029f17ab4e34147a27aeb5b772a1c4e5d4453a2fdf740801ded754d1785afa795c5a71f7f724908e6"
# b2 = bytes.fromhex(b2)


# cipher_xor = xor(b1,b2,'c')
# plain_xor = xor(a1,a2,'p')

# print("\nCIPHERTEXT RESULTS")
# print('Before')
# print([cipher_xor])
# print('After')
# print([''.join(i for i in cipher_xor if ord(i)<128)])

# if cipher_xor == ''.join(i for i in cipher_xor if ord(i)<128):
# 	print('Match!')

# print("\nPLAINTEXT RESULTS")
# print('Before')
# print(plain_xor)
# print('After')
# print(''.join(i for i in plain_xor if ord(i)<128))

# if plain_xor == ''.join(i for i in plain_xor if ord(i)<128):
# 	print('Match!')
