package priorityqueue;

import java.util.*;
import java.io.IOException;


/**
 * @author : Alberto Costamagna, Damiano Gianotti
 */
public class PriorityQueue <T>
{
    private ArrayList<Element<T>> list;
    private int nElem; 

    /**
     * Constructor of the PriorityQueue class
     */
    public PriorityQueue()
    {
        list = new ArrayList<Element<T>>();
        nElem = 0;
    }

    public int getnElem()
    { return this.nElem; }

    /**
     * Method that allows to insert a new element in the queue
     * @param elem : the new element to insert
     */
    public void insert(Element<T> elem)
    {
        nElem++;
        Element<T> e = new Element<T>(Double.MIN_VALUE,elem.getValue(),elem.getName());
        list.add(e);
        try{
            changeKey(nElem,elem.getKey());    
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
    public void changeKey(int i,double k) throws PriorityQueueException
    {
        i--;
        list.get(i).setKey(k);
        while(i > 0 && list.get(parent(i)).getKey() > list.get(i).getKey())
        {
            swap(i,parent(i));
            i = parent(i);
        }
    }

    public void changeKeyParent(int i,double k,T parent) throws PriorityQueueException
    {
        list.get(i).setKey(k);
        list.get(i).setValue(parent);
        while(i > 0 && list.get(parent(i)).getKey() > list.get(i).getKey())
        {
            swap(i,parent(i));
            i = parent(i);
        }
    }

    /**
     * @return: the top element of the queue by dequeue it
     */
    public Element<T> extractMin() throws PriorityQueueException
    {
        if (nElem < 1)
            throw new PriorityQueueException("Error empty list");
        Element<T> max = getMin();
        list.set(0,list.get(--nElem));
        minHeapfy(0);

        return max;
    }

    /**
     * Put on the top the element with the hightes value of key
     */
    private void minHeapfy(int i)
    {
        int l = left(i);
        int r = right(i);
        int massimo;

        if (l<= nElem && list.get(l).getKey() < list.get(i).getKey())
            massimo = l;
        else
            massimo = r;

        if (r <= nElem && list.get(r).getKey() < list.get(massimo).getKey())
            massimo = r;

        if (massimo != i)
        {
            swap(i,massimo);
            minHeapfy(massimo);
        }
    }

    /**
     * @return: the max element of the queue keeping it queued
     */
    public Element<T> getMin()
    {
        return list.get(0);
    }

    /**
     * Swap the two element of the list
     * @param i,j: the two index
     */
    private void swap(int i,int j)
    {
        Element<T> temp;
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

    public boolean containElement(Element<T> e)
    {
        return list.contains(e);
    }

    public Element<T> getElem(Object x)
    {
        for(Element<T> e : list)
        {
            if (e.getName() == x)
                return e;
        }
        return null;
    }

    public int getPosElem(Object x)
    {
        int i = 0;
        for(Element<T> e: list)
        {
            if(e.getName() == x)
                return i;
            i++;
        }

        return -1;
    }

}