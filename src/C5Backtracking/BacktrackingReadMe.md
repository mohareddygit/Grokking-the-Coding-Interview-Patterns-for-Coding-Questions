
In Java, a backtracking template typically uses a helper method that maintains the state through recursion. This structure is commonly used to find all possible solutions for problems like permutations or subsets.

Universal Java Backtracking Template



```java
public List<List<Integer>> solveProblem(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();
    // Optional: Arrays.sort(nums); // Helps with duplicates or ordered results
    backtrack(results, new ArrayList<>(), nums, 0);
    return results;
}

private void backtrack(List<List<Integer>> results, List<Integer> currentPath, int[] nums, int start) {
    // 1. BASE CASE: If a valid solution is found, add a COPY to results
    if (isSolution(currentPath)) {
        results.add(new ArrayList<>(currentPath)); 
        return;
    }

    // 2. ITERATE: Loop through possible choices
    for (int i = start; i < nums.length; i++) {
        // 3. PRUNING: Skip invalid options (optional)
        if (isInvalid(nums[i], currentPath)) continue;

        // 4. CHOOSE: Make the decision
        currentPath.add(nums[i]);

        // 5. EXPLORE: Recurse to find the next part of the solution
        // Use 'i + 1' for combinations, '0' or 'i' for others depending on rules
        backtrack(results, currentPath, nums, i + 1);

        // 6. UNCHOOSE: Backtrack by removing the last element
        currentPath.remove(currentPath.size() - 1);
    }
}

```

Use code with caution.

Key Components Explained

-   **Deep Copying Results:**  You must use  `new ArrayList<>(currentPath)`  because  `currentPath`  is a reference that changes.
-   **The for-loop:**  Often uses a  `start`  index to avoid using the same element twice (common in subset/combination problems).
-   **The Unchoose Step:**  This is critical. It resets the state so the previous recursive level can try a different branch of the decision tree.

Common Variations

-   **Permutations:**  Usually omit the  `start`  index and use a  `boolean[] visited`  array to track which elements are already in the  `currentPath`.
-   **Subsets:**  Every step in the recursion is a valid solution, so  `results.add(new ArrayList<>(currentPath))`  is placed at the very start of the  `backtrack`  method, before any conditions.



[Subsets (LeetCode 78)](https://leetcode.com/problems/subsets/)

-   **Goal:** Find all possible subsets (the power set).
-   **Key Variation:** Every state reached is a valid solution, so we add the current path to results at the very beginning of each call.



```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();
    backtrackSubsets(results, new ArrayList<>(), nums, 0);
    return results;
}

private void backtrackSubsets(List<List<Integer>> results, List<Integer> current, int[] nums, int start) {
    // Every partial candidate is a valid subset
    results.add(new ArrayList<>(current));

    for (int i = start; i < nums.length; i++) {
        current.add(nums[i]);                     // CHOOSE
        backtrackSubsets(results, current, nums, i + 1); // EXPLORE
        current.remove(current.size() - 1);       // UNCHOOSE
    }
}

```

Use code with caution.

2. [Permutations (LeetCode 46)](https://leetcode.com/problems/permutations/)

-   **Goal:** Find all possible arrangements of unique integers.
-   **Key Variation:** We do not use a `start` index because order matters (we want to look at all elements every time). Instead, we use a `used` boolean array or a contains check.



```java
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();
    backtrackPermute(results, new ArrayList<>(), nums, new boolean[nums.length]);
    return results;
}

private void backtrackPermute(List<List<Integer>> results, List<Integer> current, int[] nums, boolean[] used) {
    // BASE CASE: Permutation is complete when it reaches the input length
    if (current.size() == nums.length) {
        results.add(new ArrayList<>(current));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) continue; // PRUNING: Skip already used elements

        used[i] = true;
        current.add(nums[i]);              // CHOOSE
        backtrackPermute(results, current, nums, used); // EXPLORE
        current.remove(current.size() - 1); // UNCHOOSE
        used[i] = false;
    }
}

```

Use code with caution.

3. [Combinations (LeetCode 77)](https://leetcode.com/problems/combinations/)

-   **Goal:** Return all possible selections of 𝑘 numbers from the range [1,𝑛]
    
    .
-   **Key Variation:** We only add to results when the path size equals 𝑘



```java
public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> results = new ArrayList<>();
    backtrackCombine(results, new ArrayList<>(), 1, n, k);
    return results;
}

private void backtrackCombine(List<List<Integer>> results, List<Integer> current, int start, int n, int k) {
    // BASE CASE: Combination is complete when it contains 'k' elements
    if (current.size() == k) {
        results.add(new ArrayList<>(current));
        return;
    }

    for (int i = start; i <= n; i++) {
        current.add(i);                            // CHOOSE
        backtrackCombine(results, current, i + 1, n, k); // EXPLORE
        current.remove(current.size() - 1);        // UNCHOOSE
    }
}

```


To solve the

**N-Queens** problem using the backtracking template, we place queens row by row. At each row, we iterate through all columns and check if placing a queen is safe.

For optimal performance in 2026, we use **boolean arrays** to track occupied columns and diagonals, which reduces the safety check from 𝑂(𝑁)
to 𝑂(1)


N-Queens Java Solution



```java
import java.util.*;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(board[i], '.');

        // Tracking arrays for O(1) safety checks
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; // (row - col) constant
        boolean[] diag2 = new boolean[2 * n]; // (row + col) constant

        backtrack(results, board, 0, n, cols, diag1, diag2);
        return results;
    }

    private void backtrack(List<List<String>> results, char[][] board, int row, int n, 
                           boolean[] cols, boolean[] diag1, boolean[] diag2) {
        // 1. BASE CASE: All queens placed
        if (row == n) {
            results.add(construct(board));
            return;
        }

        // 2. ITERATE: Try every column in the current row
        for (int col = 0; col < n; col++) {
            // 3. PRUNING: Check if column or diagonals are under attack
            int d1 = row - col + n; // Offset to avoid negative index
            int d2 = row + col;
            if (cols[col] || diag1[d1] || diag2[d2]) continue;

            // 4. CHOOSE: Place queen and mark paths as used
            board[row][col] = 'Q';
            cols[col] = diag1[d1] = diag2[d2] = true;

            // 5. EXPLORE: Move to the next row
            backtrack(results, board, row + 1, n, cols, diag1, diag2);

            // 6. UNCHOOSE: Backtrack (Reset state)
            board[row][col] = '.';
            cols[col] = diag1[d1] = diag2[d2] = false;
        }
    }

    private List<String> construct(char[][] board) {
        List<String> path = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            path.add(new String(board[i]));
        }
        return path;
    }
}

```

Use code with caution.

Key Logic Improvements

-   **Diagonal Math:**
    -   **Anti-Diagonal (`diag2`):** Elements on the same anti-diagonal (top-right to bottom-left) always have the same `row + col` sum.
    -   **Main Diagonal (`diag1`):** Elements on the same main diagonal (top-left to bottom-right) always have the same `row - col` difference. We add `n` to this value to ensure the array index is never negative.
-   **Efficiency:** By using these boolean arrays, we avoid scanning the entire board for every placement, significantly speeding up the algorithm for larger values of 𝑁
   
-   **State Management:** The `board` and boolean arrays are modified before recursion and reverted immediately after, adhering strictly to the backtracking template.

==============================================================================================================================================================









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



