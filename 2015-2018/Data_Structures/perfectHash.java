//Student No: C1560749

import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class perfectHash{
	public static void main(String[] args) throws FileNotFoundException{
		setupHashTable sht = new setupHashTable();
		hashOperations ho = new hashOperations();

		//Reading File data======================================================================

		//Check if correct number of arguments passed
		if(args.length != 2){
			System.out.println("You must enter 2 arguments.");
			System.out.println("1) The filename of the .txt that contains the keys.");
			System.out.println("2) The filename of the .txt that contains the operations.");
			System.exit(0);
		}

		//Reading files
		ReadFile rf = new ReadFile();
		int[] data = rf.txt2IntArray(args[0]);	//Keys
		String[] ops = rf.txt2StringArray(args[1]);	//Commands

		//======================================================================================

		//Setup Hash Tables=====================================================================

		//Initialise Outer table:
		int[][] outer = sht.makeOuterTable(data);

		//Make subarray with no first element, i.e: first elem is num of keys.
		//called listOfKeys in hashOperations.java
		int[][] outer_1stElem_removed = new int[outer.length][]; 
		for (int i = 0; i < outer.length; i++){
			outer_1stElem_removed[i] = Arrays.copyOfRange(outer[i], 1, outer.length);
		}

		//Print out results
		System.out.println("\nKEYS GROUPED ONTO SLOTS:");

		int i = 0;
		for (int[] arr: outer_1stElem_removed){
			System.out.println("grouping slot " + i + ": " + Arrays.toString(arr).replace(",", "").replace("[", "")
								.replace("]", "").replace("-1", "").trim());
			i += 1;
		}

		int[][] inner_table = sht.makeInnerTable(outer);

		//List of values of a,b & p for outer & inner slots
		ArrayList<int[]> hash_vals = sht.getHashVals(); 
		int[][] hashfn_vals = hash_vals.toArray(new int[hash_vals.size()][]);

		for(int[] arr : hashfn_vals){
			if(arr[0] != 0){
				System.out.println();
				System.out.println("slot " + (arr[0] - 1) + 
					"; MODIFIED INNER LEVEL HASH FUNCTION PARAMETERS: a = " 
					+ arr[1] + "; b = " + arr[2] + "; p = " + arr[3]);
			}
		}

		//Fully set-up hashtable
		int[][] hashtable = new int[inner_table.length][];
		for (i = 0; i < inner_table.length; i++){
			if(inner_table[i] != null){
				hashtable[i] = new int[inner_table[i].length];
				Arrays.fill(hashtable[i], -1);
			}
		}

		//Split operations into operation , operand
		String[][] ops_list = new String[ops.length][];
		i = 0;
		for(String op : ops){
			String[] split = op.split(" ");
			ops_list[i] = split;
			i += 1;
		}

		//Start operations, read operations file
		for(String[] arr: ops_list){
			System.out.println();
			//If insert...
			if (arr[0].equals("insert")){
				//Do insert command
				System.out.println("PERFORM OPERATION: insert " + arr[1]);
				hashtable = ho.insert(hashtable, Integer.parseInt(arr[1]), hashfn_vals, outer_1stElem_removed);
			}
			//If delete...
			else if (arr[0].equals("delete")){
				//Do delete command
				System.out.println("PERFORM OPERATION: delete " + arr[1]);
				hashtable = ho.delete(hashtable, Integer.parseInt(arr[1]), hashfn_vals, outer_1stElem_removed);
			}
			//If locate...
			else if (arr[0].equals("locate")){
				//Do locate command
				System.out.println("PERFORM OPERATION: locate " + arr[1]);
				ho.locate(hashtable, Integer.parseInt(arr[1]), hashfn_vals, outer_1stElem_removed);
			}
			//If other...
			else{
				//Error: Invalid operation
				System.out.println("ERROR: invalid operation");
			}
		}
		
		//======================================================================================
	}
}