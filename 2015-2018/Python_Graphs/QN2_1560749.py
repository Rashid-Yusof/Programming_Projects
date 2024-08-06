import QN1_1560749
import random
import math
import copy
import collections
import matplotlib.pyplot as plt

#QN 2 PYTHON CODE

#Method 1/m1 = Position method data
#Method 2/m2 = Olympic method data

#Olympic method formula

def method2_score(boats, position):
	formula = 101 + (1000*(math.log10(boats))) - (1000*(math.log10(position)))
	return formula

#Generates sample data for method 1(Position Method) and method 2(Olympic Method)

def generate_method1_data():
	m1 = collections.OrderedDict() #Generates 100 key dictionary with 100 sets of Mean and S.d.
	for key in range(0,25):
		m1[key] = (float(random.randrange(0,25)), random.randrange(10.0)) #currently set to inconsistent s.ds.
	for key in range(25,50):
		m1[key] = (float(random.randrange(25,50)), random.randrange(10.0))
	for key in range(50,75):
		m1[key] = (float(random.randrange(50,75)), random.randrange(10.0))
	for key in range(75,100):
		m1[key] = (float(random.randrange(75,101)), random.randrange(10.0))
	
	results = collections.OrderedDict()
	for i in range(0,100):
		results[i] = []
	i = 0
	while i != 6: #Simulate 6 races
		scores = QN1_1560749.performance(m1) #1d
		sorted_scores = QN1_1560749.position(scores) #1e
		for name in sorted_scores:
			results[name].append(sorted_scores.index(name) + 1) #Assigns positions to the names in sorted_scores
		i += 1

	m2 = copy.deepcopy(results)
	final_m2 = generate_method2_data(m2) #Data for Olympic method

	final_m1 = []
	for key,value in results.items():
		final_score = QN1_1560749.solo_results((key,value))
		final_m1.append(final_score)
	return final_m1 , final_m2

#Generates sample data for method 2(Olympic Method).
	
def generate_method2_data(data):
	m2_data = []
	for key,value in data.items():
		temp = []
		total = 0
		for pos in value:
			score = method2_score(100, pos)
			temp.append(score)
		temp.remove(min(temp))
		total = sum(temp)
		m2_data.append(total)
	return m2_data

m1_data, m2_data = generate_method1_data() #Generates Position and Olympic method data respectively.

fig = plt.figure() #Both graphs, one figure
fig2 = plt.figure() #Position graph only
fig3 = plt.figure() #Olympic graph only
ax = fig.add_subplot(111)
ax2 = fig2.add_subplot(111)
ax3 = fig3.add_subplot(111)
plt.xlabel('Skill level/Mean (%)')
plt.ylabel('Scores')

position = ax.plot(m1_data, label = 'Position Method')
olympic = ax.plot(m2_data, label = 'Olympic Method')
position_only = ax2.plot(m1_data, label = 'Position Method')
olympic_only = ax3.plot(m2_data, label = 'Olympic Method')
plt.legend(loc = 'upper left')
plt.show()



