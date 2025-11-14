package B4Graph;

import java.util.*;

/**
 * 417. Pacific Atlantic Water Flow
 * The most efficient approach to solve the "Pacific Atlantic Water Flow" problem is to use Depth First Search (DFS) or Breadth First Search (BFS) starting from the ocean borders inwards.
 * Key Idea: Reverse Flow
 * Instead of checking if water from every internal cell can reach both oceans (inefficient), we reverse the logic. Water can flow from a cell to a neighbor if the neighbor's height is less than or equal to the current cell's height. Reversed, this means we can trace a path from the ocean inward to a cell if the neighbor's height is greater than or equal to the current cell's height.
 * We perform two separate traversals:
 * Pacific DFS: Start DFS from all cells on the top row and left column, marking all reachable cells in a pacificReachable matrix.
 * Atlantic DFS: Start DFS from all cells on the bottom row and right column, marking all reachable cells in an atlanticReachable matrix.
 * Intersection: The final result is the set of cells that are marked as reachable in both matrices
 *
 */

/**
 * Time Complexity: O(M * N), where M is the number of rows and N is the number of columns. We visit each cell at most twice (once for the Pacific DFS and once for the Atlantic DFS).
 * Space Complexity: O(M * N) for the two boolean matrices used to store reachability results and for the recursion call stack in the worst case
 */
public class Gra_DFSPacificAtlantic_B29 {
    private int rows;
    private int cols;
    private int[][] heights;
    private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return Collections.emptyList();
        }

        this.heights = heights;
        this.rows = heights.length;
        this.cols = heights[0].length;
        boolean[][] pacificReachable = new boolean[rows][cols];
        boolean[][] atlanticReachable = new boolean[rows][cols];

        // Start DFS from Pacific borders (top and left)
        for (int c = 0; c < cols; c++) {
            dfs(0, c, pacificReachable); // Top row
            dfs(rows - 1, c, atlanticReachable); // Bottom row (Atlantic)
        }
        for (int r = 0; r < rows; r++) {
            dfs(r, 0, pacificReachable); // Left column (Pacific)
            dfs(r, cols - 1, atlanticReachable); // Right column (Atlantic)
        }

        // Find the intersection of cells reachable from both oceans
        List<List<Integer>> commonCells = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacificReachable[r][c] && atlanticReachable[r][c]) {
                    commonCells.add(Arrays.asList(r, c));
                }
            }
        }

        return commonCells;
    }

    private void dfs(int r, int c, boolean[][] reachable) {
        // If already visited from this ocean, return
        if (reachable[r][c]) {
            return;
        }

        reachable[r][c] = true;

        // Explore neighbors
        for (int[] dir : directions) {
            int newRow = r + dir[0];
            int newCol = c + dir[1];

            // Check boundaries and height condition (can flow inward to higher/equal height)
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                    heights[newRow][newCol] >= heights[r][c]) {
                dfs(newRow, newCol, reachable);
            }
        }
    }
}
