package B6Modified_Binary_Search;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,2,4,5,6,7] might become:
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 *
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 *
 */
public class MBS_FindMinimumRotatedArray_B7 {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        /**
         * If you used left <= right with right = mid,
         * you would enter an infinite loop once left and right become equal.
         */
        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid element is greater than right, minimum is in right half
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // Minimum is in left half including mid
                right = mid;
            }
        }

        // When left == right, we've found the minimum
        return nums[left];
    }

    public static void main(String[] args) {
        MBS_FindMinimumRotatedArray_B7 solver = new MBS_FindMinimumRotatedArray_B7();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Minimum element: " + solver.findMin(nums)); // Output: 0
    }
}
