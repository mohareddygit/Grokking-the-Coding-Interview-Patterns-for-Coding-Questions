package B4Graph;

import java.util.*;

/**
 * Leetcode 323: Number of Connected Components in an Undirected Graph, using DFS to count components.
 */
public class Gra_ConnectedComponents_B34 {
    public int countComponents(int n, int[][] edges) {
        // Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int components = 0;

        // DFS from each unvisited node
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(graph, visited, i);
                components++; // Found a new component
            }
        }

        return components;
    }

    private void dfs(List<List<Integer>> graph, boolean[] visited, int node) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(graph, visited, neighbor);
            }
        }
    }

    public static void main(String[] args) {
        Gra_ConnectedComponents_B34 solver = new Gra_ConnectedComponents_B34();
        int n = 5;
        int[][] edges = {{0,1},{1,2},{3,4}};
        System.out.println("Number of connected components: " + solver.countComponents(n, edges)); // Output: 2
    }
}
