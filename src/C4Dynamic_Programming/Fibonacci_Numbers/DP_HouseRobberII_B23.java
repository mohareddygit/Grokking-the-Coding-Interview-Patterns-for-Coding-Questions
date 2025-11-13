package C4Dynamic_Programming.Fibonacci_Numbers;

/**
 * Leetcode 213: House Robber II, which is an extension of House Robber I — but with a circular constraint.
 */
public class DP_HouseRobberII_B23 {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        // Case 1: Rob houses from 0 to n-2 (exclude last)
        // Case 2: Rob houses from 1 to n-1 (exclude first)
        return Math.max(robLinear(nums, 0, n - 2), robLinear(nums, 1, n - 1));
    }

    // Helper to run House Robber I logic on a linear subarray
    private int robLinear(int[] nums, int start, int end) {
        int prev1 = 0, prev2 = 0;

        for (int i = start; i <= end; i++) {
            int temp = prev1;
            prev1 = Math.max(prev1, prev2 + nums[i]);
            prev2 = temp;
        }

        return prev1;
    }
}
