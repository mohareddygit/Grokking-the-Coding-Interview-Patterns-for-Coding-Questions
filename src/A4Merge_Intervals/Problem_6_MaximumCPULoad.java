package A4Merge_Intervals;

// Problem Statement: Maximum CPU Load (hard)
// LeetCode Question: 1235. Maximum Profit in Job Scheduling
/**
 * The problem of finding the
 * maximum CPU load at any given time, based on a list of jobs, is an application of the "Maximum Number of Overlapping Intervals" algorithm. Each job has a start time, an end time, and a CPU load value.
 * The most efficient approach uses a min-heap (Priority Queue) or the sweep-line algorithm to track concurrent jobs. This achieves a time complexity of O(n log n).
 * Algorithm: Using a Min-Heap
 * We maintain a min-heap of currently running jobs, ordered by their end times. The total load is summed for all jobs in the heap, and we track the maximum sum encountered.
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

import java.util.*;

public class Problem_6_MaximumCPULoad {
    static class Job {
        int start;
        int end;
        int cpuLoad;

        Job(int start, int end, int cpuLoad) {
            this.start = start;
            this.end = end;
            this.cpuLoad = cpuLoad;
        }
    }

    public class MaxCpuLoad {

        public static int findMaxCpuLoad(List<Job> jobs) {
            if (jobs == null || jobs.isEmpty()) {
                return 0;
            }

            // 1. Sort jobs by their start times
            jobs.sort(Comparator.comparingInt(a -> a.start));

            // 2. Initialize a min-heap to store running jobs, sorted by end time
            // The top of the heap will be the job that ends earliest
            PriorityQueue<Job> runningJobs = new PriorityQueue<>(Comparator.comparingInt(a -> a.end));

            int currentCpuLoad = 0;
            int maxCpuLoad = 0;

            // 3. Iterate through the sorted jobs
            for (Job currentJob : jobs) {

                // Remove all jobs from the heap that have ended before the current job starts
                while (!runningJobs.isEmpty() && runningJobs.peek().end <= currentJob.start) {
                    Job finishedJob = runningJobs.poll();
                    currentCpuLoad -= finishedJob.cpuLoad;
                }

                // Add the current job to the heap and update the current load
                runningJobs.add(currentJob);
                currentCpuLoad += currentJob.cpuLoad;

                // Update the maximum CPU load encountered so far
                maxCpuLoad = Math.max(maxCpuLoad, currentCpuLoad);
            }

            return maxCpuLoad;
        }

        public static void main(String[] args) {
            List<Job> jobs = Arrays.asList(
                    new Job(1, 4, 3), // Load 3 from 1 to 4
                    new Job(2, 5, 4), // Load 4 from 2 to 5
                    new Job(7, 9, 6), // Load 6 from 7 to 9
                    new Job(8, 12, 1) // Load 1 from 8 to 12
            );

            int maxLoad = findMaxCpuLoad(jobs);
            // Expected max load: 7 (during time interval [2, 4] when jobs A and B overlap)
            System.out.println("Maximum CPU Load: " + maxLoad);
        }
    }

}
