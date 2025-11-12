package A5Cyclic_Sort;

// Problem Statement: Cyclic Sort (easy)
// LeetCode Question: 912. Sort an Array

public class Problem_1_CyclicSort {

    public int[] sortArray(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int j = nums[i] - 1;
            if (nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                /*
                This modified condition ensures that a swap only happens if the current element is different from the element it's trying to swap with. If they are the same (duplicates), the algorithm simply increments i to move past the duplicate, thus avoiding the infinite loop and allowing you to find the missing/duplicate numbers by a second pass through the array.
                 */
                i++;
            }
        }
        return nums;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
