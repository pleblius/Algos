package assign03;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A class to test and analyze behavior of the SimplePriorityQueue class.
 * 
 * @author Tyler Wilcox
 * @version 28 January 2023
 */
public class SimpleQueueTester {

	public static void main(String[] args) {
		int length, loopTimes;
		int[] randomInts;
		long[] findMaxTimes = new long[20];
		long[] insertTimes = new long[20];
		
		long startTime, midTime, endTime;
		
		loopTimes = 20000;
		
		// Generate random integer list
		for (int i = 0; i < 20; i++) {
			// Loop index checker to make sure it's running
			System.out.println("Length = " + (i+1)*100000);
			
			// Reset the queue every run to avoid weird random-number samples affecting results
			PriorityQueue<Integer> queue = new SimplePriorityQueue<Integer>();
			
			length = (100000*(i+1));
			
			// Generate random integer list to fill queue with
			randomInts = new int[length];
			for (int j = 0; j < length; j++) {
				randomInts[j] = (int)(Math.random()*length + 1);
				queue.insert(randomInts[j]);
			}
			
			// Pause operations for one second
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) {}
			
			// Get insert() times. Deletes a value to keep queue size consistent
			startTime = System.nanoTime();
			
			for (int j = 0; j < loopTimes; j++) {
				queue.insert(randomInts[loopTimes]);
				queue.deleteMax();
			}
			
			// Account for delete method timing
			midTime = System.nanoTime();
			for (int j = 0; j < loopTimes; j++) {queue.deleteMax();}
			endTime = System.nanoTime();
			
			// Save times
			insertTimes[i] = ((midTime - startTime) - (endTime - midTime))/loopTimes;
			
			// Re-fill array up to desired length
			for (int j = length - loopTimes - 1; j < length; j++) {
				randomInts[j] = (int)(Math.random()*length + 1);
				queue.insert(randomInts[j]);
			}
			
			// Pause operations for one second
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) {}
			
			// Get time to call findMax()
			startTime = System.nanoTime();
			for (int j = 0; j < loopTimes; j++) {
				queue.findMax();
			}
			// Account for empty loop timing
			midTime = System.nanoTime();
			for (int j = 0; j < loopTimes; j++) {}
			endTime = System.nanoTime();
			
			// Save times
			findMaxTimes[i] = ((midTime - startTime) - (endTime - midTime))/loopTimes;
		}
		
		try (FileWriter writer = new FileWriter(("dat.txt")))
		{
			PrintWriter pw = new PrintWriter(writer);
			pw.flush();
			pw.println("Number of Loops = " + loopTimes);
			pw.println();
			pw.println("Insert");
			pw.println();
			pw.println("length  time(ns)");
			for (int i = 0; i < 20; i++) {
				pw.println((i+1)*100000 + ", " + insertTimes[i]);
			}
			pw.println("\n\nFindMax\n");
			pw.println("length  times(ns)");
			for (int i = 0; i < 20; i++) {
				pw.println((i+1)*100000 + ", " + findMaxTimes[i]);
			}
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
