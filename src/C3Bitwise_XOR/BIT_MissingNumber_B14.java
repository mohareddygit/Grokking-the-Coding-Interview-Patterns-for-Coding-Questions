package C3Bitwise_XOR;

import java.util.Arrays;

public class BIT_MissingNumber_B14 {
    /**
     * Time: O(n)
     *
     * Space: O(1)
     *
     * Idea: Total sum of 0..n minus actual sum gives the missing number.
     * @param nums
     * @return
     */
    public int missingNumber_SumFormulaOptimal(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }

    /**
     * Time: O(n)
     *
     * Space: O(1)
     *
     * Idea: XOR cancels out matching pairs; the leftover is the missing number.
     * @param nums
     * @return
     */
    public int missingNumber_XORTrick(int[] nums) {
        int xor = 0, n = nums.length;

        for (int i = 0; i < n; i++) {
            xor ^= i ^ nums[i];
        }

        return xor ^ n;
    }

    /**
     * Time: O(n log n)
     *
     * Space: O(1) if in-place sort
     *
     * Idea: After sorting, index should match value.
     * @param nums
     * @return
     */
    public int missingNumber_sortingLessOptimal(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }

        return nums.length;
    }


}
