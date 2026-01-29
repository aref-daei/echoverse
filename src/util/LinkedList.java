package util;

public class LinkedList<E> {
    protected Node<E> head;
    protected int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return head == null;
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
}
