package graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import priorityqueue.*;

public class Graph<T,V>
{
    private int nNode;
    private int nArch;
    private HashMap<T,HashMap<T,V>> adiacentsMap;
    private Comparator<T> comp; //Non usato
    private Comparator<V> comp2; //Non usato
    private boolean isDirect;
    
    public Graph(boolean isDirect,Comparator<T> comparator,Comparator<V> comp2) // A cosa serve il comparator ???
    {
        this.adiacentsMap = new HashMap<T,HashMap<T,V>>();  //non può essere null se no Nullpointer exception
        this.nNode = 0;  
        this.nArch = 0;
        this.isDirect = isDirect;
        this.comp = comparator;
        this.comp2 = comp2;
    }

    public Graph(boolean isDirect)
    {
        this.adiacentsMap = new HashMap<T,HashMap<T,V>>();  //non può essere null se no Nullpointer exception
        this.nNode = 0;  
        this.nArch = 0;
        this.isDirect = isDirect;
    }

    public boolean getIsDirect()
    { return this.isDirect; }

    public int getnNode()
    { return this.nNode; }

    public int getnArch()
    { return this.nArch; }

    public void addNode(T value) throws GraphException
    {
        if(!adiacentsMap.containsKey(value)){
            adiacentsMap.put(value,new HashMap<T,V>());
            nNode++;
        }else
            throw new GraphException("Failed to add Node: "+ value +" it is already part of Graph");
    }

    public void addArch(T from,T to,V label) throws GraphException
    {
        if(adiacentsMap.containsKey(from) && adiacentsMap.containsKey(to)){
            adiacentsMap.get(from).put(to,label);
            if (!isDirect)
                adiacentsMap.get(to).put(from,label);
            nArch++;
        }else
            throw new GraphException("Failed to add Arch: " + from + " or " + to + " don't exist");
    }

    public boolean isEmpty(){
        return adiacentsMap.isEmpty();
    }

    public void removeNode(T value) throws GraphException
    {
        if(adiacentsMap.containsKey(value)){
            adiacentsMap.remove(value);
            for (HashMap<T, V> temp: adiacentsMap.values())
                if (temp.containsKey(value))
                    temp.remove(value);
        }
        else
            throw new GraphException("Failed to remove Node "+ value +" don't exist");
    }

    public void removeArch(T from,T to,V label) throws GraphException
    {
        if(adiacentsMap.containsKey(from) && adiacentsMap.get(from).containsKey(to) && adiacentsMap.get(from).containsValue(label))
        {
            adiacentsMap.get(from).remove(to,label);
            if (!isDirect)
                adiacentsMap.get(to).remove(from,label);
        }
        else
            throw new GraphException("Failed to remove Arch "+from + to+"don't exist");
    }


    public double Weight() throws Error, GraphException
    {
        double result = 0; //Non sono sicuro della bontà di questa soluzione
        if(this.isEmpty() || this == null)
            throw new GraphException("This graph can't be empty or null");
        
        for(HashMap<T, V> temp : adiacentsMap.values()){
            for (Object value : temp.values()) { //Se label degli archi != double fallisce
                if (value instanceof Double)
                    result = result + (double)value;
                else
                    throw new Error("This graph has no Weight because label is: " + value.getClass());
            }
        }

        if(result > 0)
            return isDirect ? result : result / 2;
        else
            return result;
    }

    /*
     * Stampa poco significativa
     *
    public void printGraph() //TODO REMOVE
    {
        System.out.println(adiacentsMap.toString());
    }
    /*

    /**
     * Stampa del grafo piu' chiara 
     */

    public void printGraphDef(){
        for (Map.Entry<T, HashMap <T,V>> adiEntry : adiacentsMap.entrySet()){
            System.out.println("RootNode = " + adiEntry.getKey()+": ");
            HashMap<T, V> tMap = adiEntry.getValue();
                for (Map.Entry<T, V> entry : tMap.entrySet())
                    System.out.println(adiEntry.getKey() + " -> label = " + entry.getValue() + " -> " + entry.getKey());
        }
    }

    public int archiIncidenti(T key) throws GraphException
    {
        if(!adiacentsMap.containsKey(key))
            throw new GraphException("Invalid Node");

        int count = 0;
        for(HashMap<T, V> tmp : adiacentsMap.values())
        {
            for(T obj : tmp.keySet())
                if(obj.equals(key))
                    count++;
        }

        return count;
    }

    public boolean sonoAdiacenti(T nodo1,T nodo2) throws GraphException
    {
        if(adiacentsMap.containsKey(nodo1) && adiacentsMap.containsKey(nodo2))
        {            
            HashMap<T, V> tmp = adiacentsMap.get(nodo1);
            if(tmp.containsKey(nodo2))
                return true;
            else
            {
                tmp = adiacentsMap.get(nodo2);
                if(tmp.containsKey(nodo1))
                    return true;
            }
        }
        else
            throw new GraphException("Invalid Node");
        
        return false;
    }

    public HashMap<T,V> getAdiacent(T node)
    {
        return adiacentsMap.get(node);
    }

    public V getWeightArch(T u,T v)
    {
        return adiacentsMap.get(u).get(v);
    }

    public HashMap prim(T root) throws GraphException, PriorityQueueException
    {
        if (isDirect)
            throw new GraphException("Prim only works with not direct graph");

        HashMap<T,HashMap<T,V>> resultingTree = new HashMap<T,HashMap<T,V>>();

        // Creo la priorityQUeue che mi servirà per tener traccia dei nodi
        PriorityQueue pq = new PriorityQueue();

        // Vado a settare tutti i nodi nella coda di priorità con massima priorità
        initPq(pq,root);
      
        pq.printList();  //Temporaneo

        while(pq.getnElem() != 0)
        {
            Element<T> u = pq.extractMin();
            resultingTree.put(u.getValue(),new HashMap<T,V>());

            HashMap<T,V> hm = getAdiacent(u.getName());

            for (T v : hm.keySet())
            {
                if(comp2.compare(getWeightArch(u.getName(),v),hm.get(v)) < 0)
                {
                    // Cambiare valori nella priorityquque (CREDO)
                }
            }
        }

        return resultingTree;        
    }

    private void initPq(PriorityQueue pq,T root)
    {
        for(T node: adiacentsMap.keySet())
            {
                if (comp.compare(node,root) == 0)
                    pq.insert(new Element<T>(0,null,node));                
                else
                    pq.insert(new Element<T>(Double.MAX_VALUE,null,node));
            }
    }

    //https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
}