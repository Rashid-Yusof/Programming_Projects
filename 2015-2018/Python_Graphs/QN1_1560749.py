import csv
import copy
import collections
import random

#QN 1 PYTHON CODE

#ex = ("bob", [2, 4, 1, 1, 2, 5])

#1a
#Calculates and sums an individual sailor's score

def solo_results(sailor):
	worst = 0
	copied = copy.deepcopy(sailor)
	for item in copied[1]:
		try:
			if float(item) > worst:
				worst = item
		except ValueError:
			print(item)
	copied[1].remove(worst)
	total_score = sum(copied[1])
	return total_score

#print (solo_results(ex))

#every = [("Alice", [1, 2, 1, 1, 1, 1]), ("Bob", [3, 1, 5, 3, 2, 5]), 
#("Clare", [2, 3, 2, 2, 4, 2]), ("Dennis", [5, 4, 4, 4, 3, 4]), 
#("Eva", [4, 5, 3, 5, 5, 3])]

#1b

#Sorts sailors based on their summed scores.

def sort_sailors(all_sailors):
	copy = sorted(all_sailors, key=lambda sailor : solo_results(sailor))
	return copy

#print(sort_sailors(every))

#1c

#Makes an ordered dict with mu and sigma values based on the csv file given.

def sailors_dict(csv_file):
	res = collections.OrderedDict()
	with open(csv_file) as csvfile:
		rdr = csv.reader(csvfile)
		csvfile.readline()
		for row in rdr:
			values = float(row[1]) , float(row[2])
			res[row[0]] = values
		return res

#print(sailors_dict('Boats.csv'))

#1d
#Calculates scores in a race based on mu and sigma and puts them in an ordered dict.

def performance(sailor_dict):
	perf = collections.OrderedDict()
	for key in sailor_dict:
		a=sailor_dict[key][0]
		b=sailor_dict[key][1]
		perf[key] = random.normalvariate(a,b)
	return perf

#ex2 = (performance(sailors_dict('Boats.csv')))
#print(ex2)

#1e
#Returns a list of the sailors' positions based on the scores calculated in 1d.

def position(perf):
	perf_list = []
	for key,value in perf.items():
		perf_list.append([key,value])
	sort_perf = sorted(perf_list, key = lambda x : -x[1])
	sort_pos_perf = []
	for i in sort_perf:
		sort_pos_perf.append(i[0])
	return (sort_pos_perf)

#print(position(ex2))
#print(results)

#1f

#Generates an empty ordered dict based on keys

def make_dict(csv_file):
	results = collections.OrderedDict()
	with open(csv_file) as csvfile:
		rdr = csv.reader(csvfile)
		csvfile.readline()
		for row in rdr:
			results[row[0]] = []
	return results

#Runs 6 races and makes csv into proper ordered dict

def calc_all(your_csv):
	i = 0
	results = make_dict(your_csv)
	while i != 6:
		scores = performance(sailors_dict(your_csv)) #1c and 1d
		sorted_scores = position(scores) #1e
		for name in sorted_scores:
			results[name].append(sorted_scores.index(name) + 1) #Assigns positions to the names in sorted_scores
		i += 1
	return results

#Arrange dict from calc_all according to series score

def series_sort(results):
	results_list = []
	for key,value in results.items():
		results_list.append((key,value))
	sorted_results = (sort_sailors(list(results_list))) #1b
	return sorted_results

#Outputs names from series_sort winners in order.
def output_positions(sorted_results):
	final_positions = []
	for item in sorted_results:
		final_positions.append(item[0])
	return final_positions

#Run the two print lines below to simulate 6 races and get the output for 1f.

#print(series_sort(calc_all('Boats.csv')))
#print(output_positions(series_sort(calc_all('Boats.csv'))))