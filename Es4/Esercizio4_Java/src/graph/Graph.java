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
    /**
     * the number of nodes
    */
    protected int nNode;
    /**
     * the number of arches
     */
    protected int nArch;
    /**
     * adiacent list of the Graph
     * implemented using a HashMap of (T,(HashMap of T,V))
     * @see https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
     */
    protected HashMap<T,HashMap<T,V>> adiacentsMap;
    /**
     * flag; true if the Graph is oriented false otherwise
     */
    protected boolean isDirect;

    /**
     * Main constructor of the Object Graph
     * @param isDirect: boolean, true if it Graph is oriented, false the opposite
     */
    public Graph(boolean isDirect)
    {
        this.adiacentsMap = new HashMap<T,HashMap<T,V>>();  //non può essere null se no Nullpointer exception
        this.nNode = 0;  
        this.nArch = 0;
        this.isDirect = isDirect;
    }

    /**
     * Alternative constructor of the Object Graph, used to "clone" an other Graph
     * @param clone: the Object Graph that we want to "clone"
     */
    public Graph(Graph<T,V> clone)
    {
        this.adiacentsMap = clone.adiacentsMap;
        this.nNode = clone.nNode;
        this.nArch = clone.nArch;
        this.isDirect = clone.isDirect;
    }
    /**
     * Getter for field isDirect
     * @return: isDirect value (true or false)
     */
    public boolean getIsDirect()
    { return this.isDirect; }

    /**
     * Getter for field nNode
     * @return: nNode value [0,1...,n]
     */
    public int getnNode()
    { return this.nNode; }

    /**
     * Getter for field nArch
     * @return: nArch value [0,1...,n]
     */
    public int getnArch()
    { return this.nArch; }

    /**
     * Method that look if the Graph is empty.
     * It looks the adiancent list is empty only if counters nNode nArch are equals to 0 
     * @return: true if it's empty, false otherwise
     */
    public boolean isEmpty(){
        if(nArch == 0 && nNode == 0)
            return adiacentsMap.isEmpty();
        else
            return false;
    }

    /**
     * Method that check if two Graph are equals
     * @return: true if they are equals, false otherwise
     * @param other: the Graph to compare with
     */
    public boolean equals(Graph<T, V> other){
        if (other == this)
            return true;
        if (!(other instanceof Graph))
            return false;
        if(this.adiacentsMap.equals(other.adiacentsMap) && this.isDirect == other.isDirect)
            return true;
        else
            return false;
    }

    /**
     * Core function of the Graph, add a Node to the structure only if isn't already contained
     * @param value: the value of the node to be added
     * @throws graph.GraphException: if the param is already contained
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
     * Same function as "addNode" but if it fails it doesn't throws GraphException
     * @param value: the value of the node to be added
     */
    public void addNodeNoFlag(T value)
    {
        if(!adiacentsMap.containsKey(value)){
            adiacentsMap.put(value,new HashMap<T,V>());
            nNode++;
        }
    }

    /**
     * Core function of the Graph, add an "Arch"(connection between Nodes) to the structure with a label.
     * It require that both node exist in the graph
     * @param from: Node origin
     * @param to: Node destination
     * @param label: generic value of the "Arch"
     * @throws graph.GraphException: if the one or both of the Node params are not in the structure
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
     * Remove a Node from the Graph, only if the Node is contained
     * @param value: the Node to be removed
     * @throws graph.GraphException: if the param are not in the structure
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
     * Remove an "Arch" from the Graph, only if the parameters match an existing "Arch".
     * It require that both node exist in the graph
     * @param from: Node origin
     * @param to: Node destination
     * @param label: generic value of the "Arch" 
     * @throws graph.GraphException: if the params didn't match
     */
    public void removeArch(T from,T to,V label) throws GraphException
    {
        if(adiacentsMap.containsKey(from) && adiacentsMap.get(from).containsKey(to) && adiacentsMap.get(from).containsValue(label))
        {
            adiacentsMap.get(from).remove(to,label);
            if (!isDirect)
                adiacentsMap.get(to).remove(from,label);
            nArch--;
        }
        else
            throw new GraphException("Failed to remove Arch "+from + to +"don't exist");
    }

    /**
     * Print of the Graph using his adiancentlist.
     * 
     * Example
     * 
     * RootNode = someNode:
     * someNode -> someLabel -> otherNode
     */
    public void printGraphDef(){
        if(this.isEmpty() || this == null){
            System.out.println(this +" is empty or null !");
            return;
        }

        System.out.println("Print of the "+ this);
        for (Map.Entry<T, HashMap <T,V>> adiEntry : adiacentsMap.entrySet()){
            System.out.println("RootNode = " + adiEntry.getKey()+": ");
            HashMap<T, V> tMap = adiEntry.getValue();
                for (Map.Entry<T, V> entry : tMap.entrySet())
                    System.out.println(adiEntry.getKey() + " -> label = " + entry.getValue() + " -> " + entry.getKey());
        }
    }

    /**
     * Indirected graph: the degree of a Node is the number of arcs that depart from it.
     * Directed graph: the incoming (outgoing) degree of a Node is the number of edges accidents in (from) it 
     * the total degree of a Node is the sum of this two
     * @param node: the Node whose degree must be calculated
     * @return: the degree of the node [0,..,n]
     * @throws graph.GraphException: if the Node don't exist
     */
    public long nodeDegree(T node) throws GraphException
    {
        if(!adiacentsMap.containsKey(node))
            throw new GraphException("Invalid Node "+ node);

        long count = 0;
        count = count + adiacentsMap.get(node).size();

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
     * An Arch and a Node on that Arch are called incident.
     * This method create an ArrayList of Object graph.Arch that are incident to a Node
     * @param node: the chosen Node
     * @return: an ArrayList of Arch<T, V> 
     * @throws graph.GraphException: if the Node don't exist
     * @see https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
     */
    public ArrayList<Arch<T,V>> incidentArches(T node) throws GraphException
    {
        if(!adiacentsMap.containsKey(node))
            throw new GraphException("Invalid Node "+ node);

        ArrayList<Arch<T,V>> listArch = new ArrayList<Arch<T,V>>();        
        if (!isDirect)
        {            
            HashMap<T,V> hm = adiacentsMap.get(node);
            for(T to: hm.keySet())            
                listArch.add(new Arch<T,V>(node,to,hm.get(to)));
            
            return listArch;
        }
        else
        {
            for(T tmp: adiacentsMap.keySet())
            {
                if(!tmp.equals(node))
                {
                   HashMap<T,V> hm = adiacentsMap.get(tmp);
                   for(T to: hm.keySet())
                    if(to.equals(node))
                        listArch.add(new Arch<T,V>(tmp,to,hm.get(to)));
                }
    
            }
        }
       return listArch;
    }

    /**
     * Two Nodes of a graph are called adjacent if they share a common Arch.
     * This method looks if from node1 you are able to reach node2 or not 
     * @param node1: Node origin
     * @param node2: Node destination
     * @return boolean: true if  you are able, false viceversa
     * @throws graph.GraphException: if the Nodes don't exist
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
     * Getter: it returns the list of all adiacent Nodes
     * @return: HashMap<T,V> containg all adiacent Nodes
     * @param node: the chosen Node
     */
    public HashMap<T,V> getAdiacent(T node)
    {
        return adiacentsMap.get(node);
    }

    /**
     * Getter: it returns the label of the Arch beetwen two Nodes
     * @return: V the type of label
     * @param from: Node origin
     * @param to: Node destination
     */
    public V getLabelArch(T from,T to)
    {
        return adiacentsMap.get(from).get(to);
    }

}