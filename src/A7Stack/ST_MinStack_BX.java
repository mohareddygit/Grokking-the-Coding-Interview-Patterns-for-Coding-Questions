package A7Stack;

import java.util.Stack;


/** leet: 155
 * Minimum Stack
 * Design a stack class that supports the push, pop, top, and getMin operations.
 *
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * Each function should run in
 * O
 * (
 * 1
 * )
 * O(1) time.
 *
 * Example 1:
 *
 * Input: ["MinStack", "push", 1, "push", 2, "push", 0, "getMin", "pop", "top", "getMin"]
 *
 * Output: [null,null,null,null,0,null,2,1]
 *
 * Explanation:
 * MinStack minStack = new MinStack();
 * minStack.push(1);
 * minStack.push(2);
 * minStack.push(0);
 * minStack.getMin(); // return 0
 * minStack.pop();
 * minStack.top();    // return 2
 * minStack.getMin(); // return 1
 * Constraints:
 *
 * -2^31 <= val <= 2^31 - 1.
 * pop, top and getMin will always be called on non-empty stacks.
 */
public class ST_MinStack_BX {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public ST_MinStack_BX() {
        stack = new Stack<>();     // Main stack to store all values
        minStack = new Stack<>();  // Stack to track current minimums
    }

    public void push(int val) {
        stack.push(val);

        // Push to minStack if it's empty or val is <= current min
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        int removed = stack.pop();

        // If removed element is the current min, pop from minStack too
        if (removed == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek(); // Top of minStack is the current minimum
    }

    public static void main(String[] args) {
        ST_MinStack_BX minStack = new ST_MinStack_BX();
        minStack.push(5);
        minStack.push(3);
        minStack.push(7);
        minStack.push(3);
        System.out.println("Min: " + minStack.getMin()); // Output: 3
        minStack.pop();
        System.out.println("Min after pop: " + minStack.getMin()); // Output: 3
        minStack.pop();
        System.out.println("Min after another pop: " + minStack.getMin()); // Output: 3
        minStack.pop();
        System.out.println("Min after another pop: " + minStack.getMin()); // Output: 5
    }
}
