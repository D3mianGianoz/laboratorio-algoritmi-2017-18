package graph;

/**
 * @author Costamagna Alberto e Gianotti Damiano
 * @param <T>: type of the Node
 * @param <V>: type of the label
 */
public class Arch <T,V>
{
    /**
     * 
     */
    private T from;
    /**
     * 
     */
    private T to;
    /**
     * 
     */
    private V label;

    /**
     * Constructor of the support class Arch
     * @param from: 
     * @param to:
     * @param label:
     */
    public Arch(T from,T to,V label)
    {
        this.from = from;
        this.to = to;
        this.label = label;
    }

    /**
     * @return:
     */
    public T getFrom()
    { return this.from; }

    /**
     * @return:
     */
    public T getTo()
    { return this.to; }

    /**
     * @return:
     */
    public V getLabel()
    { return this.label; }

    /**
     * Method that check if two Arches are equals
     * @return: true if they are equals, false otherwise
     * @param other: the Arch to compare with
     */
    public boolean equals(Arch<T, V> otherArch){
        if (otherArch == this)
            return true;
        if (!(otherArch instanceof Arch))
            return false;
        if(this.from.equals(otherArch.from) && this.to.equals(otherArch.to) && this.label.equals(otherArch.label))
            return true;
        else
            return false;

    }
}