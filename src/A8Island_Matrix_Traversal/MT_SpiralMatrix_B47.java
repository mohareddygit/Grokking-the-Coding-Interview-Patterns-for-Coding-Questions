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
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        // Traverse until boundaries overlap
        while (top <= bottom && left <= right) {
            // 1: Top row : left → right
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++; // move boundary down

            // 2: right column: top → bottom
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--; // move boundary left

            // 3: bottom row (if still valid): right → left
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--; // move boundary up
            }

            // 4: left column (if still valid) : bottom → top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++; // move boundary right
            }
        }

        return result;
    }
}
