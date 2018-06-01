package priorityqueue;

/**
 * 
 * @author : Alberto Costamagna, Damiano Gianotti
 * @param T : the type of the value field of the elment 
 */
public class Element <T>
{
    private double key;
    private T value;
    private T name;

    /**
     * Constructor of the object element
     * @param key: Value of the generic key of the element
     * @param value: Generic value of the elment
     * @param name: Generic "id"(name) of the element
     */
    public Element(double key,T value, T name)
    {
        this.key = key;
        this.value = value;
        this.name = name;
    }

    public Element(double key,T value)
    {
        this.key = key;
        this.value = value;
        this.name = null;
    }

    public void setName(T name)
    { this.name = name;  }

    public T getName()
    { return this.name;}

    /**
     * @return: the value of the element
     */
    public T getValue()
    {
        return this.value;
    }

    /**
     * @return: the key of the element
     */
    public double getKey()
    {
        return this.key;
    }

    /**
     * Set key of the element
     */
    public void setKey(double key)
    {
        this.key = key;
    }

    /**
     * Set value of the element
     */
    public void setValue(T value)
    {
        this.value = value;
    }

    /**
     * @return: the formattet string to print the element
     */
    @Override
    public String toString()
    {
        return "Name: "+this.name+ " Key: "+this.key+ " Value: "+this.value;
    }
}

