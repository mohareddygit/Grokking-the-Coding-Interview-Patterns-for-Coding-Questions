package A0CompFAQ;

/**
 * The optimal solution for LeetCode 75: Sort Colors uses the Dutch National Flag algorithm,
 * an in-place sorting technique invented by Edsger Dijkstra.
 * It sorts an array containing only 0s, 1s, and 2s in a single pass
 * (\(O(n)\) time) without extra space (\(O(1)\) space)
 *
 */

/**
 * This algorithm uses three pointers: low (for 0s), mid (current element), and high (for 2s)
 * How the Algorithm Works
 * Initialization: Set low and mid to the start of the array and high to the end.
 * Region 0 (Red): Everything to the left of low contains only 0s.
 * Region 1 (White): Everything between low and mid-1 contains only 1s.
 * Region 2 (Blue): Everything to the right of high contains only 2s.
 * Process: The mid pointer traverses the array until it crosses high.
 * When nums[mid] is 2, it is swapped to the end, but since the new value at mid is unknown,
 * the pointer stays put for one more chec
 */
class CT29_SortColors_LC75 {
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