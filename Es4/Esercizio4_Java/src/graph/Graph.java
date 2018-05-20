package graph;

import java.util.*;

public class Graph<T,V>
{
    private int nNode;
    private int nArch;
    private HashMap<T,HashMap<T,V>> adiacentsMap;
    private boolean isDirect;
    
    public Graph(boolean isDirect)
    {
        adiacentsMap = new HashMap<T,HashMap<T,V>>();
        nNode = 0;  //TODO forse varuabile superflua
        nArch = 0;
        this.isDirect = isDirect;
    }

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
            //remove
        }
        else
            throw new GraphException("Failed to remove Node "+ value +" don't exist");
    }
/*
    public void removeArch(T from,T to,V label) throws GraphException
    {
        if(true){ //TODO specificare la condizione
            //remove
        }
        else
            throw new GraphException("Failed to remove Arch don't exist");
    }
*/

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