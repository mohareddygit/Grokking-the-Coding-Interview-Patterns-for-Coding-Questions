

ArrayList + Lower Bound BinarySearch - O(nlogn) - optimized
https://www.youtube.com/watch?v=TocJOW6vx_I

If using binarySearch(int[] a, int key) then
The "insertion point" is the index where the key would be
inserted while maintaining order (the index of the first element greater than the key).
The Math to get the actual index for insertion point: `i = -(i + 1)`

```java
int index = Arrays.binarySearch(array, target);

//insertion position
if (index < 0) {
    index = -(index + 1);
}
```
 or perform a lower bound binary search


In the Longest Increasing Subsequence** approach using ArrayList + Binary Search **𝑂(𝑛log𝑛)**

, the `tails` list/array is always sorted. This allows us to use binary search to quickly find where a new number belongs.



1. The Goal of the Search

We are looking for the smallest element in `tails` that is **greater than or equal to** our current number `num`.

- **If we find `num`:** We don't need to change the size of our subsequence; we just overwrite that same value (it doesn't change anything).
- **If we don't find `num`:** we want to find the "insertion point"—the index where `num` _would_ be if it were in the array.
This can be achieved using lowerBoundSearch ( Refer BinarySearchReadMe)



### DP array - O(n^2) using DP array
https://www.youtube.com/watch?v=Xq3hlMvhrkE
For **LeetCode 300 (Longest Increasing Subsequence)**, the Dynamic Programming approach is the most intuitive for explaining how subproblems build upon each other. It has a time complexity of **𝑂(𝑛2)**.


1. The Logic

-   **State:** Let `dp[i]` be the length of the Longest Increasing Subsequence that **ends** with the element at `nums[i]`.
-   **Initialization:** Every element is technically an increasing subsequence of length 1. So, we fill the `dp` array with `1`.
-   **Nested Loops:**
    -   The **outer loop** (`i`) moves through each number in the array.
    -   The **inner loop** (`j`) checks all numbers _before_ `i`.
-   **The Condition:** If `nums[i] > nums[j]`, it means the number at `i` can be appended to the sequence ending at `j`. We update `dp[i]` to be the maximum of its current value or `dp[j] + 1`.

2. Java Solution (𝑂(𝑛2))

```java
import java.util.Arrays;

class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dp = new int[n];
        
        // Base case: Every element is a subsequence of length 1
        Arrays.fill(dp, 1);
        
        int maxOverall = 1;
        
        // i is the current element we are solving for
        for (int i = 1; i < n; i++) {
            // j is a previous element we are trying to append nums[i] onto
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // Update dp[i] if appending to nums[j] creates a longer sequence
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Keep track of the global maximum
            maxOverall = Math.max(maxOverall, dp[i]);
        }
        
        return maxOverall;
    }
}

```

3. Example Trace

**Input:** `nums = [10, 9, 2, 5, 3]`

1.  **Start:** `dp = [1, 1, 1, 1, 1]`
2.  **i = 1 (9):** `9 > 10` is False. `dp[1]` stays 1.
3.  **i = 2 (2):** `2 > 10` False, `2 > 9` False. `dp[2]` stays 1.
4.  **i = 3 (5):**
    -   `5 > 10` False, `5 > 9` False.
    -   `5 > 2` is **True**. `dp[3] = max(1, dp[2] + 1) = 2`.
    -   `dp = [1, 1, 1, 2, 1]`
5.  **i = 4 (3):**
    -   `3 > 10` False, `3 > 9` False.
    -   `3 > 2` is **True**. `dp[4] = max(1, dp[2] + 1) = 2`.
    -   `3 > 5` False.
    -   `dp = [1, 1, 1, 2, 2]`
6.  **Result:** `max(dp)` is **2**.

4. Complexity Analysis

-   **Time Complexity:** **𝑂(𝑛2)**. We have two nested loops, each running up to 𝑛

    times.
-   **Space Complexity:** **𝑂(𝑛)**to store the `dp` array.

5. Why use this in 2026?

While the 𝑂(𝑛log𝑛)

Binary Search method is faster, the **DP method** is often the "Part 1" of a 2026 interview question.
Interviewers use it to see if you can define a state and a transition. 
If you jump straight to Binary Search, they may ask you to derive the DP solution first to prove you understand the problem's structure.