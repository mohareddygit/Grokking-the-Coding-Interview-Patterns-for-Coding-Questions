package B9Greedy_Algorithm;

/**
 * 53. Maximum Subarray
 * Given an integer array nums, find the subarray with the largest sum, and return i
 * using Kadane’s Algorithm, which is a classic dynamic programming technique.
 * Explanation
 * Idea: At each index, decide whether to:
 *
 * Extend the previous subarray (currentSum + nums[i]), or
 *
 * Start a new subarray at nums[i].
 *
 * currentSum tracks the best sum ending at index i.
 *
 * maxSum tracks the overall best sum seen so far.
 */
public class GADP_MaximumSubarray_B5 {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];      // Stores the maximum subarray sum found so far
        int currentSum = nums[0];  // Stores the current subarray sum

        for (int i = 1; i < nums.length; i++) {
            // Either extend the current subarray or start a new one
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }


    public int maxSubArrayWithIndices(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];

        // Track indices
        int currentStart = 0;
        int bestStart = 0;
        int bestEnd = 0;

        for (int i = 1; i < nums.length; i++) {
            // Decide whether to extend or restart
            if (nums[i] > currentSum + nums[i]) {
                currentSum = nums[i];
                currentStart = i; // restart at i
            } else {
                currentSum += nums[i]; // extend
            }

            // Update best if needed
            if (currentSum > maxSum) {
                maxSum = currentSum;
                bestStart = currentStart;
                bestEnd = i;
            }
        }

        // Print indices for clarity
        System.out.println("Max Subarray Sum = " + maxSum);
        System.out.println("Start Index = " + bestStart + ", End Index = " + bestEnd);

        return maxSum;
    }

    public static void main(String[] args) {
        GADP_MaximumSubarray_B5 solver = new GADP_MaximumSubarray_B5();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum Subarray Sum: " + solver.maxSubArray(nums)); // Output: 6
    }
}
