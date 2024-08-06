//Student No: C1560749

import java.util.Arrays;
import java.util.ArrayList;
import java.lang.Math;

public class setupHashTable {
	public int num_of_hashes = 1;
	public int printed = 0;
	public ArrayList<int[]> hash_vals = new ArrayList<int[]>();
	public boolean val_stored = false;

	//Indexing Fn, becomes recursive if too many collisions occur in outer table.
	public int[][] indexing(int a, int b, int p, int[] keys, int[][] outer){
		//Initializing
		hasher h = new hasher();
		int collisions = 0;
		int[] index_order = new int[keys[0]];

		//Keep an unedited array. Makes a copy of original array.
		int[][] outerCopy = new int[outer.length][];
		for(int l = 0; l < outer.length; l++){
			outerCopy[l] = outer[l].clone();
		}

		//Indexing Process//=================================================================
		//Applying hashFn to each key
		int i = 0;
		for (int key : keys) {

			if (key == keys[0]){
				continue; //skip the first element as 1st elem is num of keys
			}

			//hashFn
			int index = h.hashFn(a,b,p,keys[0],key);

			//if key belongs to empty index...
			if (outer[index][0] == -1){
				outer[index][0] = 0;	//Set up for initial increment
			}

			//If there are already keys in this position...
			if (outer[index][0] >= 1){
				collisions += outer[index][0];	//Add to num of total collisions
			}

			//increment
			outer[index][0] += 1;
			index_order[i] = index;
			int key_pos = outer[index][0]; //Determines the next key's position
			if (key_pos < outer.length){
				outer[index][key_pos] = key;
			}

			i += 1;

		}

		if (num_of_hashes == 1){
			System.out.println("HASHED TO OUTER HASH TABLE AT: " + Arrays.toString(index_order).replace(",", "").replace("[", "").replace("]", "").trim() + "\n");
		}
		//===================================================================================

		//Collisions too many, change hashFn//===============================================

		//If num of collision pairs > length of table...
		if (collisions > keys[0]){ 
			System.out.println("NUMBER OF PAIRS OF COLLISIONS IN OUTER HASH TABLE: " + collisions);
			//increment vals to change hashFn
			a += 1;
			b += 177;

			//Check if values need to be looped back
			if (a > (p-1)){
				a = a % p;
				if (a == 0){
					a = 1;		//Restart from 1
				}
			}

			if (b > (p-1)){
				b = b % p;		//Restart from 0
			}

			//Restart indexing, call self with updated vals.
			//Return outer to first iteration
			for(int l = 0; l < outerCopy.length; l++){
				outer[l] = outerCopy[l].clone();
			}

			num_of_hashes += 1;

			outer = indexing(a,b,p,keys,outer);
		}

		//====================================================================================
		if(printed == 0){
			printed += 1;
			System.out.println("NUMBER OF PAIRS OF COLLISIONS IN OUTER HASH TABLE: " + collisions);
			if(collisions > 0){
				System.out.println(num_of_hashes + " OUTER HASH FUNCTIONS TESTED");
			}
			else{
				System.out.println(num_of_hashes + " OUTER HASH FUNCTION TESTED");
			}
			if ( (a != 13) || (b != 1207)){
				System.out.println("MODIFIED OUTER HASH FUNCTION PARAMETERS: a = " +
					a + "; b = " + b + "; p = " + p);

				hash_vals = updateHashVals(a, b, p, 0, hash_vals); //Update hashfn values

				System.out.println("MODIFIED HASHING TO OUTER HASH TABLE AT: " + Arrays.toString(index_order).replace(",", "").replace("[", "").replace("]", "").trim());
			}
		}
		return outer;
	}

	//Central class method
	public int[][] makeOuterTable(int[] keys){
		//Initializing Values
		int a = 13;
		int b = 1207;
		int p = 40487;

		//Make subarray with no first element(num of keys)
		int[] keys_1stElem_removed = new int[keys.length];
		keys_1stElem_removed = Arrays.copyOfRange(keys, 1, keys.length);

		//Initializing array
		System.out.println("SETTING HASH TABLE SIZE: " + keys[0]);
		System.out.println("READ SET OF KEYS: " + Arrays.toString(keys_1stElem_removed).replace(",", "").replace("[", "").replace("]", "").trim());
		System.out.println("INITIAL OUTER HASH FUNCTION PARAMETERS: a = 13; b = 1207; p = 40487");
		int[][] outer_val = new int[keys[0]][keys[0]];	//Holds total keys and vals,
														//but not the inner tables.

		for (int[] arr: outer_val){	//For each array in outer_val...
			Arrays.fill(arr, -1);
		}
								
		//Index for outer table
		int[][] indexed_Outer = indexing(a,b,p,keys,outer_val);

		return indexed_Outer;
	}

