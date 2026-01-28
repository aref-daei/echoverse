package util;

public class LinkedBinaryTree<E> {

    protected TreeNode<E> root;
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

    public TreeNode<E> root() {
        return root;
    }

    public TreeNode<E> parent(TreeNode<E> node) {
        return node.parent;
    }

    public TreeNode<E> left(TreeNode<E> node) {
        return node.left;
    }

    public TreeNode<E> right(TreeNode<E> node) {
        return node.right;
    }

    public boolean isInternal(TreeNode<E> node) {
        return node.left != null || node.right != null;
    }

    public boolean isExternal(TreeNode<E> node) {
        return node.left == null && node.right == null;
    }

    public boolean isRoot(TreeNode<E> node) {
        return node == root;
    }

    public void addRoot(E element) {
        if (!isEmpty())
            throw new IllegalStateException("Tree already has a root");

        root = new TreeNode<>(element, null);
        size = 1;
    }

    public void addLeft(TreeNode<E> parent, E element) {
        if (parent.left != null)
            throw new IllegalArgumentException("Left child already exists");

        parent.left = new TreeNode<>(element, parent);
        size++;
    }

    public void addRight(TreeNode<E> parent, E element) {
        if (parent.right != null)
            throw new IllegalArgumentException("Right child already exists");

        parent.right = new TreeNode<>(element, parent);
        size++;
    }

    public E set(TreeNode<E> node, E element) {
        E old = node.element;
        node.element = element;
        return old;
    }
}
