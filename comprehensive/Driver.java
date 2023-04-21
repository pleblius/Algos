package comprehensive;

import java.io.BufferedReader;
import java.io.File;
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
		
		DisjointSet<String> discreteSet = new DiscreteMap<String>();
		
		try(FileReader fileReader = new FileReader(args[0]);
            BufferedReader reader = new BufferedReader(fileReader)) {
			
			String s = reader.readLine();
			ArrayList<String> lines = new ArrayList<String>();
			
			// Extract elements
			while(!s.isEmpty()) {
				lines.add(s);
				s = reader.readLine();
			}
			
			makeSets(discreteSet, lines);
			
			// Extract Unions
			s = reader.readLine();
			lines = new ArrayList<String>();
			while(!s.isEmpty()) {
				lines.add(s);
				s = reader.readLine();
			}
			
			unionize(discreteSet, lines);
			
			// Check if connected
			lines = new ArrayList<String>();
			while((s = reader.readLine()) != null) {
				lines.add(s);
			}
			
			areConnected(discreteSet, lines);
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Creates a set from each element in the list of strings provided.
	 * @param discreteSet - the discreteSet to which sets are added
	 * @param elements - List of strings containing the elements to be added, one per line
	 */
	private static void makeSets(DisjointSet<String> discreteSet, List<String> elements) {
		for (String element : elements) {
			discreteSet.makeSet(element);
		}
	}
	
	/**
	 * Unions each element contained in each line of lines
	 * @param discreteSet - the discreteSet containing the sets of elements
	 * @param lines - String array containing instructions for unions, containing two space separated elements
	 */
	private static void unionize(DisjointSet<String> discreteSet, ArrayList<String> lines) {
		
		String[] instructions;
		for (String line : lines) {
			instructions = line.split(" ");
			try {
				discreteSet.union(instructions[0], instructions[1]);
			}
			catch(NoSuchElementException e) {
				System.err.println("No such element exception at elements " + line);
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.err.println("Array out of bounds at " + line);
			}
			catch(NullPointerException e) {
				System.err.println("Null pointer exception at " + line);
			}
			catch(StackOverflowError e) {
				System.err.println("Stack overflow at " + line);
			}
		}
	}
	
	/**
	 * Checks if two specified elements are connected and prints the result to the console.
	 * @param discreteSet - the DiscreteSet containing the sets to be checked
	 * @param lines - String array containing strings of space separated elements to be checked for connection
	 */
	private static void areConnected(DisjointSet<String> discreteSet, ArrayList<String> lines) {

		String[] elements;
		String rep1, rep2;
		
		for (String line : lines) {
			elements = line.split(" ");
			
			try {
				rep1 = discreteSet.getRepresentative(elements[0]);
				rep2 = discreteSet.getRepresentative(elements[1]);
				
				if (rep1 == rep2)
					System.out.println("connected");
				else
					System.out.println("not connected");
			}
			catch(NoSuchElementException e) {
				System.err.println("No such element exception at elements " + line);
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.err.println("Array out of bounds at " + line);
			}
			catch(NullPointerException e) {
				System.err.println("Null pointer exception at " + line);
			}
			catch(StackOverflowError e) {
				System.err.println("Stack overflow at " + line);
			}
		}	
	}
}
