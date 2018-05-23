package priorityqueue;

public class Test
{
    public static void main(String[] args)
    {
        try{

            PriorityQueue pq = new PriorityQueue();

            Element<Integer> e = new Element<Integer>(5,9);
            pq.insert(e);
            pq.insert(new Element<Integer>(2,10));
            pq.insert(new Element<Integer>(3,4));
            pq.insert(new Element<Integer>(9,1));

            Element e4 = pq.getMin();
            System.out.println("MAX: "+e4.toString());

            pq.printList();

            Element e2 = pq.extractMin();

            System.out.println("DOPO");
            pq.printList();

            //pq.increaseKey(3,8);

            Element e3 = pq.extractMin();

            System.out.println("DOPO");
            pq.printList();

            

        } catch(PriorityQueueException ex)
        {
            System.out.println(ex);
        }
        

    }
}