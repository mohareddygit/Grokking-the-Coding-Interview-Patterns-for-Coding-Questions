# Topic 3 Sliding Window

The **Sliding Window** technique is a highly efficient approach in programming, particularly useful for solving problems involving arrays or strings. This technique optimizes operations on subsets of data to find results like maximum sum, length, or count of elements. Let's break it down step by step! 🏊‍♂️

---

## 📌 What is Sliding Window?

![Sliding Window](example-2.gif)

The **Sliding Window** technique involves maintaining a "window" that moves across a data structure (usually an array or string). This "window" represents a subset of elements, and it can be:

- **Fixed size**: The window size remains constant throughout.
- **Dynamic size**: The window size adjusts based on certain conditions.

### 💡 Key Idea

<br>
<img src="example-3.gif" alt="Sliding Window" width="300">

Instead of recalculating results for every possible subset, the Sliding Window technique avoids redundant computations by:

1. **Adding** the new element entering the window. ➕
2. **Removing** the old element leaving the window. ➖

This dramatically reduces computation time and improves efficiency. 🚀

---

## 🛠️ How to Apply Sliding Window

<br>
<img src="example-1.gif" alt="Sliding Window" width="600">

Follow these general steps to apply the Sliding Window technique:

1. **Initialize** a window starting at the beginning of the array or string.
2. Slide the window across the data:
   - Add new elements entering the window.
   - Remove old elements leaving the window.
3. Perform calculations on the current window to update the result.
4. **Return** the final result after processing all possible windows.

---

## 📝 Example 1: Maximum Sum of Subarray of Size `k` 💰

### Problem

Find the maximum sum of any contiguous subarray of size `k` in an array.

### Code

```dart
int maxSumOfSubArray(List<int> arr, int k) {
  int n = arr.length;
  if (n < k) return -1; // Not enough elements

  int maxSum = 0, currentSum = 0;

  // Create the first window
  for (int i = 0; i < k; i++) {
    currentSum += arr[i];
  }
  maxSum = currentSum;

  // Slide the window
  for (int i = k; i < n; i++) {
    currentSum += arr[i] - arr[i - k]; // Add new element, remove old element
    maxSum = max(maxSum, currentSum); // Update max sum
  }

  return maxSum;
}

void main() {
  List<int> arr = [2, 3, 5, 2, 9, 7, 1];
  int k = 3;
  print(maxSumOfSubArray(arr, k)); // Output: 18
}
```

### Explanation 📝

1. Input: `arr = [2, 3, 5, 2, 9, 7, 1]`, `k = 3`
2. Sliding Window Steps:
   - `[2, 3, 5]` → sum = 10
   - `[3, 5, 2]` → sum = 10
   - `[5, 2, 9]` → sum = 16
   - `[2, 9, 7]` → **sum = 18** ✅
   - `[9, 7, 1]` → sum = 17
3. Maximum sum: `18`

---

## 📝 Example 2: Longest Substring Without Repeating Characters 🔑

### Problem

Find the length of the longest substring without repeating characters in a string.

### Code

```dart
int lengthOfLongestSubstring(String s) {
  int maxLength = 0;
  int start = 0;
  Map<String, int> seen = {};

  for (int end = 0; end < s.length; end++) {
    String currentChar = s[end];

    if (seen.containsKey(currentChar)) {
      start = max(start, seen[currentChar]! + 1); // Update start if char repeats
    }

    seen[currentChar] = end; // Update the last seen position
    maxLength = max(maxLength, end - start + 1); // Update max length
  }

  return maxLength;
}

void main() {
  String s = "abcabcbb";
  print(lengthOfLongestSubstring(s)); // Output: 3
}
```

### Explanation 📝

1. Input: `s = "abcabcbb"`
2. Sliding Window Steps:
   - Start: `a` → Length = 1
   - Extend: `ab` → Length = 2
   - Extend: `abc` → Length = **3** ✅
   - Repeat: Move start to skip the first `a`.
   - Final: The longest substring is `abc`, length = `3`.

---

## 🔥 Applications of Sliding Window

