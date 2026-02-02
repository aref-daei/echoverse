package util;

public class Queue<E> extends LinkedList<E> {
    public Queue() {
    }

    public void offer(E e) {
        addLast(e);
    }

    public E poll() {
        return removeFirst();
    }

    public E peek() {
        if (isEmpty()) return null;
        return getFirst();
    }
}
