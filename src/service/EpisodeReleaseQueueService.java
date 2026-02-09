package service;

import model.Episode;
import util.MinHeap;

public class EpisodeReleaseQueueService {
    MinHeap<Episode> heap;

    public EpisodeReleaseQueueService() {
        heap = new MinHeap<>();
    }

    public void insert(Episode episode) {
        heap.insert(episode);
    }

    public Episode extractMin() {
        return heap.removeMin();
        // return heap.getMin();
    }

    public Episode delete(Episode episode) {
        return heap.remove(episode);
    }

    public String display() {
        return heap.toString();
    }

    public void HeapSort() {
        System.out.println("Heap sort is performed automatically!");
    }
}
