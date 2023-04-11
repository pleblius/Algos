/**
 * 
 */
package assign10;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindKLargestTest {

    private final List<Integer> integerList = Arrays.asList(5, 20, 7, 9, 3, 1, 15);
    private final List<String> stringList = Arrays.asList("Apple", "Banana", "Cherry", "Apricot", "Grape", "Peach", "Watermelon");
    private final Comparator<Integer> reverseIntegerComparator = (a, b) -> b.compareTo(a);
    private final Comparator<String> reverseStringComparator = (a, b) -> b.compareTo(a);

    @Test
    void testFindKLargestHeap() {
        List<Integer> kLargestIntegers = FindKLargest.findKLargestHeap(integerList, 3);
        assertEquals(Arrays.asList(20, 15, 9), kLargestIntegers);

        List<String> kLargestStrings = FindKLargest.findKLargestHeap(stringList, 3);
        assertEquals(Arrays.asList("Watermelon", "Peach", "Grape"), kLargestStrings);
    }

    @Test
    void testFindKLargestHeapWithComparator() {
        List<Integer> kLargestIntegers = FindKLargest.findKLargestHeap(integerList, 3, reverseIntegerComparator);
        assertEquals(Arrays.asList(20, 15, 9), kLargestIntegers);

        List<String> kLargestStrings = FindKLargest.findKLargestHeap(stringList, 3, reverseStringComparator);
        assertEquals(Arrays.asList("Watermelon", "Peach", "Grape"), kLargestStrings);
    }

    @Test
    void testFindKLargestSort() {
        List<Integer> kLargestIntegers = FindKLargest.findKLargestSort(integerList, 3);
        assertEquals(Arrays.asList(20, 15, 9), kLargestIntegers);

        List<String> kLargestStrings = FindKLargest.findKLargestSort(stringList, 3);
        assertEquals(Arrays.asList("Watermelon", "Peach", "Grape"), kLargestStrings);
    }

    @Test
    void testFindKLargestSortWithComparator() {
        List<Integer> kLargestIntegers = FindKLargest.findKLargestSort(integerList, 3, reverseIntegerComparator);
        assertEquals(Arrays.asList(20, 15, 9), kLargestIntegers);

        List<String> kLargestStrings = FindKLargest.findKLargestSort(stringList, 3, reverseStringComparator);
        assertEquals(Arrays.asList("Watermelon", "Peach", "Grape"), kLargestStrings);
    }

    @Test
    void testFindKLargestThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(integerList, -1));
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(integerList, 8));
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(stringList, -1));
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(stringList, 8));

        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(integerList, -1));
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(integerList, 8));
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(stringList, -1));
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(stringList, 8));

        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(integerList, -1, reverseIntegerComparator));
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(integerList, 8, reverseIntegerComparator));
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(stringList, -1, reverseStringComparator));
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(stringList, 8, reverseStringComparator));

        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(integerList, -1, reverseIntegerComparator));
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(integerList, 8, reverseIntegerComparator));
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(stringList, -1, reverseStringComparator));
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(stringList, 8, reverseStringComparator));
    }
}
