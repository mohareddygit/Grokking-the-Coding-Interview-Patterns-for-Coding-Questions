package B6Modified_Binary_Search;

public class MBS_FindMinimumRotatedArray_B7 {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

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
