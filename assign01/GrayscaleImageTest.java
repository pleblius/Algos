package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrayscaleImageTest {

    private GrayscaleImage smallSquare;
    private GrayscaleImage smallWide;
    private GrayscaleImage smallThin;
    private GrayscaleImage bigSquare;

    @BeforeEach
    void setUp() {
        smallSquare = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        smallWide = new GrayscaleImage(new double[][]{{1,2,3},{4,5,6}});
        smallThin = new GrayscaleImage(new double[][] {{1,2},{3,4},{5,6}});
        bigSquare = new GrayscaleImage(new double[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}});
    }

    @Test
    void testGetPixel(){
        assertEquals(smallSquare.getPixel(0,0), 1);
        assertEquals(smallSquare.getPixel(1,0), 2);
        assertEquals(smallSquare.getPixel(0,1), 3);
        assertEquals(smallSquare.getPixel(1,1), 4);
        
        // Check non-square images
        assertEquals(smallWide.getPixel(0,0), 1);
        assertEquals(smallWide.getPixel(1,0), 2);
        assertEquals(smallWide.getPixel(2,0), 3);
        assertEquals(smallWide.getPixel(0,1), 4);
        assertEquals(smallWide.getPixel(1,1), 5);
        assertEquals(smallWide.getPixel(2,1), 6);
        
        assertEquals(smallThin.getPixel(0,0), 1);
        assertEquals(smallThin.getPixel(1,0), 2);
        assertEquals(smallThin.getPixel(0,1), 3);
        assertEquals(smallThin.getPixel(1,1), 4);
        assertEquals(smallThin.getPixel(0,2), 5);
        assertEquals(smallThin.getPixel(1,2), 6);
        
        // Check exception throwing
        assertThrows(IllegalArgumentException.class, () -> {smallThin.getPixel(100,0);});
        assertThrows(IllegalArgumentException.class, () -> {smallThin.getPixel(0, 100);});
    }

    @Test
    void testEquals() {
        assertEquals(smallSquare, smallSquare);
        assertEquals(smallWide, smallWide);
        assertEquals(smallThin, smallThin);
        var equivalent = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        var equivalentWide = new GrayscaleImage(new double[][] {{1,2,3},{4,5,6}});
        var equivalentThin = new GrayscaleImage(new double[][] {{1,2}, {3,4}, {5,6}});
        var bigger = new GrayscaleImage(new double[][] {{1,2,3}, {3,4,5}, {5,6,7}});
        var notAnImage = new String("test");
        
        // Verify it identifies different objects
        assertEquals(smallSquare, equivalent);
        assertEquals(smallWide, equivalentWide);
        assertEquals(smallThin, equivalentThin);
        
        // Verify it catches non-equivalences
        assertNotEquals(smallSquare, smallWide);
        assertNotEquals(smallThin, smallWide);
        assertNotEquals(smallSquare, notAnImage);
        
        // Verify it catches size differences
        assertNotEquals(smallSquare, bigger);
        assertNotEquals(smallThin, bigger);
        assertNotEquals(bigger, smallThin);
    }

    @Test
    void averageBrightness() {
        assertEquals(smallSquare.averageBrightness(), 2.5, 2.5*.001);
        var bigZero = new GrayscaleImage(new double[1000][1000]);
        assertEquals(bigZero.averageBrightness(), 0);
        
        // Check for white values
        var white = new GrayscaleImage(new double[][] {{255,255},{255,255}});
        assertEquals(white.averageBrightness(), 255);
        
        // Check a non-square image
        assertEquals(smallThin.averageBrightness(),3.5);
    }

    @Test
    void normalized() {
        var smallNorm = smallSquare.normalized();
        assertEquals(smallNorm.averageBrightness(), 127, 127*.001);
        var scale = 127/2.5;
        var expectedNorm = new GrayscaleImage(new double[][]{{scale, 2*scale},{3*scale, 4*scale}});
        for(var row = 0; row < 2; row++){
            for(var col = 0; col < 2; col++){
                assertEquals(smallNorm.getPixel(col, row), expectedNorm.getPixel(col, row),
                        expectedNorm.getPixel(col, row)*.001,
                        "pixel at row: " + row + " col: " + col + " incorrect");
            }
        }
        
        // Check a black image
        var blackImage = new GrayscaleImage(new double[][] {{0,0},{0,0}});
        assertEquals(blackImage.normalized().averageBrightness(), 127, 127*.001);
        
        // Check a white image
        var whiteImage = new GrayscaleImage(new double[][] {{255,255},{255,255}});
        assertEquals(whiteImage.normalized().averageBrightness(), 127, 127*.001);
    }

    @Test
    void mirrored() {
        var expected = new GrayscaleImage(new double[][]{{2,1},{4,3}});
        assertEquals(smallSquare.mirrored(), expected);
        
        // Verify it handles non-square images
        var expectedWide = new GrayscaleImage(new double[][] {{3,2,1},{6,5,4}});
        var expectedThin = new GrayscaleImage(new double[][] {{2,1}, {4,3}, {6,5}});
        assertEquals(smallThin.mirrored(),expectedThin);
        assertEquals(smallWide.mirrored(),expectedWide);
    }

    @Test
    void cropped() {
    	// Check for boundary exceptions
    	assertThrows(IllegalArgumentException.class, () -> {smallSquare.cropped(0, 0, 0, 1);});
    	assertThrows(IllegalArgumentException.class, () -> {smallSquare.cropped(0, 0, 1, 0);});
    	assertThrows(IllegalArgumentException.class, () -> {smallSquare.cropped(0, 0, 3, 0);});
    	assertThrows(IllegalArgumentException.class, () -> {smallSquare.cropped(0, 0, 0, 3);});
    	assertThrows(IllegalArgumentException.class, () -> {smallSquare.cropped(-1, 0, 0, 0);});
    	assertThrows(IllegalArgumentException.class, () -> {smallSquare.cropped(0, -1, 0, 0);}); 
    	
        var cropped = smallSquare.cropped(1,1,1,1);
        assertEquals(cropped, new GrayscaleImage(new double[][]{{4}}));
        
        // Check bigger crops
        var cropped1 = bigSquare.cropped(0, 0, 2, 2);
        assertEquals(cropped1, new GrayscaleImage(new double[][] {{1,2},{5,6}}));
        
        // Check uneven crops
        var cropped2 = bigSquare.cropped(2, 1, 2, 1);
        assertEquals(cropped2, new GrayscaleImage(new double[][] {{10,11}}));
        var cropped3 = bigSquare.cropped(0, 1, 3, 4);
        assertEquals(cropped3, new GrayscaleImage(new double[][] {{2,3,4},{6,7,8},{10,11,12},{14,15,16}}));
    }

    @Test
    void squarified() {
    	// Square confirmation
    	assertEquals(bigSquare,bigSquare.squarified());
    	assertEquals(smallSquare,smallSquare.squarified());
    	
        var squared = smallWide.squarified();
        var expected = new GrayscaleImage(new double[][]{{1,2},{4,5}});
        assertEquals(squared, expected);
        
        // Check for even lopping off top
        var tall = new GrayscaleImage(new double[10][8]);
        var tallTest = new GrayscaleImage(new double[8][8]);
        assertEquals(tall.squarified(), tallTest);
        
        // Check for even cutting off side
        var wide = new GrayscaleImage(new double[8][10]);
        var wideTest = new GrayscaleImage(new double[8][8]);
        assertEquals(wide.squarified(), wideTest);
        
        // Check for uneven transformations (top heavy)
        double[][] dataSet = new double[7][4];
        int k = 0;
        for (int i = 0; i < 7; i++) {
        	for (int j = 0; j < 4; j++) {
        		dataSet[i][j] = k++;
        	}
        }
        var bigTall = new GrayscaleImage(dataSet);
        double[][] dataTest = new double[][]{{4,5,6,7},{8,9,10,11},{12,13,14,15},{16,17,18,19}};
        var bigTallTest = new GrayscaleImage(dataTest);
        
        assertEquals(bigTall.squarified(),bigTallTest);
        
        // Check for uneven transformations (side heavy)
        dataSet = new double[4][7];
        k = 0;
        for (int i = 0; i < 4; i++) {
        	for (int j = 0; j < 7; j++) {
        		dataSet[i][j] = k++;
        	}
        }
        var bigWide = new GrayscaleImage(dataSet);
        dataTest = new double[][]{{1,2,3,4},{8,9,10,11},{15,16,17,18},{22,23,24,25}};
        var bigWideTest = new GrayscaleImage(dataTest);
        
        assertEquals(bigWide.squarified(),bigWideTest);
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