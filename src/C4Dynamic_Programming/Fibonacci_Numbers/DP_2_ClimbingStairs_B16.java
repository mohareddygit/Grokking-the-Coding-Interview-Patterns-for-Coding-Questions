package C4Dynamic_Programming.Fibonacci_Numbers;

// Problem Statement: Problem_2_Staircase
// LeetCode Question: 70. Climbing Stairs

public class DP_2_ClimbingStairs_B16 {


    // Bottom-up Dynamic Programming Approach
    public int climbStairs(int n) {
        // Base cases:
        // If there's only 1 step, only 1 way (take 1 step).
        // If there are 2 steps, 2 ways (1+1 or 2).
        if (n <= 2) return n;

        // dp[i] will store number of ways to reach step i
        int[] dp = new int[n + 1];
        dp[1] = 1; // one way to reach step 1
        dp[2] = 2; // two ways to reach step 2

        // Build the solution bottom-up
        for (int i = 3; i <= n; i++) {
            // To reach step i, you can come from step (i-1) or (i-2)
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // Memory optimization Approach - O(1)
    public int climbStairsMemOptimized(int n) {
        if (n <= 2) return n;

        int oneStepBefore = 2; // ways to reach step 2
        int twoStepsBefore = 1; // ways to reach step 1
        int result = 0;

        // Iteratively compute ways to reach each step
        for (int i = 3; i <= n; i++) {
            result = oneStepBefore + twoStepsBefore;
            twoStepsBefore = oneStepBefore;
            oneStepBefore = result;
        }

        return result;
    }
}
