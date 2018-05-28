package heap;

public class Test
{
    public static void main(String[] args)
    {
        try{

            Heap<Integer,Integer> pq = new Heap<Integer,Integer>(new IntegerComparator());

            Element<Integer,Integer> e = new Element<Integer,Integer>(5,9);
            pq.insert(e);
            pq.insert(new Element<Integer,Integer>(2,10));
            pq.insert(new Element<Integer,Integer>(3,4));
            pq.insert(new Element<Integer,Integer>(9,1));

            Element<Integer,Integer> e4 = pq.getMax();
            System.out.println("MAX: "+e4.toString());

            pq.printList();

            Element<Integer,Integer> e2 = pq.extractMax();

            System.out.println("DOPO");
            pq.printList();

            //pq.increaseKey(3,8);

            Element<Integer,Integer> e3 = pq.extractMax();

            System.out.println("DOPO");
            pq.printList();

            

        } catch(HeapException ex)
        {
            System.out.println(ex);
        }
    }
}