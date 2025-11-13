package B7Top_K_Elements;

// Problem Statement: Kth Smallest Number (easy)
// LeetCode Question:

/**
 * To find the
 * Kth smallest number in an unsorted array, the most efficient approach for a general case uses a Max-Heap,
 * implemented by the PriorityQueue.
 * This is the same approach used to find the top K smallest elements,
 * but we only need to return the single element at the top of the heap at the end.
 * Algorithm: Using a Max-Heap
 * We maintain a max-heap of size exactly K. This ensures that the element at the top of the heap is the Kth smallest element encountered so far.
 * Time Complexity: O(N log K), where N is the total number of elements.
 * Space Complexity: O(K) to store the heap.
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class Problem_2_Kth_Smallest_Number {
    /**
     * Finds the Kth smallest number in an unsorted array using a max-heap.
     *
     * @param nums The input array.
     * @param k The desired rank (1-based index) of the number (e.g., k=1 is smallest).
     * @return The Kth smallest number, or -1 if invalid input.
     */
    public static int findKthSmallest(int[] nums, int k) {
        if (k <= 0 || k > nums.length) {
            return -1; // Invalid input for K
        }

        // 1. Initialize a Max-Heap (stores the largest element at the top)
        // We will store the 'k' smallest candidates in this heap.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // 2. Iterate through all elements in the input array
        for (int num : nums) {
            // Add the current number to the heap
            maxHeap.add(num);

            // 3. If the heap size exceeds K, remove the largest element
            // This maintains exactly K elements in the heap: the K smallest seen so far.
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // 4. The root of the max-heap is the Kth smallest number overall.
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        int[] data = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        int k = 4; // Find the 4th smallest number

        // Sorted array would be: {1, 1, 2, [3], 3, 4, 5, 5, 5, 6, 9}
        // The 4th smallest is 3.

        int kthSmallest = findKthSmallest(data, k);
        System.out.println("The " + k + "th smallest number is: " + kthSmallest); // Output: 3
    }
}

