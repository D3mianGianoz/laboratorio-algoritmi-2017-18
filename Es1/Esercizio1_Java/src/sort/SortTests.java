package sort;

import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * @author Costamagna Alberto e Gianotti Damiano
 */
public class SortTests {
    private Integer i1, i2, i3;
    private Sort<Integer> sort;
    ArrayList<Integer> list;

    @Before
    public void createArray() {
        i1 = 5;
        i2 = 3;
        i3 = -8;
        list = new ArrayList<>();
        sort = new Sort<>();
    }

    @Test
    public void testInsSort_threeEl() {
        try {

            Integer[] test = { i3, i2, i1 };
            Integer[] lVett = new Integer[3];
            list.add(i1);
            list.add(i2);
            list.add(i3);
            sort.insertionSort(list, new IntegerComparator());

            for (int i = 0; i < 3; i++)
                lVett[i] = list.get(i);

            assertArrayEquals(test, lVett);
        } catch (SortException ex) {
            System.out.println("Error");
        }
    }

    @Test
    public void testInsSort_twoEl() {
        try {

            Integer[] test = { i3, i2 };
            Integer[] lVett = new Integer[2];
            list.add(i2);
            list.add(i3);
            sort.insertionSort(list, new IntegerComparator());

            for (int i = 0; i < 2; i++)
                lVett[i] = list.get(i);

            assertArrayEquals(test, lVett);
        } catch (SortException ex) {
            System.out.println("Error");
        }
    }

    @Test
    public void testInsSort_oneEl() {
        try {
            Integer[] test = { i3 };
            Integer[] lVett = new Integer[1];
            list.add(i3);
            sort.insertionSort(list, new IntegerComparator());

            for (int i = 0; i < 1; i++)
                lVett[i] = list.get(i);

            assertArrayEquals(test, lVett);
        } catch (SortException ex) {
            System.out.println("Error");
        }
    }

    @Test
    public void testInsSort_zeroEl() {
        try {
            Integer[] test = {};
            Integer[] lVett = new Integer[list.size()];
            sort.insertionSort(list, new IntegerComparator());

            for (int i = 0; i < list.size(); i++)
                lVett[i] = list.get(i);

            assertArrayEquals(test, lVett);
        } catch (SortException ex) {
            System.out.println("Error");
        }
    }

    @Test
    public void testMergSort_threeEl() {
        try {
            Integer[] test = { i3, i2, i1 };
            Integer[] lVett = new Integer[3];
            list.add(i1);
            list.add(i2);
            list.add(i3);
            sort.mergeSort(list, 0, list.size() - 1, new IntegerComparator());

            for (int i = 0; i < 3; i++)
                lVett[i] = list.get(i);

            assertArrayEquals(test, lVett);
        } catch (SortException ex) {
            System.out.println("Error");
        }
    }

    @Test
    public void testMergSort_twoEl() {
        try {
            Integer[] test = { i3, i2 };
            Integer[] lVett = new Integer[2];
            list.add(i2);
            list.add(i3);
            sort.mergeSort(list, 0, list.size() - 1, new IntegerComparator());

            for (int i = 0; i < 2; i++)
                lVett[i] = list.get(i);

            assertArrayEquals(test, lVett);
        } catch (SortException ex) {
            System.out.println("Error");
        }
    }

    @Test
    public void testMergSort_oneEl() {
        try {
            Integer[] test = { i3 };
            Integer[] lVett = new Integer[1];
            list.add(i3);
            sort.mergeSort(list, 0, list.size() - 1, new IntegerComparator());

            for (int i = 0; i < 1; i++)
                lVett[i] = list.get(i);

            assertArrayEquals(test, lVett);
        } catch (SortException ex) {
            System.out.println("Error");
        }
    }

    @Test
    public void testMergSort_empty() {
        try {
            Integer[] test = {};
            Integer[] lVett = new Integer[list.size()];

            sort.mergeSort(list, 0, list.size() - 1, new IntegerComparator());

            for (int i = 0; i < list.size(); i++)
                lVett[i] = list.get(i);

            assertArrayEquals(test, lVett);
        } catch (SortException ex) {
            System.out.println("Error");
        }
    }


    @Test
    public void testSumInVett_Empty() 
    { 
        try {
          ArrayList<Long>list = new ArrayList<Long>();
          assertFalse(sort.isSumContained(list,(long)40));
        } catch (SortException ex) {
            System.out.println("Error");
        }
    
    }

    @Test
    public void testSumInVett_OneElem() 
    { 
        try {
          ArrayList<Long>list = new ArrayList<Long>();
          list.add((long)4);
          assertFalse(sort.isSumContained(list,(long)40));
        } catch (SortException ex) {
            System.out.println("Error");
        }
    
    }

    @Test
    public void testSumInVett_Correct() 
    { 
        try {
          ArrayList<Long>list = new ArrayList<Long>();
          list.add((long)4);
          list.add((long)1);
          list.add((long)7);
          list.add((long)5);
          assertTrue(sort.isSumContained(list,(long)9));
        } catch (SortException ex) {
            System.out.println("Error");
        }
    
    }

}
