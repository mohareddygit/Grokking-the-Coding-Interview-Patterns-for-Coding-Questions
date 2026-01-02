package A0CompFAQ;

/**
 * To solve LeetCode 3071: Minimum Operations to Write the Letter Y on a Grid, you must partition the grid into "Y" and "non-Y" regions and find the most cost-effective pair of distinct values to fill them. Key Logic Identify the "Y" Region: For an \(n\times n\) grid where \(n\) is odd and \(mid=n/2\), a cell \((r,c)\) belongs to the letter Y if:It is on the left diagonal: \(r==c\) and \(r\le mid\).It is on the right diagonal: \(r+c==n-1\) and \(r\le mid\).It is on the vertical stem: \(c==mid\) and \(r\ge mid\).Count Frequencies: Iterate through the grid and maintain two frequency arrays (one for the Y region and one for the non-Y region) to count how many 0s, 1s, and 2s currently exist in each area.Try All Combinations: There are only 6 possible target configurations (since there are 3 choices for the Y value and 2 remaining choices for the non-Y value). For each combination \((val_{Y},val_{nonY})\), the operations needed is:\(\text{Total\ Cells}-(\text{Count\ of\ }val_{Y}\text{\ in\ Y})-(\text{Count\ of\ }val_{nonY}\text{\ in\ non-Y})\)
 */

/**
 *
 */
class CT4_MinimumOperationsToWriteY_LC3071 {
    public int minimumOperationsToWriteY(int[][] grid) {
        int n = grid.length;
        int mid = n / 2;
        int[] yCount = new int[3];
        int[] nonYCount = new int[3];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                boolean isY = (r <= mid && (r == c || r + c == n - 1)) || (r >= mid && c == mid);
                if (isY) {
                    yCount[grid[r][c]]++;
                } else {
                    nonYCount[grid[r][c]]++;
                }
            }
        }

        int minOps = n * n;
        for (int i = 0; i < 3; i++) { // Target value for Y region
            for (int j = 0; j < 3; j++) { // Target value for non-Y region
                if (i != j) {
                    // ops = total - already correct
                    int currentOps = (n * n) - yCount[i] - nonYCount[j];
                    minOps = Math.min(minOps, currentOps);
                }
            }
        }
        return minOps;
    }
}
