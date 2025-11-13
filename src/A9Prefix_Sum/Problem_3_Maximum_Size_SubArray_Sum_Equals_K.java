package A9Prefix_Sum;

// Problem Statement: Maximum Size Subarray Sum Equals k (medium)
// LeetCode Question: 325. Maximum Size Subarray Sum Equals k

import java.util.HashMap;
import java.util.Map;

/**
 * Using Prefix Sums and a HashMap
 * The most efficient solution uses the prefix sum technique with a HashMap. The time complexity is O(N).
 * The key insight is: if the cumulative sum up to the current index i is sum_i, and we previously encountered a cumulative sum sum_j such that sum_i - sum_j == k, then the subarray between index j+1 and i has a sum of exactly k. We use the HashMap to quickly look up the index j of previous sums.
 */

/**
 *
 can it be efficiently solved with sliding window
 November 08, 2025
 No, this specific problem (LeetCode 325: Maximum Size Subarray Sum Equals K with potential negative numbers)
 cannot be efficiently solved with the standard dynamic sliding window technique (the two-pointer approach).
 Why the standard sliding window fails here
 The traditional dynamic sliding window approach requires that the values in the array are all positive (or at least non-negative).
 The sliding window works because when the window sum exceeds the target, we can safely shrink the window from the left, confident that the sum will decrease.
 However, this problem allows negative numbers. The inclusion of negative numbers breaks the fundamental assumption of the sliding window technique:
 Shrinking the window might increase the sum: If you remove a large negative number from the left of the window, the sum goes up.
 Expanding the window might decrease the sum: If you add a negative number to the right of the window, the sum goes down.
 Because the sum can fluctuate unpredictably when the window size changes, you cannot use the simple two-pointer sliding window to guarantee finding the optimal maximum length subarray.
 The Required Approach
 The only efficient way to solve this problem is using the Prefix Sums and HashMap approach, which achieves an optimal O(N) time complexity. This approach handles both positive and negative numbers correctly because it tracks all cumulative sums encountered.
 */
public class Problem_3_Maximum_Size_SubArray_Sum_Equals_K {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int currentSum = 0;
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];

            // Case 1: The subarray from the beginning (index 0) to the current index sums to k
            if (currentSum == k) {
                maxLength = Math.max(maxLength, i + 1);
            }
            // Case 2: Check if a previous sum exists such that currentSum - previousSum = k
            else if (map.containsKey(currentSum - k)) {
                // If found, calculate the length (current index - index of the previous sum)
                int length = i - map.get(currentSum - k);
                maxLength = Math.max(maxLength, length);
            }

            // Store the current sum and its *first* occurrence index if it's not already in the map.
            // Storing the first occurrence ensures we always find the *maximum* length subarray.
            if (!map.containsKey(currentSum)) {
                map.put(currentSum, i);
            }
        }

        return maxLength;
    }
}

