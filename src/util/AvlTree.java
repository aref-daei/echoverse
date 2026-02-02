package util;

import model.Model;

public class AvlTree<E extends Model> extends LinkedBinaryTree<E> {

    protected static class AvlNode<E> extends Node<E> {
        int height;

        AvlNode(E element, Node<E> parent) {
            super(element, parent);
            this.height = 1;
        }
    }

    protected AvlNode<E> insert(AvlNode<E> node, E element) {
        if (node == null) {
            size++;
            return new AvlNode<>(element, null);
        }

        if (element.compareTo(node.element) < 0) {
            node.left = insert((AvlNode<E>) node.left, element);
            node.left.parent = node;
        } else if (element.compareTo(node.element) > 0) {
            node.right = insert((AvlNode<E>) node.right, element);
            node.right.parent = node;
        } else {
            return node;
        }

        updateHeight(node);
        return balance(node);
    }

    protected AvlNode<E> delete(AvlNode<E> node, E element) {
        if (node == null) return null;

        if (element.compareTo(node.element) < 0) {
            node.left = delete((AvlNode<E>) node.left, element);
        } else if (element.compareTo(node.element) > 0) {
            node.right = delete((AvlNode<E>) node.right, element);
        } else {
            size--;
            if (node.left == null || node.right == null) {
                AvlNode<E> temp =
                        node.left != null ? (AvlNode<E>) node.left : (AvlNode<E>) node.right;
                if (temp == null) return null;
                temp.parent = node.parent;
                return temp;
            } else {
                AvlNode<E> successor = min((AvlNode<E>) node.right);
                node.element = successor.element;
                node.right = delete((AvlNode<E>) node.right, successor.element);
            }
        }

        updateHeight(node);
        return balance(node);
    }

    protected AvlNode<E> balance(AvlNode<E> node) {
        int bf = balanceFactor(node);

        if (bf > 1) {
            if (balanceFactor((AvlNode<E>) node.left) < 0)
                node.left = rotateLeft((AvlNode<E>) node.left);
            return rotateRight(node);
        }

        if (bf < -1) {
            if (balanceFactor((AvlNode<E>) node.right) > 0)
                node.right = rotateRight((AvlNode<E>) node.right);
            return rotateLeft(node);
        }
        return node;
    }

    protected AvlNode<E> rotateRight(AvlNode<E> y) {
        AvlNode<E> x = (AvlNode<E>) y.left;
        AvlNode<E> t2 = (AvlNode<E>) x.right;

        x.right = y;
        y.left = t2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    protected AvlNode<E> rotateLeft(AvlNode<E> x) {
        AvlNode<E> y = (AvlNode<E>) x.right;
        AvlNode<E> t2 = (AvlNode<E>) y.left;

        y.left = x;
        x.right = t2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    protected void updateHeight(AvlNode<E> n) {
        n.height = Math.max(height(n.left), height(n.right)) + 1;
    }

    protected int height(Node<E> n) {
        return n == null ? 0 : ((AvlNode<E>) n).height;
    }

    protected int balanceFactor(AvlNode<E> n) {
        return height(n.left) - height(n.right);
    }

    protected AvlNode<E> min(AvlNode<E> n) {
        while (n.left != null) n = (AvlNode<E>) n.left;
        return n;
    }

    @Override
    public void add(E element) {
        if (root == null) {
            root = new AvlNode<>(element, null);
            size = 1;
            return;
        }
        root = insert((AvlNode<E>) root, element);
    }

    @Override
    public void remove(E element) {
        root = delete((AvlNode<E>) root, element);
    }

    public E findById(String id) {
        AvlNode<E> current = (AvlNode<E>) root;
        while (current != null) {

            if (id.compareTo(current.element.getId()) == 0)
                return current.element;
            current = id.compareTo(current.element.getId()) < 0
                    ? (AvlNode<E>) current.left
                    : (AvlNode<E>) current.right;
        }
        return null;
    }
}
