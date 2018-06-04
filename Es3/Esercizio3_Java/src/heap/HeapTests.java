package heap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author : Alberto Costamagna, Damiano Gianotti
 */
public class HeapTests
{
    Element<Integer,Integer> e;
    Heap pq;
    
    @Before
    public void createQueue() {
        pq = new Heap<Integer,Integer>(new IntegerComparator());
        pq.insert(new Element<Integer,Integer>(3,4));
        pq.insert(new Element<Integer,Integer>(2,10));
        pq.insert(new Element<Integer,Integer>(6,2));
        pq.insert(new Element<Integer,Integer>(1,8));
    }

    @Test
    public void increaseKey_Test() {
        try {
            pq.increaseKey(1,90);
            assertTrue((Integer)pq.getMax().getKey() == 90);            
        } catch (HeapException ex) {
            System.out.println("Error");
        }
    }


    @Test
    public void extractMax_Test() {
        try {
            Element e = pq.extractMax();
            assertTrue((Integer)e.getKey() == 6);
        } catch (HeapException ex) {
            System.out.println("Error");
        }
    }

    @Test
    public void getMax_Test() {        
        Element e = pq.getMax();
        assertTrue((Integer)e.getKey() == 6 && e.getKey() == pq.getMax().getKey());
        
    }
}