package graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import priorityqueue.*;

/**
 * @author Costamagna Alberto e Gianotti Damiano
 * @param <T>: type of the Node
 */
public class WeightedGraph<T> extends Graph<T,Double>
{
    /**
     *  The comparator that we use to compare generic name of the Node
     */
    protected Comparator<T> comp; 

    /**
    * Main constructor of the Object Graph
    * @param isDirect: boolean, true if it Graph is oriented, false the opposite
    * @param comp: the comparator for the name of the node
    */
    public WeightedGraph(boolean isDirect,Comparator<T> comp)
    {
        super(isDirect);
        this.comp = comp; 
    }

    /**
     * Methid that clone the graph
     */
    public WeightedGraph(WeightedGraph<T> clone)
    {
        super(clone);
    }

    /**
     * @return: Method that returns the weight of the weighted graph
     */
    public double weight() throws GraphException
    {
        double result = 0d;
        if(this.isEmpty() || this == null)
            throw new GraphException(this +" can't be empty or null");
        
        //System.out.println("Print of Weight of "+ this);
        for(HashMap<T, Double> temp : adiacentsMap.values()){
            for (Double value : temp.values()) {
                    result = result + value.doubleValue();
            }
        }

        if(result > 0)
            return isDirect ? result : result / 2;
        else
            return result;
    }

    /**
     * Method that allows to finde the minumun spanning tree of the weighted graph
     *  @return: return a new WeightedGraph that rappresents the minimun spanning tree
     */
    public WeightedGraph<T> prim(T root) throws GraphException, PriorityQueueException
    {
        if(this.isEmpty() || this == null)
            throw new GraphException(this +" can't be empty or null");
        if (isDirect)
            throw new GraphException("Prim only works with not direct graph");

        WeightedGraph<T> resultingGraph = new WeightedGraph<T>(isDirect,this.comp);
        
        // Creo la priorityQUeue che mi servirà per tener traccia dei nodi
        PriorityQueue<T> pqueue = new PriorityQueue<T>();
        // Vado a settare tutti i nodi nella coda di priorità con massima priorità
        initPq(pqueue,root);
      
        //pq.printList();  //Temporaneo 
        while(pqueue.getnElem() != 0)
        {
            Element<T> element = pqueue.extractMin();
            //System.out.println("Name: "+element.getName()+" Key: "+element.getKey() + " Value: "+element.getValue());
            if(element.getKey() == Double.MAX_VALUE){
                element.setKey(0);
                element.setValue(null);
            }
            
            addToResultingGraph(resultingGraph,element);
            
            //System.out.println(u.getName());
            HashMap<T,Double> hm = getAdiacent(element.getName());

            //System.out.println(hm.toString());
            for (T vElem : hm.keySet())
            {
                //System.out.println("U: "+u.getName()+ " V: "+v);
                if(pqueue.getPosElem(vElem) >= 0 && getLabelArch(element.getName(),vElem) < pqueue.getElem(vElem).getKey())
                    pqueue.decreaseKeyParent(pqueue.getPosElem(vElem), getLabelArch(element.getName(),vElem), element.getName());
            }
            //pq.printList();
        }
        return resultingGraph;        
    }

    /**
     * Method that allow to add the nodes and the archs to the weighted graph
     */
    private void addToResultingGraph(WeightedGraph<T> res,Element<T> elem) throws GraphException 
    {
        res.addNode(elem.getName());
        if (elem.getValue() != null)        
            res.addArch(elem.getName(), elem.getValue(), elem.getKey());
    }

    /**
     * Auxiliary method that is used to create and initialize the min priority queue
     */
    private void initPq(PriorityQueue<T> pqueue,T root)
    {
        for(T node: adiacentsMap.keySet())
            {
                if (comp.compare(node,root) == 0)
                    pqueue.insert(new Element<T>(0, null, node));                
                else
                    pqueue.insert(new Element<T>(Double.MAX_VALUE, null, node));
            }
    }

}