package A0CompFAQ;

/**
 * The optimal solution for LeetCode 75: Sort Colors uses the Dutch National Flag algorithm,
 * an in-place sorting technique invented by Edsger Dijkstra.
 * It sorts an array containing only 0s, 1s, and 2s in a single pass
 * (\(O(n)\) time) without extra space (\(O(1)\) space)
 *
 */



/**
 * How it Works
 * Low Pointer (low): Tracks where the next 0 should be placed.
 * Mid Pointer (mid): Iterates through the array to examine each element.
 * High Pointer (high): Tracks where the next 2 should be placed.
 * The Logic:
 *
 * When you see a 0, swap it to the front (low) and move both low and mid forward.
 * When you see a 1, just move mid forward.
 * When you see a 2, swap it to the back (high) and decrement high.
 * Do not increment mid yet, as the new value at mid (swapped from the back) could be a 0 or 1
 * that still needs to be processed.
 * The mid pointer traverses the array until it crosses high.
 */
class CT29_SortColorsDutchFlag_LC75 {
    public void sortColors(int[] nums) {
        int low = 0;           // Boundary for 0s
        int mid = 0;           // Current element pointer
        int high = nums.length - 1; // Boundary for 2s

        while (mid <= high) {
            if (nums[mid] == 0) {
                // If it's a 0, swap with low and move both forward
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // If it's a 1, it's already in the correct region
                mid++;
            } else {
                // If it's a 2, swap with high and decrease high
                // Do not increment mid yet, as the swapped element needs checking
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}