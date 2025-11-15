package B4Graph;

import java.util.*;

public class Gra1_DFS {

    public static void dfsIterative(Map<String, List<String>> graph, String startNode) {
        // Use a Stack to store nodes to visit (LIFO behavior for depth first)
        Stack<String> stack = new Stack<>();
        // Use a Set to keep track of visited nodes to prevent infinite loops
        Set<String> visited = new HashSet<>();

        // Start the traversal from the given node
        stack.push(startNode);
        visited.add(startNode);

        // Continue as long as there are nodes in the stack to explore
        while (!stack.isEmpty()) {
            String currentNode = stack.pop();
            // Process the current node (e.g., print it)
            System.out.println(currentNode + " ");

            // Get the neighbors of the current node
            List<String> neighbors = graph.getOrDefault(currentNode, Collections.emptyList());

            // Iterate through neighbors: push unvisited neighbors onto the stack
            // The order here affects which branch is explored *first*
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }
    }

    // A Set to keep track of visited nodes across the recursive calls
    private static Set<String> visited = new HashSet<>();

    public static void dfsRecursive(Map<String, List<String>> graph, String startNode) {
        // Base case/termination check
        if (visited.contains(startNode)) {
            return;
        }

        // Process the current node
        System.out.println(startNode + " ");
        visited.add(startNode);

        // Recursively visit all neighbors
        List<String> neighbors = graph.getOrDefault(startNode, Collections.emptyList());
        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(graph, neighbor);
            }
        }
    }

    public static void main(String[] args) {
        // Example Graph (Adjacency List)
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D", "E"));
        graph.put("C", Arrays.asList("F"));
        graph.put("D", Collections.emptyList());
        graph.put("E", Arrays.asList("F"));
        graph.put("F", Collections.emptyList());

        System.out.println("DFSIterative Traversal starting from node A:");
        dfsIterative(graph, "A"); // Example Output: A C F B E D (Note: Neighbor order matters)
        System.out.println("DFSRecursive Traversal starting from node A:");
        dfsRecursive(graph, "A"); // Example Output: A B D E F C
        /**
         * When you run this program, it outputs the order in which the vertices are visited during DFS traversal.
         *     The recursive and iterative approaches might yield different orders of visiting nodes due to the nature of the stack
         *     operations and the order of neighbors in the adjacency list. However, both approaches correctly traverse the
         *     graph depth-first.
         */
    }
}
