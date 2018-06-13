package priorityqueue;

/**
 * @author : Alberto Costamagna, Damiano Gianotti
 * @param T : the type of the value field of the elment
 */
public class Element<T> {
    private double key;
    private T value;
    private T name;

    /**
     * Constructor of the object element
     * 
     * @param key: Value of the generic key of the element
     * @param value: Generic value of the elment
     * @param name: Generic "id"(name) of the element
     */
    public Element(double key, T value, T name) {
        this.key = key;
        this.value = value;
        this.name = name;
    }

    /**
     * Alternative Constructor of the Element with no param name
     * 
     * @param key: Value of the generic key of the element
     * @param value: Generic value of the elment
     */
    public Element(double key, T value) {
        this.key = key;
        this.value = value;
        this.name = null;
    }

    /**
     * @return: the value of the element
     */
    public T getValue() {
        return this.value;
    }

    /**
     * @return: the key of the element
     */
    public double getKey() {
        return this.key;
    }

    /**
     * @return: the name of the element
     */
    public T getName() {
        return this.name;
    }

    /**
     * Set key of the element
     * 
     * @param key: the key to set
     */
    public void setKey(double key) {
        this.key = key;
    }

    /**
     * Set value of the element
     * 
     * @param value: the value to set
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Set the name of the element
     * 
     * @param name: the name to set
     */
    public void setName(T name) {
        this.name = name;
    }

    /**
     * @return: the formattet string to print the element
     */
    @Override
    public String toString() {
        return "Name: " + this.name + " Key: " + this.key + " Value: " + this.value;
    }
}
