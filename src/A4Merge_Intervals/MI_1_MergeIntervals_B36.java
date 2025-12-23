package A4Merge_Intervals;

// Problem Statement: Merge Intervals (medium)
// LeetCode Question: 56. Merge Intervals

import java.util.*;

public class MI_1_MergeIntervals_B36 {

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;

        // 1. Sort by start time: O(n log n)
        //Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            // 2. Check for overlap
            if (currentEnd >= nextStart) {
                // Overlap exists: Merge by updating the end to the maximum
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // No overlap: Move to the next interval
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }

        // 3. Convert List to 2D array
        //return (int[][]) merged.stream().toArray();
        return merged.toArray(new int[merged.size()][]);
    }

    class Interval{
        int start;
        int end;
        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public List<Interval> merge (List<Interval> intervals) {
        if (intervals.size() < 2) {
            return intervals;
        }
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        List<Interval> mergedIntervals = new LinkedList<Interval>();
        Iterator<Interval> intervalItr = intervals.iterator();
        Interval interval = intervalItr.next();
        int start = interval.start;
        int end = interval.end;
        while(intervalItr.hasNext()){
            interval = intervalItr.next();
            if (interval.start <= end) {
                end = Math.max(interval.end, end);
            } else {
                mergedIntervals.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        mergedIntervals.add(new Interval(start, end));
        return mergedIntervals;
    }
}
