package util;

import model.Model;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<E extends Model> {
    // لیست برای نگه‌داری عناصر هیپ
    private final List<E> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // اضافه کردن عنصر جدید به هیپ
    public void insert(E e) {
        heap.add(e);
        heapifyUp(heap.size() - 1);
    }

    // حذف یک عنصر خاص از هیپ
    public E remove(E element) {
        if (isEmpty()) return null;

        int index = -1;
        for (int i = 0; i < heap.size(); i++) {
            if (element.equals(heap.get(i))) {
                index = i;
                break;
            }
        }

        if (index == -1) return null;

        if (index == heap.size() - 1) {
            return heap.removeLast();
        }

        // جایگزینی با آخرین عنصر
        heap.set(index, heap.removeLast());

        // اصلاح ساختار هیپ (HeapSort)
        heapifyUp(index);
        heapifyDown(index);

        return element;
    }

    // حذف کوچک‌ترین عنصر هیپ
    public E removeMin() {
        if (isEmpty()) return null;

        E min = heap.getFirst();
        E lastElement = heap.removeLast();

        if (!isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }

        return min;
    }

    // گرفتن کوچک‌ترین عنصر بدون حذف
    public E getMin() {
        if (isEmpty()) return null;
        return heap.getFirst();
    }

    // اصلاح هیپ به سمت بالا
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            // اگر از والد کوچک‌تر بود جابه‌جا می‌شود
            if (heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // اصلاح هیپ به سمت پایین
    private void heapifyDown(int index) {
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(smallest)) < 0) {
                smallest = leftChild;
            }

            if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(smallest)) < 0) {
                smallest = rightChild;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    // جابه‌جایی دو عنصر
    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // نمایش عناصر هیپ
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < heap.size(); i++) {
            sb.append(heap.get(i));
            if (i < heap.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString().trim();
    }
}
