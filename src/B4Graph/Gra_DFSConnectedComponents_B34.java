package B4Graph;

import java.util.*;

/**
 * Leetcode 323: Number of Connected Components in an Undirected Graph, using DFS to count components.
 *
 * To find the number of connected components in an undirected graph, we can use Depth First Search (DFS), Breadth First Search (BFS), or the Union-Find data structure. Each time we start a new traversal (DFS/BFS) from an unvisited node, it means we have found a new connected component.
 * Approach 1: Depth First Search (DFS)
 * The DFS approach builds an adjacency list from the given edges. It then iterates through all nodes, and if a node hasn't been visited yet, it starts a DFS traversal to mark all nodes in that component as visited, incrementing the component count.
 */
public class Gra_DFSConnectedComponents_B34 {
    public int countComponents(int n, int[][] edges) {
        // 1. Build the adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v); // Add edge u -> v
            adj.get(v).add(u); // Add edge v -> u (undirected)
        }

        boolean[] visited = new boolean[n];
        int count = 0;

        // 2. Iterate through all nodes
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // If the node hasn't been visited, it belongs to a new component
                count++;
                // Perform DFS to mark all nodes in this component as visited
                dfs(adj, visited, i);
            }
        }

        return count;
    }

    private void dfs(List<List<Integer>> adj, boolean[] visited, int node) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(adj, visited, neighbor);
            }
        }
    }

    public static void main(String[] args) {
        Gra_DFSConnectedComponents_B34 solver = new Gra_DFSConnectedComponents_B34();
        int n = 5;
        int[][] edges = {{0,1},{1,2},{3,4}};
        System.out.println("Number of connected components: " + solver.countComponents(n, edges)); // Output: 2
    }
}
