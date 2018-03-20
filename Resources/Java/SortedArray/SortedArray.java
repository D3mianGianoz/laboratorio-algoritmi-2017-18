import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class SortedArray<T> {
    
    private ArrayList list;

    /*
    *   Creo il vettore con grandezza e tipo specificati
    *   Parameter:
    *   s = grandezza del vettore
    */
    public SortedArray(int s)
    {
        if (s > 0)
            list = new ArrayList<T>(s);
        else
            throw new IllegalStateException("Size >= 0");
    }

    /*
    *   Aggiungo al vettore l' elemento
    *   Parameter:
    *   item = oggetto da aggiungere
    */
    public boolean addItem (Object item)
    {
        return list.add((T)item);
    }

    /*
    *   Aggiungo al vettore l' elemento alla posizione selezionata
    *   Parameter:
    *   index = posizione in cui aggiungere l' elemento
    *   item = oggetto da aggiungere
    */
    public void addToIndex (int index,Object item)
    {
        list.add(index,(T)item);
    }

    // Stampo il vettore
    public void printArray()
    {
        for (Object item : list) {
            System.out.println("Element: "+item);
        }
    }

    // Rimuovo l' elemnto nella posizione indicata
    public void removeItem(int index)
    {
        Object e = list.remove(index);
    }

    // Ordino la lista in modo crescente
    public void sortArrayAsc()
    {
        Collections.sort(list);
    }

    // Ordino la lista in modo decrescente
    public void sortArrayDes()
    {
        Collections.sort(list,Collections.reverseOrder());
    }



}