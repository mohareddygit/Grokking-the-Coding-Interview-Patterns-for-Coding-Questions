## 100. Same Tree 🔗

**Difficulty**: `Easy` - **Tags**: `Binary Tree`, `DFS`, `Recursion`

[LeetCode Problem Link](https://leetcode.com/problems/same-tree/)

---

### Problem Statement 📜

Given the roots of two binary trees `p` and `q`, write a function to check if they are the same or not.

Two binary trees are considered the same if they are:
1. Structurally identical.
2. Have nodes with the same values.

---

### Examples 🌟

🔹 **Example 1**:

![](ex1.jpg)

**Input**:

```plaintext
p = [1,2,3], q = [1,2,3]
```

**Output**:

```plaintext
true
```

---

🔹 **Example 2**:

![](ex2.jpg)

**Input**:

```plaintext
p = [1,2], q = [1,null,2]
```

**Output**:

```plaintext
false
```

---

🔹 **Example 3**:

![](ex3.jpg)

**Input**:

```plaintext
p = [1,2,1], q = [1,1,2]
```

**Output**:

```plaintext
false
```

---

### Constraints ⚙️

- The number of nodes in both trees is in the range `[0, 100]`.
- `-10⁴ <= Node.val <= 10⁴`.

---

### Solution 💡

To determine if two trees are the same, compare the following for both trees:
1. Values of the current nodes.
2. Recursively check their left and right subtrees.

---

#### Java Solution (Recursive Approach)

```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Both nodes are null: the trees are identical
        if (p == null && q == null) {
            return true;
        }
        // One node is null, or the values are different: the trees are not the same
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```

---

### Explanation of the Solution

1. If both nodes are `null`, the subtrees are identical, return `true`.
2. If only one node is `null` or their values are not equal, return `false`.
3. Recursively compare the left and right subtrees of `p` and `q`.
4. If both recursive calls return `true`, the trees are the same.

---

### Time Complexity ⏳

- **O(n)**, where `n` is the minimum number of nodes in both trees. Each node is visited once.

### Space Complexity 💾

- **O(h)**, where `h` is the height of the tree (stack space for recursion).

---

### Follow-up 🧐

- Consider solving the problem iteratively using a stack or queue.
- How would the solution change if the input was not binary trees but general trees?

You can find the full solution [here](Solution.java)


##LeetCode 100: Same Tree** 
iteratively, you can use either a **Stack** (for Depth-First Search) or a **Queue** (for Breadth-First Search). The iterative approach is often preferred in production to prevent stack overflow errors on very deep trees.

1. The Idea: Synchronized Traversal

The core logic is to traverse both trees simultaneously and compare the nodes at each step. For the trees to be the same:

-   Both current nodes must be `null`, OR
-   Both current nodes must be non-null AND have the same value.
-   If one is `null` and the other is not, or their values differ, the trees are not the same.

2. Solution Approach (BFS with a Queue)

3.  Initialize a single `Queue` and add the root nodes of both trees as a pair.
4.  While the queue is not empty:
    -   Retrieve the next pair of nodes (`p` and `q`).
    -   **Check equality:**
        -   If both are `null`, continue to the next pair (they match).
        -   If only one is `null`, or if `p.val != q.val`, return `false`.
    -   **Add children:** Push the left children of both nodes as a pair, then the right children as a pair into the queue.
5.  If the loop completes without returning `false`, return `true`.


```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);

        while (!queue.isEmpty()) {
            TreeNode nodeP = queue.poll();
            TreeNode nodeQ = queue.poll();

            // Both null is okay, continue checking others
            if (nodeP == null && nodeQ == null) continue;
            
            // Structural mismatch or value mismatch
           /* if (nodeP == null || nodeQ == null || nodeP.val != nodeQ.val) {
                return false;
            }*/

            if (nodeP == null && nodeQ != null) return false;
            if (nodeQ == null && nodeP != null) return false;
            
            // 2. Since we know both are NOT null, we can safely check values
            if (nodeP.val != nodeQ.val) return false;

            // Add corresponding children as pairs
            queue.add(nodeP.left);
            queue.add(nodeQ.left);
            queue.add(nodeP.right);
            queue.add(nodeQ.right);
        }
        return true;
    }
}

```

4. Complexity Analysis

-   **Time Complexity:** **𝑂(𝑁)**, where 𝑁 is the number of nodes in the smaller tree, as we visit each node at most once.
-   **Space Complexity:** **𝑂(𝑁)**. In the worst case (a perfectly balanced tree), the queue can hold up to 𝑂(𝑁) nodes at the widest level.

5. Why use `Deque` or `LinkedList` instead of `Stack`?

In Java, the legacy `Stack` class is synchronized, which adds unnecessary performance overhead. For iterative solutions in 2026, it is standard practice to use `ArrayDeque` or `LinkedList` as they are more efficient for single-threaded tasks.