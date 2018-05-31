package heap;

import java.util.*;
import java.io.IOException;


/**
 * @author : Alberto Costamagna, Damiano Gianotti
 * @param K,T: Type of the elment 
 */
public class Heap <K,T>
{
    private ArrayList<Element<K,T>> list;
    private int nElem; 
    private Comparator<K> comp;

    /**
     * Constructor of the Heap class
     */
    public Heap(Comparator<K> c)
    {
        list = new ArrayList<Element<K,T>>();
        nElem = 0;
        this.comp = c;
    }

    public int getnElem()
    { return this.nElem; }

    /**
     * Method that allows to insert a new element in the queue
     * @param elem : the new element to insert
     */
    public void insert(Element<K,T> elem)
    {
        nElem++;
        list.add(elem);
        try{
            increaseKey(nElem,elem.getKey());    
        } catch(HeapException ex)
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
    public void increaseKey(int i,K k) throws HeapException
    {
        i--;
        if (comp.compare(k, list.get(i).getKey()) < 0)
            throw new HeapException("Key is less than the current key");
        list.get(i).setKey(k);
        while(i > 0 &&comp.compare(list.get(parent(i)).getKey(),list.get(i).getKey()) < 0)
        {
            swap(i,parent(i));
            i = parent(i);
        }
    }


    /**
     * @return: the top element of the queue by dequeue it
     */
    public Element<K,T> extractMax() throws HeapException
    {
        if (nElem < 1)
            throw new HeapException("Error empty list");
        Element<K,T> max = getMax();
        list.set(0,list.get(--nElem));
        maxHeapfy(0);

        return max;
    }

    /**
     * Put on the top the element with the hightes value of key
     */
    private void maxHeapfy(int i)
    {
        int l = left(i);
        int r = right(i);
        int massimo;

        if (l<= nElem && comp.compare(list.get(l).getKey(),list.get(i).getKey()) > 0)
            massimo = l;
        else
            massimo = r;

        if (r <= nElem &&comp.compare(list.get(r).getKey(),list.get(massimo).getKey()) > 0)
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
    public Element<K,T> getMax()
    {
        return list.get(0);
    }

    /**
     * Swap the two element of the list
     * @param i,j: the two index
     */
    private void swap(int i,int j)
    {
        Element<K,T> temp;
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