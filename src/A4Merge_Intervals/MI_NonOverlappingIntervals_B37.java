package A4Merge_Intervals;

import java.util.Arrays;

public class MI_NonOverlappingIntervals_B37 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        // Sort by end time ascending
        //Arrays.sort(intervals, (a, b) -> a[1] - b[1]); Dangerous
        /*Do not do this. If the values are very large (e.g., 2,000,000,000 and -2,000,000,000),
        the subtraction can overflow and return an incorrect result,
        causing a sorting bug. Always use Integer.compare()
         */

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            // If current interval starts BEFORE the previous ends, it's an overlap
            if (intervals[i][0] < end) {
                count++; // remove this interval
                //don't have to move the end boundary, as this interval is being marked for removal
                // and we can't compare further with the one that is already removed(marked for removal)
            } else {
                // No overlap: update the end boundary to current interval's end
                end = intervals[i][1];
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
