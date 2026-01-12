## 169. Majority Element 👑

**Difficulty**: `Easy` - **Tags**: `Array`, `Hash Map`, `Sorting`, `Divide and Conquer`

### Description 📋

Given an array `nums` of size `n`, return the **majority element**.

The majority element is the element that appears **more than** ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

### Examples 🌟

**Example 1:**

**Input:**
```python
nums = [3,2,3]
```

**Output:**
```python
3
```

**Example 2:**

**Input:**
```python
nums = [2,2,1,1,1,2,2]
```

**Output:**
```python
2
```

### Constraints ⚙️

- `n == nums.length`
- `1 <= n <= 5 * 10^4`
- `-10^9 <= nums[i] <= 10^9`

The majority element always exists in the array.

### Solution 💡

**LeetCode 169: Majority Element** in 2026, the primary goal is to find the element that appears more than ⌊𝑛/2⌋

times in linear time and with minimal extra space.

1. The Core Idea: "Survival of the Fittest"

Because the majority element occurs more than half the time, it will always "outvote" all other elements combined. Even if every non-majority element is used to "cancel out" one instance of the majority element, at least one instance of the majority element will remain at the end.

2. Optimal Approach: Boyer-Moore Voting Algorithm

This is the gold standard for this problem, achieving**𝑂(𝑛)time** and            **𝑂(1)space**

-   **Initialization:** Start with a `candidate` (can be any value) and a `count` set to 0.
-   **One-Pass Traversal:** Iterate through the array once:
    -   If `count` is 0, set the current element as the new `candidate` and set `count` to 1.
    -   If the current element matches the `candidate`, increment `count`.
    -   If the current element is different, decrement `count`.
-   **Result:** The final `candidate` is guaranteed to be the majority element because its frequency is strictly greater than all others.

#### Java

```java
public class Solution {
    public int majorityElement(int[] nums) {
        int majority = nums[0];
        int count = 1;

        // Because the number of majority elements is greater than n/2, the count of that element will always be greater than 0
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                majority = nums[i];
                count = 1;
            } else if (nums[i] == majority) {
                count++;
            } else {
                count--;
            }
        }

        return majority;
    }
}
```

You can find the full `Solution.java` file [here](Solution.java).
