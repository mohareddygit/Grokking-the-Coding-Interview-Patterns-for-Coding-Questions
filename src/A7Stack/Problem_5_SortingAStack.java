package A7Stack;

// Problem Statement: Sorting a Stack
// LeetCode Question: -
/**
 * To sort a stack in Java, you must use at least one auxiliary stack because you do not have random access to elements within a stack data structure.
 *
 * The goal is to sort the original stack such that the smallest elements are at the top (or bottom, depending on your preferred definition of "sorted stack"). The most common interpretation is that the elements should be ordered from bottom to top in ascending order, so the smallest element is at the top when finished.
 * Algorithm: Using One Additional Stack
 * This approach works similarly to an insertion sort. We move elements one by one from the original stack (inputStack) to a temporary sorted stack (tempStack), ensuring that tempStack always remains sorted.
 * Initialize a temporary empty stack (tempStack).
 * While inputStack is not empty:
 * Pop an element (currentElement) from inputStack.
 * While tempStack is not empty AND the element at the top of tempStack is greater than currentElement:
 * Pop the larger element from tempStack and push it back onto the inputStack.
 * Push currentElement onto tempStack (it is now in its correct sorted position relative to others in tempStack).
 * The tempStack now contains all elements in sorted order (smallest at the top). You can return this tempStack or push the elements back to the original inputStack.
 */

import java.util.Stack;

public class Problem_5_SortingAStack {
    /**
     * Sorts a stack using an auxiliary stack.
     * The result stack will have the smallest element at the top.
     *
     * @param inputStack The stack to be sorted.
     * @return The sorted stack.
     */
    public static Stack<Integer> sortStack(Stack<Integer> inputStack) {
        Stack<Integer> tempStack = new Stack<>();

        // While the input stack still has elements to sort
        while (!inputStack.isEmpty()) {
            // 1. Pop the current element from the input
            int currentElement = inputStack.pop();

            // 2. While tempStack is not empty AND its top element is larger than currentElement
            while (!tempStack.isEmpty() && tempStack.peek() > currentElement) {
                // Pop the larger element from tempStack and push it back to inputStack
                inputStack.push(tempStack.pop());
            }

            // 3. Place currentElement into its correct position in tempStack
            tempStack.push(currentElement);
        }

        // The tempStack now holds the elements in sorted order (smallest at the top)
        return tempStack;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(34);
        stack.push(3);
        stack.push(31);
        stack.push(98);
        stack.push(92);
        stack.push(23);

        System.out.println("Original stack (top to bottom): " + stack);
        Stack<Integer> sortedStack = sortStack(stack);
        System.out.println("Sorted stack (top to bottom):   " + sortedStack);

        // Output will show the smallest element at the top of the sorted stack
        // e.g., Sorted stack (top to bottom): [98, 92, 34, 31, 23, 3]
    }
}


