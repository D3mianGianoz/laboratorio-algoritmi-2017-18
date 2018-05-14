package priorityqueue;

import com.sun.org.apache.xpath.internal.functions.FuncSubstringBefore;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class PriorityQueueTests
{
    Element<Integer> e;
    PriorityQueue pq;
    @Before
    public void createQueue() {
        pq = new PriorityQueue();
        pq.insert(new Element<Integer>(3,4));
        pq.insert(new Element<Integer>(2,10));
        pq.insert(new Element<Integer>(6,2));
        pq.insert(new Element<Integer>(1,8));
    }

    @Test
    public void increaseKey_Test() {
        try {
            pq.increaseKey(1,90);
            assertTrue(pq.getMax().getKey() == 90);            
        } catch (PriorityQueueException ex) {
            System.out.println("Error");
        }
    }


    @Test
    public void extractMax_Test() {
        try {
            Element e = pq.extractMax();
            assertTrue(e.getKey() == 6);
        } catch (PriorityQueueException ex) {
            System.out.println("Error");
        }
    }

    @Test
    public void getMax_Test() {        
        Element e = pq.getMax();
        assertTrue(e.getKey() == 6 && e.getKey() == pq.getMax().getKey());
        
    }
}