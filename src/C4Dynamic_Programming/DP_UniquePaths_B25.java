package C4Dynamic_Programming;

import java.util.Arrays;

/**
 * Leetcode 62: Unique Paths, using dynamic programming to count the number of distinct paths from the top-left to bottom-right of a grid.
 */
public class DP_UniquePaths_B25 {

    /**
     * Time: O(m × n)
     * Space: O(m × n) (can be optimized to O(n) using a 1D array).
     */
    public int uniquePaths(int m, int n) {
        // dp[i][j] = number of paths to cell (i, j)
        int[][] dp = new int[m][n];

        // First row and first column have only one path
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;

        // Fill the rest of the grid
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // Paths to (i,j) = from top + from left
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * Example Walkthrough (3×3 Grid)
     * Initial dp = [1, 1, 1] (first row).
     *
     * Row 2 update:
     *
     * j=1 → dp[1] = dp[1] + dp[0] = 1+1=2
     *
     * j=2 → dp[2] = dp[2] + dp[1] = 1+2=3 → dp = [1, 2, 3]
     *
     * Row 3 update:
     *
     * j=1 → dp[1] = 2+1=3
     *
     * j=2 → dp[2] = 3+3=6 → dp = [1, 3, 6]
     *
     * ✅ Answer = dp[2] = 6 unique paths.
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsMemoryOptimized(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // first row is all 1s

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1]; // update in place
            }
        }

        return dp[n - 1];
    }
}
