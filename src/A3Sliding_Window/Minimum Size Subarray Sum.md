
**LeetCode 209: Minimum Size Subarray Sum**, the most efficient approach in 2026 is a **dynamic sliding window**. Unlike the "Longest Repeating Character" problem, this window **must** be shrinkable with a `while` loop to find the _absolute minimum_ size.

The Idea

You maintain a window that expands until its sum is at least the `target`. Once it meets this condition, you aggressively shrink it from the left to see how small it can get while still remaining valid.

The Approach

1.  **Initialize:** Start with two pointers (`left = 0`, `right = 0`) and a running `sum = 0`.
2.  **Expand:** Move the `right` pointer to include the next element and add it to the `sum`.
3.  **Shrink (The crucial part):** While the `sum` is ≥ `target'` update your record for the minimum length found so far and then remove the element at the `left` pointer from the `sum`.
4.  **Completion:** If the minimum length never changed from its initial value, return `0`.

```java
public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE; // Initialize with a large value

        for (int right = 0; right < nums.length; right++) {
            // 1. Expand: add the current element to window sum
            sum += nums[right];

            // 2. Shrink: check if we can reduce the window size
            while (sum >= target) {
                // Update minimum length found so far
                minLength = Math.min(minLength, right - left + 1);
                
                // Remove the leftmost element and shift 'left' pointer
                sum -= nums[left++];
            }
        }

        // 3. Return 0 if no valid subarray was ever found
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}

```

Use code with caution.

Complexity Analysis

-   **Time Complexity:**  **𝑂(𝑁)**.  Although there is a nested `while` loop, each element is visited at most twice: once by the `right` pointer and once by the `left` pointer.
-   **Space Complexity:** **𝑂(1)** as we only use a few integer variables.

Why `while` is Mandatory here

In the "Longest" replacement problem, we used an `if` because we only cared about beating a record that only grows. In this "Minimum" problem, once you reach the target, the very next element could be so large that you can shrink the window multiple times from the left and still stay above the target. The `while` loop ensures you don't miss those smaller valid windows
