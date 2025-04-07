import java.io.Serializable;

public class BrowserStack<T> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = 1L;
    
    private final BrowserLinkedList<T> stack;
    
    public BrowserStack() {
        stack = new BrowserLinkedList<>();
    }
    
    public void push(T data) {
        stack.addLast(data);
    }
    
    public T pop() {
        return stack.removeLast();
    }
    
    public T peek() {
        if (isEmpty()) throw new java.util.EmptyStackException();
        return stack.iterator().next();
    }
    
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    
    public int size() {
        return stack.size();
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return stack.iterator();
    }
}