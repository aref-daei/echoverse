package util;

public class TreeNode<E> {
    public E element;
    public TreeNode<E> left;
    public TreeNode<E> right;
    public TreeNode<E> parent;

    public TreeNode(E element, TreeNode<E> parent) {
        this.element = element;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }
}
