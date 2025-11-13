package B6Modified_Binary_Search;

/**
 * Algorithm: Modified Binary Search to Find Pivot
 * A modified binary search algorithm can be used to find the pivot index by comparing the middle element with the rightmost element in the search space.
 * Initialize left and right pointers to the start and end of the array.
 * While left < right, calculate the mid index.
 * Compare nums[mid] with nums[right]. If nums[mid] > nums[right], the pivot is in the right half, so set left = mid + 1. Otherwise, the pivot is in the left half (including mid), so set right = mid.
 * When left == right, the pivot index is found.
 */
public class FindPivotIndex {

    /**
     * Finds the index of the minimum element (pivot) in a rotated sorted array.
     *
     * @param nums The rotated sorted array.
     * @return The index of the pivot (minimum element).
     */
    public static int findPivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] rotatedArray1 = {4, 5, 6, 7, 0, 1, 2};
        int[] rotatedArray2 = {11, 13, 15, 17};
        int[] rotatedArray3 = {3, 4, 5, 1, 2};

        System.out.println("Pivot Index of Array 1: " + findPivotIndex(rotatedArray1));
        System.out.println("Pivot Index of Array 2: " + findPivotIndex(rotatedArray2));
        System.out.println("Pivot Index of Array 3: " + findPivotIndex(rotatedArray3));
    }
}
