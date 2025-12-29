package C5Backtracking;

// Problem Statement: Word Search (medium)
// LeetCode Question: 79. Word Search
// consider it as an extension problem of (LeetCode 30)Number of Islands w.r.t navigation in matrix

/*

This solution uses a "sink and restore" technique to avoid using extra space for a visited array.
Search Entry: Iterate through every cell (r,c) on the board.
If the character matches the first letter of the word, begin a DFS from that cell.

DFS Function:
Base Case: If the index reaches the length of the word, you have successfully found the whole word.
Invalid State: If the current cell is out of bounds or doesn't match the required character, return false.
Mark Visited: Temporarily change the current cell's value (e.g., to '#') to mark it as visited.
Explore: Recursively call DFS for all four neighbors (up, down, left, right).
Backtrack: Crucially, restore the cell's original character before returning,
 allowing other potential paths to use it later.

 */

public class BK_2_Word_Search_B49 {
    public boolean exist(char[][] board, String word) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                // Start DFS if the first character matches
                if (dfs(board, word, r, c, 0)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int index) {
        // Base case: all characters found
        if (index == word.length()) return true;

        // Boundary and character match check
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length
                || board[r][c] != word.charAt(index)) {
            return false;
        }

        // Temporarily mark as visited
        char temp = board[r][c];
        board[r][c] = '#';

        // Explore 4 directions
        boolean found = dfs(board, word, r + 1, c, index + 1) ||
                dfs(board, word, r - 1, c, index + 1) ||
                dfs(board, word, r, c + 1, index + 1) ||
                dfs(board, word, r, c - 1, index + 1);

        // Backtrack: restore original value
        board[r][c] = temp;

        return found;
    }
}
