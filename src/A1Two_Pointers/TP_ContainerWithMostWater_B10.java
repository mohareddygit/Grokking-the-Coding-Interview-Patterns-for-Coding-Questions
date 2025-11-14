package A1Two_Pointers;

/**
 * 11. Container With Most Water
 */
public class TP_ContainerWithMostWater_B10 {
    public int maxArea(int[] height) {
        int left = 0;                  // Start pointer
        int right = height.length - 1; // End pointer
        int maxArea = 0;

        while (left < right) {
            // Calculate area between the two lines
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int area = width * minHeight;

            // Update max area if current is larger
            maxArea = Math.max(maxArea, area);

            // Move the pointer pointing to the shorter line
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
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println("Max Water Container Area: " + solver.maxArea(height)); // Output: 49
    }
}
