package util;

import model.Model;

public class AvlTree<E extends Model> extends LinkedBinaryTree<E> {

    private int height(TNode<E> n) {
        return n == null ? 0 : ((AvlNode<E>) n).height;
    }

    private void updateHeight(TNode<E> n) {
        ((AvlNode<E>) n).height =
                1 + Math.max(height(n.left), height(n.right));
    }

    private int balance(TNode<E> n) {
        return height(n.left) - height(n.right);
    }

    private int compare(E a, E b) {
        return a.getId().compareTo(b.getId());
    }

    private TNode<E> rotateRight(TNode<E> y) {
        TNode<E> x = y.left;
        TNode<E> t2 = x.right;

        x.right = y;
        y.left = t2;

        if (t2 != null) t2.parent = y;

        x.parent = y.parent;
        y.parent = x;

        if (x.parent == null) root = x;
        else if (x.parent.left == y) x.parent.left = x;
        else x.parent.right = x;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private TNode<E> rotateLeft(TNode<E> x) {
        TNode<E> y = x.right;
        TNode<E> t2 = y.left;

        y.left = x;
        x.right = t2;

        if (t2 != null) t2.parent = x;

        y.parent = x.parent;
        x.parent = y;

        if (y.parent == null) root = y;
        else if (y.parent.left == x) y.parent.left = y;
        else y.parent.right = y;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    public void insert(E e) {
        root = insert(root, e, null);
    }

    private TNode<E> insert(TNode<E> node, E e, TNode<E> parent) {
        if (node == null) {
            size++;
            return new AvlNode<>(e, parent);
        }

        int cmp = compare(e, node.element);

        if (cmp < 0)
            node.left = insert(node.left, e, node);
        else if (cmp > 0)
            node.right = insert(node.right, e, node);
        else
            return node; // duplicate id

        updateHeight(node);
        return rebalance(node);
    }

    public void delete(E e) {
        root = delete(root, e.getId());
    }

    private TNode<E> delete(TNode<E> node, String id) {
        if (node == null) return null;

        int cmp = id.compareTo(node.element.getId());

        if (cmp < 0)
            node.left = delete(node.left, id);
        else if (cmp > 0)
            node.right = delete(node.right, id);
        else {
            size--;

            if (node.left == null || node.right == null) {
                TNode<E> temp = (node.left != null) ? node.left : node.right;
                if (temp != null) temp.parent = node.parent;
                return temp;
            }

            TNode<E> successor = min(node.right);
            node.element = successor.element;
            node.right = delete(node.right, successor.element.getId());
        }

        updateHeight(node);
        return rebalance(node);
    }

    private TNode<E> min(TNode<E> n) {
        while (n.left != null) n = n.left;
        return n;
    }

    private TNode<E> rebalance(TNode<E> node) {
        int b = balance(node);

        // LL
        if (b > 1 && balance(node.left) >= 0)
            return rotateRight(node);

        // LR
        if (b > 1 && balance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RR
        if (b < -1 && balance(node.right) <= 0)
            return rotateLeft(node);

        // RL
        if (b < -1 && balance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public E search(String id) {
        TNode<E> n = root;
        while (n != null) {
            int cmp = id.compareTo(n.element.getId());
            if (cmp == 0) return n.element;
            n = (cmp < 0) ? n.left : n.right;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inorder(root, sb);
        return sb.toString().trim();
    }

    private void inorder(TNode<E> n, StringBuilder sb) {
        if (n == null) return;
        inorder(n.left, sb);
        sb.append(n.element).append(" ");
        inorder(n.right, sb);
    }
}
