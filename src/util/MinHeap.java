package util;

import model.Model;

public class MinHeap<E extends Model> extends LinkedList<E> {
    public MinHeap() {
    }

    public void insert(E e) {
        add(e);
        heapifyUp(getSize() - 1);
    }

    public E removeMin() {
        if (isEmpty()) return null;

        E min = get(0);
        E lastElement = remove(getSize() - 1);

        if (!isEmpty()) {
            set(0, lastElement);
            heapifyDown(0);
        }

        return min;
    }

    private void set(int idx, E e) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        for (int i = 0; i < idx; i++) {
            current = current.next;
        }
        current.element = e;
    }

    public E getMin() {
        if (isEmpty()) return null;
        return get(0);
    }

    private void heapifyUp(int index) {
//        while (index > 0) {
//            int parentIndex = (index - 1) / 2;
//            if (get(index) < get(parentIndex)) {
//                swap(index, parentIndex);
//                index = parentIndex;
//            } else {
//                break;
//            }
//        }
    }

    private void heapifyDown(int index) {
//        while (true) {
//            int leftChild = 2 * index + 1;
//            int rightChild = 2 * index + 2;
//            int smallest = index;
//
//            if (leftChild < getSize() && get(leftChild) < get(smallest)) {
//                smallest = leftChild;
//            }
//
//            if (rightChild < getSize() && get(rightChild) < get(smallest)) {
//                smallest = rightChild;
//            }
//
//            if (smallest != index) {
//                swap(index, smallest);
//                index = smallest;
//            } else {
//                break;
//            }
//        }
    }

    private int compareId(String id1, String id2) {
        try {
            int num1 = Integer.parseInt(id1);
            int num2 = Integer.parseInt(id2);
            return Integer.compare(num1, num2);
        } catch (NumberFormatException e) {
            return id1.compareTo(id2);
        }
    }

    private void swap(int i, int j) {
        E temp = get(i);
        set(i, get(j));
        set(j, temp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            sb.append(get(i).getId());
            if (i < getSize() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
