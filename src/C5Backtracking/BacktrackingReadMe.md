LeetCode 78: Subsets in Java, the standard approach is to use a recursive backtracking helper function that builds paths through a decision tree. 
Java Implementation This solution uses the ArrayList class to manage the results and the current path. 

```java

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Start backtracking with an empty current subset and index 0
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentPath, int[] nums, int start) {
        // Add a copy of the current path to the result list
        // New ArrayList is necessary because currentPath is modified during recursion
        result.add(new ArrayList<>(currentPath));

        for (int i = start; i < nums.length; i++) {
            // 1. Choose: Add the current element to the path
            currentPath.add(nums[i]);
            
            // 2. Explore: Recurse to move to the next possible elements
            backtrack(result, currentPath, nums, i + 1);
            
            // 3. Undo (Backtrack): Remove the last element added
            currentPath.remove(currentPath.size() - 1);
        }
    }
}
```


**Critical Technical Details**

-   **Copying the Result:** You must use `new ArrayList<>(currentPath)` when adding to the result list. If you add the original `currentPath` reference, all entries in the final list will be empty because the final `currentPath` is emptied by the last backtrack step.
-   **Time Complexity:****
    O(n.2^n) , where (n) is the length of the array. 
There are (2^n) total subsets (the power set), and each takes O(n) time to copy into the results.
 
-   **Space Complexity:**  (O(n)). This is the maximum space used by the recursive stack and the currentPath list. This is the maximum space used by the recursive stack and the `currentPath` list.

**Execution Walkthrough**

For input `nums = [1, 2]`:

1.  **Level 0:** Add `[]` to result.
2.  **Level 1 (i=0):** Path becomes `[1]`, add `[1]` to result.
3.  **Level 2 (i=1):** Path becomes `[1, 2]`, add `[1, 2]` to result.
4.  **Backtrack:** Remove `2`, then remove `1`.
5.  **Level 1 (i=1):** Path becomes `[2]`, add `[2]` to result.
6.  **Final Result:** `[[], [1], [1, 2], [2]]`




**Permutations** 

Easiest approach is to use a standard **recursive backtracking template**.

Unlike the Subsets problem where we only look forward, for Permutations we need to consider **every** number for **every** position. To do this, we use a loop starting at `0` and a `boolean[]` array to remember which numbers we have already used in our current path.

**Easy-to-Understand Java Solution**



```java
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // used[i] will be true if nums[i] is already in the current permutation
        boolean[] used = new boolean[nums.length];
        backtrack(result, new ArrayList<>(), nums, used);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentPath, int[] nums, boolean[] used) {
        // BASE CASE: If the current path is the same length as nums, we found a permutation
        if (currentPath.size() == nums.length) {
            result.add(new ArrayList<>(currentPath)); // Snapshot of currentPath
            return;
        }

        // EXPLORE: Try every number in the array for the current position
        for (int i = 0; i < nums.length; i++) {
            // If the number is already used, skip it
            if (used[i]) continue;

            // 1. CHOOSE: Mark as used and add to path
            used[i] = true;
            currentPath.add(nums[i]);

            // 2. EXPLORE: Recurse to fill the next position
            backtrack(result, currentPath, nums, used);

            // 3. BACKTRACK (UNDO): Unmark and remove from path
            currentPath.remove(currentPath.size() - 1);
            used[i] = false;
        }
    }
}

```

Use code with caution.

**Why this is "Easy" to Understand**

1.  **The `used[]` Array:** Think of this as your "memory." Without it, the algorithm would just keep picking the first number (`1, 1, 1...`). The array tells the computer: "You already picked 1, look for something else."
2.  **The Loop starts at `0`:** Since permutations depend on order, `[1, 2]` is different from `[2, 1]`. By starting the loop at `0` every time, we ensure that after picking `2`, we can still go back and pick `1` for the next spot.
3.  **The Snapshot:** Just like in the Subsets problem, `new ArrayList<>(currentPath)` is used to save a permanent copy of the list at the moment it becomes a full permutation.

**Visualizing the Trace for `[1, 2, 3]`**

-   **Pick 1:** Path is `[1]`.
    -   **Pick 2:** Path is `[1, 2]`.
        -   **Pick 3:** Path is `[1, 2, 3]` → **Save!**
        -   _Backtrack:_ Remove 3.
    -   _Backtrack:_ Remove 2.
    -   **Pick 3:** Path is `[1, 3]`.
        -   **Pick 2:** Path is `[1, 3, 2]` → **Save!**
-   **Backtrack** all the way to `[]` and start with **Pick 2**...

**Complexity in 2026 Terms**

-   **Time:**
   (O(n. n!)) — There are n! (factorial) permutations, and we spend (O(n)) time copying each one.
-   **Space:**
  (O(n) - We store the `used` array and the recursion stack, both of which are proportional to the number of elements.



**Combinations** problem (e.g., [LeetCode 77](https://leetcode.com/problems/combinations/)) 

requires finding all possible sets of 𝑘 numbers from the range  [1,n]

. Unlike permutations, **order does not matter**, meaning `[1, 2]` is the same as `[2, 1]`.

**Java Backtracking Solution**

To solve this efficiently, we use a `start` index to ensure we only pick numbers in increasing order, which naturally prevents duplicate sets.



```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), n, k, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentPath, int n, int k, int start) {
        // Base Case: If the current combination size reaches k
        if (currentPath.size() == k) {
            result.add(new ArrayList<>(currentPath));
            return;
        }

        // Standard backtracking loop starting from 'start'
        for (int i = start; i <= n; i++) {
            // 1. Choose: Add number to current path
            currentPath.add(i);
            
            // 2. Explore: Recurse to pick the next number (must be > current i)
            backtrack(result, currentPath, n, k, i + 1);
            
            // 3. Undo (Backtrack): Remove number to try different candidates
            currentPath.remove(currentPath.size() - 1);
        }
    }
}

```



