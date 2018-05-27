package graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import priorityqueue.*;
import java.util.ArrayList;

/**
 * @author Costamagna Alberto e Gianotti Damiano
 * @param <T>: type of the Node
 * @param <V>: type of the label
 */
public class Graph<T,V>
{
    protected int nNode;
    protected int nArch;
    protected HashMap<T,HashMap<T,V>> adiacentsMap;
    protected boolean isDirect;

    /**
     * Constructor of the Object Graph 
     * @param isDirect: boolean with a simple job [true means the Graph is oriented, false the opposite] 
     */
    public Graph(boolean isDirect)
    {
        this.adiacentsMap = new HashMap<T,HashMap<T,V>>();  //non può essere null se no Nullpointer exception
        this.nNode = 0;  
        this.nArch = 0;
        this.isDirect = isDirect;
    }
    /**
     * @return isDirect value (true or false)
     */
    public boolean getIsDirect()
    { return this.isDirect; }

    /**
     * @return nNode value [0,1...,n]
     */
    public int getnNode()
    { return this.nNode; }

    /**
     * @return nArch value [0,1...,n]
     */
    public int getnArch()
    { return this.nArch; }

    /**
     * 
     */
    public boolean isEmpty(){
        return adiacentsMap.isEmpty();
    }

    /**
     * 
     */
    public void addNode(T value) throws GraphException
    {
        if(!adiacentsMap.containsKey(value)){
            adiacentsMap.put(value,new HashMap<T,V>());
            nNode++;
        }else
            throw new GraphException("Failed to add Node: "+ value +" it is already part of Graph");
    }

    /**
     * 
     */
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

    /**
     * 
     */
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

    /**
     * 
     */
    public void removeArch(T from,T to,V label) throws GraphException
    {
        if(adiacentsMap.containsKey(from) && adiacentsMap.get(from).containsKey(to) && adiacentsMap.get(from).containsValue(label))
        {
            adiacentsMap.get(from).remove(to,label);
            if (!isDirect)
                adiacentsMap.get(to).remove(from,label);
        }
        else
            throw new GraphException("Failed to remove Arch "+from + to +"don't exist");
    }

    /**
     * Stampa del grafo piu' chiara 
     */
    public void printGraphDef(){
        System.out.println("Print of the "+ this);
        if(this.isEmpty() || this == null)
            System.out.println(this +" is empty or null !");
        for (Map.Entry<T, HashMap <T,V>> adiEntry : adiacentsMap.entrySet()){
            System.out.println("RootNode = " + adiEntry.getKey()+": ");
            HashMap<T, V> tMap = adiEntry.getValue();
                for (Map.Entry<T, V> entry : tMap.entrySet())
                    System.out.println(adiEntry.getKey() + " -> label = " + entry.getValue() + " -> " + entry.getKey());
        }
    }

    /**
     * 
     */
    public int gradoNodo(T key) throws GraphException
    {
        if(!adiacentsMap.containsKey(key))
            throw new GraphException("Invalid Node "+ key);

        int count = 0;           
        for(T obj : adiacentsMap.get(key).keySet()) //Obj mai usata
                count++;
        if (this.isDirect)
        {
            for(T tmp : adiacentsMap.keySet())
            {
                if (!tmp.equals(key))
                {
                    for(T obj : adiacentsMap.get(tmp).keySet())
                        if(obj.equals(key))
                            count++;
                }
            }
        }

        return count;
    }

    /**
     * 
     */
    public ArrayList<Arch<T,V>> archiIncidenti(T key) throws GraphException
    {
        if(!adiacentsMap.containsKey(key))
            throw new GraphException("Invalid Node "+ key);
        ArrayList<Arch<T,V>> listArch = new ArrayList<Arch<T,V>>();        
        if (!isDirect)
        {            
            HashMap<T,V> hm = adiacentsMap.get(key);
            for(T to: hm.keySet())            
                listArch.add(new Arch<T,V>(key,to,hm.get(to)));
            
            return listArch;
        }
        else
        {
            for(T tmp: adiacentsMap.keySet())
            {
                if(!tmp.equals(key))
                {
                   HashMap<T,V> hm = adiacentsMap.get(tmp);
                   for(T to: hm.keySet())
                    if(to.equals(key))
                        listArch.add(new Arch<T,V>(tmp,to,hm.get(to)));
                }
    
            }
        }
       return listArch;
    }

    /**
     * 
     */
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
            throw new GraphException("Invalid Nodes "+ nodo1 +" and/or "+ nodo2);
        
        return false;
    }

    /**
     * 
     */
    public HashMap<T,V> getAdiacent(T node)
    {
        return adiacentsMap.get(node);
    }

    /**
     * 
     */
    public V getWeightArch(T u,T v)
    {
        return adiacentsMap.get(u).get(v);
    }

        //https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
}