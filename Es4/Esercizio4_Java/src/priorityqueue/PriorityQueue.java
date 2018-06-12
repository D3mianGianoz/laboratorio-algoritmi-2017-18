package priorityqueue;

import java.util.*;
import java.io.IOException;


/**
 * @author : Alberto Costamagna, Damiano Gianotti
 * @param T: Type of the elment of the priorityQueue
 */
public class PriorityQueue<T>
{
    private ArrayList<Element<T>> list;

    /**
     * Constructor of the PriorityQueue class
     */
    public PriorityQueue()
    {
        list = new ArrayList<Element<T>>();
    }

    public int getnElem()
    {return this.list.size();}

    /**
     * Method that allows to insert a new element in the queue
     * @param elem : the new element to insert
     */
    public void insert(Element<T> elem)
    {
        list.add(elem);
        int i = list.size()-1;
        int parent = parent(i);

        while(parent != i && list.get(i).getKey() < list.get(parent).getKey())
        {
            swap(i,parent);
            i = parent;
            parent = parent(i);
        }
        
    }

    /**
     * Method that allows to set a new value of the key of the selected element
     * @param i: the index of the selected element
     * @param k: the new value of the key
     */
    public void decreaseKey(int i,double k) throws PriorityQueueException
    {
        i--;
        if (list.get(i).getKey() < k)
            throw new PriorityQueueException("Key not valid");

        list.get(i).setKey(k);
        int parent = parent(i);

        while(i > 0 && list.get(parent).getKey() > list.get(i).getKey())
        {
            swap(i,parent);
            i = parent;
            parent = parent(parent);
        }
    }

    /**
     * Method that allows to set a new value of the key of the selected element and change the value of the node to save the parent of the node
     * @param i: the index of the selected element
     * @param k: the new value of the key
     * @param p: the new value of the value 
     */
    public void decreaseKeyParent(int i,double k,T p) throws PriorityQueueException
    {
        if (list.get(i).getKey() < k)
            throw new PriorityQueueException("Key not valid");

        list.get(i).setKey(k);
        list.get(i).setValue(p);
        int parent = parent(i);

        while(i > 0 && list.get(parent).getKey() > list.get(i).getKey())
        {
            swap(i,parent);
            i = parent;
            parent = parent(parent);
        }
    }

    /**
     * @return: the top element of the queue by dequeue it
     */
    public Element<T> extractMin() throws PriorityQueueException
    {
        if (list.size() == 0) 
            throw new PriorityQueueException("MinHeap is EMPTY");

        if (list.size() == 1){
            Element<T> min = list.remove(0);
            return min;
        }

        // remove the last item ,and set it as new root
        Element<T> min = list.get(0);
        Element<T> lastItem = list.remove(list.size() - 1);
        list.set(0, lastItem);

        // bubble-down until heap property is maintained
        minHeapify(0);

        // return min key
        return min;
    }

    /**
     * Put on the top the element with the hightes value of key
     * @param i: index of the element
     */
    private void minHeapify(int i)
    {
        int l = left(i);
        int r = right(i);
        int min;

        if (l<= list.size()-1 && list.get(l).getKey() < list.get(i).getKey())
            min = l;
        else
            min = i;

        if (r <= list.size()-1 && list.get(r).getKey() < list.get(min).getKey())
            min = r;

        if (min != i)
        {
            swap(i,min);
            minHeapify(min);
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
     * @param i,j: the two index of the corrisponding element
     */
    private void swap(int i,int j)
    {
        Element<T> temp;
        temp = list.get(i);
        list.set(i,list.get(j));
        list.set(j,temp);
    }

    /**
     * @return: the parent index of the element
     * @param i: the index of the element
     */
    private int parent(int i)
    {       
        if (i % 2 == 1) {
            return i / 2;
        }
        return (i - 1) / 2;            
    }

    /**
     * @return: the left index of the given index
     */
    private int left(int i)
    {
        return 2 * i + 1;
        
    }

    /**
     * @return: the right index of the given index
     */
    private int right(int i)
    {
        return 2 * i + 2;     
    }

    /**
     * Print the elements of the queue
     */
    public void printList()
    {
        for (int i=0; i< list.size() ;i++)
            System.out.println(list.get(i).toString());
    }

    /**
     * @return: true if the queue contains the element, false otherwise
     * @param elem: the element to check
     */
    public boolean containElement(Element<T> elem)
    {
        return list.contains(elem);
    }

    /**
     * @return: the element if it is contained in the queue, null otherwise
     * @param elem: the element to check
     */
    public Element<T> getElem(T elem)
    {
        for(Element<T> eTemp : list)
        {
            if (eTemp.getName() == elem)
                return eTemp;
        }
        return null;
    }

    /**
     * @return: the position of the element if it is contained in the queue, -1  otherwise
     * @param elem: the element to check
     */
    public int getPosElem(T elem)
    {
        int i = 0;
        for(Element<T> eTemp: list)
        {
            if(eTemp.getName() == elem)
                return i;
            i++;
        }
        return -1;
    }

}