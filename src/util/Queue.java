package util;

public class Queue<E> extends LinkedList<E> {
    private Node<E> tail;
    
    public Queue() {
        this.tail = null;
    }

    @Override
    public void add(E e) {
        offer(e);
    }

    @Override
    public void addLast(E e) {
        offer(e);
    }

    public void offer(E e) {
        Node<E> newNode = new Node<>(e);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public E poll() {
        if (isEmpty()) return null;
        E element = head.element;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return element;
    }

    @Override
    public E remove() {
        return poll();
    }

    @Override
    public E remove(E element) {
        if (isEmpty()) return null;
        
        if (head.element.equals(element)) {
            return poll();
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

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return get(0);
    }
}
