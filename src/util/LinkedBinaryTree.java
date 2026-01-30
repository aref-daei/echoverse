package util;

public class LinkedBinaryTree<E> {

    protected TNode<E> root;
    protected int size;

    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public TNode<E> root() {
        return root;
    }

    public TNode<E> parent(TNode<E> node) {
        return node.parent;
    }

    public TNode<E> left(TNode<E> node) {
        return node.left;
    }

    public TNode<E> right(TNode<E> node) {
        return node.right;
    }

    public boolean isInternal(TNode<E> node) {
        return node.left != null || node.right != null;
    }

    public boolean isExternal(TNode<E> node) {
        return node.left == null && node.right == null;
    }

    public boolean isRoot(TNode<E> node) {
        return node == root;
    }

    public void addRoot(E element) {
        if (!isEmpty())
            throw new IllegalStateException("Tree already has a root");

        root = new TNode<>(element, null);
        size = 1;
    }

    public void addLeft(TNode<E> parent, E element) {
        if (parent.left != null)
            throw new IllegalArgumentException("Left child already exists");

        parent.left = new TNode<>(element, parent);
        size++;
    }

    public void addRight(TNode<E> parent, E element) {
        if (parent.right != null)
            throw new IllegalArgumentException("Right child already exists");

        parent.right = new TNode<>(element, parent);
        size++;
    }

    public E set(TNode<E> node, E element) {
        E old = node.element;
        node.element = element;
        return old;
    }
}
