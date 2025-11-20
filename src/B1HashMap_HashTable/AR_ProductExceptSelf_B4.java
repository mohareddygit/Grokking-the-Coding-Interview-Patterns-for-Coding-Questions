package B1HashMap_HashTable;

/**
 * 238. Product of Array Except Self
 * The LeetCode 238 "Product of Array Except Self" problem asks for an output array where output[i] is equal to the product of all elements in the input array except nums[i], with the constraint of solving it in O(n) time without using the division operation.
 * The standard approach uses two passes: one to calculate products to the left of each element, and another to incorporate products to the right.
 * Explanation of the Two-Pass Approach
 * Initialize result array: Create an output array of the same size as the input, initialized with 1s.
 * Left Products Pass:
 * Iterate through the array from left to right.
 * For each index i, result[i] will temporarily hold the product of all elements to the left of i.
 * We maintain a running product (leftProduct), which is updated in each step.
 * Right Products Pass:
 * Iterate through the array from right to left.
 * Now, result[i] already contains the left product.
 * We maintain a running product (rightProduct) of all elements to the right of the current index.
 * Multiply result[i] by this rightProduct to get the final answer.
 * Update the rightProduct in each step.
 * This method achieves O(n) time complexity because it performs two linear scans of the array, and O(1) space complexity (if we disregard the output array as per the problem constraints)
 */
public class AR_ProductExceptSelf_B4 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // --- First Pass: Calculate left products ---
        int leftProduct = 1;
        for (int i = 0; i < n; i++) {
            // At this point, leftProduct holds the product of everything to the left of i
            result[i] = leftProduct;
            // Update leftProduct for the next iteration (i+1)
            leftProduct *= nums[i];
        }

        // --- Second Pass: Calculate right products and final result ---
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            // result[i] currently holds the left product
            // Multiply it by the right product calculated so far
            result[i] *= rightProduct;
            // Update rightProduct for the next iteration (i-1)
            rightProduct *= nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        AR_ProductExceptSelf_B4 solver = new AR_ProductExceptSelf_B4();
        int[] nums = {1, 2, 3, 4};
        int[] result = solver.productExceptSelf(nums);
        System.out.print("Output: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
        // Output: 24 12 8 6
    }
}
