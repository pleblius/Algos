package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
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
			
			// Get Sets
			s = reader.nextLine();
			while(!s.isEmpty()) {
				discreteSet.makeSet(s);
				
				s = reader.nextLine();
			}
			
			// Get unions
			s = reader.nextLine();
			while(!s.isEmpty()) {
				nodes = s.split(" ");
				discreteSet.union(nodes[0], nodes[1]);
				
				s = reader.nextLine();
			}
			
			// Get queries
			while(reader.hasNext()) {
				s = reader.nextLine();
				
				nodes = s.split(" ");
				connected = discreteSet.getRepresentative(nodes[0]) == discreteSet.getRepresentative(nodes[1]);
				
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
