package B4Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 207. Course Schedule
 * <p>
 * The LeetCode 207 "Course Schedule" problem asks whether it is possible to complete all given courses with their prerequisites. This problem can be modeled as a directed graph where courses are nodes and prerequisites are directed edges. The task reduces to detecting if a cycle exists in this graph. If a cycle exists (e.g., course A requires B, and B requires A), it's impossible to finish the courses.
 * Here are two common Java solutions using Topological Sort (BFS via Kahn's Algorithm) and DFS (Cycle Detection).
 * Approach 2: Cycle Detection (DFS)
 * This approach uses DFS to traverse the graph and uses color-coding to detect cycles.
 * 0: Unvisited
 * 1: Visiting (currently in the recursion stack/DFS path)
 * 2: Visited (fully processed and no cycle found in its subtree)
 */
public class Gra_DFSCourseSchedule_B28 {
    // 0: unvisited, 1: visiting, 2: visited/safe
    private int[] visitStatus;
    private List<List<Integer>> adj;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visitStatus = new int[numCourses];
        adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preReq = prerequisite[1];
            adj.get(preReq).add(course); // Edge from preReq to course
        }

        // Check every course as the graph might be disconnected
        for (int i = 0; i < numCourses; i++) {
            if (isCyclic(i)) {
                return false; // Cycle detected
            }
        }

        return true; // No cycles found
    }

    private boolean isCyclic(int course) {
        if (visitStatus[course] == 1) return true; // Cycle found (back edge)
        if (visitStatus[course] == 2) return false; // Already visited and safe

        visitStatus[course] = 1; // Mark as visiting

        for (int dependentCourse : adj.get(course)) {
            if (isCyclic(dependentCourse)) {
                return true;
            }
        }

        visitStatus[course] = 2; // Mark as visited/safe (finished processing)
        return false;
    }


    public static void main(String[] args) {
        Gra_DFSCourseSchedule_B28 solver = new Gra_DFSCourseSchedule_B28();
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 1}, {3, 2}};
        System.out.println("Can finish all courses? " + solver.canFinish(numCourses, prerequisites)); // true
    }
}
