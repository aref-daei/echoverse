package util;

public class LinkedBinaryTree<E> {

    // نود درخت
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

    // سازنده
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

    // اضافه کردن عنصر به صورت level-order
    public void add(E element) {
        if (root == null) {
            root = new Node<>(element, null);
            size = 1;
            return;
        }

        Queue<Node<E>> queue = new Queue<>();
        queue.offer(root);

        // پیدا کردن اولین جای خالی
        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();

            if (current.left == null) {
                current.left = new Node<>(element, current);
                size++;
                return;
            } else {
                queue.offer(current.left);
            }

            if (current.right == null) {
                current.right = new Node<>(element, current);
                size++;
                return;
            } else {
                queue.offer(current.right);
            }
        }
    }

    // اضافه کردن فرزند برای یک والد مشخص
    public void add(E parent, E element) {
        Node<E> node = find(parent);
        if (node == null) {
            throw new IllegalArgumentException("Parent not found");
        }

        if (node.left == null) {
            node.left = new Node<>(element, node);
        } else if (node.right == null) {
            node.right = new Node<>(element, node);
        } else {
            throw new IllegalStateException("Parent already has two children");
        }

        size++;
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
            throw new IllegalArgumentException("Parent not found");
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
            throw new IllegalArgumentException("Parent not found");
        }
        if (node.right != null) {
            throw new IllegalArgumentException("Right child already exists");
        }
        node.right = new Node<>(element, node);
        size++;
    }

    // حذف آخرین نود در level-order
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

    // حذف یک عنصر مشخص با حفظ identity نود
    public void remove(E element) {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }

        Node<E> node = find(element);
        if (node == null) {
            throw new IllegalArgumentException("Element not found");
        }

        removeNode(node);
        size--;

        if (size == 0) root = null;
    }

    // حذف نود با توجه به تعداد فرزندها
    private void removeNode(Node<E> node) {
        // نود برگ
        if (node.left == null && node.right == null) {
            unlink(node);
            return;
        }

        // فقط فرزند چپ
        if (node.left != null && node.right == null) {
            replaceWithChild(node, node.left);
            return;
        }

        // فقط فرزند راست
        if (node.left == null) {
            replaceWithChild(node, node.right);
            return;
        }

        // دو فرزند
        Node<E> replacement = node.left;

        // اتصال زیر درخت راست
        Node<E> rightMost = replacement;
        while (rightMost.right != null) {
            rightMost = rightMost.right;
        }
        rightMost.right = node.right;
        node.right.parent = rightMost;

        replaceWithChild(node, replacement);
    }

    // جایگزین کردن نود با یکی از فرزندانش
    private void replaceWithChild(Node<E> node, Node<E> child) {
        if (node == root) {
            root = child;
            child.parent = null;
            return;
        }

        Node<E> p = node.parent;

        if (p.left == node) {
            p.left = child;
        } else {
            p.right = child;
        }

        child.parent = p;

        node.parent = null;
        node.left = null;
        node.right = null;
    }

    // جدا کردن نود برگ
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

    // پیدا کردن آخرین نود در level-order
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

    // جستجو به صورت BFS
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

    // جستجو به صورت DFS
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

    // متد اصلی جستجو
    protected Node<E> find(E element) {
        return bfs(element);
    }

    // جستجو با BFS و برگرداندن عنصر
    public E findByBfs(E element) {
        Node<E> result = bfs(element);
        if (result == null) return null;
        return result.element;
    }

    // جستجو با DFS و برگرداندن عنصر
    public E findByDfs(E element) {
        Node<E> result = dfs(element);
        if (result == null) return null;
        return result.element;
    }

    // مقایسه امن عناصر
    private boolean equals(E a, E b) {
        return a == null ? b == null : a.equals(b);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString().trim();
    }

    // پیمایش inorder برای نمایش درخت
    private void inOrder(Node<E> node, StringBuilder sb) {
        if (node == null) return;
        inOrder(node.left, sb);
        sb.append(node.element).append("\n");
        inOrder(node.right, sb);
    }

    // نمایش درخت به صورت گرافیکی
    public String displayTree() {
        if (isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        printTree(root, 0, sb);
        return sb.toString();
    }

    // چاپ درخت با تورفتگی
    private void printTree(Node<E> node, int level, StringBuilder sb) {
        if (node == null) return;

        if (level != 0) sb.append("\n");
        sb.append("   ".repeat(Math.max(0, level)));
        sb.append("|-- ").append("ID: ").append(node.element);

        printTree(node.left, level + 1, sb);
        printTree(node.right, level + 1, sb);
    }
}
