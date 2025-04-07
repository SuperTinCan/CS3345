import java.io.Serializable;

public class BrowserQueue<T> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = 1L;
    
    private final BrowserArrayList<T> queue;
    
    public BrowserQueue(int capacity) {
        queue = new BrowserArrayList<>(capacity);
    }
    
    public void enqueue(T data) { // Add to rear
        queue.add(data);
    }
    
    public T dequeue() { // Remove from front
        return queue.remove();
    }
    
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    public int size() {
        return queue.size();
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return queue.iterator();
    }
}