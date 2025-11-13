package A4Merge_Intervals;

//LeetCode Question: 1288. Remove Covered Intervals
import java.util.Arrays;

    public class MI_RemoveCoveredIntervals_LC1288 {
        public int removeCoveredIntervals(int[][] intervals) {
            // Sort by start ascending, end descending
            Arrays.sort(intervals, (a, b) ->
                    a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

            int count = 0;
            int prevEnd = 0;

            for (int[] interval : intervals) {
                int start = interval[0], end = interval[1];

                // If current interval is not covered by previous
                if (end > prevEnd) {
                    count++;
                    prevEnd = end;
                }
                // Else, it's covered and we skip it
            }

            return count;
        }

        public static void main(String[] args) {
            MI_RemoveCoveredIntervals_LC1288 solver = new MI_RemoveCoveredIntervals_LC1288();
            int[][] intervals = {{1,4},{3,6},{2,8}};
            System.out.println("Remaining intervals: " + solver.removeCoveredIntervals(intervals)); // Output: 2
        }
    }