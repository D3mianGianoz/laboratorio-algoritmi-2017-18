package graph;

/**
 * @author Costamagna Alberto e Gianotti Damiano
 * @param <T>: type of the Node
 * @param <V>: type of the label
 */
public class Arch <T,V>
{
    /**
     *  The node from the arch starts
     */
    private T from;

    /**
     * The node the arch connects
     */
    private T to;

    /**
     * Label that identify the arch
     */
    private V label;

    /**
     * Constructor of the support class Arch
     * @param from: node from
     * @param to: node to
     * @param label: label of the arch
     */
    public Arch(T from,T to,V label)
    {
        this.from = from;
        this.to = to;
        this.label = label;
    }

    /**
     * @return: the node from of the arch
     */
    public T getFrom()
    { return this.from; }

    /**
     * @return: the node to of the arch
     */
    public T getTo()
    { return this.to; }

    /**
     * @return: the label of the arch
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