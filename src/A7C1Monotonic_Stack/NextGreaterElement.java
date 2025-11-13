package A7C1Monotonic_Stack;

import java.util.Stack;
import java.util.Arrays;

public class NextGreaterElement {

    public static int[] findNGE(int[] nums) {
        int n = nums.length;
        // Result array initialized to -1 (default for no NGE found)
        int[] result = new int[n];
        Arrays.fill(result, -1);

        // Stack to store indices of elements that haven't found their NGE yet
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int currentNum = nums[i];

            // While the stack is not empty AND the element at the top index
            // is smaller than the current number...
            while (!stack.isEmpty() && nums[stack.peek()] < currentNum) {
                // ... the current number is the NGE for the element at the top index.
                int stackIndex = stack.pop();
                result[stackIndex] = currentNum;
            }

            // Push the current index onto the stack
            stack.push(i);
        }

        // Elements remaining in the stack have no greater element to their right,
        // which is already handled by the initial fill(-1).

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 25};
        // NGE for 4 is 5
        // NGE for 5 is 25
        // NGE for 2 is 25
        // NGE for 25 is -1
        int[] ngeResult = findNGE(arr);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("NGE:   " + Arrays.toString(ngeResult)); // Output: [5, 25, 25, -1]

        int[] arr2 = {13, 7, 6, 12};
        int[] ngeResult2 = findNGE(arr2);
        System.out.println("Array: " + Arrays.toString(arr2));
        System.out.println("NGE:   " + Arrays.toString(ngeResult2)); // Output: [-1, 12, 12, -1]
    }
}

