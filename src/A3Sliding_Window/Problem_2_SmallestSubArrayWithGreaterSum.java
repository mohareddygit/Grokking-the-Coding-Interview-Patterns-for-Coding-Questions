package A3Sliding_Window;

// Problem Statement: Smallest Subarray With a Greater Sum (easy)
// LeetCode Question: 209. Minimum Size Subarray Sum

/**
 * The LeetCode problem 209, "Minimum Size Subarray Sum", asks for the minimum length of a contiguous subarray whose sum is greater than or equal to a given
 * target value.
 * This problem is solved using a Dynamic Sliding Window approach. Unlike fixed-size windows, this window dynamically expands and contracts to find the minimum possible length that satisfies the sum condition.
 * Algorithm: Dynamic Sliding Window
 * The approach uses two pointers, left and right, to define the boundaries of a dynamic window.
 * Expand the Window: Move the right pointer forward, adding elements to the windowSum.
 * Check Condition & Contract: Once the windowSum is greater than or equal to the target:
 * Record the current window length (right - left + 1) and update the minLength found so far.
 * Contract the window from the left side (subtract nums[left] from windowSum and increment left) while the sum still meets the target condition. This tries to find a smaller valid subarray starting later.
 * Repeat: Continue expanding the window with the right pointer until the end of the array.
 */
public class Problem_2_SmallestSubArrayWithGreaterSum {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int windowSum = 0;
        // Initialize minLength to a value larger than any possible array length
        int minLength = Integer.MAX_VALUE;

        // Iterate with the right pointer to expand the window
        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];

            // While the window sum meets or exceeds the target condition
            while (windowSum >= target) {
                // 1. Record the current length and update minimum length found so far
                minLength = Math.min(minLength, (right - left + 1));

                // 2. Contract the window from the left
                windowSum -= nums[left];
                left++;
            }
        }

        // If minLength remains Integer.MAX_VALUE, no valid subarray was found.
        // Otherwise, return the minimum length found.
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * The variable
     * minLength is initialized to Integer.MAX_VALUE because we are trying to find the absolute minimum possible length among valid subarrays.
     * Here is why this initialization value is necessary and effective:
     * 1. Ensures Math.min() Works Correctly
     * The core logic of the algorithm relies on updating minLength using Math.min():
     *
     * minLength = Math.min(minLength, (right - left + 1));
     *

     * For this comparison to work correctly on the very first valid subarray we find, the initial value of minLength must be larger than any possible valid length. Integer.MAX_VALUE guarantees this.
     * Example: If the first valid subarray found has a length of 4, Math.min(Integer.MAX_VALUE, 4) correctly sets minLength to 4.
     * If you initialized minLength to a small number like 0 or 1, Math.min() would incorrectly keep that small initial value throughout the process, giving a wrong answer.
     * 2. Handles the "No Solution" Case Gracefully
     * The problem requires a specific return value if no subarray is found that sums up to or exceeds the target.
     * If the loop finishes and minLength is still Integer.MAX_VALUE, it means the condition windowSum >= target was never met anywhere in the array. We can easily detect this state at the end of the function:
     *
     * return minLength == Integer.MAX_VALUE ? 0 : minLength;
     */
}
