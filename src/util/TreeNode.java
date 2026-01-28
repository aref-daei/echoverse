package util;

public class TreeNode<E> {
    E element;
    TreeNode<E> left;
    TreeNode<E> right;
    TreeNode<E> parent;

    public TreeNode(E element, TreeNode<E> parent) {
        this.element = element;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }
}
