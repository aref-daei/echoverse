package util;

public class AvlNode<E> extends TNode<E> {
    public int height;

    public AvlNode(E element, TNode<E> parent) {
        super(element, parent);
        height = 1;
    }
}
