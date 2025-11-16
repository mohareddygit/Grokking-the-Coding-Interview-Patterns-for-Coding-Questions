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

🌳 Binary Tree Node - hierarchial, acyclic
```java
class TreeNode {
int val;
TreeNode left, right;
TreeNode(int val) { this.val = val; }
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
```

🔺 Heap (Min-Heap)
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.offer(5);       // Insert
int smallest = minHeap.poll(); // Remove min
int peekMin = minHeap.peek();  // View min
```
