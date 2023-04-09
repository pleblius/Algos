package assign10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class ChatGPTTest {
    private BinaryMaxHeap<Integer> heap;
    private BinaryMaxHeap<String> stringHeap;

    @BeforeEach
    public void setUp() {
        heap = new BinaryMaxHeap<Integer>();
        stringHeap = new BinaryMaxHeap<String>();
    }

    @Test
    public void testEmptyHeap() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        assertThrows(NoSuchElementException.class, () -> heap.peek());
        assertThrows(NoSuchElementException.class, () -> heap.extractMax());
    }

    @Test
    public void testSingleNode() {
        heap.add(42);
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.size());
        assertEquals(42, heap.peek());
        assertEquals(42, heap.extractMax());
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testInsertAndRemoveMax() {
        int[] values = {15, 30, 45, 60, 75, 90, 105};
        for (int value : values) {
            heap.add(value);
        }
        for (int i = values.length - 1; i >= 0; i--) {
            assertEquals(values[i], heap.peek());
            assertEquals(values[i], heap.extractMax());
        }
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testDuplicateValues() {
        heap.add(42);
        heap.add(42);
        assertFalse(heap.isEmpty());
        assertEquals(2, heap.size());
        assertEquals(42, heap.peek());
        assertEquals(42, heap.extractMax());
        assertEquals(1, heap.size());
        assertEquals(42, heap.peek());
    }

    @Test
    public void testNegativeValues() {
        heap.add(-42);
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.size());
        assertEquals(-42, heap.peek());
        assertEquals(-42, heap.extractMax());
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testMixedValues() {
        int[] values = {-10, 50, 0, 30, -20, 40, 10, 20, -30};
        int[] sortedValues = {-30, -20, -10, 0, 10, 20, 30, 40, 50};

        for (int value : values) {
            heap.add(value);
        }

        for (int i = sortedValues.length - 1; i >= 0; i--) {
            assertEquals(sortedValues[i], heap.peek());
            assertEquals(sortedValues[i], heap.extractMax());
        }
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testLargeDataSet() {
        int n = 1000;
        for (int i = 1; i <= n; i++) {
            heap.add(i);
        }
        for (int i = n; i >= 1; i--) {
            assertEquals(i, heap.peek());
            assertEquals(i, heap.extractMax());
        }
        assertTrue(heap.isEmpty());
    }
    
    @Test
    public void testHeapifyWithCustomComparator() {
        List<Integer> values = Arrays.asList(15, 30, 45, 60, 75, 90, 105);
        Comparator<Integer> reverseOrder = Comparator.reverseOrder();
        heap = new BinaryMaxHeap<>(values, reverseOrder);

        for (int i = 0; i < values.size(); i++) {
            assertEquals(values.get(i), heap.peek());
            assertEquals(values.get(i), heap.extractMax());
        }
        assertTrue(heap.isEmpty());
    }
    
    @Test
    public void testHeapify() {
        List<Integer> values = Arrays.asList(15, 30, 45, 60, 75, 90, 105);
        heap = new BinaryMaxHeap<>(values);

        for (int i = values.size() - 1; i >= 0; i--) {
            assertEquals(values.get(i), heap.peek());
            assertEquals(values.get(i), heap.extractMax());
        }
        assertTrue(heap.isEmpty());
    }
    
    @Test
    public void testCustomComparator() {
        Comparator<Integer> reverseOrder = Comparator.reverseOrder();
        heap = new BinaryMaxHeap<>(reverseOrder);

        heap.add(42);
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.size());
        assertEquals(42, heap.peek());
        assertEquals(42, heap.extractMax());
        assertTrue(heap.isEmpty());
    }
    
    @Test
    public void testInsertIncreasingOrder() {
        int n = 10;
        for (int i = 1; i <= n; i++) {
            heap.add(i);
        }
        for (int i = n; i >= 1; i--) {
            assertEquals(i, heap.peek());
            assertEquals(i, heap.extractMax());
        }
        assertTrue(heap.isEmpty());
    }
    
    @Test
    public void testInsertDecreasingOrder() {
        int n = 10;
        for (int i = n; i >= 1; i--) {
            heap.add(i);
        }
        for (int i = n; i >= 1; i--) {
            assertEquals(i, heap.peek());
            assertEquals(i, heap.extractMax());
        }
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testMinMaxIntegerValues() {
        heap.add(Integer.MIN_VALUE);
        heap.add(Integer.MAX_VALUE);

        assertFalse(heap.isEmpty());
        assertEquals(2, heap.size());
        assertEquals(Integer.MAX_VALUE, heap.peek());
        assertEquals(Integer.MAX_VALUE, heap.extractMax());
        assertEquals(Integer.MIN_VALUE, heap.peek());
        assertEquals(Integer.MIN_VALUE, heap.extractMax());
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testMinIntegerValue() {
        heap.add(Integer.MIN_VALUE);
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.size());
        assertEquals(Integer.MIN_VALUE, heap.peek());
        assertEquals(Integer.MIN_VALUE, heap.extractMax());
        assertTrue(heap.isEmpty());
    }

    
    @Test
    public void testMaxIntegerValue() {
        heap.add(Integer.MAX_VALUE);
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.size());
        assertEquals(Integer.MAX_VALUE, heap.peek());
        assertEquals(Integer.MAX_VALUE, heap.extractMax());
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testEmptyStringHeap() {
        assertTrue(stringHeap.isEmpty());
        assertEquals(0, stringHeap.size());
        assertThrows(NoSuchElementException.class, () -> stringHeap.peek());
        assertThrows(NoSuchElementException.class, () -> stringHeap.extractMax());
    }

    @Test
    public void testSingleElementStringHeap() {
        stringHeap.add("apple");
        assertFalse(stringHeap.isEmpty());
        assertEquals(1, stringHeap.size());
        assertEquals("apple", stringHeap.peek());
        assertEquals("apple", stringHeap.extractMax());
        assertTrue(stringHeap.isEmpty());
    }

    @Test
    public void testMultipleElementStringHeap() {
        List<String> fruits = Arrays.asList("apple", "orange", "banana", "kiwi", "grape");
        for (String fruit : fruits) {
            stringHeap.add(fruit);
        }
        assertFalse(stringHeap.isEmpty());
        assertEquals(fruits.size(), stringHeap.size());
        assertEquals("orange", stringHeap.peek());
        assertEquals("orange", stringHeap.extractMax());
        assertEquals("kiwi", stringHeap.peek());
        assertEquals("kiwi", stringHeap.extractMax());
    }

    @Test
    public void testCustomComparatorStringHeap() {
        Comparator<String> customComparator = (a, b) -> a.length() - b.length();
        stringHeap = new BinaryMaxHeap<>(customComparator);

        List<String> words = Arrays.asList("one", "two", "three", "four", "five", "six");
        for (String word : words) {
            stringHeap.add(word);
        }
        assertFalse(stringHeap.isEmpty());
        assertEquals(words.size(), stringHeap.size());
        assertEquals("three", stringHeap.peek());
        assertEquals("three", stringHeap.extractMax());
        assertEquals("four", stringHeap.peek());
        assertEquals("four", stringHeap.extractMax());
    }

    @Test
    public void testHeapifyStringList() {
        List<String> words = Arrays.asList("one", "two", "three", "four", "five", "six");
        stringHeap = new BinaryMaxHeap<>(words);

        assertEquals("two", stringHeap.peek());
        assertEquals("two", stringHeap.extractMax());
        assertEquals("three", stringHeap.peek());
        assertEquals("three", stringHeap.extractMax());
    }

    @Test
    public void testHeapifyStringListWithCustomComparator() {
        List<String> words = Arrays.asList("one", "two", "three", "four", "five", "six");
        Comparator<String> customComparator = (a, b) -> a.length() - b.length();
        stringHeap = new BinaryMaxHeap<>(words, customComparator);

        assertEquals("three", stringHeap.peek());
        assertEquals("three", stringHeap.extractMax());
        assertEquals("four", stringHeap.peek());
        assertEquals("four", stringHeap.extractMax());
    }
    
    @Test
    public void testSameValueDifferentCase() {
        stringHeap.add("apple");
        stringHeap.add("Apple");
        assertFalse(stringHeap.isEmpty());
        assertEquals(2, stringHeap.size());
        assertEquals("apple", stringHeap.peek());
        assertEquals("apple", stringHeap.extractMax());
        assertEquals("Apple", stringHeap.peek());
        assertEquals("Apple", stringHeap.extractMax());
        assertTrue(stringHeap.isEmpty());
    }

    @Test
    public void testSpecialCharacters() {
        stringHeap.add("!Special");
        stringHeap.add("@Characters");
        assertFalse(stringHeap.isEmpty());
        assertEquals(2, stringHeap.size());
        assertEquals("@Characters", stringHeap.peek());
        assertEquals("@Characters", stringHeap.extractMax());
        assertEquals("!Special", stringHeap.peek());
        assertEquals("!Special", stringHeap.extractMax());
        assertTrue(stringHeap.isEmpty());
    }

    @Test
    public void testStringsWithSpaces() {
        stringHeap.add("apple pie");
        stringHeap.add("apple");
        assertFalse(stringHeap.isEmpty());
        assertEquals(2, stringHeap.size());
        assertEquals("apple pie", stringHeap.peek());
        assertEquals("apple pie", stringHeap.extractMax());
        assertEquals("apple", stringHeap.peek());
        assertEquals("apple", stringHeap.extractMax());
        assertTrue(stringHeap.isEmpty());
    }

    @Test
    public void testStringsWithNumbersAndSymbols() {
        stringHeap.add("apple#2");
        stringHeap.add("apple#1");
        assertFalse(stringHeap.isEmpty());
        assertEquals(2, stringHeap.size());
        assertEquals("apple#2", stringHeap.peek());
        assertEquals("apple#2", stringHeap.extractMax());
        assertEquals("apple#1", stringHeap.peek());
        assertEquals("apple#1", stringHeap.extractMax());
        assertTrue(stringHeap.isEmpty());
    }

    @Test
    public void testEmptyStrings() {
        stringHeap.add("");
        stringHeap.add("apple");
        assertFalse(stringHeap.isEmpty());
        assertEquals(2, stringHeap.size());
        assertEquals("apple", stringHeap.peek());
        assertEquals("apple", stringHeap.extractMax());
        assertEquals("", stringHeap.peek());
        assertEquals("", stringHeap.extractMax());
        assertTrue(stringHeap.isEmpty());
    }

    @Test
    public void testToArrayStringHeap() {
        List<String> values = Arrays.asList("apple", "orange", "banana", "kiwi", "grape");
        for (String value : values) {
            stringHeap.add(value);
        }

        Object[] array = stringHeap.toArray();
        Arrays.sort(array);
        assertEquals(values.size(), array.length);

        for (int i = 0; i < values.size(); i++) {
            assertEquals(values.get(i), array[i]);
        }
    }

    @Test
    public void testToArrayIntegerHeap() {
        List<Integer> values = Arrays.asList(5, 3, 8, 1, 4);
        for (Integer value : values) {
            heap.add(value);
        }

        Object[] array = heap.toArray();
        Arrays.sort(array);
        assertEquals(values.size(), array.length);

        for (int i = 0; i < values.size(); i++) {
            assertEquals(values.get(i), array[i]);
        }
    }

    @Test
    public void testToArrayMaintainsHeapPropertyIntegerHeap() {
        List<Integer> values = Arrays.asList(5, 3, 8, 1, 4);
        for (Integer value : values) {
            heap.add(value);
        }

        Object[] array = heap.toArray();
        int n = array.length;

        for (int i = 0; i < n; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n) {
                assertTrue((Integer) array[i] >= (Integer) array[left]);
            }

            if (right < n) {
                assertTrue((Integer) array[i] >= (Integer) array[right]);
            }
        }
    }

    @Test
    public void testToArrayMaintainsHeapPropertyStringHeap() {
        List<String> values = Arrays.asList("apple", "orange", "banana", "kiwi", "grape");
        for (String value : values) {
            stringHeap.add(value);
        }

        Object[] array = stringHeap.toArray();
        int n = array.length;

        for (int i = 0; i < n; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n) {
                assertTrue(((String) array[i]).compareTo((String) array[left]) >= 0);
            }

            if (right < n) {
                assertTrue(((String) array[i]).compareTo((String) array[right]) >= 0);
            }
        }
    }
    
    @Test
    public void testToArrayEmptyHeap() {
        Object[] array = heap.toArray();
        assertEquals(0, array.length);
    }

    @Test
    public void testToArrayEmptyStringHeap() {
        Object[] array = stringHeap.toArray();
        assertEquals(0, array.length);
    }

    @Test
    public void testToArraySingleElementHeap() {
        heap.add(5);
        Object[] array = heap.toArray();
        assertEquals(1, array.length);
        assertEquals(5, array[0]);
    }

    @Test
    public void testToArraySingleElementStringHeap() {
        stringHeap.add("apple");
        Object[] array = stringHeap.toArray();
        assertEquals(1, array.length);
        assertEquals("apple", array[0]);
    }

    @Test
    public void testToArrayAfterElementRemoval() {
        heap.add(5);
        heap.add(3);
        heap.add(8);
        heap.extractMax();
        Object[] array = heap.toArray();
        int n = array.length;

        for (int i = 0; i < n; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n) {
                assertTrue((Integer) array[i] >= (Integer) array[left]);
            }

            if (right < n) {
                assertTrue((Integer) array[i] >= (Integer) array[right]);
            }
        }
    }

    @Test
    public void testToArrayAfterElementRemovalStringHeap() {
        stringHeap.add("apple");
        stringHeap.add("orange");
        stringHeap.add("banana");
        stringHeap.extractMax();
        Object[] array = stringHeap.toArray();
        int n = array.length;

        for (int i = 0; i < n; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n) {
                assertTrue(((String) array[i]).compareTo((String) array[left]) >= 0);
            }

            if (right < n) {
                assertTrue(((String) array[i]).compareTo((String) array[right]) >= 0);
            }
        }
    }

    @Test
    public void testToArrayAfterElementRemovalCustomComparatorIntegerHeap() {
        Comparator<Integer> customComparator = (a, b) -> b % 5 - a % 5;
        heap = new BinaryMaxHeap<>(customComparator);
        heap.add(5);
        heap.add(3);
        heap.add(8);
        heap.extractMax();

        Object[] array = heap.toArray();
        int n = array.length;

        for (int i = 0; i < n; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n) {
                assertTrue(customComparator.compare((Integer) array[i], (Integer) array[left]) >= 0);
            }

            if (right < n) {
                assertTrue(customComparator.compare((Integer) array[i], (Integer) array[right]) >= 0);
            }
        }
    }

    @Test
    public void testToArrayAfterElementRemovalCustomComparatorStringHeap() {
        Comparator<String> customComparator = (a, b) -> a.length() - b.length();
        stringHeap = new BinaryMaxHeap<>(customComparator);
        stringHeap.add("apple");
        stringHeap.add("orange");
        stringHeap.add("banana");
        stringHeap.extractMax();

        Object[] array = stringHeap.toArray();
        int n = array.length;

        for (int i = 0; i < n; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n) {
                assertTrue(customComparator.compare((String) array[i], (String) array[left]) >= 0);
            }

            if (right < n) {
                assertTrue(customComparator.compare((String) array[i], (String) array[right]) >= 0);
            }
        }
    }


}
