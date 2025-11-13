package B9Greedy_Algorithm;

public class GADP_MaximumProductSubarray_B6 {
    public int maxProduct(int[] nums) {
        int maxProduct = nums[0]; // Global max product
        int currMax = nums[0];    // Max product ending at current index
        int currMin = nums[0];    // Min product ending at current index (for negatives)

        for (int i = 1; i < nums.length; i++) {
            int tempMax = currMax;

            // Update current max and min considering current number
            currMax = Math.max(nums[i], Math.max(currMax * nums[i], currMin * nums[i]));
            currMin = Math.min(nums[i], Math.min(tempMax * nums[i], currMin * nums[i]));

            // Update global max
            maxProduct = Math.max(maxProduct, currMax);
        }

        return maxProduct;
    }

    public static void main(String[] args) {
        GADP_MaximumProductSubarray_B6 solver = new GADP_MaximumProductSubarray_B6();
        int[] nums = {2, 3, -2, 4};
        System.out.println("Maximum Product Subarray: " + solver.maxProduct(nums)); // Output: 6
    }
}
