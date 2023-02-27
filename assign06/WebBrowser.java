package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowser {
	
	private ArrayStack<URL> forStack;
	private ArrayStack<URL> backStack;
	private URL currentPage;
	
	public WebBrowser() {
		forStack = new ArrayStack<URL>();
		backStack = new ArrayStack<URL>();
	}
	
	public WebBrowser(SinglyLinkedList<URL> history) {
		forStack = new ArrayStack<URL>();
		
		URL[] tempArr = (URL[]) history.toArray();
		for (int ii = tempArr.length - 1; ii > 0; ii--) {
			backStack.push(tempArr[ii]);
		}
		
		currentPage = tempArr[0];
	}
	
	public void visit(URL webpage) {
		backStack.push(currentPage);
		forStack.clear();
		currentPage = webpage;
	}
	
	public URL back() throws NoSuchElementException {
		if (backStack.isEmpty()) {
			throw new NoSuchElementException("Nowhere to go back to...");
		}
		
		currentPage = backStack.pop();
		return currentPage;
	}
	
	public URL forward() throws NoSuchElementException {
		if (forStack.isEmpty()) {
			throw new NoSuchElementException("Nowhere to go...");
		}
		
		backStack.push(currentPage);
		currentPage = forStack.pop();
		return currentPage;
	}
	
	public SinglyLinkedList<URL> history() {
		
	}
}
