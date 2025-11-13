package B6Modified_Binary_Search;

// Problem Statement: Search in Rotated Array (medium)
// LeetCode Question: 33. Search in Rotated Sorted Array

/**
 * Algorithm: Modified Binary Search
 * The time complexity of this approach remains O(log n) because we still discard half of the search space in each step.
 * Initialize left and right pointers to the start and end of the array.
 * In a while (left <= right) loop, find the mid index.
 * Check if the target is found at mid.
 * Determine which part of the array is sorted:
 * If the left side is sorted (arr[left] <= arr[mid]): Check if the target falls within this sorted range (arr[left] <= target < arr[mid]). If yes, search the left half (right = mid - 1); otherwise, search the right half (left = mid + 1).
 * If the right side is sorted (arr[left] > arr[mid]): Check if the target falls within this sorted range (arr[mid] < target <= arr[right]). If yes, search the right half (left = mid + 1); otherwise, search the left half (right = mid - 1).
 * If the loop terminates, the target was not found; return -1.
 */

public class MBS_9_Search_In_Rotated_Array_B8 {
    /**
     * Searches for a target value in a rotated sorted array.
     *
     * @param nums The rotated sorted array.
     * @param target The number to search for.
     * @return The index of the target if found, otherwise -1.
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Determine which half is sorted
            if (nums[left] <= nums[mid]) {
                // Left half is sorted
                // Check if the target is within the bounds of the sorted left half
                if (target >= nums[left] && target < nums[mid]) {
                    // Target is in the left half, discard the right half
                    right = mid - 1;
                } else {
                    // Target is in the unsorted right half, discard the left half
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                // Check if the target is within the bounds of the sorted right half
                if (target > nums[mid] && target <= nums[right]) {
                    // Target is in the right half, discard the left half
                    left = mid + 1;
                } else {
                    // Target is in the unsorted left half, discard the right half
                    right = mid - 1;
                }
            }
        }

        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] rotatedArray = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        int target2 = 3;

        System.out.println("Index of " + target1 + ": " + search(rotatedArray, target1)); // Output: 4
        System.out.println("Index of " + target2 + ": " + search(rotatedArray, target2)); // Output: -1
    }
}


