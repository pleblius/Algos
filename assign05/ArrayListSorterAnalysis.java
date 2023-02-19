package assign05;

import analysis.Utils;

public class ArrayListSorterAnalysis {
	
	public static void main(String[] args) {
		int[] lengths;
		int[] times;
		
		// Threshold experiment
		lengths = new int[10];
		times = new int[10];
		
		for (int i = 0; i < 10; i++) {
			lengths[i] = (i+1)*10000;
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j <= 10; j++) {
				ArrayListSorter.setThreshold(j);
			}
		}
	}
}
