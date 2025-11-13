package B4Graph;

import java.util.*;

/**
 * 417. Pacific Atlantic Water Flow
 *
 */
public class Gra_PacificAtlantic_B29 {
    private int rows, cols;
    private int[][] heights;
    private boolean[][] pacific, atlantic;
    private final int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        rows = heights.length;
        cols = heights[0].length;
        pacific = new boolean[rows][cols];
        atlantic = new boolean[rows][cols];

        // Start DFS from Pacific (top and left edges)
        for (int r = 0; r < rows; r++) {
            dfs(r, 0, pacific);         // Left edge
            dfs(r, cols - 1, atlantic); // Right edge
        }
        for (int c = 0; c < cols; c++) {
            dfs(0, c, pacific);         // Top edge
            dfs(rows - 1, c, atlantic); // Bottom edge
        }

        // Collect cells reachable by both oceans
        List<List<Integer>> result = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }

        return result;
    }

    // DFS from ocean edge, marking reachable cells
    private void dfs(int r, int c, boolean[][] visited) {
        visited[r][c] = true;

        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            // Check bounds and height condition
            if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) continue;
            if (visited[nr][nc]) continue;
            if (heights[nr][nc] < heights[r][c]) continue;

            dfs(nr, nc, visited);
        }
    }
}
