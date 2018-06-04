package graph;

/**
 * @author : Alberto Costamagna, Damiano Gianotti
 */
public class Node <T>
{
    private double d;
    private T pigrec;

    public Node(double d,T p)
    {
        this.d = d;
        this.pigrec = p;
    }

    public Node(double d)
    {
        this.d = d;
        this.pigrec = null;
    }

    public double getD()
    { return this.d; }

    public T getPigrec()
    { return this.pigrec; }

    public void setPigrec(T p)
    { this.pigrec = p;}

    public void setD (double d)
    { this.d = d;}

}