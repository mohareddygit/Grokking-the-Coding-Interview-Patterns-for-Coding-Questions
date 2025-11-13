package B7Top_K_Elements;

// Problem Statement: Top 'K' Frequent Numbers (medium)
// LeetCode Question: 347. Top K Frequent Elements

import java.util.*;

/**
 * To find the
 * Top K frequent numbers in Java, the most efficient standard approach (LeetCode 347) uses a combination of a HashMap for frequency counting and a Min-Heap (PriorityQueue) to manage the top K candidates.
 * Algorithm: HashMap and Min-Heap
 * The overall time complexity is O(N log K), which is better than O(N log N) (sorting the entire list of frequencies).
 * Count Frequencies: Use a HashMap to count the occurrences of every number in the input array.
 * Use a Min-Heap: Initialize a PriorityQueue that sorts based on frequency (smallest frequency at the top). Store pairs of (number, frequency) in the heap.
 * Populate Heap: Iterate through the frequency map. Add each element to the min-heap. If the heap size exceeds K, remove the element with the smallest frequency (the one at the top).
 * Extract Results: The heap will contain the K most frequent elements. Extract them into a result array.
 */
public class TOPK_5_Top_K_Frequent_Numbers_B75 {
    public static int[] topKFrequent(int[] nums, int k) {
        // Step 1: Count Frequencies using a HashMap (O(N) time)
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Initialize a Min-Heap based on frequency (O(N) space)
        // The comparator ensures the element with the SMALLEST frequency is at the top (peek)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                (a, b) -> freqMap.get(a) - freqMap.get(b)
        );

        // Step 3: Populate the heap (O(N log K) time)
        for (int num : freqMap.keySet()) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove the element with the lowest frequency
            }
        }

        // Step 4: Extract the results from the heap (O(K log K) time)
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] topK = topKFrequent(nums, k);

        System.out.println("Input Array: " + Arrays.toString(nums));
        System.out.println("Top " + k + " frequent elements: " + Arrays.toString(topK)); // Output: [1, 2]

        int[] nums2 = {1, 2};
        int k2 = 2;
        int[] topK2 = topKFrequent(nums2, k2);
        System.out.println("Top " + k2 + " frequent elements: " + Arrays.toString(topK2)); // Output: [1, 2]
    }
}

