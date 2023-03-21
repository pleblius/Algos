package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
		
		LEAPText = new File("src/assign08/LEAP.txt");
		randomWords = new ArrayList<String>();
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
	
	@Test
	void testAll() {
		var testList = cowDict.spellCheck(fileWriter("one.txt", "the cow jumped over the moon"));
		
		assertTrue(testList.isEmpty());
	}
	
	@Test
	void testOneWrong() {
		var testList = cowDict.spellCheck(fileWriter("one.txt", "the cow jumped over the hoon"));
		
		assertTrue(testList.size() == 1);
		assertTrue(testList.get(0).equals("hoon"));
	}
	
	@Test
	void testAllWrong() {
		var testList = cowDict.spellCheck(fileWriter("one.txt", "teh dog jmpd ore tah mon"));
		
		assertTrue(testList.size() == 6);
		var newList = new ArrayList<String>();
		newList.add("teh");
		newList.add("dog");
		newList.add("jmpd");
		newList.add("ore");
		newList.add("tah");
		newList.add("mon");
		
		for (int i = 0; i < newList.size(); i++) {
			assertEquals(newList.get(i), testList.get(i), "Failed at index " + i);
		}
	}
	
	@Test
	void randomTestALot() {
		List<String> wordList = new ArrayList<String>();
		try {
		Scanner scanner = new Scanner(new File("src/assign08/dictionary.txt"));
		
		String str;
		
		while (scanner.hasNext()) {
			str = scanner.next();
			
			if (!wordList.contains(str.toLowerCase())) {
				wordList.add(str.toLowerCase());
			}
		}
		
		Collections.shuffle(wordList);
		
		for (int i = 0; i < wordList.size(); i++) {
			randomWords.add(wordList.get(i));
		}
		
		var testList = dictionary.spellCheck(randomWords);
		
		System.out.println(testList);
		assertTrue(testList.isEmpty());
		
		}
		catch(IOException e) {}
	}
	
	@Test
	void oneWordDictionaryTest() {
		var asdf = new ArrayList<String>();
		asdf.add("a");
		
		var sc = new SpellChecker(asdf);
		
		var testList = sc.spellCheck(fileWriter("one.txt", "the cow jumped over the hoon"));
		assertEquals(6, testList.size());
	}
	
	@Test
	void emptyDictionaryTest() {
		var asdf = new ArrayList<String>();
		
		var sc = new SpellChecker(asdf);
		
		var testList = sc.spellCheck(fileWriter("one.txt", "the cow jumped over the hoon"));
		assertEquals(6, testList.size());
	}
	
	@Test
	void twoDictionaryTest() {
		var asdf = new ArrayList<String>();
		asdf.add("A");
		asdf.add("a");
		
		var sc = new SpellChecker(asdf);
		
		var testList = sc.spellCheck(fileWriter("one.txt", "a a a a a a"));
		
		for (int i = 0; i < testList.size(); i++) {
			assertTrue(testList.isEmpty());
		}
	}
	
	@Test
	void threeDictionaryTest() {
		var asdf = new ArrayList<String>();
		asdf.add("a");
		asdf.add("b");
		asdf.add("c");
		
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(asdf);
			var sc = new SpellChecker(asdf);
			var testList = sc.spellCheck(new File("src/assign08/words.txt"));
			assertTrue(testList.size() > 0);
		}
	}
	
	@Test
	void fuckIt() {
		var asdf = new ArrayList<String>();
		var asdf2 = new ArrayList<String>();
		var asdf3 = new ArrayList<String>();
		var asdf4 = new ArrayList<String>();
		
		List<String> testList = new ArrayList<String>();
		List<String> testList2 = new ArrayList<String>();
		List<String> testList3 = new ArrayList<String>();
		
		SpellChecker checker;
		
		try (Scanner scanner = new Scanner(new File("src/assign08/dictionary.txt"))) {
			while(scanner.hasNext())
				asdf.add(scanner.next());
		}
		catch (IOException e) {}
		
		Collections.shuffle(asdf);
		asdf4.addAll(asdf);
		Collections.shuffle(asdf4);
		
		for (int i = 0; i < asdf.size(); i++) {			
			testList.clear();
			testList2.clear();
			testList3.clear();
			
			asdf2.add(asdf.get(i));
			
			String str = new String();
			String str2 = new String();
			for (int j = 0; j < 10; j++) {
				Integer rand = (int)(Math.random()*56) + 65;
				if (rand > 90 && rand < 97) continue;
				str += (char)((int) rand);
			}
			
			asdf3.add(str);
			
			Collections.shuffle(asdf2);
			
			checker = new SpellChecker(asdf2);
			
			testList = checker.spellCheck(asdf2);
			testList2 = checker.spellCheck(asdf3);
			testList3 = checker.spellCheck(asdf4);
			
			assertEquals(0, testList.size(), "Failed at index " + i);
			assertEquals(i + 1, testList2.size(), "Failed at index " + i);
		}
		
		assertEquals(0, testList3.size());
	}
	
	@Test
	void fsfaTest() {
		var testList = new ArrayList<String>();
		List<String> testList2;
		List<String> testList3 = new ArrayList<String>();
		List<String> testList4;
		
		testList3.add("asdf");
		
		String str;
		
		for (int i = 0; i < 10_000_000; i++) {
			str = new String();
			
			for (int j = 0; j < 10; j++) {
				Integer rand = (int)(Math.random()*56) + 65;
				if (rand > 90 && rand < 97) continue;
				str += (char)((int) rand);
			}
			
			testList.add(str);
		}
		
		List<String> wordList = new ArrayList<String>();
		try {
			Scanner scanner = new Scanner(new File("src/assign08/dictionary.txt"));
			
			str = new String();
			
			while (scanner.hasNext()) {
				str = scanner.next();
				
				if (!wordList.contains(str.toLowerCase())) {
					wordList.add(str.toLowerCase());
				}
			}
		}
		catch(IOException e) {}
		
		var checker = new SpellChecker(testList);
		
		testList2 = checker.spellCheck(wordList);
		
		var checker2 = new SpellChecker(testList3);
		
		testList4 = checker2.spellCheck(testList);
		
		assertTrue(testList2.size() > 2600);
		assertEquals(testList.size(), testList4.size());
	}
}
