package comprehensive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Driver {

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

	private static void makeSets(DisjointSet<String> discreteSet, List<String> elements) {
		for (String element : elements) {
			discreteSet.makeSet(element);
		}
	}
	
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
