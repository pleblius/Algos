package comprehensive;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ChatGPTTest {
    public static void main(String[] args) {
        String filename = "large_test.txt";
        int numSets = 5000;
        int numIntermediateSets = 10;

        try {
            generateTestFile(filename, numSets, numIntermediateSets);
        } catch (IOException e) {
            System.err.println("Error while creating the test file: " + e.getMessage());
        }
    }

    public static void generateTestFile(String filename, int numSets, int numIntermediateSets) throws IOException {
        int setSize = numSets / numIntermediateSets;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Add elements to the disjoint set
            for (int i = 0; i < numSets; i++) {
                writer.write("Element" + i + "\n");
            }

            // Separate the sections with an empty line
            writer.write("\n");

            // Perform union operations to create intermediate sets
            for (int i = 0; i < numIntermediateSets; i++) {
                for (int j = 1; j < setSize; j++) {
                    writer.write("Element" + (i * setSize + j - 1) + " Element" + (i * setSize + j) + "\n");
                }
            }

            // Separate the sections with an empty line
            writer.write("\n");

            // Write queries to check connections between the last element of one intermediate set and the first element of the next intermediate set
            for (int i = 0; i < numIntermediateSets - 1; i++) {
                writer.write("Element" + ((i + 1) * setSize - 1) + " Element" + ((i + 1) * setSize) + "\n");
            }
            
            // Write queries to check connections between elements in each set
            for (int i = 0; i < numIntermediateSets; i++) {
            	for (int j = 0; j < setSize - 1; j++) {
            		writer.write("Element" + ((i*setSize) + j) + " Element" + (((i*setSize) + j + 1)) + "\n");
            	}
            }
            
            // Check every element within its own set
            for (int i = 1; i <= numIntermediateSets; i++) {
            	for (int j = 1; j < setSize; j++) {
            		String prefix = new String();
            		int prefixint = 1;
            		if (i == 1) {
            			prefix = "";
            		}
            		else if (i == 2) {
            			prefix = "";
            			prefixint = 2;
            		}
            		else if (i == 3) {
            			prefix = "1";
            		}
            		else if (i == 4) {
            			prefix = "1";
            			prefixint = 2;
            		}
            		else if (i == 5) {
            			prefix = "1";
            		}
            		else if (i == 6) {
            			prefix = "2";
            			prefixint = 2;
            		}
            		else if (i == 7) {
            			prefix = "3";
            		}
            		else if (i == 8) {
            			prefix = "3";
            			prefixint = 2;
            		}
            		else if (i == 9) {
            			prefix = "4";
            		}
            		else if (i == 10) {
            			prefix = "4";
            			prefixint = 2;
            		}
            		writer.write("Element" + prefix + prefixint*j + " Element" + prefixint*j + "\n");
            	}
            }
        }
    }
}