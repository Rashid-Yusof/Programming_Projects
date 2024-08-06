//Student No: C1560749

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ReadFile{

	//Read ints
	public int[] txt2IntArray(String filename) throws FileNotFoundException {
		Scanner scr = new Scanner(new File(filename));
		int length = scr.nextInt();
		int [] data = new int [length + 1];
		data[0] = length;
		int i = 1;
		while(scr.hasNextInt())
		{	
     		data[i] = scr.nextInt();
     		i++;
		}
		scr.close();
		return data;
	}

	//Read Strings
	public String[] txt2StringArray(String filename) throws FileNotFoundException {
		Scanner scr = new Scanner(new File(filename));
		int lines = 0;
		while(scr.hasNextLine())
		{	
     		lines++;
     		scr.nextLine();
		}

		String[] ops = new String[lines];
		Scanner scr2 = new Scanner(new File(filename)); //Scanner must be redefined to 'reset'
		int i = 0;

		while(scr2.hasNextLine()){
			ops[i] = scr2.nextLine();
			i++;
		}
		scr.close();
		scr2.close();
		return ops;
	}
}
