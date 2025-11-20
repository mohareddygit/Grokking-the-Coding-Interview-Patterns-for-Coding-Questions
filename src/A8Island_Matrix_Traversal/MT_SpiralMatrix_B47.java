package A8Island_Matrix_Traversal;

import java.util.*;

/**
 * 54. Spiral Matrix
 */
public class MT_SpiralMatrix_B47 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        // Define boundaries
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        // Traverse until boundaries overlap
        while (rowBegin <= rowEnd && colBegin <= colEnd) {

            // Step 1: Traverse from left → right along the top row
            for (int i = colBegin; i <= colEnd; i++) {
                result.add(matrix[rowBegin][i]);
            }
            rowBegin++; // move boundary down

            // Step 2: Traverse from top → bottom along the right column
            for (int i = rowBegin; i <= rowEnd; i++) {
                result.add(matrix[i][colEnd]);
            }
            colEnd--; // move boundary left

            // Step 3: Traverse from right → left along the bottom row (if still valid)
            if (rowBegin <= rowEnd) {
                for (int i = colEnd; i >= colBegin; i--) {
                    result.add(matrix[rowEnd][i]);
                }
                rowEnd--; // move boundary up
            }

            // Step 4: Traverse from bottom → top along the left column (if still valid)

            if (colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    result.add(matrix[i][colBegin]);
                }
                colBegin++; // move boundary right
            }
        }

        return result;
    }
}
