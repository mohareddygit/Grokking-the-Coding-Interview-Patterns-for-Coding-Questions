package A4Merge_Intervals;

// Problem Statement: Employee Free Time
// LeetCode Question: 759. Employee Free Time

/**
 * To find the common free time for all employees in
 * Java, the optimal approach is to treat all busy intervals across all employees as a single sorted stream of events, merge overlapping busy intervals, and then identify the gaps between the merged busy intervals.
 * This method has a time complexity of O(N log N), where N is the total number of busy intervals across all employees.
 * Algorithm Steps
 * Flatten Schedules: Combine all employees' individual busy intervals into a single master list.
 * Sort Intervals: Sort this master list based on the start time of each interval.
 * Merge Overlaps: Iterate through the sorted list and merge any intervals that overlap or touch to create a consolidated timeline of all occupied times across the entire company.
 * Find Gaps: The gaps between the end of one consolidated busy interval and the start of the next represent the times when every employee is free.
 */

import java.util.*;

public class Problem_7_EmployeeFreeTime {
    static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }

    public class EmployeeFreeTime {

        public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
            List<Interval> allIntervals = new ArrayList<>();

            // Step 1: Flatten all schedules into a single list
            for (List<Interval> employeeSchedule : schedule) {
                allIntervals.addAll(employeeSchedule);
            }

            // Step 2: Sort all intervals by start time
            allIntervals.sort(Comparator.comparingInt(a -> a.start));

            List<Interval> freeTime = new ArrayList<>();

            // We track the end time of the most recent consolidated busy period.
            int previousEnd = allIntervals.get(0).end;

            // Step 3 & 4: Merge overlaps and find gaps
            for (int i = 1; i < allIntervals.size(); i++) {
                Interval current = allIntervals.get(i);

                // Check for a gap (common free time) between the previous busy block and the current one
                if (current.start > previousEnd) {
                    // Gap found: the free time is from previousEnd to current.start
                    freeTime.add(new Interval(previousEnd, current.start));
                    // Update the consolidated end time to the end of the current block
                    previousEnd = Math.max(previousEnd, current.end);
                } else {
                    // Overlap or touch: merge the intervals by extending the end time if necessary
                    previousEnd = Math.max(previousEnd, current.end);
                }
            }

            return freeTime;
        }

        public static void main(String[] args) {
            // Example: [[[1,3],[6,7]], [[2,4]], [[2,5],[9,12]]]
            List<List<Interval>> schedule = Arrays.asList(
                    Arrays.asList(new Interval(1, 3), new Interval(6, 7)),
                    Arrays.asList(new Interval(2, 4)),
                    Arrays.asList(new Interval(2, 5), new Interval(9, 12))
            );

            List<Interval> commonFreeTime = employeeFreeTime(schedule);

            System.out.println("Common Free Time Intervals: ");
            for (Interval interval : commonFreeTime) {
                System.out.println(interval);
            }
            // Expected Output:
            // Common Free Time Intervals:
            // [5, 6]
            // [7, 9]
        }
    }
}
