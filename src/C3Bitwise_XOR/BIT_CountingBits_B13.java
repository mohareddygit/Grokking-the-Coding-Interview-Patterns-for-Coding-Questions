package C3Bitwise_XOR;

/**
 * Leetcode 338: Counting Bits, using dynamic programming to count the number of 1s in binary representations from 0 to n.
 */
public class BIT_CountingBits_B13 {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0; // base case

        for (int i = 1; i <= n; i++) {
            // i >> 1 removes the last bit (i / 2)
            // i & 1 adds 1 if the last bit is 1
            dp[i] = dp[i >> 1] + (i & 1);
        }

        return dp;
    }
}
