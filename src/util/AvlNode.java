package util;

public class AvlNode<E> extends TreeNode<E> {
    int height;

    public AvlNode(E element, TreeNode<E> parent) {
        super(element, parent);
        height = 1;
    }
}
