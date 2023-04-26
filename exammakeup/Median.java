package exammakeup;

import java.util.Random;


/**
 * Method for computing the median of an int[], along with some other helpers
 * Author: Ben Jones
 **/
public class Median {

	/**
	 * Returns the median value of the provided array.
	 * The median value is one that corresponds to the ith value, where </br>
	 * i = arr.length/2 </br>
	 * E.g. For an array of length 10, the median will be the 6th largest number, corresponding
	 * to array index 5 if the array was sorted. </br>
	 * This method will modify the input array such that all elements left of the median
	 * will be smaller than the median and all elements right of the median will be larger than the median.
	 * 
	 * @param arr - The integer array whose median is to be obtained.
	 * @return median value in the integer array.
	 */
    public static int median(int[] arr) {
    	return median(arr, 0, arr.length);
    }
    
    /**
     * Recursively finds the median value for a provided integer array using the
     * quickselect algorithm on the portion of the array between the indexes
     * begin (inclusive) and end (exclusive).
     * When the pivot is selected and the array is partitioned, the method will recursively call
     * itself on the portion of the array that logically contains the median.
     * 
     * @param arr - The integer array whose median is to be obtained.
     * @param begin - The index corresponding to the start of the section to be parsed. (Inclusive)
     * @param end - The index corresponding to the end of the section to be parsed. (Exclusive)
     * @return The median value in the integer array.
     */
    private static int median(int[] arr, int begin, int end) {
    	var pivot = partition(arr, begin, end, getPivotIndex(begin, end));
    	if (pivot == arr.length/2)
    		return arr[pivot];
    	else if (pivot > arr.length/2)
    		return median(arr, begin, pivot);
    	else
    		return median(arr, pivot + 1, end);
    }

    /**
     * Driver for quicksort
     * @param arr the array to sort
     */
    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length);
    }

    /**
     * Recursive quicksort method
     * @param arr
     * @param begin inclusive index
     * @param end exclusive index
     */
    private static void quickSort(int[] arr, int begin, int end){
        if((end - begin) < 2) return;
        var pivot = partition(arr, begin, end, getPivotIndex(begin, end));
        quickSort(arr, begin, pivot);
        quickSort(arr, pivot + 1, end);
    }


    /**
     * Partition the sub-array so all elements left of the pivot are less than or equal to it
     * and all elements to its right are greater than it
     * @param arr the array to partition
     * @param begin first element in the range to be partitioned
     * @param end one-past the end of the range to be partitioned (ie end is EXclusive)
     * @param pivotIndex index of element to use as the pivot
     * @return index of the pivot element after partitioning
     */
    public static int partition(int[] arr, int begin, int end, int pivotIndex){
        int left = begin;
        int right = end  -1;
        var pivot = arr[pivotIndex];

        //move pivot out of the way
        swap(arr, pivotIndex, right);
        right--;

        while(left <= right){
            while(left < end -1 && arr[left] <= pivot){ left++; }
            while(right >= begin && arr[right] > pivot){ right--;}
            if(left < right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        swap(arr, left, end -1);
        return left;
    }

    private static void swap(int[] arr, int i, int j){
        var tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void shuffle(int[] arr){
        Random r = new Random();
        for(var i = arr.length - 1; i > 0; i--){
            var j = r.nextInt(i + 1);
            swap(arr, i, j);
        }
    }


    /// use the middle element, which will usually be fine.
    private static int getPivotIndex(int begin, int end){
        return (begin + end)/2;
    }
}
