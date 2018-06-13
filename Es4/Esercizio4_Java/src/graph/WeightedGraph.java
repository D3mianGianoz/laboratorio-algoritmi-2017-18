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
     * @param clone: the Object Graph that we want to "clone"
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
     * Method that allows to finde the minumun spanning tree of the weighted graph,
     * using a priorityqueue as support
     * @return: return a new WeightedGraph that rappresents the minimun spanning tree
     * @param root: the first vertex from which we start the algoritm
     * @throws graph.GraphException: if the Graph isEmpty, null, isDirect or addToResultingGraph throws
     * @throws priorityqueue.PriorityQueueException: iff some method fails
     */
    public WeightedGraph<T> prim(T root) throws GraphException, PriorityQueueException
    {
        if(this.isEmpty() || this == null)
            throw new GraphException(this +" can't be empty or null");
        if (isDirect)
            throw new GraphException("Prim only works with not direct graph");

        WeightedGraph<T> resultingGraph = new WeightedGraph<T>(isDirect,this.comp);
        PriorityQueue<T> pqueue = new PriorityQueue<T>();

        initPq(pqueue,root);
      
        while(!pqueue.isEmpty())
        {
            Element<T> element = pqueue.extractMin();

            if(element.getKey() == Double.MAX_VALUE){
                element.setKey(0);
                element.setValue(null);
            }
            addToResultingGraph(resultingGraph,element);

            HashMap<T,Double> hm = getAdiacent(element.getName());

            for (T vElem : hm.keySet())
            {
                if(pqueue.getPosElem(vElem) >= 0 && getLabelArch(element.getName(),vElem) < pqueue.getElem(vElem).getKey())
                    pqueue.decreaseKeyParent(pqueue.getPosElem(vElem), getLabelArch(element.getName(),vElem), element.getName());
            }
        }
        return resultingGraph;        
    }

    /**
     * Method that allow to add the nodes and the archs to the weighted graph
     * @param res: the WGraph
     * @param elem: the element from the pqueue to add
     * @throws graph.GraphException: iff addNode or addArch fails
     */
    private void addToResultingGraph(WeightedGraph<T> res,Element<T> elem) throws GraphException 
    {
        res.addNode(elem.getName());
        if (elem.getValue() != null)        
            res.addArch(elem.getName(), elem.getValue(), elem.getKey());
    }

    /**
     * Auxiliary method that is used to create and inizialize the min priority queue
     * @param pqueue: the queue
     * @param root: the first element of the queue
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