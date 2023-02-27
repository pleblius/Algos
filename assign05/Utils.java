package assign05;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Utils {
	
	public static void writeToFile(int[] lengths, long[] times, String filename) {
		try (FileWriter writer = new FileWriter((filename)))
		{
			PrintWriter pw = new PrintWriter(writer);
			pw.flush();
			pw.println("Number of Loops = " + lengths.length);
			pw.println();
			pw.println("Lengths \t Times(ns)");
			for (int i = 0; i < times.length; i++) {
				pw.print(lengths[i]);
				pw.println(" , " + times[i]);
			}
			
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void copyArrayValues(ArrayList<Integer> dest, ArrayList<Integer> src) {
		for (int i = 0; i < src.size(); i++) {
			dest.add(src.get(i));
		}
	}
	
	public static void copyEntireArray(List<ArrayList<Integer>> dest, List<ArrayList<Integer>> src) {
		for (int i = 0; i < src.size(); i++) {
			for (int j = 0; j < src.get(i).size(); j++) {
				dest.get(i).add(src.get(i).get(j));
			}
		}
	}
	
	public static ArrayList<Integer> generateCopy(ArrayList<Integer> src) {
		var temp = new ArrayList<Integer>();
		
		copyArrayValues(temp, src);
		
		return temp;
	}
	
	public static ArrayList<ArrayList<Integer>> arrayArrayCopy(ArrayList<ArrayList<Integer>> src) {
		ArrayList<ArrayList<Integer>> copy = new ArrayList<ArrayList<Integer>>(src.size());
		ArrayList<Integer> currentSrcList;
		ArrayList<Integer> currentCopyList;
		
		for (ArrayList<Integer> srcList : src) {
			currentCopyList = new ArrayList<Integer>(srcList.size());
			
			for (Integer element : srcList) {
				currentCopyList.add(element);
			}
			copy.add(currentCopyList);			
		}
		
		return copy;
	}
}
