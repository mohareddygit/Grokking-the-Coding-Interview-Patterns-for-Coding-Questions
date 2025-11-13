package B7Top_K_Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Top K smallest elements in a Java array or list, the most efficient and standard approach is to use a Max-Heap, implemented by the PriorityQueue class with a custom Comparator.
 * Why use a Max-Heap?
 * A max-heap keeps the largest element at the top. We maintain a heap of size K. As we iterate through the array:
 * We add elements to the heap.
 * If the heap size exceeds K, we remove the largest element (the top of the max-heap).
 * After processing all elements, the K elements remaining in the heap will be the K smallest elements from the entire input because any larger element encountered was eventually discarded.
 * Time Complexity: O(N log K), where N is the total number of elements.
 * Space Complexity: O(K) to store the heap.
 */
public class TopKSmallestElements {

    /**
     * Finds the K smallest elements in an array.
     *
     * @param nums The input array.
     * @param k The number of smallest elements to find.
     * @return A list containing the K smallest elements.
     */
    public static List<Integer> findTopKSmallest(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length == 0) {
            return Arrays.asList();
        }

        // 1. Initialize a Max-Heap (using reverseOrder comparator)
        // This heap will store exactly K elements at most, with the largest at the top.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // 2. Iterate through all elements in the input array
        for (int num : nums) {
            // Add the current number to the heap
            maxHeap.add(num);

            // 3. If the heap size exceeds K, remove the largest element (top of the max-heap)
            // This keeps only the K smallest seen so far in the heap
            if (maxHeap.size() > k) {
                maxHeap.poll(); 
            }
        }

        // 4. The maxHeap now contains the K smallest elements
        // Return them as a List (PriorityQueue implements Collection)
        return new ArrayList<>(maxHeap);
    }

    public static void main(String[] args) {
        int[] data = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        int k = 4;
        List<Integer> topKSmallest = findTopKSmallest(data, k);
        
        System.out.println("Original Array: " + Arrays.toString(data));
        System.out.println("Top " + k + " smallest elements: " + topKSmallest);
        // Output might be in any order: [3, 1, 1, 2] or similar
    }
}
