package B4Graph;

import java.util.*;

public class Gra1_BFS {

    public static void bfs(Map<String, List<String>> graph, String startNode) {
        // Use a Queue to store nodes to visit (FIFO behavior for breadth first)
        Queue<String> queue = new LinkedList<>();
        // Use a Set to keep track of visited nodes to prevent infinite loops
        Set<String> visited = new HashSet<>();

        // Start the traversal from the given node
        queue.add(startNode);
        visited.add(startNode);

        // Continue as long as there are nodes in the queue to explore
        while (!queue.isEmpty()) {
            String currentNode = queue.poll(); // Remove node from front of queue
            // Process the current node (e.g., print it)
            System.out.print(currentNode + " ");

            // Get the neighbors of the current node
            List<String> neighbors = graph.getOrDefault(currentNode, Collections.emptyList());

            // Iterate through neighbors: add unvisited neighbors to the queue
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor); // Mark as visited as soon as it's discovered
                    queue.add(neighbor); // Add to the back of the queue
                }
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

        System.out.println("BFS Traversal starting from node A:");
        bfs(graph, "A"); // Example Output: A B C D E F
    }
}
