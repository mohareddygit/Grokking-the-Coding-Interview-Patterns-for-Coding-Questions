package A8Island_Matrix_Traversal;

/**
 * Leetcode 48: Rotate Image, which rotates an n x n matrix by 90 degrees in-place.
 *
 * In a square matrix, the main diagonal runs from the top-left corner (matrix[0][0])
 * to the bottom-right corner (matrix[n-1][n-1]).
 * The elements on this diagonal are those where the row index i equals the column index j
 */
public class MT_RotateImage_B48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose the matrix (swap elements across the main diagonal)
        // We only iterate over the upper triangle of the matrix to avoid swapping back elements.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        // After transpose, reversing each row completes the 90-degree clockwise rotation.
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    /**
     * 4-Way Swap. This approach rotates four elements at a time in a single loop,
     * moving them in a cycle from one position to the next.
     * The four positions for a given corner at
     * \((i,j)\) are: \((i,j)\), \((j,n-1-i)\), \((n-1-i,n-1-j)\), and \((n-1-j,i)\).
     * While this approach performs the rotation in a single pass over the relevant sections of the matrix,
     * the logic for correctly calculating the four positions and managing the loop boundaries
     * can be more complex and harder to reason about.
     * This makes it more prone to off-by-one errors and less readable than the two-step method
     *
     * @param matrix
     */
    public void rotateUsing4waySwap(int[][] matrix) {
        int n = matrix.length;

        // Iterate through layers from the outside in
        for (int i = 0; i < n / 2; i++) {
            // Iterate through elements within the current layer's edge
            // The range for j decreases as n increases
            for (int j = i; j < n - 1 - i; j++) {

                // Store the top-left element
                int temp = matrix[i][j];

                // Move bottom-left to top-left
                matrix[i][j] = matrix[n - 1 - j][i];

                // Move bottom-right to bottom-left
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];

                // Move top-right to bottom-right
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];

                // Move stored top-left to top-right
                matrix[j][n - 1 - i] = temp;
            }
        }
    }
}
