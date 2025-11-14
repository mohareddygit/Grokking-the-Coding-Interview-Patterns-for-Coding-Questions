package B1HashMap_HashTable;

import java.util.*;

/**
 * Leetcode 36: Valid Sudoku, which checks whether a given 9x9 Sudoku board is valid according to Sudoku rules.
 */
public class HM_ValidSudoku_BX {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    // Encode row, column, and box constraints
                    String rowKey = num + " in row " + i;
                    String colKey = num + " in col " + j;
                    String boxKey = num + " in box " + (i / 3) + "-" + (j / 3);

                    // Check for duplicates
                    if (!seen.add(rowKey) || !seen.add(colKey) || !seen.add(boxKey)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
