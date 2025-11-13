package A4Merge_Intervals;

// Problem Statement: Minimum Meeting Rooms (hard)
// LeetCode Question: 252. Meeting Rooms 1 .. canattend
// LeetCode Question: 253. Meeting Rooms 11 .. minMeetingRooms

import java.util.*;

public class MI_5_MinimumMeetingRooms_B38_B39 {
    class Interval {
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
    }

    public boolean canAttendMeetings(int[][] intervals) {
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Check for overlaps
        for (int i = 1; i < intervals.length; i++) {
            // If current meeting starts before previous one ends → overlap
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        MI_5_MinimumMeetingRooms_B38_B39 solver = new MI_5_MinimumMeetingRooms_B38_B39();
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("Can attend all meetings? " + solver.canAttendMeetings(intervals)); // Output: false
    }

    /**
     * Method 1: Using a Min-Heap (Priority Queue)
     * This is a very common and elegant solution. A min-heap tracks the end times of all meetings currently in progress. The size of the heap at any point represents the number of rooms currently occupied.
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    public class MeetingRoomScheduler {
        public static int minMeetingRooms(List<Interval> intervals) {
            if (intervals == null || intervals.isEmpty()) {
                return 0;
            }

            // 1. Sort the intervals by their start times
            intervals.sort(Comparator.comparingInt(a -> a.start));

            // 2. Initialize a min-heap to store the end times of meetings
            // The heap will store the meeting that ends earliest at its top
            PriorityQueue<Integer> endTimes = new PriorityQueue<>();

            // 3. Add the first meeting's end time to the heap
            endTimes.add(intervals.get(0).end);

            // 4. Iterate through the rest of the meetings
            for (int i = 1; i < intervals.size(); i++) {
                Interval current = intervals.get(i);

                // If the current meeting starts after or at the time the earliest running meeting ends,
                // we can reuse that room.
                if (current.start >= endTimes.peek()) {
                    endTimes.poll(); // Free up the room that ends earliest
                }

                // Occupy a room for the current meeting by adding its end time to the heap.
                endTimes.add(current.end);
            }

            // The size of the heap is the maximum number of simultaneous meetings,
            // which equals the minimum number of rooms needed.
            return endTimes.size();
        }
    }

    /**Method 2: Using Two Sorted Arrays
    This approach uses two separate sorted arrays for start and end times and a two-pointer approach to track overlaps.
    Time Complexity: O(n log n)
    Space Complexity: O(n) for the extra arrays.
     */

    public class TwoArrayScheduler {
        public static int minMeetingRoomsTwoArrays(int[] startTimes, int[] endTimes) {
            // 1. Sort both the start times and the end times arrays
            Arrays.sort(startTimes);
            Arrays.sort(endTimes);

            int roomsNeeded = 0;
            int maxRoomsNeeded = 0;
            int startPointer = 0;
            int endPointer = 0;
            int n = startTimes.length;

            // 2. Use two pointers to iterate through the sorted times
            while (startPointer < n) {
                // If a meeting starts before the earliest running meeting ends, we need a new room
                if (startTimes[startPointer] < endTimes[endPointer]) {
                    roomsNeeded++;
                    maxRoomsNeeded = Math.max(maxRoomsNeeded, roomsNeeded);
                    startPointer++;
                }
                // Otherwise, a meeting has ended, so we can free up a room
                else {
                    roomsNeeded--;
                    endPointer++;
                }
            }

            return maxRoomsNeeded;
        }
    }

}
