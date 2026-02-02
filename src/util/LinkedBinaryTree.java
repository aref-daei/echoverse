package util;

public class LinkedBinaryTree<E> {
    public static class Node<E> {
        public E element;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }
    }

    protected Node<E> root;
    private int size;

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

    public Node<E> root() {
        return root;
    }

    public Node<E> parent(Node<E> node) {
        return node.parent;
    }

    public Node<E> left(Node<E> node) {
        return node.left;
    }

    public Node<E> right(Node<E> node) {
        return node.right;
    }

    public boolean isInternal(Node<E> node) {
        return node.left != null || node.right != null;
    }

    public boolean isExternal(Node<E> node) {
        return node.left == null && node.right == null;
    }

    public boolean isRoot(Node<E> node) {
        return node == root;
    }

    public void addRoot(E element) {
        if (!isEmpty())
            throw new IllegalStateException("Tree already has a root");

        root = new Node<>(element, null);
        size = 1;
    }

    public void addLeft(Node<E> parent, E element) {
        if (parent.left != null)
            throw new IllegalArgumentException("Left child already exists");

        parent.left = new Node<>(element, parent);
        size++;
    }

    public void addRight(Node<E> parent, E element) {
        if (parent.right != null)
            throw new IllegalArgumentException("Right child already exists");

        parent.right = new Node<>(element, parent);
        size++;
    }

    public E set(Node<E> node, E element) {
        E old = node.element;
        node.element = element;
        return old;
    }
}
