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
        nNode = 0;
        nArch = 0;
        this.isDirect = isDirect;
    }

    public int getnNode()
    { return this.nNode; }

    public int getnArch()
    { return this.nArch; }

    public void addNode(T value)
    {
        adiacentsMap.put(value,new HashMap<T,V>());
        nNode++;
    }

    public void addArch(T from,T to,V label)
    {
        adiacentsMap.get(from).put(to,label);
        if (!isDirect)
            adiacentsMap.get(to).put(from,label);
        nArch++;
    }

    public void printGraph()
    {
        System.out.println(adiacentsMap.toString());
    }
    
    public int Weight(){
        int result = -1; 

        for(HashMap temp : adiacentsMap.values()){
            for (Object value : temp.values()) {
                result = result + (int)value;
            }
        }
        return result + 1;
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
     */
    
   

}