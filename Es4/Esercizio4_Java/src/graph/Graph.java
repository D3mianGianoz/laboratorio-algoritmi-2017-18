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
     * Constructor of the Object Graph used to "clone"
     * @param clone: the Object Graph used as source of the "cloning" process
     */
    public Graph(Graph<T,V> clone)
    {
        this.adiacentsMap = clone.adiacentsMap;
        this.nNode = clone.nNode;
        this.nArch = clone.nArch;
        this.isDirect = clone.isDirect;
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
            throw new GraphException("Failed to add Node: "+ value);
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
            for (HashMap<T, V> temp: adiacentsMap.values())
                if (temp.containsKey(value)){
                    temp.remove(value);
                    nArch--;
                }
            if(this.isDirect)
                nArch = nArch - adiacentsMap.get(value).size();
            adiacentsMap.remove(value);
            nNode--;
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
            if (!isDirect){
                adiacentsMap.get(to).remove(from,label);
                nArch--;
            }
            nArch--;
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
    public long nodeDegree(T node) throws GraphException
    {
        if(!adiacentsMap.containsKey(node))
            throw new GraphException("Invalid Node "+ node);

        long count = 0;
        count = count + adiacentsMap.get(node).size();

        //for(T obj : adiacentsMap.get(node).keySet()) //Obj mai usata
        //        count++;

        if (this.isDirect)
        {
            for(T tmp : adiacentsMap.keySet())
            {
                if (!tmp.equals(node))
                {
                    for(T obj : adiacentsMap.get(tmp).keySet())
                        if(obj.equals(node))
                            count++;
                }
            }
        }

        return count;
    }

    /**
     * 
     */
    public ArrayList<Arch<T,V>> incidentArchs(T key) throws GraphException
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
     * @return boolean: true if from node1 you are able to reach node2 or viceversa
     */
    public boolean areAdiacent(T node1,T node2) throws GraphException
    {
        if(adiacentsMap.containsKey(node1) && adiacentsMap.containsKey(node2))
        {            
            HashMap<T, V> tmp = adiacentsMap.get(node1);
            if(tmp.containsKey(node2))
                return true;
            else
            {
                tmp = adiacentsMap.get(node2);
                if(tmp.containsKey(node1))
                    return true;
            }
        }
        else
            throw new GraphException("Invalid Nodes "+ node1 +" and/or "+ node2);
        
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

}