
**LeetCode 36: Valid Sudoku** using **Boolean 2D Arrays**, you track the occurrence of digits across rows, columns, and 3×3 sub-boxes simultaneously during a single traversal of the board.

1. The Idea

The core concept is to use state tracking with three separate grids:

The most efficient approach is a **one-pass traversal**. As you visit each cell (𝑖,𝑗) in the 9×9 grid, you track the numbers seen so far for that specific row, column, and sub-box.

The key challenge is mapping cell coordinates
(𝑖,𝑗)

to the correct
3×3

sub-box. You can treat the nine sub-boxes as a
3×3

grid and calculate the index using the formula:  


Box  Index=(𝑖/3)×3+(𝑗/3)



-   **Row Tracker:** A 9×9 boolean table where `rows[i][num]` is `true` if the digit `num` has already appeared in row `i`.
-   **Column Tracker:** A 9×9 boolean table where `cols[j][num]` is `true` if the digit `num` has already appeared in column `j`.
-   **Box Tracker:** A 9×9 boolean table where `boxes[k][num]` is `true` if the digit `num` has already appeared in sub-box `k`.

By checking these flags before marking a cell, you can identify a duplicate immediately and return `false`.

2. Solution Approach

1.  **Initialize:** Create three boolean arrays of size `[9][9]`.
2.  **Iterate:** Use nested loops to visit every cell (𝑖,𝑗) in the 9×9 board.
3.  **Filter:** If the cell is empty ('.'), skip it.
4.  **Map Values:**
    -   Convert the character (e.g., '5') to a 0-indexed integer (e.g., 4) by calculating `board[i][j] - '1'`.
    -   Determine the sub-box index using the formula: `(i / 3) * 3 + (j / 3)`.
5.  **Check & Set:**
    -   If any of the boolean flags at the calculated indices are already `true`, return `false`.
    -   Otherwise, set the flags for the current row, column, and box to `true`.

```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        // boolean[index][digit_index]
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;

                int num = board[i][j] - '1'; // Convert '1'-'9' to 0-8
                int boxIndex = (i / 3) * 3 + (j / 3);

                // If this number was already seen in row, col, or sub-box
                if (rows[i][num] || cols[j][num] || boxes[boxIndex][num]) {
                    return false;
                }

                // Mark the number as seen in all three contexts
                rows[i][num] = true;
                cols[j][num] = true;
                boxes[boxIndex][num] = true;
            }
        }
        return true;
    }
}

```

Complexity Analysis

-   **Time Complexity:**𝑂(1). Since the board is always 9×9, the nested loops run exactly 81 times regardless of input.
-   **Space Complexity:** 𝑂(1) We use exactly 3×(9×9)=243 boolean values, which is a constant amount of memory.

The formula **Box  Index=(𝑖/3)×3+(𝑗/3)**

is a mathematical mapping used to group a 9×9

grid into nine 3×3

sub-boxes, numbered 0 to 8. This mapping is necessary because a standard traversal uses row index 𝑖

and column index 𝑗

, but Sudoku rules require checking uniqueness within these localized 3×3

regions.

Breaking Down the Components

The formula works by treating the 9×9

board as a grid of "boxes," where each box itself has a position in a 3×3

layout.

1.  **Row Component  (𝑖/3)×3 **:
    -   Using integer division, 𝑖/3 identifies which "band" of three rows the cell belongs to.
    -   Rows 0, 1, and 2 result in **0**; rows 3, 4, and 5 result in **1**; rows 6, 7, and 8 result in **2**.
    -   Multiplying by 3 shifts this result to the starting index of that band's first box (0, 3, or 6).
2.  **Column Component  (𝑗/3)**:
    -   Similarly, 𝑗/3  identifies which "stack" of three columns the cell belongs to.
    -   Columns 0-2 map to **0**, columns 3-5 map to **1**, and columns 6-8 map to **2**.
3.  **The Summation:**
    -   By adding these together, you get a unique index from 0 to 8 for each 3×3 box, moving left-to-right and then top-to-bottom
    
**Example Cell (row 4, col 7):**

-   **Row part:** (4/3)×3=1×3=𝟑
    
-   **Col part:** 7/3=𝟐
    
-   **Result:** 3+2=𝟓
    
    (The cell is in Box 5).
