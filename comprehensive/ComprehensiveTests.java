package comprehensive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ComprehensiveTests {

    private DiscreteMap<String> discreteMap;

    @BeforeEach
    public void setUp() {
        discreteMap = new DiscreteMap<>();
    }

    @Test
    public void testEmptyConstructor() {
        assertEquals(0, discreteMap.size());
    }

    @Test
    public void testListConstructor() {
        discreteMap = new DiscreteMap<>(Arrays.asList("A", "B", "C"));
        assertEquals(3, discreteMap.size());
    }

    @Test
    public void testMakeSet() {
        discreteMap.makeSet("A");
        discreteMap.makeSet("B");
        discreteMap.makeSet("C");
        assertEquals(3, discreteMap.size());
    }

    @Test
    public void testMakeSetDuplicateElement() {
        discreteMap.makeSet("A");
        discreteMap.makeSet("B");
        discreteMap.makeSet("A");
        assertEquals(2, discreteMap.size());
    }

    @Test
    public void testGetRepresentative() {
        discreteMap.makeSet("A");
        assertEquals("A", discreteMap.getRepresentative("A"));
    }

    @Test
    public void testGetRepresentativeNoSuchElement() {
        assertThrows(NoSuchElementException.class, () -> discreteMap.getRepresentative("A"));
    }

    @Test
    public void testUnion() {
        discreteMap.makeSet("A");
        discreteMap.makeSet("B");
        discreteMap.union("A", "B");

        String repA = discreteMap.getRepresentative("A");
        String repB = discreteMap.getRepresentative("B");
        assertEquals(repA, repB);
    }

    @Test
    public void testUnionElementNotInStructure() {
        discreteMap.makeSet("A");
        assertThrows(NoSuchElementException.class, () -> discreteMap.union("A", "B"));
    }

    @Test
    public void testUnionAlreadyInSameSet() {
        discreteMap.makeSet("A");
        discreteMap.makeSet("B");
        discreteMap.union("A", "B");

        String repA1 = discreteMap.getRepresentative("A");
        String repB1 = discreteMap.getRepresentative("B");

        discreteMap.union("A", "B");
        String repA2 = discreteMap.getRepresentative("A");
        String repB2 = discreteMap.getRepresentative("B");

        assertEquals(repA1, repA2);
        assertEquals(repB1, repB2);
    }

    @Test
    public void testUnionWithDifferentSizes() {
        discreteMap = new DiscreteMap<>(Arrays.asList("A", "B", "C", "D", "E", "F"));
        discreteMap.union("A", "B");
        discreteMap.union("C", "D");
        discreteMap.union("E", "F");
        discreteMap.union("A", "C");
        discreteMap.union("E", "A");

        String repA = discreteMap.getRepresentative("A");
        String repB = discreteMap.getRepresentative("B");
        String repC = discreteMap.getRepresentative("C");
        String repD = discreteMap.getRepresentative("D");
        String repE = discreteMap.getRepresentative("E");
        String repF = discreteMap.getRepresentative("F");

        assertEquals(repA, repB);
        assertEquals(repA, repC);
        assertEquals(repA, repD);
        assertEquals(repA, repE);
        assertEquals(repA, repF);
    }

    @Test
    public void testMultipleUnions() {
        discreteMap = new DiscreteMap<>(Arrays.asList("A", "B", "C", "D", "E", "F"));
        discreteMap.union("A", "B");
        discreteMap.union("C", "D");
        discreteMap.union("E", "F");
        discreteMap.union("A", "C");

        String repA = discreteMap.getRepresentative("A");
        String repB = discreteMap.getRepresentative("B");
        String repC = discreteMap.getRepresentative("C");
        String repD = discreteMap.getRepresentative("D");
        String repE = discreteMap.getRepresentative("E");
        String repF = discreteMap.getRepresentative("F");

        assertEquals(repA, repB);
        assertEquals(repA, repC);
        assertEquals(repA, repD);
        assertNotEquals(repA, repE);
        assertNotEquals(repA, repF);
        assertEquals(repE, repF);
    }
    
    @Test
    public void testUnionWithLargeSizeDifference() {
        discreteMap = new DiscreteMap<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
        discreteMap.union("A", "B");
        discreteMap.union("A", "C");
        discreteMap.union("A", "D");
        discreteMap.union("A", "E");
        discreteMap.union("A", "F");

        discreteMap.union("G", "H");
        discreteMap.union("G", "I");

        discreteMap.union("A", "G");

        String repA = discreteMap.getRepresentative("A");
        String repG = discreteMap.getRepresentative("G");
        String repJ = discreteMap.getRepresentative("J");

        assertEquals(repA, repG);
        assertNotEquals(repA, repJ);
    }

    @Test
    public void testListConstructorWithEmptyList() {
        discreteMap = new DiscreteMap<>(new ArrayList<>());
        assertEquals(0, discreteMap.size());
    }

    @Test
    public void testListConstructorWithDuplicateElements() {
        discreteMap = new DiscreteMap<>(Arrays.asList("A", "B", "C", "A", "B"));
        assertEquals(3, discreteMap.size());
    }
    
    @Test
    public void testUnionSizeNotAffected() {
        discreteMap.makeSet("A");
        discreteMap.makeSet("B");
        int initialSize = discreteMap.size();
        discreteMap.union("A", "B");
        int newSize = discreteMap.size();
        assertEquals(initialSize, newSize);
    }

    @Test
    public void testUnionChangingRepresentative() {
        discreteMap.makeSet("A");
        discreteMap.makeSet("B");
        String initialRepA = discreteMap.getRepresentative("A");
        discreteMap.union("A", "B");
        String newRepA = discreteMap.getRepresentative("A");
        assertEquals(initialRepA, newRepA);
    }

    @Test
    public void testGetRepresentativeAfterUnion() {
        discreteMap.makeSet("A");
        discreteMap.makeSet("B");
        discreteMap.union("A", "B");

        assertThrows(NoSuchElementException.class, () -> discreteMap.getRepresentative("C"));
    }
    
    @Test
    public void testSizeAfterMakeSetAndUnion() {
        discreteMap.makeSet("A");
        discreteMap.makeSet("B");
        discreteMap.makeSet("C");
        discreteMap.makeSet("D");
        discreteMap.union("A", "B");
        discreteMap.union("C", "D");
        assertEquals(4, discreteMap.size());
    }

    @Test
    public void testUnionWithNonexistentElement() {
        discreteMap.makeSet("A");
        discreteMap.makeSet("B");
        assertThrows(NoSuchElementException.class, () -> discreteMap.union("A", "C"));
        assertThrows(NoSuchElementException.class, () -> discreteMap.union("C", "A"));
    }
    
    @Test
    public void testLargeNumberOfSetsCombinedIntoLargeIntermediateSets() {
        int setSize = 1000;
        List<String> elements = new ArrayList<>();
        for (int i = 0; i < setSize; i++) {
            elements.add("E" + i);
        }

        discreteMap = new DiscreteMap<>(elements);

        for (int i = 1; i < setSize; i += 2) {
            discreteMap.union("E" + i, "E" + (i - 1));
        }

        for (int i = 2; i < setSize; i += 4) {
            discreteMap.union("E" + i, "E" + (i - 2));
        }

        String repE0 = discreteMap.getRepresentative("E0");
        String repE2 = discreteMap.getRepresentative("E2");
        String repE4 = discreteMap.getRepresentative("E4");

        assertEquals(repE0, repE2);
        assertNotEquals(repE0, repE4);
    }
    
    @Test
    public void testAllElementsCombinedIntoSingleSet() {
        int setSize = 100;
        List<String> elements = new ArrayList<>();
        for (int i = 0; i < setSize; i++) {
            elements.add("E" + i);
        }

        discreteMap = new DiscreteMap<>(elements);

        for (int i = 1; i < setSize; i++) {
            discreteMap.union("E" + (i - 1), "E" + i);
        }

        String repE0 = discreteMap.getRepresentative("E0");

        for (int i = 1; i < setSize; i++) {
            assertEquals(repE0, discreteMap.getRepresentative("E" + i));
        }
    }
}
