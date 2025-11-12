package A4Merge_Intervals;

//LeetCode Question: 1288. Remove Covered Intervals
import java.util.Arrays;

    public class MI_RemoveCoveredIntervals_B37 {
        public int removeCoveredIntervals(int[][] intervals) {
            // Sort intervals. First by the starting point in ascending order,
            // then by the ending point in descending order if the starting points are the same.
            Arrays.sort(intervals, (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                } else {
                    return Integer.compare(b[1], a[1]);
                }
            });

            int count = 0;
            int maxRight = 0;

            for (int[] interval : intervals) {
                // If the current interval is not covered by the previous ones
                if (interval[1] > maxRight) {
                    count++;
                    maxRight = interval[1];
                }
            }

            return count;
        }

        public static void main(String[] args) {
            MI_RemoveCoveredIntervals_B37 solution = new MI_RemoveCoveredIntervals_B37();
            int[][] intervals = {{1, 4}, {3, 6}, {2, 8}};
            System.out.println(solution.removeCoveredIntervals(intervals));  // Output: 2
        }
    }