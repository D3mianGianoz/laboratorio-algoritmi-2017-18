package graph;

import java.util.*;

public class Graph<T>
{
    private ArrayList<T> listNode;
    private Hashtable<T,T> arch;
    private int nNode;
    private int nArch;

    public Graph()
    {
        listNode = new ArrayList<T>();
        arch = new Hashtable<T,T>();
        nNode = 0;
        nArch = 0;
    }

    public int getnNode()
    { return this.nNode; }

    public int getnArch()
    { return this.nArch; }

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


}