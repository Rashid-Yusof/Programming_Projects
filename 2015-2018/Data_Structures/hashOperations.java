//Student No: C1560749

import java.util.Arrays;
import java.lang.ArrayIndexOutOfBoundsException;

public class hashOperations{
	private hasher h = new hasher();
	//Variable descriptions:
	//hashtable = New empty table to be returned after operations
	//hashfn_vals = Array containing values of a, b & p for outer & inner slots.
	//listOfKeys = Array containing keys and their respective index positions.

	public int[][] insert(int[][] hashtable, int key, int[][] hashfn_vals, int[][] listOfKeys){
		int ii;
		//Find outer index
		try{
			int oi = (getOuterIndex(hashtable, key, hashfn_vals));
			//Find inner index
			if(listOfKeys[oi] != null){
				ii = getInnerIndex(hashtable, key, hashfn_vals, oi);

				//hashtable[outer][inner] = key
				if (contains(listOfKeys[oi], key)){
					System.out.println("HASH KEY TO OUTER SLOT: " + oi + ", INNER SLOT: " + ii);
					hashtable[oi][ii] = key;

					int i = 0;
					for(int[] arr : hashtable){
						if(arr != null){
							System.out.println("operation slot " + i + ": " + 
								Arrays.toString(arr).replace(",", "").replace("[", "")
								.replace("]", "").trim());
						}
						else{
							System.out.println("operation slot " + i + ": ");
						}
					i += 1;
					}
				}
				else{
					System.out.println("ERROR: cannot insert key = " + key + "; not in hash table");
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			int oi = (h.hashFn(13, 1207, 177, hashtable.length, key)) - 1;
			ii = 0;
			hashtable[oi][ii] = key;

			if(listOfKeys[oi] != null){
				//hashtable[outer][inner] = key
				if (contains(listOfKeys[oi], key)){
					System.out.println("HASH KEY TO OUTER SLOT: " + oi + ", INNER SLOT: " + ii);
					hashtable[oi][ii] = key;

					int i = 0;
					for(int[] arr : hashtable){
						if(arr != null){
							System.out.println("operation slot " + i + ": " + 
								Arrays.toString(arr).replace(",", "").replace("[", "")
								.replace("]", "").trim());
						}
						else{
							System.out.println("operation slot " + i + ": ");
						}
					i += 1;
					}
				}
				else{
					System.out.println("ERROR: cannot insert key = " + key + "; not in hash table");
				}
			}
		}
		return hashtable;
	}

	public int[][] delete(int[][] hashtable, int key, int[][] hashfn_vals, int[][] listOfKeys){
		int ii;
		//Find outer index

		try{
			int oi = (getOuterIndex(hashtable, key, hashfn_vals));

			//Find inner index
			if(listOfKeys[oi] != null){
				ii = getInnerIndex(hashtable, key, hashfn_vals, oi);

				//hashtable[outer][inner] = -1
				if (contains(listOfKeys[oi], key)){
					System.out.println("LOCATED KEY = " + key + " at: " + oi + ", " + ii);
					hashtable[oi][ii] = -1;

					int i = 0;
					for(int[] arr : hashtable){
						if(arr != null){
							System.out.println("operation slot " + i + ": " + 
								Arrays.toString(arr).replace(",", "").replace("[", "")
									.replace("]", "").trim());
						}
						else{
							System.out.println("operation slot " + i + ": ");
						}
						i += 1;
					}
				}
				else{
					System.out.println("ERROR: cannot delete key = " + key + "; not in hash table");
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			int oi = (h.hashFn(13, 1207, 177, hashtable.length, key)) - 1;
			ii = 0;
			hashtable[oi][ii] = key;

			if(listOfKeys[oi] != null){

				//hashtable[outer][inner] = -1
				if (contains(listOfKeys[oi], key)){
					System.out.println("LOCATED KEY = " + key + " at: " + oi + ", " + ii);
					hashtable[oi][ii] = -1;

					int i = 0;
					for(int[] arr : hashtable){
						if(arr != null){
							System.out.println("operation slot " + i + ": " + 
								Arrays.toString(arr).replace(",", "").replace("[", "")
									.replace("]", "").trim());
						}
						else{
							System.out.println("operation slot " + i + ": ");
						}
						i += 1;
					}
				}
				else{
					System.out.println("ERROR: cannot delete key = " + key + "; not in hash table");
				}
			}
		}

		return hashtable;
	}

	public void locate(int[][] hashtable, int key, int[][] hashfn_vals, int[][] listOfKeys){
		int ii;
		//Find outer index
		try{
			int oi = (getOuterIndex(hashtable, key, hashfn_vals));

			//Find inner index
			if(listOfKeys[oi] != null){
				ii = getInnerIndex(hashtable, key, hashfn_vals, oi);
				//print inner & outer
				if (contains(listOfKeys[oi], key)){
					System.out.println("LOCATED KEY = " + key + " at: " + oi + ", " + ii);
				}
				else{
					System.out.println("ERROR: cannot locate key = " + key + "; not in hash table");
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			int oi = (h.hashFn(13, 1207, 177, hashtable.length, key)) - 1;
			ii = 0;
			hashtable[oi][ii] = key;

			if(listOfKeys[oi] != null){
				ii = getInnerIndex(hashtable, key, hashfn_vals, oi);
				//print inner & outer
				if (contains(listOfKeys[oi], key)){
					System.out.println("LOCATED KEY = " + key + " at: " + oi + ", " + ii);
				}
				else{
					System.out.println("ERROR: cannot locate key = " + key + "; not in hash table");
				}
			}		
		}
	}

	public int getOuterIndex(int[][] hashtable, int key, int[][] hashfn_vals){
		int oi = h.hashFn(hashfn_vals[0][1], hashfn_vals[0][2], hashfn_vals[0][3], 
									hashtable.length, key);
		return oi;
	}

	public int getInnerIndex(int[][] hashtable, int key, int[][] hashfn_vals, int oi){	
		oi = oi - 1;
		int ii = h.hashFn(hashfn_vals[oi][1], hashfn_vals[oi][2], hashfn_vals[oi][3], 
				hashtable[oi + 1].length, key);
		return ii;
	}

	public boolean contains(int[] listOfKeys_arr, int key){
		boolean found = false;
		for(int elem : listOfKeys_arr){
			if (elem == key){
				found = true;
				break;
			}
		}
		return found;
	}
}