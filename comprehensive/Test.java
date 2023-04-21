package comprehensive;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Test {

	public static void main(String[] args) {
		try (FileWriter writer = new FileWriter(("test.txt")))
		{
			PrintWriter pw = new PrintWriter(writer);
			pw.flush();
		for (int i = 0; i < 1000000; i++) {
			pw.println("a" + i);
		}
		pw.println();
		
		for (int i = 0; i < 499999; i++) {
			if (i%10000 == 0)
				continue;
			pw.println("a" + i + " " + "a" + (i + 1));
		}
		
		for (int i = 500000; i < 999999; i++) {
			if (i%10000 == 0)
				continue;
			pw.println("a" + i + " " + "a" + (i + 1));
		}
		
		for (int i = 0; i < 999999; i++) {
			if (i%10000 == 0 && i%100000 != 0)
				pw.println("a" + i + " " + "a" + (i + 1));
		}
		
//		for (int i = 0; i < 999999; i++) {
//			if (i%100000 == 0)
//				pw.println("a" + i + " " + "a" + (i + 1));
//		}
//		pw.println("a400000 a900000");
		
		pw.println();
		
		pw.print("a1" + " " + "a200000");
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
