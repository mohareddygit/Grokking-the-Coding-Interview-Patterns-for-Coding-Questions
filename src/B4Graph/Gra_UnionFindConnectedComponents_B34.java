package B4Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 323: Number of Connected Components in an Undirected Graph, using UnionFind to count components.
 *
 * Approach 2: Union-Find (Disjoint Set Union - DSU)
 * The Union-Find data structure is particularly well-suited for problems involving connected components in a graph given a list of edges. It uses a find operation (to find the representative/root of a set) and a union operation (to merge two sets/components)
 */

/**
 * Complexity Analysis
 * DFS/BFS Approach:
 * Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
 * Space Complexity: O(V + E) for the adjacency list and O(V) for the visited array and the recursion call stack/queue.
 * Union-Find Approach:
 * Time Complexity: O(E * α(V)), where α is the inverse Ackermann function, which is nearly constant time. This is very efficient for this problem.
 * Space Complexity: O(V) for the parent array
 */
public class Gra_UnionFindConnectedComponents_B34 {
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        int components = n;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // If the two nodes are not already in the same set (component), union them and reduce the component count
            if (uf.union(u, v)) {
                components--;
            }
        }

        return components;
    }

    // Union-Find Data Structure Class
    class UnionFind {
        int[] parent;

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i; // Initially, each node is its own parent (own component)
            }
        }

        // Find the root of a node with path compression
        public int find(int i) {
            if (parent[i] == i)
                return i;
            return parent[i] = find(parent[i]); // Path compression
        }

        // Union two sets/components
        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ; // Union the two sets
                return true; // Union occurred
            }
            return false; // Already in the same set
        }
    }


    public static void main(String[] args) {
        Gra_UnionFindConnectedComponents_B34 solver = new Gra_UnionFindConnectedComponents_B34();
        int n = 5;
        int[][] edges = {{0,1},{1,2},{3,4}};
        System.out.println("Number of connected components: " + solver.countComponents(n, edges)); // Output: 2
    }
}
