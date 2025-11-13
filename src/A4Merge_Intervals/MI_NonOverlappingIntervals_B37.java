package A4Merge_Intervals;

import java.util.Arrays;

public class MI_NonOverlappingIntervals_B37 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        // Sort by end time ascending
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int count = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0], end = intervals[i][1];

            if (start < prevEnd) {
                // Overlap → remove this interval
                count++;
            } else {
                // No overlap → update end
                prevEnd = end;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        MI_NonOverlappingIntervals_B37 solver = new MI_NonOverlappingIntervals_B37();
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        System.out.println("Intervals to remove: " + solver.eraseOverlapIntervals(intervals)); // Output: 1
    }
}
