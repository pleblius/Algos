package assign01;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BugReportTests {
    private GrayscaleImage smallThin;
    private GrayscaleImage bigSquare;

    @BeforeEach
    void setUp() {
        smallThin = new GrayscaleImage(new double[][] {{1,2},{3,4},{5,6}});
        bigSquare = new GrayscaleImage(new double[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}});
    }
    
    @Test
    void getPixelNegativeArrayIndexTest() {
    	// Tests to check that getPixel() throws appropriately with negative indexes
        assertThrows(IllegalArgumentException.class, () -> {smallThin.getPixel(-1, 0);});
        assertThrows(IllegalArgumentException.class, () -> {smallThin.getPixel(0, -1);});
    }
    
    @Test
    void croppedNegativeArrayTest() {
    	// Tests to check that cropped() throws appropriately with negative sized width/height selection
    	assertThrows(IllegalArgumentException.class, () -> {bigSquare.cropped(2, 2, -1, 1);});
    	assertThrows(IllegalArgumentException.class, () -> {bigSquare.cropped(2, 2, 1, -1);});
    }
}
