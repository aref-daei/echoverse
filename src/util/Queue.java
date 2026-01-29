package util;

public class Queue<E> extends LinkedList<E> {
    private Node<E> tail;
    
    public Queue() {
        this.tail = null;
    }

    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E element = head.element;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return element;
    }

    public E remove(E element) {
        if (isEmpty()) return null;
        
        if (head.element.equals(element)) {
            return dequeue();
        }
        
        Node<E> current = head;
        while (current.next != null) {
            if (current.next.element.equals(element)) {
                Node<E> toRemove = current.next;
                current.next = current.next.next;
                if (toRemove == tail) {
                    tail = current;
                }
                size--;
                return toRemove.element;
            }
            current = current.next;
        }
        return null;
    }
}
