package A1Two_Pointers;

/**
 * Leetcode 167: Two Sum II – Input Array Is Sorted, using the two-pointer technique.
 */
public class TP_TwoSumII_BX {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                // Return 1-based indices
                return new int[] {left + 1, right + 1};
            } else if (sum < target) {
                left++; // Need a larger sum
            } else {
                right--; // Need a smaller sum
            }
        }

        return new int[] {-1, -1}; // Should never reach here
    }
}
