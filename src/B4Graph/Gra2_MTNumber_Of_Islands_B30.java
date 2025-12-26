package B4Graph;

// Problem Statement: Number of Islands (easy)
// LeetCode Question: 200. Number of Islands
/**
 * This problem can be efficiently solved using a graph traversal algorithm,
 * such as Depth-First Search (DFS) or Breadth-First Search (BFS).
 * The general approach is to iterate through each cell in the grid.
 * When a land cell ('1') is found, it signifies the beginning of a new island.
 * We increment the island count and then use DFS (or BFS) to "sink" (mark as '0') all connected land cells,
 * ensuring they are not counted again.
 * DFS Java Solution (Recursive)
 * The recursive DFS approach uses the call stack for traversal and
 * modifies the input grid in-place to mark visited land cells as water ('0').
 */

/**
 * Time and Space Complexity
 * Time Complexity: O(M * N), where M is the number of rows and N is the number of columns. We visit each cell in the grid at most once.
 * Space Complexity: O(M * N) in the worst-case scenario. This space is used by the implicit recursion call stack when the grid is filled with land, and the DFS goes to a depth equal to the total number of cells. If the grid can be modified in-place, no additional auxiliary space is required for a visited matrix.
 */
public class Gra2_MTNumber_Of_Islands_B30 {
    // Depth First Approach - Recursive

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numRows = grid.length;
        int numCols = grid[0].length;
        int islandCount = 0;

        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (grid[r][c] == '1') {
                    // Found a new island, increment count and sink the entire island
                    islandCount++;
                    sinkIsland(grid, r, c);
                }
            }
        }

        return islandCount;
    }

    private void sinkIsland(char[][] grid, int r, int c) {
        // Base cases for recursion: out of bounds or water cell
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == '0') {
            return;
        }

        // Mark the current cell as visited/sunk ('0')
        grid[r][c] = '0';

        // Recursively call DFS for all 4 adjacent directions (horizontal and vertical)
        sinkIsland(grid, r + 1, c); // Down
        sinkIsland(grid, r - 1, c); // Up
        sinkIsland(grid, r, c + 1); // Right
        sinkIsland(grid, r, c - 1); // Left
    }
}