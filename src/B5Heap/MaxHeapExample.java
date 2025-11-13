package B5Heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class MaxHeapExample {
    public static void main(String[] args) {
        // Initialize with a Comparator to reverse the natural order
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        // Alternative using lambda expression for custom objects:
        // PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        maxHeap.offer(10);
        maxHeap.offer(2);
        maxHeap.offer(5);
        maxHeap.offer(15);
        maxHeap.offer(1);

        System.out.println("Max-Heap elements (order varies internally): " + maxHeap);
        // poll() retrieves and removes the largest element
        System.out.println("Polling largest element: " + maxHeap.poll()); // Output: 15
        System.out.println("Polling next largest: " + maxHeap.poll());    // Output: 10
        System.out.println("After polling twice: " + maxHeap);
    }
}
