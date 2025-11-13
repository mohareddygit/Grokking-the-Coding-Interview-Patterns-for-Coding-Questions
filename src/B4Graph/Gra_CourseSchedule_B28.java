package B4Graph;

import java.util.*;

/**
 * 207. Course Schedule
 *
 */
public class Gra_CourseSchedule_B28 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build adjacency list for the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for (int[] pair : prerequisites) {
            graph.get(pair[1]).add(pair[0]); // b → a
        }

        // Track visit status: 0 = unvisited, 1 = visiting, 2 = visited
        int[] visited = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(graph, visited, i)) return false;
        }

        return true;
    }

    // DFS to detect cycle
    private boolean hasCycle(List<List<Integer>> graph, int[] visited, int course) {
        if (visited[course] == 1) return true;  // Found a cycle
        if (visited[course] == 2) return false; // Already checked

        visited[course] = 1; // Mark as visiting

        for (int neighbor : graph.get(course)) {
            if (hasCycle(graph, visited, neighbor)) return true;
        }

        visited[course] = 2; // Mark as visited
        return false;
    }

    public static void main(String[] args) {
        Gra_CourseSchedule_B28 solver = new Gra_CourseSchedule_B28();
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,1},{3,2}};
        System.out.println("Can finish all courses? " + solver.canFinish(numCourses, prerequisites)); // true
    }
}
