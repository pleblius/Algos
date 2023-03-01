package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WebBrowserTest {
	URL url1;
	URL url2;
	URL url3;
	URL url4;
	SinglyLinkedList<URL> history;
	WebBrowser testBrowser;
	
	@BeforeEach
	void setup() {
		try {
			url1 = new URL("http://google.com/");
			url2 = new URL("https://www.youtube.com/");
			url3 = new URL("https://www.youtube.com/watch?v=cPJUBQd-PNM&t=78s");
			url4 = new URL("https://www.twitch.tv/");
		}
		catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		}
		
		testBrowser = new WebBrowser();
	}
	
	/*
	 * Constructor Test
	 */
	
	@Test
	void testParameterizedConstructor() {
		var list = new SinglyLinkedList<URL>();
		list.insertFirst(url2);
		list.insertFirst(url1);
		
		testBrowser = new WebBrowser(list);
		
		for (int i = 0; i < list.size(); i++) {
			assertTrue(list.get(i).toString().equals(testBrowser.history().get(i).toString()));
		}
	}
	
	/*
	 * Visit Test
	 */
	@Test
	void testVisitAddsToBackStack() {
		var list = new SinglyLinkedList<URL>();
		list.insertFirst(url2);
		list.insertFirst(url1);
		
		testBrowser = new WebBrowser(list);
		
		testBrowser.visit(url3);
		list.insertFirst(url3);
		
		for (int i = 0; i < list.size(); i++) {
			assertTrue(list.get(i).toString().equals(testBrowser.history().get(i).toString()));
		}
	}
	
	@Test
	void testBackVisit2() {
		var list = new SinglyLinkedList<URL>();
		list.insertFirst(url4);
		list.insertFirst(url3);
		list.insertFirst(url2);
		list.insertFirst(url1);
		
		testBrowser = new WebBrowser(list);
		
		testBrowser.back();
		testBrowser.back();
		testBrowser.visit(url4);
		
		list = new SinglyLinkedList<URL>();
		
		list.insertFirst(url4);
		list.insertFirst(url3);
		list.insertFirst(url4);
		
		for (int i = 0; i < list.size(); i++) {
			assertTrue(list.get(i).toString().equals(testBrowser.history().toString()));
		}
	}
	
	@Test
	void testVisitClearsForward() {
		var list = new SinglyLinkedList<URL>();
		list.insertFirst(url4);
		list.insertFirst(url3);
		list.insertFirst(url2);
		list.insertFirst(url1);
		
		testBrowser = new WebBrowser(list);
		
		testBrowser.back();
		testBrowser.back();
		testBrowser.visit(url4);
		
		assertThrows(NoSuchElementException.class, () -> {testBrowser.forward();});
	}
	
	@Test
	void webBrowserHistory() {
		testBrowser.visit(url1);
		testBrowser.visit(url2);
		testBrowser.visit(url3);
		testBrowser.visit(url4);
		
		
		var testHistory = new SinglyLinkedList<URL>();
		testHistory.insertFirst(url1);
		testHistory.insertFirst(url2);
		testHistory.insertFirst(url3);
		testHistory.insertFirst(url4);
		
		history = testBrowser.history();
		
		for (int i = 0; i < 4; i++) {
			assertTrue(testHistory.get(i).toString().equals(history.get(i).toString()));
		}
	}
	
	@Test
	void testHistoryCreation() {
		testBrowser.visit(url1);
		testBrowser.visit(url2);
		testBrowser.visit(url3);
		
		
		var testHistory = new SinglyLinkedList<URL>();
		testHistory.insertFirst(url1);
		testHistory.insertFirst(url2);
		testHistory.insertFirst(url3);
		
	
		
		for (int i = 0; i < 3; i++) {
			assertTrue(testHistory.get(i).toString().equals(testBrowser.history().get(i).toString()));
		}
		
		testBrowser.visit(url4);
		testHistory.insertFirst(url4);
		
		for (int i = 0; i < 4; i++) {
			assertTrue(testHistory.get(i).toString().equals(testBrowser.history().get(i).toString()));
		}
	}

}
