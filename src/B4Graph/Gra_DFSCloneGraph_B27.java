package B4Graph;

import java.util.*;

/**
 * 133. Clone Graph
 * using DFS with a HashMap to track visited nodes and avoid cycles.
 * The LeetCode problem "Clone Graph" (133) requires creating a deep copy of a given undirected graph, which may contain cycles. The graph is represented as an adjacency list.
 * The most effective way to solve this is using a graph traversal algorithm (DFS or BFS) while using a hash map to keep track of nodes already copied. The hash map prevents infinite loops when encountering cycles and ensures we don't create multiple copies of the same node.
 * Approach: Depth First Search (DFS)
 * We can use recursion (DFS) to traverse the original graph. A HashMap stores the mapping from original nodes to their corresponding cloned nodes.
 */

/**
 * Complexity Analysis (Both DFS and BFS)
 * Time Complexity: O(V + E), where V is the number of vertices (nodes) and E is the number of edges. We visit each node and each edge exactly once.
 * Space Complexity: O(V) for the hash map used to store visited nodes and their clones, and O(V) for the recursion call stack (DFS) or the queue (BFS) in the worst-case scenario (e.g., a linear graph).
 */
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


public class Gra_DFSCloneGraph_B27 {
    // HashMap to store the mapping of original node to its clone
    private HashMap<Node, Node> visited = new HashMap<>();

    public Node dfsCloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // If the node has already been visited/cloned, return its clone from the map
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a new clone node with the same value
        Node cloneNode = new Node(node.val);
        // Put the new clone into the visited map immediately to handle cycles correctly
        visited.put(node, cloneNode);

        // Recurse through all original neighbors to clone them and build the neighbors list of the clone
        for (Node neighbor : node.neighbors) {
            // Add the clone of the neighbor to the current clone's neighbors list
            cloneNode.neighbors.add(dfsCloneGraph(neighbor));
        }

        return cloneNode;
    }


    /**
     * Approach: Breadth First Search (BFS)
     * Alternatively, BFS can be used with a queue for iterative traversal.
     * @param node
     * @return
     */
    public Node bfscloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        HashMap<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        // Start BFS: Add the original start node and its clone to the map/queue
        visited.put(node, new Node(node.val));
        queue.add(node);

        while (!queue.isEmpty()) {
            Node originalCurrent = queue.poll();
            Node clonedCurrent = visited.get(originalCurrent);

            // Iterate over original node's neighbors
            for (Node originalNeighbor : originalCurrent.neighbors) {
                // Check if the neighbor has already been cloned
                if (!visited.containsKey(originalNeighbor)) {
                    // If not, clone it, add to map, and add original to the queue for processing later
                    visited.put(originalNeighbor, new Node(originalNeighbor.val));
                    queue.add(originalNeighbor);
                }
                // Connect the current clone's neighbor list to the neighbor's clone
                clonedCurrent.neighbors.add(visited.get(originalNeighbor));
            }
        }

        return visited.get(node); // Return the clone of the original starting node
    }
}
