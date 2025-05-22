
import java.util.LinkedList;

public class HashTableChaining<T> implements DataStructure<T> {
    private LinkedList<T>[] table;
    private int size;

    public HashTableChaining(int capacity) { // constructor
        size = capacity;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) { // create an array of linked lists
            table[i] = new LinkedList<>();
        }
    }

    private int hash(T key) {
        return Math.abs(key.hashCode()) % size;
    }

    @Override
    public void insert(T key) {
        int index = hash(key);
        if (!table[index].contains(key))
            table[index].add(key);
    }

    @Override
    public void delete(T key) {
        int index = hash(key);
        table[index].remove(key);
    }

    @Override
    public boolean contains(T key) {
        int index = hash(key);
        return table[index].contains(key);
    }
}
