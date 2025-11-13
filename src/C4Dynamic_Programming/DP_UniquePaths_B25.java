package C4Dynamic_Programming;

/**
 * Leetcode 62: Unique Paths, using dynamic programming to count the number of distinct paths from the top-left to bottom-right of a grid.
 */
public class DP_UniquePaths_B25 {
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
}
