package priorityqueue;

import java.util.*;


/**
 * @author : Alberto Costamagna, Damiano Gianotti
 */
public class PriorityQueue
{
    private ArrayList<Element> list;
    private int nElem; 

    /**
     * Constructor of the PriorityQueue Object
     */
    public PriorityQueue()
    {
        list = new ArrayList<Element>();
        nElem = 0;
    }

    /**
     * Method that allows to insert a new element in the queue
     * @param elem : the new element to insert
     */
    public void insert(Element elem)
    {
        nElem++;
        Element e = new Element(Double.MIN_VALUE,elem.getValue());
        list.add(e);
        try{
            increaseKey(nElem,elem.getKey());    
        } catch(PriorityQueueException ex)
        {
            ex.getMessage();
            ex.printStackTrace();
        }
        
    }

    /**
     * Method that allows to set a new value of the key of the selected element
     * @param i: the index of the selected element
     * @param k: the new value of the key
     */
    public void increaseKey(int i,double k) throws PriorityQueueException
    {
        i--;
        if (k < list.get(i).getKey())
            throw new PriorityQueueException("Key is less than the current key");
        list.get(i).setKey(k);
        while(i > 0 && list.get(parent(i)).getKey() < list.get(i).getKey())
        {
            swap(i,parent(i));
            i = parent(i);
        }
    }

    /**
     * @return: the top element of the queue by dequeue it
     */
    public Element extractMax() throws PriorityQueueException
    {
        if (nElem < 1)
            throw new PriorityQueueException("Error empty list");
        Element max = getMax();
        list.set(0, list.get(--nElem));
        maxHeapfy(0);

        return max;
    }

    /**
     * Put on the top the element with the hightes value of key
     * @param i: the element  TODO
     */
    private void maxHeapfy(int i)
    {
        int l = left(i);
        int r = right(i);
        int massimo;

        if (l <= nElem && list.get(l).getKey() > list.get(i).getKey())
            massimo = l;
        else
            massimo = r;

        if (r <= nElem && list.get(r).getKey() > list.get(massimo).getKey())
            massimo = r;

        if (massimo != i)
        {
            swap(i,massimo);
            maxHeapfy(massimo);
        }
    }

    /**
     * @return: the max element of the queue keeping it queued
     */
    public Element getMax()
    {
        return list.get(0);
    }

    /**
     * Swap the two element of the list
     * @param i,j: the two index
     */
    private void swap(int i,int j)
    {
        Element temp;
        temp = list.get(i);
        list.set(i,list.get(j));
        list.set(j,temp);
    }

    /**
     * @return: the parent index of the given index
     */
    private int parent(int i)
    {
        if (i > 0 && i <= nElem)
            return i/2;
        return 0;
    }

    /**
     * @return: the left index of the given index
     */
    private int left(int i)
    {
        if (2*i <= nElem)
            return 2*i;
        else
            return i;
    }

    /**
     * @return: the right index of the given index
     */
    private int right(int i)
    {
        if ((2*i)+1 <= nElem)
            return (2*i)+1;
        else
            return i;      
    }

    /**
     * Print the elements of the queue
     */
    public void printList()
    {
        for (int i=0;i< nElem ;i++)
            System.out.println(list.get(i).toString());
    }

}