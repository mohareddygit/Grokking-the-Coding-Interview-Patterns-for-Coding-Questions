## 27. Remove Element 🧹

**Difficulty**: `Easy` - **Tags**: `Array`, `Two Pointers`

### Description 📋

Given an integer array `nums` and an integer `val`, remove all occurrences of `val` in `nums` in-place. The order of the elements may be changed. Then return the number of elements in `nums` which are not equal to `val`.

Consider the number of elements in `nums` which are not equal to `val` to be `k`. To get accepted, you need to do the following things:

1. Change the array `nums` such that the first `k` elements of `nums` contain the elements which are not equal to `val`. The remaining elements of `nums` are not important, as well as the size of `nums`.
2. Return `k`.

### Custom Judge 🧪

The judge will test your solution with the following code:

```python
int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i < actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
```

If all assertions pass, then your solution will be accepted.

### Examples 🌟

**Example 1:**

**Input:**
```python
nums = [3,2,2,3]
val = 3
```

**Output:**
```python
2
```

**Explanation:**
Your function should return `k = 2`, with the first two elements of `nums` being 2. It does not matter what you leave beyond the returned `k` (hence they are underscores).

**Example 2:**

**Input:**
```python
nums = [0,1,2,2,3,0,4,2]
val = 2
```

**Output:**
```python
5
```

**Explanation:**
Your function should return `k = 5`, with the first five elements of `nums` containing 0, 0, 1, 3, and 4. Note that the five elements can be returned in any order. It does not matter what you leave beyond the returned `k` (hence they are underscores).

### Constraints ⚙️

- `0 <= nums.length <= 100`
- `0 <= nums[i] <= 50`
- `0 <= val <= 100`

### Solution 💡

**LeetCode 27: Remove Element**, the objective is to remove all occurrences of a specific value `val` in an integer array `nums` **in-place**. You must return the number of elements that are not equal to `val`.

1. The Idea: Two Pointers (Reader & Writer)

Since you cannot use extra space for another array, you use two pointers to reorder the existing array:

-   **Writer Pointer (`i`):** Tracks the position where the next "valid" (non-`val`) element should be placed.
-   **Reader Pointer (`j`):** Iterates through the entire array to inspect every element.

As the reader finds elements that are **not** equal to `val`, it "hands them over" to the writer to be saved at the front of the array.

2. Solution Approach

1.  Initialize a pointer `i = 0`. This will represent the count and the index for valid elements.
2.  Loop through the array with a second pointer `j` from `0` to `n-1`.
3.  **Check:** If `nums[j]` is not equal to `val`:
    -   Assign the value at `nums[j]` to `nums[i]`.
    -   Increment `i`.
4.  If `nums[j]` **is** equal to `val`, simply ignore it and move `j` to the next element.
5.  **Return:** The value of `i`, which now represents the number of elements not equal to `val`.

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0; // The "Writer" pointer
        
        for (int j = 0; j < nums.length; j++) { // The "Reader" pointer
            // If the current element is NOT the one we want to remove
            if (nums[j] != val) {
                // Move it to the 'i-th' position
                nums[i] = nums[j];
                // Increment i to prepare for the next valid element
                i++;
            }
        }
        
        // i is the count of elements that are not equal to val
        return i;
    }
}

```

4. Complexity Analysis

-   **Time Complexity:** **𝑂(𝑛)**. We traverse the array exactly once.
-   **Space Complexity:** **𝑂(1)**. We perform the operation in-place without using any additional data structures like a List or Set.

5. Advanced Tip: Optimization for Rare Elements

If the elements to remove are very rare (e.g., removing 1 element from an array of 1,000,000), the above code performs unnecessary assignments. In such cases, you can **swap** the element to be removed with the **last** element of the array and decrease the array size. This avoids shifting all valid elements forward.

For further reference on in-place array manipulation, you can check the LeetCode Explore Card for Arrays.