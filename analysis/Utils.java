package analysis;

import java.awt.Toolkit;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple utility class with methods that are useful during code analysis
 * for CS2420 assignments.
 * 
 * @author Tyler Wilcox
 * @version 18 February 2023
 *
 */
public class Utils {
	
	/**
	 * Writes timing analysis data in the form of two arrays to a file with a specified filename.
	 * If the file already exists, it will overwrite the data in that file; otherwise,
	 * it will create a new file with the given filename.
	 * The lengths array must be of type int[], and the times array must be of type long[].
	 * </br></br>
	 * Data is written as comma and space separated vertical columns, with the headers
	 * "Lengths" and "Times(ns)"
	 * </br></br>
	 * Will throw an IllegalArgumentException if the data arrays are not the same size.
	 * Will throw an IOException if the file cannot be accessed or created.
	 * </br></br>
	 * @throws IllegalArgumentException If arrays are different lengths
	 * @throws IOException If file cannot be created
	 * @param lengths - int[] array of corresponding data lengths
	 * @param times - long[] array of corresponding data times
	 * @param filename - String of file to be written to or created
	 */
	public static void writeToFile(int[] lengths, long[] times, String filename) {
		if (lengths.length != times.length) {
			throw new IllegalArgumentException("Data arrays must be the same size.");
		}
		
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
	
	/**
	 * Utility method to copy the data from one ArrayList<Integer> to another.
	 * Should be redundant for use outside of this class.
	 * </br>
	 * Requires that destination array list is empty.
	 * </br>
	 * @param dest Destination ArrayList<Integer> to be written to
	 * @param src Source ArrayList<Integer> to be copied
	 */
	public static void copyArrayValues(ArrayList<Integer> dest, ArrayList<Integer> src) {
		for (int i = 0; i < src.size(); i++) {
			dest.add(i, src.get(i));
		}
	}
	
	/**
	 * Copies the primitive integer data from one list of array lists into another
	 * list of array lists.
	 * </br></br>
	 * Intended to avoid reference conflicts when attempting to copy and modify an array dataset
	 * without overriding the original data.
	 * </br></br>
	 * Requires that destination list is full of empty array lists.
	 * </br></br>
	 * @param dest Destination List<> to be written to
	 * @param src Source List<> to be copied
	 */
	public static void copyEntireArray(List<ArrayList<Integer>> dest, List<ArrayList<Integer>> src) {
		for (int i = 0; i < src.size(); i++) {
			for (int j = 0; j < src.get(i).size(); j++) {
				dest.get(i).add(j, src.get(i).get(j));
			}
		}
	}
	
	/**
	 * Generates and returns a value-based copy of the source array list.
	 * </br></br>
	 * Copies the integer value from each index in the source arraylist into the
	 * corresponding index of the generated arraylist, ensuring an exact copy of
	 * the list without copying the variable pointers for the list itself or its data,
	 * preventing modifications to the new list from affecting the data contained in the old
	 * list.
	 * 
	 * @param src Source ArrayList<Integer> to be copied from.
	 * @return a new unlinked ArrayList<Integer>
	 */
	public static ArrayList<Integer> generateCopy(ArrayList<Integer> src) {
		var temp = new ArrayList<Integer>();
		
		copyArrayValues(temp, src);
		
		return temp;
	}
	
	/**
	 * Generates a simple beep noise based on the user's system hardware
	 * and default OS settings.
	 */
	public static void beep() {
		Toolkit.getDefaultToolkit().beep();
	}
	
	/**
	 * This method converts the collected run times for method timing into a usable
	 * time, in nanoseconds.</br>
	 * Uses the following time benchmarks: </br>
	 * </br>
	 * Start: The start-time for the timing operation. Corresponds to the time when
	 * total experimentation begins.</br>
	 * Mid: The mid-time for the timing operation. Corresponds to the time when
	 * the desired loop has been finished and the clean-up loop is yet to be
	 * executed.</br>
	 * End: The end-time for the timing operation. Corresponds to the time when
	 * the clean-up loop, and thus all timing, has been finished.
	 * </br></br>
	 * It averages the difference between the two loop execution times over the
	 * number of iterations of each loop and returns this time, in nanoseconds.
	 * 
	 * @param start - The start time of the operation.
	 * @param mid - The time between execution of both timing loops.
	 * @param end - The end time of the operation.
	 * @param loops - The number of times both loops are executed.
	 * @return long - The time in nanoseconds of the average loop execution.
	 */
	public static long getTime(long start, long mid, long end, long loops) {
		long time = ((mid - start) - (end - mid))/loops;
		return time;
	}
}
