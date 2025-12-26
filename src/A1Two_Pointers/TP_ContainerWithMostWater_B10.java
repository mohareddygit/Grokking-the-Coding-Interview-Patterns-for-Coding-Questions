package A1Two_Pointers;

/**
 * 11. Container With Most Water
 */
public class TP_ContainerWithMostWater_B10 {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            // 1. Calculate the width (distance between pointers)
            int width = right - left;

            // 2. The height of the water is limited by the shorter line
            int currentHeight = Math.min(height[left], height[right]);

            // 3. Calculate area and update maxArea if current is larger
            int area = width * currentHeight;
            maxArea = Math.max(maxArea, area);

            // 4. Greedy Move: Move the pointer pointing to the shorter line
            // Why? To find a potentially taller line that might increase the area.
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        TP_ContainerWithMostWater_B10 solver = new TP_ContainerWithMostWater_B10();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Max Water Container Area: " + solver.maxArea(height)); // Output: 49
    }
}
