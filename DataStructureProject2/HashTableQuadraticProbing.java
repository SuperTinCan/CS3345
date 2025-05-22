@SuppressWarnings("unchecked")
public class HashTableQuadraticProbing<T> implements DataStructure<T> {
    private Object[] table;
    private int size;

    public HashTableQuadraticProbing(int capacity) {
        size = capacity;
        table = new Object[size];
    }

    private int hash(T key) { // hashing formula
        return Math.abs(key.hashCode()) % size;
    }

    @Override
    public void insert(T key) { // use quadratic probing by looping until it finds open slot
        int i = 0, index;
        while (i < size) {
            index = Math.abs((int)((long)hash(key) + (long)i * i) % size);
            if (table[index] == null || table[index].equals(key)) {
                table[index] = key; // if the key is empty or same, place at index
                return;
            }
            i++;
        }
    }

    @Override
    public void delete(T key) { // delete the key by setting it to null
        int i = 0, index;
        while (i < size) {
            index = (hash(key) + i * i) % size;
            if (table[index] == null) return;
            if (table[index].equals(key)) {
                table[index] = null;
                return;
            }
            i++;
        }
    }

    @Override
    public boolean contains(T key) { // check if the key is in the table
        int i = 0, index;
        while (i < size) { // loop until it finds the key or empty slot
            index = (hash(key) + i * i) % size;
            if (table[index] == null) return false;
            if (table[index].equals(key)) return true;
            i++;
        }
        return false;
    }
}
