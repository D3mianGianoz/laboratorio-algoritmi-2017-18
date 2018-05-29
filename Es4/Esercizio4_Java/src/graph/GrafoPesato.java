package graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import priorityqueue.*;

/**
 * @author Costamagna Alberto e Gianotti Damiano
 * @param <T>: type of the Node
 */
public class GrafoPesato<T> extends Graph<T,Double>
{
    protected Comparator<T> comp; 

    public GrafoPesato(boolean isDirect,Comparator<T> comp)
    {
        super(isDirect);
        this.comp = comp; 
    }

    public double weight() throws Error, GraphException
    {
        double result = 0d;
        if(this.isEmpty() || this == null)
            throw new GraphException(this +" can't be empty or null");
        
        System.out.println("Print of Weight of "+ this);
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

    public GrafoPesato<T> prim(T root) throws GraphException, PriorityQueueException
    {
        if(this.isEmpty() || this == null)
            throw new GraphException(this +" can't be empty or null");
        if (isDirect)
            throw new GraphException("Prim only works with not direct graph");

        GrafoPesato<T> resultingGraph = new GrafoPesato<T>(isDirect,this.comp);
        // Creo la priorityQUeue che mi servirà per tener traccia dei nodi
        PriorityQueue<T> pq = new PriorityQueue<T>();
        // Vado a settare tutti i nodi nella coda di priorità con massima priorità
        initPq(pq,root);
      
        //pq.printList();  //Temporaneo 
        while(pq.getnElem() != 0)
        {
            Element<T> u = pq.extractMin();
        
            addToResultingGraph(resultingGraph,u);
            
            //System.out.println(u.getName());
            HashMap<T,Double> hm = getAdiacent(u.getName());

            //System.out.println(hm.toString());
            for (T v : hm.keySet())
            {
                //System.out.println("U: "+u.getName()+ " V: "+v);
                if(pq.getPosElem(v) >= 0 && getWeightArch(u.getName(),v) < pq.getElem(v).getKey())
                    pq.changeKeyParent(pq.getPosElem(v), getWeightArch(u.getName(),v), u.getName());
            }
            //pq.printList();
          
            
        }
        return resultingGraph;        
    }

    /**
     * TODO NON LANCIA NESSSUNA ECCEZIONE ! (NON E VERO)
     */
    private void addToResultingGraph(GrafoPesato<T> res,Element<T> x) throws GraphException 
    {
        res.addNode(x.getName());
        if (x.getValue() != null)
        {
            res.addArch(x.getName(),x.getValue(),x.getKey());
            res.addArch(x.getValue(),x.getName(),x.getKey());
        }   
    }

    private void initPq(PriorityQueue<T> pq,T root)
    {
        for(T node: adiacentsMap.keySet())
            {
                if (comp.compare(node,root) == 0)
                    pq.insert(new Element<T>(0, null, node));                
                else
                    pq.insert(new Element<T>(Double.MAX_VALUE, null, node));
            }
    }

}