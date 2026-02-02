package util;

import java.util.Objects;

public class LinkedBinaryTree<E> {

    protected static class Node<E> {
        public E element;
        public Node<E> left, right, parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean hasChildren() {
            return left != null || right != null;
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

    protected boolean isInternal(Node<E> node) {
        return node.left != null || node.right != null;
    }

    protected boolean isExternal(Node<E> node) {
        return node.left == null && node.right == null;
    }

    protected boolean isRoot(Node<E> node) {
        return node == root;
    }

    public void addRoot(E element) {
        if (!isEmpty()) {
            throw new IllegalStateException("Tree already has a root");
        }
        root = new Node<>(element, null);
        size = 1;
    }

    public void addLeft(E parent, E element) {
        Node<E> node = find(parent);
        if (node == null) {
            throw new IllegalArgumentException("Parent cannot be null");
        }
        if (node.left != null) {
            throw new IllegalArgumentException("Left child already exists");
        }
        node.left = new Node<>(element, node);
        size++;
    }

    public void addRight(E parent, E element) {
        Node<E> node = find(parent);
        if (node == null) {
            throw new IllegalArgumentException("Parent cannot be null");
        }
        if (node.right != null) {
            throw new IllegalArgumentException("Right child already exists");
        }
        node.right = new Node<>(element, node);
        size++;
    }

    public E remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }
        Node<E> last = lastNode();
        E e = last.element;
        unlink(last);
        size--;
        if (size == 0) root = null;
        return e;
    }

    public E remove(E element) {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }

        Node<E> node = find(element);
        if (node == null) {
            throw new IllegalArgumentException("Element not found");
        }

        Node<E> last = lastNode();
        E removedElement = node.element;

        if (node != last) {
            node.element = last.element;
        }

        unlink(last);
        size--;
        if (size == 0) root = null;

        return removedElement;
    }

    private void unlink(Node<E> node) {
        if (node == root) {
            root = null;
            return;
        }
        Node<E> p = node.parent;
        if (p.left == node) p.left = null;
        else p.right = null;
        node.parent = null;
    }

    private Node<E> lastNode() {
        Queue<Node<E>> q = new Queue<>();
        q.offer(root);
        Node<E> current = null;

        while (!q.isEmpty()) {
            current = q.poll();
            if (current.left != null) q.offer(current.left);
            if (current.right != null) q.offer(current.right);
        }
        return current;
    }

    protected Node<E> bfs(E element) {
        if (root == null) return null;

        Queue<Node<E>> q = new Queue<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node<E> n = q.poll();
            if (equals(element, n.element)) return n;
            if (n.left != null) q.offer(n.left);
            if (n.right != null) q.offer(n.right);
        }
        return null;
    }

    protected Node<E> dfs(E element) {
        if (root == null) return null;

        Stack<Node<E>> s = new Stack<>();
        s.push(root);

        while (!s.isEmpty()) {
            Node<E> n = s.pop();
            if (equals(element, n.element)) return n;
            if (n.right != null) s.push(n.right);
            if (n.left != null) s.push(n.left);
        }
        return null;
    }

    protected Node<E> find(E element) {
        return bfs(element);
    }

    private boolean equals(E a, E b) {
        return Objects.equals(a, b);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb, "", "");
        return sb.toString();
    }

    private void buildString(Node<E> node, StringBuilder sb, String prefix, String childrenPrefix) {
        if (node != null) {
            sb.append(prefix);
            sb.append(node.element);
            sb.append("\n");

            if (node.left != null || node.right != null) {
                if (node.right != null) {
                    buildString(node.right, sb, childrenPrefix + "├── ", childrenPrefix + "│   ");
                }
                if (node.left != null) {
                    buildString(node.left, sb, childrenPrefix + "└── ", childrenPrefix + "    ");
                }
            }
        }
    }
}
