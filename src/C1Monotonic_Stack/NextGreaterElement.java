package C1Monotonic_Stack;

import java.util.Stack;
import java.util.Arrays;

public class NextGreaterElement {

    public static int[] findNextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n]; // Array to store the result
        Stack<Integer> stack = new Stack<>(); // Stack to keep track of indices

        // Traverse the array from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements from the stack that are less than or equal to current element
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }

            // If stack is empty, no greater element to the right
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current element onto the stack
            stack.push(nums[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 2, 25};
        int[] result = findNextGreaterElements(nums);
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("Next Greater Elements: " + Arrays.toString(result));
    }
}
