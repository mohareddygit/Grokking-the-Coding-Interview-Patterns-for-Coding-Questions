package B5Heap;

import java.util.PriorityQueue;

public class MinHeapExample {
    public static void main(String[] args) {
        // Default behavior for Integers is a Min-Heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.offer(10);
        minHeap.offer(2);
        minHeap.offer(5);
        minHeap.offer(15);
        minHeap.offer(1);

        System.out.println("Min-Heap elements (order varies internally): " + minHeap);
        // poll() retrieves and removes the smallest element
        System.out.println("Polling smallest element: " + minHeap.poll()); // Output: 1
        System.out.println("Polling next smallest: " + minHeap.poll());    // Output: 2
        System.out.println("After polling twice: " + minHeap);
    }
}
