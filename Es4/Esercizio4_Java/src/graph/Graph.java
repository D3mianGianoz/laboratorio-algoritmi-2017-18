package graph;

import java.util.*;

public class Graph<T,V>
{
    private int nNode;
    private int nArch;
    private HashMap<T,HashMap<T,V>> adiacentsMap;
    private Comparator<V> comp;
    private boolean isDirect;
    
    public Graph(boolean isDirect,Comparator<V> comparator)
    {
        this.adiacentsMap = new HashMap<T,HashMap<T,V>>();
        this.nNode = 0;  //TODO forse varuabile superflua
        this.nArch = 0;
        this.isDirect = isDirect;
        this.comp = comparator;
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
            for (HashMap temp: adiacentsMap.values())
            {
                if (temp.containsKey(value))
                {
                   temp.remove(value);
                }
            }
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


    /**
     * prima implementazione
     */
    
    public int Weight(){
        int result = -7; 

        for(HashMap temp : adiacentsMap.values()){
            if(!temp.values().isEmpty()){
                for (Object value : temp.values()) { //Se non sono Int fallisce
                    result = result + (int)value;
                }
                return result;
            } throw new Error("This graph has no Weight!");
        }

        return -1;
    }

    public void printGraph()
    {
        System.out.println(adiacentsMap.toString());
    }

    public int archiIncidenti(T key) throws GraphException
    {
        if(!adiacentsMap.containsKey(key))
            throw new GraphException("Node not valid");

        int count = 0;
        for(HashMap tmp : adiacentsMap.values())
        {
            for(Object obj : tmp.keySet())
                if((T) obj == key)
                    count++;

        }

        return count;
    }

    public boolean sonoAdiacenti(T nodo1,T nodo2) throws GraphException
    {
        if(adiacentsMap.containsKey(nodo1) && adiacentsMap.containsKey(nodo2))
        {            
            HashMap tmp = adiacentsMap.get(nodo1);
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
            throw new GraphException("Node not valid");
        
        return false;
    }


    /**
     * 
        If you're only interested in the keys, you can iterate through the keySet() of the map:

        Map<String, Object> map = ...;

        for (String key : map.keySet()) {
            // ...
        }
        If you only need the values, use values():

        for (Object value : map.values()) {
            // ...
        }
        Finally, if you want both the key and value, use entrySet():

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // ...
        }

        or 

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
     */


}