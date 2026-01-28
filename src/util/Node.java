package util;

public class Node<E> {
    public E element;
    public Node<E> next;

    public Node(E element) {
        this.element = element;
        this.next = null;
    }
}
