package util;

public class LinkedList<E> {
    protected static class Node<E> {
        public E element;
        public Node<E> next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    protected Node<E> head;
    protected Node<E> tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        addLast(e);
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
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public E removeFirst() {
        if (head == null) return null;
        E element = head.element;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return element;
    }

    public E removeLast() {
        if (head == null) return null;

        // Just one element
        if (head == tail) {
            E element = head.element;
            head = null;
            tail = null;
            size--;
            return element;
        }

        // More than one element
        Node<E> current = head;
        while (current.next != tail) {
            current = current.next;
        }

        E element = tail.element;
        current.next = null;
        tail = current;
        size--;

        return element;
    }

    public E remove() {
        return removeFirst();
    }

    public E remove(E e) {
        if (head == null) return null;
        if (e.equals(head)) return removeFirst();

        Node<E> current = head;
        while (current.next != null) {
            if (current.next.element.equals(e)) {
                E element = current.next.element;
                current.next = current.next.next;
                size--;
                return element;
            }
            current = current.next;
        }
        return null;
    }

    public E remove(int idx) {
        if (idx < 0 || idx >= size || head == null) return null;
        if (idx == 0) return removeFirst();
        if (idx == size - 1) return removeLast();

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

    public E get(int idx) {
        if (idx < 0 || idx >= size) return null;

        Node<E> current = head;
        for (int i = 0; i < idx; i++) {
            current = current.next;
        }
        return current.element;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public boolean contains(E e) {
        Node<E> current = head;
        while (current != null) {
            if (current.element.equals(e)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.element).append("\n");
            current = current.next;
        }
        return sb.toString().trim();
    }
}
