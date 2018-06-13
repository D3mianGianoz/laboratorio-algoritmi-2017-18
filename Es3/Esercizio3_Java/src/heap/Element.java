package heap;

/**
 * @author : Alberto Costamagna, Damiano Gianotti
 * @param T : the type of the value field of the elment
 */
public class Element<K, T> {
    private K key;
    private T value;

    /**
     * Constructor of the object element of MaxHeap
     * 
     * @param key: Value of the key of the element
     * @param value: Value of the value of the elment
     */
    public Element(K key, T value) {
        this.key = key;
        this.value = value;
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
    public K getKey() {
        return this.key;
    }

    /**
     * Set key of the element
     * 
     * @param key: Value of the generic key of the element
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Set value of the element
     * 
     * @param value: Generic value of the elment
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * @return: the formattet string to print the element
     */
    @Override
    public String toString() {
        return " Key: " + this.key + " Value: " + this.value;
    }
}
