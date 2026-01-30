package util;

public class TNode<E> {
    public E element;
    public TNode<E> left;
    public TNode<E> right;
    public TNode<E> parent;

    public TNode(E element, TNode<E> parent) {
        this.element = element;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }
}
