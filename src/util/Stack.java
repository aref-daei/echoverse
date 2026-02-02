package util;

public class Stack<E> extends LinkedList<E> {
    public Stack() {
        super();
    }

    public void push(E e) {
        addFirst(e);
    }

    public E pop() {
        if (isEmpty()) return null;
        return removeFirst();
    }

    public E peek() {
        if (isEmpty()) return null;
        return getFirst();
    }
}
