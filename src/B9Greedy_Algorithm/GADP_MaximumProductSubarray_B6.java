package B9Greedy_Algorithm;

public class GADP_MaximumProductSubarray_B6 {

    /**
     *  This is copilot solution - better than Google & Nikhil Lohia
     *  and also follows/builds upon the kadane algo pattern/formula for maxSubArray
     *
     *  maxSum:
     *  int result = nums[0];
     *  currentSum = Math.max(nums[i], currentSum + nums[i])
     *   result/maxSum = Math.max(result/maxSum, currentSum);
     *
     *  maxProduct:
     *  int result = nums[0];
     *  maxSoFar = Math.max(current, maxSoFar * current);
     *  minSoFar = Math.min(current, minSoFar * current);
     *  result = Math.max(result, maxSoFar);
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        // Initialize with first element
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = nums[0];

        // Traverse the array
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];


            /**
             * If nums[i] is negative, swap maxSoFar and minSoFar before updating
             * multiplying by a negative number flips signs:
             *
             * A large positive product becomes negative.
             *
             * A large negative product becomes positive (and potentially the new maximum).
             *
             * So when nums[i] is negative, the roles of maxSoFar and minSoFar switch. That’s why we swap them before updating.
             */
            // If current is negative, swap max and min
            if (current < 0) {
                int temp = maxSoFar;
                maxSoFar = minSoFar;
                minSoFar = temp;
            }

            // Update max and min products ending at current index
            maxSoFar = Math.max(current, maxSoFar * current);
            minSoFar = Math.min(current, minSoFar * current);

            // Update global result
            result = Math.max(result, maxSoFar);
        }

        return result;
    }

    public static void main(String[] args) {
        GADP_MaximumProductSubarray_B6 solver = new GADP_MaximumProductSubarray_B6();
        int[] nums = {2, 3, -2, 4};
        System.out.println("Maximum Product Subarray: " + solver.maxProduct(nums)); // Output: 6
    }
}
