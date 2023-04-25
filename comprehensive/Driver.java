package comprehensive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Driver class testing our DisjointSet implementation from a provided file input.
 * @version April 20th, 2023
 * @authors Andrew Tolton && Tyler Wilcox
 */
public class Driver {
	
	/**
	 * Main method of driver, creating a discrete set from the elements listed in the file
	 * found at args[0], unioning them in accordance to the file, and printing out the 
	 * connection between the specified nodes.
	 * @param args : String array - args[0] must be the filename of the instructions file.
	 */
	public static void main(String[] args) {
		if (args.length == 0)
			throw new IllegalArgumentException("Needs a filename");
		
		DisjointSet<String> discreteSet = new DisjointForest<String>();
		
		Scanner reader;
		try {
			reader = new Scanner(new File(args[0]));

			String s;
			String[] nodes = new String[2];
			boolean connected;
			
			while(true) {
				s = reader.nextLine();
				if(s.isEmpty())
					break;
				
				discreteSet.makeSet(s);
			}
				
			while(true) {
				s = reader.nextLine();
				if(s.isEmpty())
					break;
				
				nodes = s.split(" ");
				discreteSet.union(nodes[0], nodes[1]);
			}
			
			while(reader.hasNext()) {
				s = reader.nextLine();
				if(s.isEmpty() || s == null)
					break;
				
				nodes = s.split(" ");
				connected = discreteSet.getRepresentative(nodes[0]).equals(discreteSet.getRepresentative(nodes[1]));
				
				if (connected)
					System.out.println("connected");
				else
					System.out.println("not connected");
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
