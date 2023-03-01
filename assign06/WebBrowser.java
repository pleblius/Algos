package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

/**
 * This class represents a web browser for the navigation of web pages.
 * 
 * Users are allowed to visit new webpages, and it maintains the user's browsing 
 * history, allowing them to traverse backwards and forward to previously visited
 * webpages.
 * 
 * @author Tyler Wilcox and Andrew Tolton.
 * @version 28 February, 2023.
 */
public class WebBrowser {
	
	private ArrayStack<URL> forStack;
	private ArrayStack<URL> backStack;
	private URL currentPage;
	
	/**
	 * Generates a new web browser object with an empty browsing history.
	 */
	public WebBrowser() {
		forStack = new ArrayStack<URL>();
		backStack = new ArrayStack<URL>();
	}
	
	/**
	 * Generates a new web browser object, with the provided history list
	 * loaded into the browser's back stack.
	 * 
	 * @param history - the user's browsing history to be stored in this browser instance.
	 */
	public WebBrowser(SinglyLinkedList<URL> history) {
		forStack = new ArrayStack<URL>();
		backStack = new ArrayStack<URL>();
		
		var tempArr = history.toArray();
		for (int ii = tempArr.length - 1; ii > 0; ii--) {
			backStack.push((URL) tempArr[ii]);
		}
		
		currentPage = (URL) tempArr[0];
	}
	
	/**
	 * Sends the user to the provided webpage.
	 * Adds the user's current page to the history stack and clears the forward stack.
	 * 
	 * @param webpage - the new webpage to be visited.
	 */
	public void visit(URL webpage) {
		backStack.push(currentPage);
		forStack.clear();
		currentPage = webpage;
	}
	
	/**
	 * Sends the user back to the most recent webpage in their backwards history stack.
	 * This corresponds to the most recent webpage that they called visit() from.
	 * Throws an exception if there are no back webpages to access.
	 * 
	 * @return the webpage that the user is going back to.
	 * @throws NoSuchElementException - If there are no back webpages.
	 */
	public URL back() throws NoSuchElementException {
		if (backStack.isEmpty()) {
			throw new NoSuchElementException("Nowhere to go back to...");
		}
		
		forStack.push(currentPage);
		currentPage = backStack.pop();
		
		return currentPage;
	}
	
	/**
	 * Sends the user forward to the most recent webpage in their forward history stack.
	 * This corresponds to the most recent webpage that they called back() from.
	 * Throws an exception if there are no forward webpages to access.
	 * 
	 * @return the webpage that the user is going forward to.
	 * @throws NoSuchElementException - If there are no forward webpages.
	 */
	public URL forward() throws NoSuchElementException {
		if (forStack.isEmpty()) {
			throw new NoSuchElementException("Nowhere to go...");
		}
		
		backStack.push(currentPage);
		currentPage = forStack.pop();
		
		return currentPage;
	}
	
	/**
	 * Generates a SinglyLinkedList of type <URL> containing the user's browsing history.
	 * This history corresponds to the stack of "back" webpages the user could return to,
	 * in order from the most recently visited to the least recently visited, with the
	 * user's currently visited page as the most recently viewed page at the front of the list
	 * (At index 0).
	 * 
	 * This runs in O(N) time.
	 * 
	 * @return a list containing the user's browsing history.
	 */
	public SinglyLinkedList<URL> history() {
		URL[] arr = new URL[backStack.size() + 1];
		var list = new SinglyLinkedList<URL>();
		
		arr[arr.length - 1] = currentPage;
		
		for (int i = arr.length - 2; i >= 0; i--) {
			arr[i] = backStack.pop();
		}
		
		for (int i = 0; i < arr.length - 1; i++) {
			list.insertFirst(arr[i]);
			backStack.push(arr[i]);
		}
		
		list.insertFirst(currentPage);
		return list;
	}
}
