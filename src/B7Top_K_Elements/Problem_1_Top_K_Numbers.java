package B7Top_K_Elements;

// Problem Statement: Top 'K' Numbers (easy)
// LeetCode Question:

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * To find the
 * Top K largest elements in a Java array or list, the most efficient and standard approach is to use a Min-Heap, implemented by the PriorityQueue class.
 * Why use a Min-Heap?
 * A min-heap keeps the smallest element at the top. We maintain a heap of size K. As we iterate through the array:
 * We add elements to the heap.
 * If the heap size exceeds K, we remove the smallest element (the top of the min-heap).
 * After processing all elements, the K elements remaining in the heap will be the K largest elements from the entire input.
 * Time Complexity: O(N log K), where N is the total number of elements.
 * Space Complexity: O(K) to store the heap.
 */
public class Problem_1_Top_K_Numbers {
    /**
     * Finds the K largest elements in an array.
     *
     * @param nums The input array.
     * @param k The number of largest elements to find.
     * @return A list containing the K largest elements.
     */
    public static List<Integer> findTopKLargest(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length == 0) {
            return Arrays.asList();
        }

        // 1. Initialize a Min-Heap (default PriorityQueue behavior)
        // This heap will store exactly K elements at most.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 2. Iterate through all elements in the input array
        for (int num : nums) {
            // Add the current number to the heap
            minHeap.add(num);

            // 3. If the heap size exceeds K, remove the smallest element
            // This keeps only the K largest seen so far in the heap
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // 4. The minHeap now contains the K largest elements
        // Return them as a List (PriorityQueue implements Collection)
        return new ArrayList<>(minHeap);
    }

    public static void main(String[] args) {
        int[] data = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        int k = 4;
        List<Integer> topK = findTopKLargest(data, k);

        System.out.println("Original Array: " + Arrays.toString(data));
        System.out.println("Top " + k + " largest elements: " + topK);
        // Output might be in any order: [5, 6, 5, 9] or [5, 9, 6, 5]
    }
}

