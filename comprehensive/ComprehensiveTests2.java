package comprehensive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ComprehensiveTests2 {

    private DisjointForest<String> DisjointForest;

    @BeforeEach
    public void setUp() {
        DisjointForest = new DisjointForest<>();
    }

    @Test
    public void testEmptyConstructor() {
        assertEquals(0, DisjointForest.size());
    }

    @Test
    public void testListConstructor() {
        DisjointForest = new DisjointForest<>(Arrays.asList("A", "B", "C"));
        assertEquals(3, DisjointForest.size());
    }

    @Test
    public void testMakeSet() {
        DisjointForest.makeSet("A");
        DisjointForest.makeSet("B");
        DisjointForest.makeSet("C");
        assertEquals(3, DisjointForest.size());
    }

    @Test
    public void testMakeSetDuplicateElement() {
        DisjointForest.makeSet("A");
        DisjointForest.makeSet("B");
        DisjointForest.makeSet("A");
        assertEquals(2, DisjointForest.size());
    }

    @Test
    public void testGetRepresentative() {
        DisjointForest.makeSet("A");
        assertEquals("A", DisjointForest.getRepresentative("A"));
    }

    @Test
    public void testGetRepresentativeNoSuchElement() {
        assertThrows(NoSuchElementException.class, () -> DisjointForest.getRepresentative("A"));
    }

    @Test
    public void testUnion() {
        DisjointForest.makeSet("A");
        DisjointForest.makeSet("B");
        DisjointForest.union("A", "B");

        String repA = DisjointForest.getRepresentative("A");
        String repB = DisjointForest.getRepresentative("B");
        assertEquals(repA, repB);
    }

    @Test
    public void testUnionElementNotInStructure() {
        DisjointForest.makeSet("A");
        assertThrows(NoSuchElementException.class, () -> DisjointForest.union("A", "B"));
    }

    @Test
    public void testUnionAlreadyInSameSet() {
        DisjointForest.makeSet("A");
        DisjointForest.makeSet("B");
        DisjointForest.union("A", "B");

        String repA1 = DisjointForest.getRepresentative("A");
        String repB1 = DisjointForest.getRepresentative("B");

        DisjointForest.union("A", "B");
        String repA2 = DisjointForest.getRepresentative("A");
        String repB2 = DisjointForest.getRepresentative("B");

        assertEquals(repA1, repA2);
        assertEquals(repB1, repB2);
    }

    @Test
    public void testUnionWithDifferentSizes() {
        DisjointForest = new DisjointForest<>(Arrays.asList("A", "B", "C", "D", "E", "F"));
        DisjointForest.union("A", "B");
        DisjointForest.union("C", "D");
        DisjointForest.union("E", "F");
        DisjointForest.union("A", "C");
        DisjointForest.union("E", "A");

        String repA = DisjointForest.getRepresentative("A");
        String repB = DisjointForest.getRepresentative("B");
        String repC = DisjointForest.getRepresentative("C");
        String repD = DisjointForest.getRepresentative("D");
        String repE = DisjointForest.getRepresentative("E");
        String repF = DisjointForest.getRepresentative("F");

        assertEquals(repA, repB);
        assertEquals(repA, repC);
        assertEquals(repA, repD);
        assertEquals(repA, repE);
        assertEquals(repA, repF);
    }

    @Test
    public void testMultipleUnions() {
        DisjointForest = new DisjointForest<>(Arrays.asList("A", "B", "C", "D", "E", "F"));
        DisjointForest.union("A", "B");
        DisjointForest.union("C", "D");
        DisjointForest.union("E", "F");
        DisjointForest.union("A", "C");

        String repA = DisjointForest.getRepresentative("A");
        String repB = DisjointForest.getRepresentative("B");
        String repC = DisjointForest.getRepresentative("C");
        String repD = DisjointForest.getRepresentative("D");
        String repE = DisjointForest.getRepresentative("E");
        String repF = DisjointForest.getRepresentative("F");

        assertEquals(repA, repB);
        assertEquals(repA, repC);
        assertEquals(repA, repD);
        assertNotEquals(repA, repE);
        assertNotEquals(repA, repF);
        assertEquals(repE, repF);
    }
    
    @Test
    public void testUnionWithLargeSizeDifference() {
        DisjointForest = new DisjointForest<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
        DisjointForest.union("A", "B");
        DisjointForest.union("A", "C");
        DisjointForest.union("A", "D");
        DisjointForest.union("A", "E");
        DisjointForest.union("A", "F");

        DisjointForest.union("G", "H");
        DisjointForest.union("G", "I");

        DisjointForest.union("A", "G");

        String repA = DisjointForest.getRepresentative("A");
        String repG = DisjointForest.getRepresentative("G");
        String repJ = DisjointForest.getRepresentative("J");

        assertEquals(repA, repG);
        assertNotEquals(repA, repJ);
    }

    @Test
    public void testListConstructorWithEmptyList() {
        DisjointForest = new DisjointForest<>(new ArrayList<>());
        assertEquals(0, DisjointForest.size());
    }

    @Test
    public void testListConstructorWithDuplicateElements() {
        DisjointForest = new DisjointForest<>(Arrays.asList("A", "B", "C", "A", "B"));
        assertEquals(3, DisjointForest.size());
    }
    
    @Test
    public void testUnionSizeNotAffected() {
        DisjointForest.makeSet("A");
        DisjointForest.makeSet("B");
        int initialSize = DisjointForest.size();
        DisjointForest.union("A", "B");
        int newSize = DisjointForest.size();
        assertEquals(initialSize, newSize);
    }

    @Test
    public void testGetRepresentativeAfterUnion() {
        DisjointForest.makeSet("A");
        DisjointForest.makeSet("B");
        DisjointForest.union("A", "B");

        assertThrows(NoSuchElementException.class, () -> DisjointForest.getRepresentative("C"));
    }
    
    @Test
    public void testSizeAfterMakeSetAndUnion() {
        DisjointForest.makeSet("A");
        DisjointForest.makeSet("B");
        DisjointForest.makeSet("C");
        DisjointForest.makeSet("D");
        DisjointForest.union("A", "B");
        DisjointForest.union("C", "D");
        assertEquals(4, DisjointForest.size());
    }

    @Test
    public void testUnionWithNonexistentElement() {
        DisjointForest.makeSet("A");
        DisjointForest.makeSet("B");
        assertThrows(NoSuchElementException.class, () -> DisjointForest.union("A", "C"));
        assertThrows(NoSuchElementException.class, () -> DisjointForest.union("C", "A"));
    }
    
    @Test
    public void testLargeNumberOfSetsCombinedIntoLargeIntermediateSets() {
        int setSize = 1000;
        List<String> elements = new ArrayList<>();
        for (int i = 0; i < setSize; i++) {
            elements.add("E" + i);
        }

        DisjointForest = new DisjointForest<>(elements);

        for (int i = 1; i < setSize; i += 2) {
            DisjointForest.union("E" + i, "E" + (i - 1));
        }

        for (int i = 2; i < setSize; i += 4) {
            DisjointForest.union("E" + i, "E" + (i - 2));
        }

        String repE0 = DisjointForest.getRepresentative("E0");
        String repE2 = DisjointForest.getRepresentative("E2");
        String repE4 = DisjointForest.getRepresentative("E4");

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

        DisjointForest = new DisjointForest<>(elements);

        for (int i = 1; i < setSize; i++) {
            DisjointForest.union("E" + (i - 1), "E" + i);
        }

        String repE0 = DisjointForest.getRepresentative("E0");

        for (int i = 1; i < setSize; i++) {
            assertEquals(repE0, DisjointForest.getRepresentative("E" + i));
        }
    }
}