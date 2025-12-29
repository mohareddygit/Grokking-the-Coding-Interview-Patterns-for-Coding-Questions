package A0CompFAQ;

/**
 * The optimal solution for LeetCode 283: Move Zeroes uses a two-pointer approach to rearrange the array in-place. This method achieves \(O(n)\) time complexity and \(O(1)\) space complexity by maintaining the relative order of non-zero elements while shifting all zeros to the end. Java Implementation The following Java solution efficiently moves zeros by overwriting elements in a single pass and then filling the remainder with zeros
 *
 */
class CT22_MoveZeroes_LC283 {
    public void moveZeroes(int[] nums) {
        // Pointer to keep track of the next position for a non-zero element
        int lastNonZeroFoundAt = 0;

        // First pass: Move all non-zero elements to the front
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroFoundAt++] = nums[i];
            }
        }

        // Second pass: Fill the remaining space with zeros
        for (int i = lastNonZeroFoundAt; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * Alternative: Swap Approach
     * For an even more optimized approach that minimizes array writes, you can swap non-zero elements with the element at the tracking pointer. This completes the task in a single pass.
     *
     */

    public void moveZeroesSwapApproachSinglePass(int[] nums) {
        for (int lastNonZeroFoundAt = 0, current = 0; current < nums.length; current++) {
            if (nums[current] != 0) {
                // Swap non-zero element with the first available 'zero' position
                int temp = nums[lastNonZeroFoundAt];
                nums[lastNonZeroFoundAt++] = nums[current];
                nums[current] = temp;
            }
        }
    }
}