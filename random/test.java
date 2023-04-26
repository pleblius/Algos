/**
* Review functions used for week 1 practice assignment.
* @author Tyler C. Wilcox
* @version 09 January 2023
*/
package random;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String text = input.nextLine();
		System.out.println("Hello, World.");
		System.out.println(text);
	}
	
	public static List<Integer> rotateLeft(int d, List<Integer> arr) {
		List<Integer> newArray = new ArrayList<Integer>(arr.size());
		
		for (int i = 0; i < arr.size() - d; i++) {
			newArray.add(arr.get(i + d));
		}
		for (int i = arr.size() - d; i < arr.size(); i++) {
			newArray.add(arr.get(i - arr.size() + d));
		}
		
		return newArray;
	}
	
    public static int hourglassSum(List<List<Integer>> arr) {
    	List<Integer> sums = new ArrayList<Integer>();
    	
    	int currentSum = 0;
    	
    	for (int i = 0; i < arr.size() - 2; i++) {
    		for (int j = 0; j < arr.get(i).size() - 2; j++) {
    			currentSum = arr.get(i).get(j) + 
    					arr.get(i).get(j+1) + 
    					arr.get(i).get(j+2) + 
    					arr.get(i+1).get(j+1) +
    					arr.get(i+2).get(j) + 
    					arr.get(i+2).get(j+1) +
    					arr.get(i+2).get(j+2);
    			
    			sums.add(currentSum);
    		}
    	}
    	int maxSum = sums.get(0);
    	
    	for (int i = 0; i < sums.size(); i++)
    		if (sums.get(i) > maxSum) maxSum = sums.get(i);
    	
    	return maxSum;
    }
    
    public static long largestRectangle(List<Integer> h) {
    	long maximum = h.get(0);
    	
    	List<Integer> skipIndex = new ArrayList<Integer>();
    	
    	for (int i = 0; i < h.size(); i++) {
    		if (skipIndex.contains(i)) continue;
    		
    		long current = h.get(i);
    		
    		for (int j = i + 1; j < h.size(); j++) {
    			if (h.get(j) < h.get(i)) break;
    			
    			if (h.get(j) == h.get(i))
    				skipIndex.add(j);
    			
    			current += h.get(i);
    		}
    		for (int j = i - 1; j >= 0; j--) {
    			if (h.get(j) < h.get(i)) break;
    			
    			current += h.get(i);
    		}
    		
    		if (current > maximum) maximum = current;
    	}
    	
    	return maximum;
    }
    
    public class Point {
    	public int x;
    	public int y;
    }
}
