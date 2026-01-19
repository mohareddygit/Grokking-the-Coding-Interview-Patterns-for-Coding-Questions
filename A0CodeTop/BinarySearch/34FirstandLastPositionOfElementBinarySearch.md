**LeetCode 34: Find First and Last Position of Element in Sorted Array**, you must find the boundaries of a target value within an array sorted in non-decreasing order. The problem specifically requires an 𝑂(log𝑛)

runtime complexity.

1. The Idea: Binary Search Twice

In a sorted array, multiple occurrences of a target will be adjacent. While a standard binary search finds _any_ instance of the target, you can modify its logic to specifically "hunt" for the leftmost and rightmost instances.

-   **To find the first position:** When you find the target, don't stop. Instead, treat the current index as a potential candidate and continue searching in the **left half** (`right = mid - 1`).
-   **To find the last position:** Similarly, when you find the target, keep searching in the **right half** (`left = mid + 1`) to see if there are later occurrences.

2. Solution Approach

1.  **Initialize:** Create a helper function `findBound(nums, target, isFirst)` that performs a binary search.
2.  **First Search:** Call the helper with `isFirst = true`. If it returns -1, the target isn't in the array; return `[-1, -1]` immediately.
3.  **Second Search:** Call the helper with `isFirst = false` to find the end position.
4.  **Edge Cases:** Handle empty arrays by returning `[-1, -1]` at the start.


```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);
        if (first == -1) return new int[]{-1, -1};
        
        int last = findBound(nums, target, false);
        return new int[]{first, last};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1;
        int bound = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                bound = mid;
                if (isFirst) {
                    right = mid - 1; // Look left for earlier occurrence
                } else {
                    left = mid + 1;  // Look right for later occurrence
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return bound;
    }
}

```

4. Complexity Analysis

-   **Time Complexity:** 𝑂(log𝑛)

    . You perform two separate binary searches, each taking 𝑂(log𝑛)

    time.
-   **Space Complexity:** 𝑂(1)

    . No extra data structures are used regardless of input size.

5. Why Not a Linear Scan?

A linear scan (searching from both ends) would find the result but takes** 𝑂(𝑛)

**time in the worst case (e.g., if every element is the target). For technical interviews in 2026, failing to use the sorted property to achieve 𝑂(log𝑛)

is considered a sub-optimal solution.