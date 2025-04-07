import java.io.Serializable;

public class BrowserLinkedList<T> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = 1L;

    public class Node implements Serializable {
        private static final long serialVersionUID = 1L;

        T data;
        Node next, prev;

        Node(T data) {
            this.data = data;
            this.prev = this.next = null;
        }
    }

    private Node head, tail;
    private int size;

    public BrowserLinkedList() {
        head = tail = null;
        size = 0;
    }

    public void addLast(T data) {
        Node newNode = new Node(data);
        if (tail == null) { // Add to tail/head if list empty
            head = tail = newNode;
        } else { // Add to tail if not empty
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public T removeLast() {
        if (size == 0) {
            throw new java.util.EmptyStackException();
        }
        T data = tail.data;
        tail = tail.prev;
        if (tail == null) { // List is empty
            head = null;
        } else { // Remove tail
            tail.next = null;
        }
        size--;
        return data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size(){
        return size;
    }

    public Node getHead() {
        return head;
    }

    @Override
    public java.util.Iterator<T> iterator() { // Start from linkedlist head and loop till tail/null
        return new java.util.Iterator<T>() {
            private Node current = head;
            
            @Override
            public boolean hasNext() {
                return current != null;
            }
            
            @Override
            public T next() {
                if (!hasNext()) throw new java.util.NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}