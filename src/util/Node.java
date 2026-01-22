package util;

public class Node<E> {
    E element;
    Node<E> next;

    public Node(E element) {
        this.element = element;
        this.next = null;
    }
}
