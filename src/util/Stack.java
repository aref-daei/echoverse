package util;

public class Stack<E> extends LinkedList<E> {
    public Stack() {
    }

    public boolean push(E e) {
        addFirst(e);
        return true;
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        return removeFirst();
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return get(0);
    }
}
