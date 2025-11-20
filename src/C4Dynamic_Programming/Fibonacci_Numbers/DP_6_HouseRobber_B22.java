package C4Dynamic_Programming.Fibonacci_Numbers;

// Problem Statement: House thief
// LeetCode Question: 198. House Robber

public class DP_6_HouseRobber_B22 {


    /**
     * Time: O(n)
     *
     * Space: O(n) for DP array, or O(1) for optimized version.
     * @param nums
     * @return
     */
    // Bottom-up Dynamic Programming Approach
    public int robDPBottomUp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // dp[i] = max money that can be robbed up to house i
        int[] dp = new int[nums.length];
        dp[0] = nums[0]; // rob first house
        dp[1] = Math.max(nums[0], nums[1]); // rob max of first two houses

        // Build solution bottom-up
        for (int i = 2; i < nums.length; i++) {
            // Either rob current house + dp[i-2], or skip current house (dp[i-1])
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    // Memory optimization Approach - O(1)
    public int robDPBottomUpMemOpt(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int prev1 = Math.max(nums[0], nums[1]); // best up to house 1
        int prev2 = nums[0];                    // best up to house 0
        int result = prev1;

        for (int i = 2; i < nums.length; i++) {
            result = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = result;
        }

        return result;
    }



    // Brute Force Approach
    public int findMaxSteal(int[] wealth) {
        return findMaxStealRecursive(wealth, 0);
    }

    private int findMaxStealRecursive(int[] wealth, int currentIndex) {
        if( currentIndex >= wealth.length)
            return 0;

        // steal from current house and skip one to steal from the next house
        int stealCurrent = wealth[currentIndex] + findMaxStealRecursive(wealth, currentIndex + 2);
        // skip current house to steel from the adjacent house
        int skipCurrent = findMaxStealRecursive(wealth, currentIndex + 1);

        return Math.max(stealCurrent, skipCurrent);
    }

    // Top-down Dynamic Programming with Memoization Approach
    public int findMaxSteal_1(int[] wealth) {
        int dp[] = new int[wealth.length];
        return findMaxStealRecursive(dp, wealth, 0);
    }

    private int findMaxStealRecursive(int[] dp, int[] wealth, int currentIndex) {
        if( currentIndex >= wealth.length)
            return 0;

        if(dp[currentIndex] == 0) {
            // steal from current house and skip one to steal next
            int stealCurrent = wealth[currentIndex] + findMaxStealRecursive(dp, wealth, currentIndex + 2);
            // skip current house to steel from the adjacent house
            int skipCurrent = findMaxStealRecursive(dp, wealth, currentIndex + 1);

            dp[currentIndex] = Math.max(stealCurrent, skipCurrent);
        }
        return dp[currentIndex];
    }


}
