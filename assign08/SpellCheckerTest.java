package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpellCheckerTest {
	
	private SpellChecker cowDict;
	private SpellChecker dictionary;
	
	private File LEAPText;
	private List<String> randomWords;
	
	private String cowSentences;

	@BeforeEach
	void setup() {
		
		cowDict = new SpellChecker(new File("src/assign08/cowDict.txt"));
		dictionary = new SpellChecker(new File("src/assign08/dictionary.txt"));
		
		LEAPText = new File("src/assig08/LEAP.txt");		
	}
	
	File fileWriter(String filename, List<String> words) {
		try (FileWriter writer = new FileWriter((filename)))
        {
            PrintWriter pw = new PrintWriter(writer);
            for (int i = 0; i < words.size(); i++) {
                pw.println(words.get(i));
            }

        }
		catch (IOException e) {
            System.err.println(e.getMessage());
        }
		
		return new File(filename);
	}
	
	File fileWriter(String filename, String sentence) {
		
		List<String> words = new ArrayList<String>();
		Scanner reader = new Scanner(sentence);
		while(reader.hasNext()) {
			words.add(reader.next());
		}
		
		
		try (FileWriter writer = new FileWriter((filename)))
        {
            PrintWriter pw = new PrintWriter(writer);
            for (int i = 0; i < words.size(); i++) {
                pw.println(words.get(i));
            }

        }
		catch (IOException e) {
            System.err.println(e.getMessage());
        }
		
		return new File(filename);
	}
	
	@Test
	void testEmpty() {
		var testList = cowDict.spellCheck(fileWriter("empty.txt", ""));
		
		assertTrue(testList.isEmpty());
	}
	
	@Test
	void testOne() {
		var testList = cowDict.spellCheck(fileWriter("one.txt", "cow"));
		
		assertTrue(testList.isEmpty());
	}
}