	//Indexing for inner table
	public int[][] inner_indexing(int a, int b, int p, int[][] inner, int[][] indexKeys){
		//Indexing Process//=================================================================
		//Applying hashFn to each key
		int i = 0;

		for (int[] arr : indexKeys){
			//Reset vals for new array
			//Checking if current index contains keys
			if(inner[i] != null){
				inner = arr_indexing(a, b, p, inner[i].length, i , inner, indexKeys, arr);
			}

			i += 1; // Move on to next array if empty
			val_stored = false;
		}
		return inner;
	}
	
	public int[][] makeInnerTable(int[][] outer){
		//Initializing Values
		int a = 13;
		int b = 1207;
		int p = 40487;

		int[][] inner_table = new int[outer.length][];

		//Fill inner_table with empty arrays size n^2
		int i = 0;
		for(int[] arr: outer){
			if (arr[0] != -1){
				inner_table[i] = new int[(int)(Math.pow(arr[0] , 2))];
				Arrays.fill(inner_table[i], -1);
			}
			i += 1;
		}

		//Copy outer keys, but ignore num of keys in each index
		int[][] indexKeys = new int[outer.length][]; //Array containing keys in proper index position
		
		i = 0;
		for(int[] arr : outer){
			indexKeys[i] = Arrays.copyOfRange(arr, 1, arr.length);
			i += 1;
		}
		
		int[][] indexed_Inner = inner_indexing(a,b,p,inner_table,indexKeys);

		//Put indexed array in finalised 2d inner array
		int[][] hash_arr = hash_vals.toArray(new int[hash_vals.size()][]);

		return indexed_Inner;
	}

	public int[][] arr_indexing(int a, int b, int p, int m, int i ,int[][] inner, int[][] indexKeys, int[] arr){
	//Initializing
	hasher h = new hasher();
	int collisions = 0;
	
	//Keep an unedited array. Makes a copy of original array.
	int[][] innerCopy = new int[inner.length][];
	for(int l = 0; l < inner.length; l++){
		if(inner[l] != null){
			innerCopy[l] = inner[l].clone();
		}
	}

	//Indexing Process//=================================================================
	//Applying hashFn to each key
	int[] arrCopy = arr.clone();
	outerloop:
	for(int key : arr){
		int index = h.hashFn(a,b,p,m,key);
		if(indexKeys[i][1] == -1){ //No need to hash if only one key
			inner[i][0] = key;
			break outerloop;
		}
		if(key == -1){	//If no more keys to hash
			break outerloop;
		}
		else if(inner[i][index] == -1){	//If empty spot
			inner[i][index] = key;
		}
		else{
			//collision detected
			//Make copy
			inner[i] = innerCopy[i].clone();
			arr = arrCopy.clone();
			
			//increment vals to change hashFn============================================
			a += 1;
			b += 177;

			//Check if values need to be looped back
			if (a > (p-1)){
				a = a % p;
				if (a == 0){
					a = 1;		//Restart from 1
				}
			}

			if (b > (p-1)){
				b = b % p;		//Restart from 0
			}
			//==========================================================================

			//System.out.println("A: " + a + " B: " + b);
			inner = arr_indexing(a, b, p, m, i, inner, indexKeys, arr);

			if (val_stored == false){ //Checks if final hash values has been stored
				hash_vals = updateHashVals(a, b, p, i+1, hash_vals); //Update hashfn values
									//Stored as i+1 as index 0 contains outer hash vals
				val_stored = true;
			}
			break;
		}
	}
	//===================================================================================
	return inner;
	}

	public ArrayList<int[]> updateHashVals(int a, int b, int p, int i, ArrayList<int[]> vals){
		int[] new_vals = {i, a, b, p};
		vals.add(new_vals);
		return vals;
	}

	public ArrayList<int[]> getHashVals(){
		return hash_vals;
	}
}