package graph;

import java.util.*;
import java.util.Comparator;

public class StringComparator implements Comparator<String> {
	@Override
	public int compare(String i1, String i2) {
		return i1.compareTo(i2);
	}
}
