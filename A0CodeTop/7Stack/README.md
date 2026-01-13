# Topic 7 Stack

## 🧑‍💻 Introduction to Stack

A **stack** is a linear data structure that follows the **Last In, First Out (LIFO)** principle. Think of it like a stack of plates 🍽️ — you always add new plates on top and take the top one off first.

Stacks are powerful tools used to solve a variety of problems efficiently. They’re like a magic box 🧙‍♂️ for handling nested structures, evaluating expressions, and keeping track of previous elements in many algorithms.

### ⚡ Key Operations of a Stack

- **Push**: Add an element to the top of the stack 🆙
- **Pop**: Remove the top element from the stack 🔽
- **Peek** or **Top**: Look at the top element without removing it 👀
- **IsEmpty**: Check if the stack is empty 🚫
- **Size**: Get the number of elements in the stack 📏

### 💡 Where are Stacks Used?

Stacks are commonly used in scenarios like:

- **Expression evaluation** (e.g., arithmetic expressions, reverse Polish notation) ➗
- **Parsing and backtracking** (e.g., balanced parentheses) 🧩
- **Depth-first search (DFS)** in graph traversal 🌲

They can be implemented using arrays or linked lists, and many algorithms rely on stack-based operations to solve problems effectively.

---

## 📝 Problems in the Stack Topic

1. **Valid Parentheses**
   Problem URL: [LeetCode - Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
   [Study here](052%20Valid%20Parentheses/README.md) 📘

2. **Simplify Path**
   Problem URL: [LeetCode - Simplify Path](https://leetcode.com/problems/simplify-path/)
   [Study here](053%20Simplify%20Path/README.md) 📘

3. **Min Stack**
   Problem URL: [LeetCode - Min Stack](https://leetcode.com/problems/min-stack/)
   [Study here](054%20Min%20Stack/README.md) 📘

4. **Evaluate Reverse Polish Notation**
   Problem URL: [LeetCode - Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/)
   [Study here](055%20Evaluate%20Reverse%20Polish%20Notation/README.md) 📘

5. **Basic Calculator**
   Problem URL: [LeetCode - Basic Calculator](https://leetcode.com/problems/basic-calculator/)
   [Study here](056%20Basic%20Calculator/README.md) 📘

---

### Queue Using Stack

LeetCode 232 requires implementing a First-In-First-Out (FIFO) queue using two Last-In-First-Out (LIFO) stack

the most efficient implementation of a FIFO queue using LIFO stacks involves two

`Deque` objects. While Java has a legacy `Stack` class, it is generally recommended to use `ArrayDeque` because it is faster, not synchronized, and follows modern collection standards.

```java
import java.util.ArrayDeque;
import java.util.Deque;

class MyQueue {
    // Stack for incoming elements
    private Deque<Integer> input;
    // Stack for outgoing elements
    private Deque<Integer> output;

    public MyQueue() {
        input = new ArrayDeque<>();
        output = new ArrayDeque<>();
    }
    
    // Pushes element x to the back of the queue
    public void push(int x) {
        input.push(x);
    }
    
    // Removes the element from the front of the queue and returns it
    public int pop() {
        peek(); // Ensure output stack is populated
        return output.pop();
    }
    
    // Returns the element at the front of the queue
    public int peek() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        return output.peek();
    }
    
    // Returns true if the queue is empty, false otherwise
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }
}

```

-   **Two Stacks:** Elements are always added to the `input` stack. When a retrieval (`pop` or `peek`) is needed, if the `output` stack is empty, all elements are moved from `input` to `output`.
-   **Order Reversal:** Moving elements from one stack to another reverses their order, which turns the "last-in" element into the "first-out" element.
-   **Amortized Complexity:** Each element is pushed and popped at most twice. Thus, the average (amortized) time complexity for each operation is **𝑂(1)**.

Why use `ArrayDeque` over `Stack`?

-   **Performance:** `Stack` extends `Vector`, which is synchronized. This adds unnecessary overhead in single-threaded environments like LeetCode.
-   **Architecture:** `Deque` is an interface, allowing for more flexible and consistent code than the legacy `Stack` class.


### Stack using queue
To implement a stack using queues (LeetCode 225), you must

simulate Last-In-First-Out (LIFO) behavior using First-In-First-Out (FIFO) operations. The most efficient approach in Java uses a single `Queue` and re-orders elements during the `push` operation.


This implementation uses `ArrayDeque` as the underlying queue structure for better performance.

```java
import java.util.ArrayDeque;
import java.util.Queue;

class MyStack {
    private Queue<Integer> queue;

    public MyStack() {
        queue = new ArrayDeque<>();
    }
    
    // Pushes element x onto the stack
    // Time Complexity: O(n)
    public void push(int x) {
        queue.offer(x);
        // Rotate the queue so the newest element is at the front
        int size = queue.size();
        for (int i = 0; i < size - 1; i++) {
            queue.offer(queue.poll());
        }
    }
    
    // Removes the element on top of the stack and returns it
    // Time Complexity: O(1)
    public int pop() {
        return queue.poll();
    }
    
    // Returns the element on top of the stack
    // Time Complexity: O(1)
    public int top() {
        return queue.peek();
    }
    
    // Returns true if the stack is empty, false otherwise
    public boolean empty() {
        return queue.isEmpty();
    }
}

```

Key Logic

-   **Reversing Order on Push:** Every time a new element is added to the back of the queue, the existing elements are immediately moved from the front to the back one by one. This positions the most recently added element at the front, effectively simulating a stack's "top".
-   **Standard Queue Operations:** The implementation strictly uses `offer()` (push to back), `poll()` (pop from front), and `peek()` (look at front).

Complexity Summary

-   **Push:** 𝑂(𝑛), as it requires rotating  𝑛−1 elements for every new insertion.
-   **Pop/Top:** 𝑂(1), because the current "top" of the stack is always maintained at the front of the queue.
-   **Space:** 𝑂(𝑛), to store 𝑛 elements in the queue.
