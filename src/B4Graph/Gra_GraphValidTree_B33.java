package B4Graph;

import java.util.*;

/**
 * Leetcode 261: Graph Valid Tree, using DFS to check for cycles and connectivity.
 */
public class Gra_GraphValidTree_B33 {
    public boolean validTree(int n, int[][] edges) {
        // A tree must have exactly n - 1 edges
        if (edges.length != n - 1) return false;

        // Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];

        // DFS to check for cycles
        if (hasCycle(graph, visited, 0, -1)) return false;

        // Check if all nodes are visited (connected)
        for (boolean v : visited) {
            if (!v) return false;
        }

        return true;
    }

    // DFS helper to detect cycles
    private boolean hasCycle(List<List<Integer>> graph, boolean[] visited, int node, int parent) {
        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                if (hasCycle(graph, visited, neighbor, node)) return true;
            } else if (neighbor != parent) {
                // Found a back edge (cycle)
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Gra_GraphValidTree_B33 solver = new Gra_GraphValidTree_B33();
        int n = 5;
        int[][] edges = {{0,1},{0,2},{0,3},{1,4}};
        System.out.println("Is valid tree? " + solver.validTree(n, edges)); // true
    }
}
