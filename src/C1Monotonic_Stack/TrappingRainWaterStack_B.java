package C1Monotonic_Stack;

import java.util.Stack;

public class TrappingRainWaterStack_B {
    public int trap(int[] height) {
        int n = height.length;
        int water = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // While current height is greater than the height at stack top
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop(); // Bottom of the valley

                if (stack.isEmpty()) {
                    break; // No left boundary
                }

                int distance = i - stack.peek() - 1; // Width between boundaries
                int boundedHeight = Math.min(height[i], height[stack.peek()]) - height[top];
                water += distance * boundedHeight;
            }

            stack.push(i); // Push current index
        }

        return water;
    }

    public static void main(String[] args) {
        TrappingRainWaterStack_B solver = new TrappingRainWaterStack_B();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Trapped Water: " + solver.trap(height)); // Output: 6
    }
}
