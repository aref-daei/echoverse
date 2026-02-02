package util;

public class LinkedBinaryTree<E> {

    protected static class Node<E> {
        E element;
        Node<E> left, right, parent;

        Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    protected Node<E> root;
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
        return a == null ? b == null : a.equals(b);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString();
    }

    private void inOrder(Node<E> node, StringBuilder sb) {
        if (node == null) return;
        inOrder(node.left, sb);
        sb.append(node.element).append("\n");
        inOrder(node.right, sb);
    }
}
