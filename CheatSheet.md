![img.png](DataStructure.png)

![img.png](BigO.png)
📦 Stack (LIFO)
```java
Deque<Integer> stack = new ArrayDeque<>();
//Stack<Integer> stack = new Stack<>();
stack.push(10);         // Add to top
int top = stack.pop();  // Remove from top
int peek = stack.peek(); // View top
```

Stack is legacy (extends Vector, which is synchronized and slower).

ArrayDeque is faster and non-synchronized.

ArrayDeque has better cache locality and performance.

No overhead of node objects (as in LinkedList).

📬 Queue (FIFO)
```java
Deque<Integer> queue = new ArrayDeque<>();
//Queue<Integer> queue = new LinkedList<>();
queue.offer(10);        // Add to rear
int front = queue.poll(); // Remove from front
int peekFront = queue.peek(); // View front
```

🔗 LinkedList - flexible insert/remove
```java
LinkedList<String> list = new LinkedList<>();
list.add("A");          // Append
list.addFirst("B");     // Add to front
list.removeLast();      // Remove from end
```
Custom Single Linked List
```java
public class Node {
    int data;
    Node next;

// Constructor
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}
```

Custom Double Linked List
```java
public class Node {
    int data;
    Node next;
    Node prev;


// Constructor
    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
```
🌳 Binary Tree Node - hierarchial, acyclic
```java
class TreeNode { 
    
int val;
TreeNode left, right;
TreeNode(int val) {
    this.val = val; 
    }
}

TreeNode root = new TreeNode(1);
root.left = new TreeNode(2);
root.right = new TreeNode(3);
```

🌐 Graph (Adjacency List) - Flexible connections, can be cyclic
```java
Map<Integer, List<Integer>> graph = new HashMap<>();
graph.put(1, Arrays.asList(2, 3));
graph.put(2, Arrays.asList(4));
graph.put(3, Arrays.asList(4));

//observe get neighbors
graph.get(1); --> [2,3] are neignbors of 1
```
Custom Graph Node
```java
import java.util.List;
import java.util.ArrayList;
public class GraphNode {
    int data;
    List<GraphNode> neighbors;
    // Constructor
    public GraphNode(int data) {
        this.data = data;
        this.neighbors = new ArrayList<>();
    }
    // Add a neighbor
    public void addNeighbor(GraphNode neighbor) {
        this.neighbors.add(neighbor);
    }
}
```

Binary Tree & Graph Problems solutions approach:

Recursive solution is concise and intuitive. Can result in Stack overflow on very deep trees

Iterative solution avoids recursion stack overflow and is useful for very deep trees. Easy to understand and debug, easy to maintain.
Iterative can use more memory in wide trees.

Also, if possible prefer to use BreadFirstSearch(LevelOrderTraversal) with queue, for clarity and ease of solution.

For Dynamic programming solutions, some times there will be 2 solutions
1. with DP array - this array would need additional memory
2. Memory Optimized solution - without using DP array, use tracking variables of prior steps

🔺 Heap (Min-Heap)
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//PriorityQueue is a minHeap by default
//PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
minHeap.offer(5);       // Insert
int smallest = minHeap.poll(); // Remove min
int peekMin = minHeap.peek();  // View min
```

![img.png](AlgoPatterns.png)