1. **Maximum/Minimum Sum Subarray** 💵
2. **Longest Substring Problems** 🔤
3. **Counting Subarrays/Substrings** 🧮
4. Optimizing algorithms for **time and space complexity** ⚡

---

## 🚀 Why Use Sliding Window?

- **Efficiency**: Reduces time complexity from \(O(n^2)\) to \(O(n)\) in many problems.
- **Intuitive**: Mirrors how we naturally approach subsets visually.
- **Widely Applicable**: Used in dynamic programming, searching, and optimization problems.

---

## 🏁 Template

This template is the "Variable Size Sliding Window" standard. 
It is the most versatile pattern for problems like Longest Substring Without Repeating Characters or Minimum Size Subarray Sum.
Sliding Window Template 

```java
public int slidingWindowTemplate(int[] nums, int target) {
int left = 0;
int windowData = 0; // Can be a sum, a counter, or a frequency map
int result = 0;     // Stores max, min, or count

    // Outer Loop: Expand the window using the 'right' pointer
    for (int right = 0; right < nums.length; right++) {
        // 1. ADD the current element into window logic
        windowData += nums[right]; 

        // 2. INNER WHILE LOOP: Shrink the window if the condition is met
        // This 'condition' depends on if you're looking for a max, min, or specific sum
        while (/* windowData meets specific condition */) {
            
            // a) Update result if looking for the SMALLEST window (e.g., Min Subarray Sum)
            // result = Math.min(result, right - left + 1);

            // b) REMOVE the element at 'left' from window logic
            windowData -= nums[left];
            
            // c) Move the left pointer forward
            left++;
        }

        // 3. Update result if looking for the LONGEST window (e.g., Longest Substring)
        // result = Math.max(result, right - left + 1);
    }

    return result;
}
```

When to use which "Update Result" position:

1.  **If finding the SHORTEST window (e.g., LC 209):**
   -   Update  `result`  **inside**  the  `while`  loop.
   -   You want the smallest size that  _just barely_  satisfies the condition before you shrink it further.
2.  **If finding the LONGEST window (e.g., LC 3, LC 424):**
   -   Update  `result`  **outside**  the  `while`  loop (after shrinking).
   -   The  `while`  loop is used to "clean up" the window until it is valid; once valid, you measure its length.

Common Condition Examples

-   **Unique Characters:**  `while (map.get(charAtRight) > 1)`  (shrink until no duplicates).
-   **Sum exceeds K:**  `while (currentSum > k)`  (shrink until sum is within bounds).
-   **Character Budget:**  `while (charsToReplace > k)`  (shrink until you have enough replacements).


## Fixed Window
For a **fixed window** of size `K`, you do not need a `while` loop for shrinkage. Instead, you maintain a constant distance between the `left` and `right` pointers.

In Java, the most common way to implement this is to process the first window separately and then use a `for` loop to slide across the rest of the input.

Fixed Window Template (Java)

```java
public void fixedWindowTemplate(int[] nums, int k) {
    if (nums == null || nums.length < k) return;

    // 1. Initialize the data for the FIRST window [0...k-1]
    int windowData = 0; // Could be a sum, frequency array, etc.
    for (int i = 0; i < k; i++) {
        // windowData += nums[i];
    }
    
    // 2. Process the result for the very first window
    // updateResult(windowData);

    // 3. Slide the window from index k to the end
    for (int right = k; right < nums.length; right++) {
        // The element to REMOVE is at index (right - k)
        int leftElement = nums[right - k];
        // The element to ADD is at index right
        int rightElement = nums[right];

        // 4. Update windowData: subtract the left, add the right
        // windowData = windowData - leftElement + rightElement;

        // 5. Update result after each shift
        // updateResult(windowData);
    }
}

```

Key Differences from Variable Window

-   **No Inner Loop:** Since the window size is constant, you only remove **one** element for every **one** element you add.
-   **Index Math:** The element leaving the window is always at `right - k`.
-   **Efficiency:** This is strictly 𝑂(𝑁) time because every element is visited exactly once by the "add" logic and once by the "remove" logic.

