package random;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lists {

	public static void main(String[] args) {
        int q, n;
        Scanner input = new Scanner(System.in);
        List<Integer> L = new ArrayList<Integer>();
        
        n = input.nextInt();
        input.nextLine();
        for (int i = 0; i < n; i++) {
            L.add(input.nextInt());
        }
        input.nextLine();
        q = input.nextInt();
        input.nextLine();
        for (int i = 0; i < q; i++) {
            if (input.nextLine().equals("Insert")) {
                L.add(input.nextInt(), input.nextInt());
            }
            else {
                L.remove(input.nextInt());
            }
            if (i < q -1) input.nextLine();
        }
        System.out.println(L);
	}

}
