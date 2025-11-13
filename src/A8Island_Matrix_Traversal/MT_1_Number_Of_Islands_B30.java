package A8Island_Matrix_Traversal;

// Problem Statement: Number of Islands (easy)
// LeetCode Question: 200. Number of Islands

public class MT_1_Number_Of_Islands_B30 {
    // Depth First Approach

    public int numIslands(char[][] grid) {
        int count = 0;
        int rows = grid.length, cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);
                    count++; // Found a new island
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int r, int c) {
        int rows = grid.length, cols = grid[0].length;
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] != '1') return;

        grid[r][c] = '0'; // Mark as visited

        dfs(grid, r - 1, c); // Up
        dfs(grid, r + 1, c); // Down
        dfs(grid, r, c - 1); // Left
        dfs(grid, r, c + 1); // Right
    }
}
