package lab11;

import java.util.Comparator;

public class ReverseComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer arg1, Integer arg2) {
		return arg2 - arg1;
	}

}
