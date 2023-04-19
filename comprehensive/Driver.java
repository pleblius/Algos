package comprehensive;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		if (args.length == 0)
			throw new IllegalArgumentException("Needs a filename");
		
		try(Scanner scanner = new Scanner(new File(args[0]))) {
			
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
