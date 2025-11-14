package B4Graph;

import java.util.*;

/**
 * 207. Course Schedule
 *
 * The LeetCode 207 "Course Schedule" problem asks whether it is possible to complete all given courses with their prerequisites. This problem can be modeled as a directed graph where courses are nodes and prerequisites are directed edges. The task reduces to detecting if a cycle exists in this graph. If a cycle exists (e.g., course A requires B, and B requires A), it's impossible to finish the courses.
 * Here are two common Java solutions using Topological Sort (BFS via Kahn's Algorithm) and DFS (Cycle Detection).
 * Approach 1: Topological Sort (Kahn's Algorithm - BFS)
 * This approach counts the "in-degree" (number of prerequisites) for each course. Courses with zero prerequisites are starting points. We process them, reducing the in-degree of dependent courses, and adding new zero-in-degree courses to a queue
 */

/**
 * Both solutions have a time complexity of O(V + E), where V is the number of courses (vertices) and E is the number of prerequisites (edges)
 */
public class Gra_BFSCourseSchedule_B28 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build the graph (adjacency list) and calculate in-degrees
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preReq = prerequisite[1];
            adj.get(preReq).add(course); // Edge from preReq to course
            inDegree[course]++;
        }

        // Initialize a queue with courses that have no prerequisites
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        int coursesTaken = 0;
        // Process courses using BFS
        while (!q.isEmpty()) {
            int currentCourse = q.poll();
            coursesTaken++;

            // For each dependent course, reduce its in-degree
            for (int dependentCourse : adj.get(currentCourse)) {
                inDegree[dependentCourse]--;
                // If a dependent course now has zero prerequisites, add it to the queue
                if (inDegree[dependentCourse] == 0) {
                    q.add(dependentCourse);
                }
            }
        }

        // If all courses were processed, there was no cycle
        return coursesTaken == numCourses;
    }


    public static void main(String[] args) {
        Gra_BFSCourseSchedule_B28 solver = new Gra_BFSCourseSchedule_B28();
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 1}, {3, 2}};
        System.out.println("Can finish all courses? " + solver.canFinish(numCourses, prerequisites)); // true
    }
}
