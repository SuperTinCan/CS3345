import java.io.Serializable;

public class BrowserArrayList<T> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = 1L;
    
    private T[] array;
    private int front, rear, size, capacity;
    
    @SuppressWarnings("unchecked")
    public BrowserArrayList(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }
    
    public void add(T data) { // Add to rear
        if (size == capacity) resize();
        rear = (rear + 1) % capacity; // Modular indexing
        array[rear] = data;
        size++;
    }
    
    public T remove() { // Remove from front
        if (size == 0) throw new java.util.NoSuchElementException("Queue is empty");
        T data = array[front];
        front = (front + 1) % capacity; // Modular indexing
        size--;
        return data;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    @SuppressWarnings("unchecked")
    private void resize() { // Resize the array
        int newCapacity = capacity * 2; // Double the capacity
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) { // Copy elements to new array
            newArray[i] = array[(front + i) % capacity];
        }
        array = newArray;
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
    }
    
    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private int index = 0;
            
            @Override
            public boolean hasNext() {
                return index < size;
            }
            
            @Override
            public T next() {
                if (!hasNext()) throw new java.util.NoSuchElementException();
                T data = array[(front + index) % capacity];
                index++;
                return data;
            }
        };
    }
}
