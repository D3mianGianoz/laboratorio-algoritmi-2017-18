package graph;

import java.util.*;
import java.util.Comparator;

public class DoubleComparator implements Comparator<Double> {
	@Override
	public int compare(Double i1, Double i2) {
		return i1.compareTo(i2);
	}
}