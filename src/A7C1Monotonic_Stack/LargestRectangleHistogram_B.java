package A7C1Monotonic_Stack;

import java.util.Stack;

public class LargestRectangleHistogram_B {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            // Use 0 as sentinel height at the end
            int h = (i == n) ? 0 : heights[i];

            // Maintain increasing stack
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleHistogram_B solver = new LargestRectangleHistogram_B();
        int[] heights = {2,1,5,6,2,3};
        System.out.println("Max Rectangle Area: " + solver.largestRectangleArea(heights)); // Output: 10
    }
}
