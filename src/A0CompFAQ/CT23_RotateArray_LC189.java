package A0CompFAQ;

/**
 * 189. Rotate Array
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 */

/**
 * In LeetCode 189: Rotate Array, you are tasked with shifting all elements
 * of an array to the right by k positions.
 * The optimal solution uses an in-place reversal algorithm that
 * achieves \(O(n)\) time complexity and \(O(1)\) space complexity.
 * The 3-Step Reversal Algorithm.
 * The most efficient way to rotate an array to the right by k steps is to perform three specific reversals:
 * Reverse the entire array. This moves the last k elements to the front, but they will be in the wrong order.
 * Reverse the first k elements. This restores the correct order for the elements that were moved to the front.
 * Reverse the remaining n - k elements. This restores the correct order for the rest of the array
 */

/**
 * In the Rotate Array problem,
 * k %= n (where % is the modulo operator) is used to simplify the number of rotations.
 * The rule is: Rotating an array of length \(n\) exactly \(n\) times results in the original array.
 * The Logic, Imagine you have an array of length 3: [1, 2, 3].
 * Rotate 1 time: [3, 1, 2]
 * Rotate 2 times: [2, 3, 1]
 * Rotate 3 times: [1, 2, 3] (Back to start!)
 * Rotate 4 times: [3, 1, 2] (Same as rotating 1 time)
 * By using k = k % n, you are removing all the "full circles" (complete rotations)
 * and keeping only the remaining steps that actually change the array.
 *
 * A Concrete Example Input: nums = [1, 2, 3, 4, 5], k = 13
 * Array length (\(n\)) = 5Desired rotations (\(k\)) = 13
 * Calculation:13 % 5 = 3 (because 13 divided by 5 is 2 with a remainder of 3).
 * Result:Instead of moving elements 13 times (which is inefficient and could cause errors),
 * the code simply performs 3 rotations.
 * The final result is exactly the same because 13 rotations is just "two full circles plus 3 extra steps."
 */
class CT23_RotateArray_LC189 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        // Step 1: Normalize k
        k %= n; 
        
        // Step 2: Three reverse operations
        reverse(nums, 0, n - 1); // Reverse entire array
        reverse(nums, 0, k - 1); // Reverse first k elements
        reverse(nums, k, n - 1); // Reverse remaining elements
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}