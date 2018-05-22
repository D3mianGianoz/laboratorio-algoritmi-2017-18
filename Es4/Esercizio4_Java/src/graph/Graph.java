package graph;

import java.util.*;

public class Graph<T,V>
{
    private int nNode;
    private int nArch;
    private HashMap<T,HashMap<T,V>> adiacentsMap;
    private Comparator<V> comp; //Non usato
    private boolean isDirect;
    
    public Graph(boolean isDirect,Comparator<V> comparator) // A cosa serve il comparator ???
    {
        this.adiacentsMap = new HashMap<T,HashMap<T,V>>();  //non può essere null se no Nullpointer exception
        this.nNode = 0;  //TODO forse varuabile superflua
        this.nArch = 0;
        this.isDirect = isDirect;
        this.comp = comparator;
    }

    public Graph(boolean isDirect)
    {
        this.adiacentsMap = new HashMap<T,HashMap<T,V>>();  //non può essere null se no Nullpointer exception
        this.nNode = 0;  //TODO forse varuabile superflua
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
            throw new GraphException("Failed to add Node "+ value +" it is already part of Graph");
    }

    public void addArch(T from,T to,V label) throws GraphException
    {
        if(adiacentsMap.containsKey(from) && adiacentsMap.containsKey(to)){
            adiacentsMap.get(from).put(to,label);
            if (!isDirect)
                adiacentsMap.get(to).put(from,label);
            nArch++;
        }else
            throw new GraphException("Failed to add Arch " + from + " or " + to + " don't exist");
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
            throw new GraphException("Failed to remove Arch don't exist");
    }


    public double Weight() throws Error
    {
        double result = -7; //Non sono sicuro della bontà di questa soluzione

        for(HashMap<T, V> temp : adiacentsMap.values()){
            for (Object value : temp.values()) { //Se label degli archi != double fallisce
                if (value instanceof Double)
                    result = result + (double)value;
                else
                    throw new Error("This graph has no Weight because label is: " + value.getClass());
            }
        }
        return result;
    }

    /**
     * Stampa poco significativa
     */
    public void printGraph() //TODO REMOVE
    {
        System.out.println(adiacentsMap.toString());
    }

    /**
     * Stampa del grafo più chiara 
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

    //https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
}