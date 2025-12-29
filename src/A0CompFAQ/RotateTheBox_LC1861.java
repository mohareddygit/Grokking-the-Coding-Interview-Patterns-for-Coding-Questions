package A0CompFAQ;

/**
 * An easy-to-understand alternative for LeetCode 1861 involves a two-step process: first, manually shifting stones as far "down" (right) as possible, and then building the new rotated matrix. Step-by-Step Logic Imagine the box is standing on its side. Before rotating, we process each row to let gravity pull stones to the rightmost available spot. Gravity (Row-by-Row):For each row, start from the rightmost cell and move left.Keep track of the lowestAvailable index (initially the rightmost edge).If you hit a stone (#), "drop" it to the lowestAvailable spot and move that spot one index to the left.If you hit an obstacle (*), it blocks everything, so the next lowestAvailable spot must be just to the left of it.Rotation (New Matrix):Create a new matrix with swapped dimensions (\(n\times m\)).Move each element from box[i][j] to newBox[j][m - 1 - i].
 */


/**
 * Summary of Conditions
 * If box[i][j] == '*': A hard barrier is found. Reset emptySpot to \(j-1\).
 * If box[i][j] == '#': A stone is found. "Teleport" it to emptySpot,
 * set its old position to ., and decrement emptySpot by \(1\) to prepare for the next stone.
 *
 * If box[i][j] == '.': Just pass by; it doesn't affect the landing pad. 
 */
class RotateTheBox_LC1861 {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        // Step 1: Manual Gravity (Row by Row)
        for (int i = 0; i < m; i++) {
            int lowestAvailable = n - 1; // Track the rightmost available spot
            for (int j = n - 1; j >= 0; j--) {
                if (box[i][j] == '#') {
                    // Stone falls to the lowest available spot
                    // Move stone to the emptySpot and update the spot
                    box[i][j] = '.';
                    box[i][lowestAvailable] = '#';
                    lowestAvailable--;
                } else if (box[i][j] == '*') {
                    // Obstacle stops stones; new available spot is above it
                    lowestAvailable = j - 1;
                }
            }
        }

        // Step 2: Build Rotated Matrix
        char[][] result = new char[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // (i, j) maps to (j, (last_row_index - i))
                result[j][m - 1 - i] = box[i][j];
            }
        }
        return result;
    }
}
