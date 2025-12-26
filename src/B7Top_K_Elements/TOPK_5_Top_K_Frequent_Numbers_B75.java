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

/**
 * Comparison Table
 * Approach	                    Time Complexity	    Space Complexity	Best Use Case
 * HashMap + Min Heap	        O(n log k)	        O(n)	        When k ≪ n
 * HashMap + Bucket Sort(best)	O(n)	            O(n)	        When strict O(n) is required
 */
public class TOPK_5_Top_K_Frequent_Numbers_B75 {

    /**
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequentBucketSort(int[] nums, int k) {
        // 1. Count frequencies using a HashMap
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // 2. Create an array of lists (buckets) indexed by frequency
        // The maximum frequency can be the length of the array
        /*
        The size nums.length + 1 is deliberate:
The maximum number of times any single number can appear in the input array nums is nums.length.
For example, if nums = [5, 5, 5], the number 5 appears 3 times.
We need indices up to nums.length (index 3) to store these counts.
The + 1 ensures we have enough capacity, including the index 0 (which remains empty).
         */
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        //instead of below loop, just to create empty list first, create the empty list as part of the populate buckets
        /*for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }*/

        // 3. Populate the buckets
        for (int num : freqMap.keySet()) {
            int frequency = freqMap.get(num);
            //create empty list first
            if (buckets[frequency] == null) buckets[frequency] = new ArrayList<>();
            buckets[frequency].add(num);
        }

        /*
        Why < k The loop condition topKList.size() < k means:
         "Continue running this loop while we have collected fewer than k elements."
         Once topKList.size() reaches k, the condition becomes false (k < k is false),
         and the loop correctly terminates.

         Why <= k would be wrong (potentially).
         If the condition were topKList.size() <= k
         ("Continue while the size is less than or equal to k"),
         the loop would execute one final time when the list already contains k elements.
         In a well-structured input where exactly \(k\) elements exist with the highest frequency,
         this might just add an extra element to the list (which you would have to trim later).
         However, the goal is specifically to stop the moment you reach exactly k elements.
         */
        // 4. Extract top K elements by iterating backwards through the buckets
        List<Integer> topKList = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && topKList.size() < k; i--) {
            if (!buckets[i].isEmpty()) {
                for (int num : buckets[i]) {
                    topKList.add(num);
                    if (topKList.size() == k) {
                        break; // Stop if k elements are found
                    }
                }
            }
        }

        // Convert the List<Integer> to an int[]
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = topKList.get(i);
        }

        return result;
    }


    public static int[] topKFrequentMinHeap(int[] nums, int k) {
        // Step 1: Count Frequencies using a HashMap (O(N) time)
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Initialize a Min-Heap based on frequency (O(N) space)
        // The comparator ensures the element with the SMALLEST frequency is at the top (peek)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> freqMap.get(a) - freqMap.get(b));

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
        int[] topK = topKFrequentMinHeap(nums, k);

        System.out.println("Input Array: " + Arrays.toString(nums));
        System.out.println("Top " + k + " frequent elements: " + Arrays.toString(topK)); // Output: [1, 2]

        int[] nums2 = {1, 2};
        int k2 = 2;
        int[] topK2 = topKFrequentMinHeap(nums2, k2);
        System.out.println("Top " + k2 + " frequent elements: " + Arrays.toString(topK2)); // Output: [1, 2]
    }
}

