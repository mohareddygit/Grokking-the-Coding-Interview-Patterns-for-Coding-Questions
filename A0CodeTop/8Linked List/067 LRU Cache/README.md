## 146. LRU Cache 🔗

**Difficulty**: `Medium` - **Tags**: `Design`, `Linked List`, `Hash Table`

[LeetCode Problem Link](https://leetcode.com/problems/lru-cache/)

---

### Problem Statement 📜

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the `LRUCache` class:

1. `LRUCache(int capacity)`: Initialize the LRU cache with positive size capacity.
2. `int get(int key)`: Return the value of the key if the key exists, otherwise return `-1`.
3. `void put(int key, int value)`:
   - Update the value of the key if it exists.
   - Add the key-value pair if the key does not exist.
   - If the number of keys exceeds the capacity, evict the least recently used key.

**Constraints**:
- The functions `get` and `put` must run in **O(1)** average time complexity.

---

### Examples 🌟

🔹 **Example 1**:

**Input**:

```plaintext
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
```

**Output**:

```plaintext
[null, null, null, 1, null, -1, null, -1, 3, 4]
```

**Explanation**:

```plaintext
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // return -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
```

---

### Constraints ⚙️

- `1 <= capacity <= 3000`
- `0 <= key <= 10⁴`
- `0 <= value <= 10⁵`
- At most `2 * 10⁵` calls will be made to `get` and `put`.

---

### Solution 💡


**LeetCode 146: LRU Cache** in 2026, you must design a system that stores key-value pairs with a fixed capacity. When the cache is full and a new item is added, it must evict the **Least Recently Used (LRU)** item. Both `get` and `put` operations must run in **O(1) average time**.

1. The Core Idea: Hybrid Structure

No single standard data structure provides both𝑂(1) lookup and 𝑂(1) reordering:

-   **HashMap:** Provides  𝑂(1)

    lookup but has no concept of "order" or "recency."
-   **Doubly Linked List:** Provides 𝑂(1)

    addition/removal of nodes at the ends, which is perfect for tracking recency (Most Recent at the head, Least Recent at the tail).

By combining them, the **HashMap stores pointers to the Nodes** in the Doubly Linked List. This allows you to jump directly to any node in the list to update its position in 𝑂(1)

time.

2. Solution Approach

2.1 **Node Structure:** Each node contains `key`, `value`, `prev`, and `next`.
2.2 **Sentinel Nodes:** Use "dummy" `head` and `tail` nodes to avoid null checks.
2.3 **Get(key):**
- If the key doesn't exist in the Map, return -1.
- If it exists, move the corresponding node to the **head** (Most Recently Used) and return its value.
2.4 **Put(key, value):**
- If the key exists, update the value and move it to the **head**.
- If it’s a new key:
     - If the cache is at capacity, remove the node at the **tail** (Least Recently Used) and delete it from the Map.
     - Add the new node to the **head** and insert it into the Map


Use a combination of:
1. **HashMap**: For O(1) access to key-value pairs.
2. **Doubly Linked List**: For maintaining the order of usage (most recently used to least recently used).

---

#### Java Solution

```java
import java.util.*;

class LRUCache {
   class Node {
      int key, val;
      Node prev, next;
      Node(int k, int v) { key = k; val = v; }
   }

   private final int capacity;
   private final Map<Integer, Node> map;
   private final Node head, tail;

   public LRUCache(int capacity) {
      this.capacity = capacity;
      this.map = new HashMap<>();
      head = new Node(0, 0);
      tail = new Node(0, 0);
      head.next = tail;
      tail.prev = head;
   }

   public int get(int key) {
      if (!map.containsKey(key)) return -1;
      Node node = map.get(key);
      remove(node);
      insertAtHead(node);
      return node.val;
   }

   public void put(int key, int value) {
      if (map.containsKey(key)) {
         remove(map.get(key));
      }
      if (map.size() == capacity) {
         map.remove(tail.prev.key);
         remove(tail.prev);
      }
      Node newNode = new Node(key, value);
      insertAtHead(newNode);
      map.put(key, newNode);
   }

   private void remove(Node node) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
   }

   private void insertAtHead(Node node) {
      node.next = head.next;
      node.prev = head;
      head.next.prev = node;
      head.next = node;
   }
}
```

---

### Explanation of the Solution

1. **Node Definition**:
   - Each node stores a key-value pair.
   - Nodes are linked in both directions to allow quick removal.

2. **HashMap**:
   - Maps keys to nodes for O(1) access.

3. **Doubly Linked List**:
   - The head points to the most recently used node.
   - The tail points to the least recently used node.

4. **Operations**:
   - `get`: Move the node to the head of the list.
   - `put`: Add the node to the head. If the cache exceeds capacity, remove the node at the tail.

---

### Time Complexity ⏳

- **O(1)** for both `get` and `put` operations.

### Space Complexity 💾

- **O(n)** for storing `n` key-value pairs in the HashMap and Doubly Linked List.

---

### Follow-up 🧐

The solution is optimized for LRU Cache implementation with constant time complexity and minimal space usage. Further optimizations could involve concurrent data structures for multi-threaded environments.

You can find the full solution [here](Solution.java).


If an interviewer asks you to use  `LinkedHashMap`,  they are testing your knowledge of the  **Java Collections Framework (JCF)**  and its built-in capabilities. The  `LinkedHashMap`  is specifically designed to support LRU eviction through its  **access-order**  mode and the  `removeEldestEntry`  method.

1. The Idea

A standard  `LinkedHashMap`  maintains entries in insertion order. To transform it into an LRU Cache, you must:

1.  **Enable Access Order:**  Use a specific constructor that sets  `accessOrder`  to  `true`. This ensures that every time you call  `get()`  or  `put()`, the accessed element moves to the  **tail**  (Most Recently Used).
2.  **Define Eviction Policy:**  Override the  `protected`  method  `removeEldestEntry()`. This method is automatically called after every  `put()`  operation. If it returns  `true`, the map deletes its "eldest" entry (the one at the head of the list).

2. Java Implementation

The most professional way is to  **extend**  `LinkedHashMap`, allowing you to use all standard map methods directly.



```java
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache extends LinkedHashMap<Integer, Integer> {
    private final int capacity;

    public LRUCache(int capacity) {
        // super(initialCapacity, loadFactor, accessOrder)
        // Set accessOrder to true for LRU behavior
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        // getOrDefault is useful here to meet LeetCode's -1 requirement
        return super.getOrDefault(key, -1);
    }

    // No need to override put(key, value) unless you need custom behavior;
    // LinkedHashMap's put already handles moving to end and eviction check.

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        // Evict when the current size exceeds the fixed capacity
        return size() > capacity;
    }
}

```

3. Key Interview Points

-   **The "Magic" Constructor:**  Be prepared to explain the  `super(capacity, 0.75f, true)`  call. The  `true`  parameter is what switches the map from  **insertion-order**  to  **access-order**.
-   **Complexity:**  This approach maintains  **O(1) average time**  for both  `get`  and  `put`, just like a manual implementation.
-   **Trade-offs:**
    -   **Pros:**  Extremely concise, less error-prone than manual pointer manipulation, and utilizes production-ready library code.
    -   **Cons:**  Less flexible for low-level optimizations (e.g., custom synchronization) and hides the underlying logic of how a hash table and doubly linked list interact, which is why interviewers often ask for the manual version first.
-   **Thread Safety:**  Note that  `LinkedHashMap`  is  **not thread-safe**. In a real-world multi-threaded 2026 environment, you would need to wrap it using  `Collections.synchronizedMap`  or use more complex concurrency controls.