package sortusagejava;

import java.util.Comparator;

/**
 * @author Costamagna Alberto e Gianotti Damiano
 */
public class LongComparator implements Comparator<Long> {
	@Override
	public int compare(Long i1, Long i2) {
		return i1.compareTo(i2);
	}
}
