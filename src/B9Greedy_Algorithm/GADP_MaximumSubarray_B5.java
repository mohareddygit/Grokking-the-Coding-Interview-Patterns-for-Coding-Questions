package B9Greedy_Algorithm;

/**
 * 53. Maximum Subarray
 * Given an integer array nums, find the subarray with the largest sum, and return i
 * using Kadane’s Algorithm, which is a classic dynamic programming technique.
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

    public static void main(String[] args) {
        GADP_MaximumSubarray_B5 solver = new GADP_MaximumSubarray_B5();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum Subarray Sum: " + solver.maxSubArray(nums)); // Output: 6
    }
}
