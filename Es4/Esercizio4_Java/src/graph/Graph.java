package graph;

import java.util.*;

public class Graph<T,V>
{
    private int nNode;
    private int nArch;
    private HashMap<T,HashMap<T,V>> adiacentsList;
    private boolean isDirect;
    
    public Graph(boolean isDirect)
    {
        adiacentsList = new HashMap<T,HashMap<T,V>>();
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
        adiacentsList.put(value,new HashMap<T,V>());
        nNode++;
    }

    public void addArch(T from,T to,V label)
    {
        adiacentsList.get(from).put(to,label);
        if (!isDirect)
            adiacentsList.get(to).put(from,label);
        nArch++;
    }

    public void printGraph()
    {
        System.out.println(adiacentsList.toString());
    }

   


}


/*

 public void addNodeArch (T vNode,T nodeConnected)
    {
        listNode.add(vNode);   
        arch.put(nodeConnected,vNode);
        nNode++;
        nArch++;
    }

    public void addNode (T vNode)
    {
        listNode.add(vNode);   
        nNode++;
    }

    public void addArch(T nodeFrom,T nodeAt)
    {
        arch.put(nodeFrom,nodeAt);
        nArch++;
    }

    public void printGraph()
    {
        for (int i = 0;i<listNode.size();i++)
        {
            System.out.println("Nodo: "+listNode.get(i));            
        }
        System.out.println(arch.toString());
    }
*/