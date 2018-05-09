package sortusagejava;

import java.util.*;
import java.util.Comparator;

public class LongComparator implements Comparator<Long> {
	@Override
	public int compare(Long i1, Long i2) {
		return i1.compareTo(i2);
	}
}
