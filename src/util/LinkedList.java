package util;

public class LinkedList<E> {
    private Node<E> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public E removeFirst() {
        if (head == null) return null;
        E element = head.element;
        head = head.next;
        size--;
        return element;
    }

    public E removeAtIndex(int idx) {
        if (idx < 0 || idx >= size || head == null) return null;

        if (idx == 0) {
            return removeFirst();
        }

        Node<E> current = head;
        for (int i = 0; i < idx - 1; i++) {
            current = current.next;
        }

        E element = current.next.element;
        current.next = current.next.next;
        size--;
        return element;
    }

    public int indexOf(E element) {
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.element.equals(element)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public E getAtIndex(int idx) {
        if (idx < 0 || idx >= size) return null;

        Node<E> current = head;
        for (int i = 0; i < idx; i++) {
            current = current.next;
        }
        return current.element;
    }

    public int getSize() {
        return size;
    }

    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
        // محاسبه مجدد سایز
        size = 0;
        Node<E> current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}
