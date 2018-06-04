package heap;

import java.util.*;
import java.util.Comparator;

/**
 * @author : Alberto Costamagna, Damiano Gianotti
 */
public class IntegerComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer i1, Integer i2) {
		return i1.compareTo(i2);
	}
}
