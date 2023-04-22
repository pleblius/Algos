package comprehensive;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LastTest {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < 1_000_000; i++) {
			list.add(i);
		}
		
		DisjointSet<Integer> set = new DisjointForest<>();
		
//		for (Integer i : list) {
//			set.makeSet(i);
//		}
//		
//		for (int i = 0; i < set.size() - 3; i++) {
//			set.union(i, i + 3);
//		}
//		
//		for (int i = 0; i < set.size() - 3; i++) {
//			System.out.println(set.getRepresentative(0) == set.getRepresentative(i + 3));
//		}
//		
//		Collections.shuffle(list);
//		
//		 try (BufferedWriter writer = new BufferedWriter(new FileWriter("lasttest.txt"))) {
//			 for (Integer i : list) {
//				 writer.write(i + "\n");
//			 }
//			 
//			 writer.write("\n");
//			 Collections.shuffle(list);
//			 
//			 for (Integer i : list) {
//				 if (i < set.size() - 3) {
//					 writer.write(i + " " + (i + 3) + "\n");
//				 }
//				 else
//					 writer.write(i + " " + (i - 3) + "\n");
//			 }
//			 
//			 Collections.sort(list);
//			 
//			 for (int i = 0; i < set.size(); i++) {
//				 writer.write("\n" + "0 " + i);
//			 }
//		 }
//		 
//		 catch(IOException e) {
//			 System.err.println(e.getMessage());
//		 }
		
		int i = 0;
		
		try(Scanner scanner = new Scanner(new File("drivertest.txt"))) {
			String s;
			
			while (scanner.hasNext()) {
				s = scanner.nextLine();
				
				if (i%3 == 0) {
					if (!s.equals("connected")) {
						System.out.println("Error at i = " + i);
					}
				}
				else if (!s.equals("not connected"))
					System.out.println("Error at i = " + i);
				i++;
			}
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
		System.out.println(i);
	}

}
