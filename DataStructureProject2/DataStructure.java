public interface DataStructure<T> {
    void insert(T key);
    void delete(T key);
    boolean contains(T key);
}
