package comprehensive;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		if (args.length == 0)
			throw new IllegalArgumentException("Needs a filename");
		
		DisjointSet<String> discreteSet = new DiscreteMap<String>();
		
		try(Scanner scanner = new Scanner(new File(args[0]))) {
			
			String s = scanner.nextLine();
			ArrayList<String> lines = new ArrayList<String>();
			
			// Extract elements
			while(!s.isEmpty()) {
				lines.add(s);
				s = scanner.nextLine();
			}
			
			makeSets(discreteSet, lines);
			
			// Extract Unions
			s = scanner.nextLine();
			lines = new ArrayList<String>();
			while(!s.isEmpty()) {
				lines.add(s);
				s = scanner.nextLine();
			}
			
			unionize(discreteSet, lines);
			
			// Check if connected
			lines = new ArrayList<String>();
			while(scanner.hasNext()) {
				s = scanner.nextLine();
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
			discreteSet.union(instructions[0], instructions[1]);
		}
		
	}
	
	private static void areConnected(DisjointSet<String> discreteSet, ArrayList<String> lines) {

		String[] elements;
		String rep1, rep2;
		
		for (String line : lines) {
			elements = line.split(" ");
			rep1 = discreteSet.getRepresentative(elements[0]);
			rep2 = discreteSet.getRepresentative(elements[1]);
			if (rep1 == rep2)
				System.out.println("connected");
			else
				System.out.println("not connected");
		}	
	}
}
