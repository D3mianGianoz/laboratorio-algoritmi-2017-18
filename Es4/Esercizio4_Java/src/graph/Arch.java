package graph;

/**
 * @author Costamagna Alberto e Gianotti Damiano
 * @param <T>: type of the Node
 * @param <V>: type of the label
 */
public class Arch <T,V>
{
    private T from;
    private T to;
    private V label;

    public Arch(T f,T t,V l)
    {
        this.from = f;
        this.to = t;
        this.label = l;
    }

    public T getFrom()
    { return this.from; }

    public T getTo()
    { return this.to; }

    public V getLabel()
    { return this.label; }
}