To solve

**LeetCode 424: Longest Repeating Character Replacement** in 2026, the optimal approach is a **sliding window** rather than backtracking. While backtracking would explore every possibility 𝑂(2𝑁), the sliding window solves it in linear time.

The Idea

The goal is to find the largest window where we can change at most 𝑘

characters to match the most frequent character in that window.

-   **Window Validity Rule:** A window is valid if `(Window Length - Frequency of Most Common Character) <= k`.
-   This difference represents the number of "other" characters we must replace to make the entire window uniform.

The Sliding Window Approach

1.  **Expand:** Move a `right` pointer to grow the window, updating the frequency of the new character in a count array.
2.  **Track Max Frequency:** Keep track of the `maxFreq` (the highest frequency of any single character seen in the current window).
3.  **Shrink (if needed):** If the window becomes invalid—meaning the characters to replace exceed  𝑘
    —increment the `left` pointer to slide the window forward.
4.  **Result:** The answer is the maximum window size recorded during the process.





```java
public class Solution {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26]; // Frequency map for uppercase letters
        int left = 0;
        int maxFreq = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            // 1. Expand window: update frequency and global maxFreq
            //confusing .. int currentFreq = ++count[s.charAt(right) - 'A'];
           
					// Increment the count in the array first
					count[s.charAt(right) - 'A']++; 
					// Then capture the updated value to use
					int currentFreq = count[s.charAt(right) - 'A'];
					 maxFreq = Math.max(maxFreq, currentFreq);
					 
            // 2. Check validity: (Window Size - maxFreq) > k
            // If invalid, slide the window by incrementing 'left'
            while ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
                // Note: We don't need to re-calculate maxFreq here. 
                // A smaller maxFreq won't give us a better maxLength.
            }

            // 3. Update result
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}

```

The `while` loop is the "safety valve" of the sliding window. It ensures that the window never violates the core constraint of the problem: **you cannot have more than 𝑘

characters to replace**.

1. The Condition: `(right - left + 1) - maxFreq > k`

This condition checks if the current window is "invalid":

-   **`(right - left + 1)`**: This is the current window size (total characters).
-   **`maxFreq`**: This is the frequency of the most common character currently in the window.
-   **`The Subtraction`**: When you subtract the most frequent character's count from the total window size, the result is the number of **"other"** characters in that window.
-   **`> k`**: If these "other" characters exceed  𝑘
    
    , you don't have enough replacements to make the entire window uniform. The window is now invalid.

2. Why use a `while` loop?

The `while` loop continuously shrinks the window from the left (`left++`) and updates the character counts until the condition is no longer violated.

-   In this specific problem, an `if` statement actually works because we only add **one** character at a time (via `right++`), so the window can only ever be invalid by exactly **one** character.
```java

// IF condition: if current window is invalid, 
        // just shift the whole window forward by 1.
        if ((right - left + 1) - maxFreq > k) {
            count[s.charAt(left) - 'A']--;
            left++;
        }
```

-   However, using `while` is the **standard sliding window template**. It makes the code more robust—if you ever modified the logic to add multiple characters at once, the `while` loop would still correctly shrink the window until it became valid again.

3. Why `maxFreq` isn't recalculated inside the loop

This is the most counter-intuitive part of the optimization. When you remove a character from the left (`count[s.charAt(left)]--`), the true `maxFreq` of the window might actually decrease.

However, we **do not** need to update it because:

-   **We only care about beating our "record":** We are looking for the _longest_ possible substring. A smaller `maxFreq` will only lead to a smaller window size than what we've already found.
-   **Window Logic:** Our `maxLength` only updates when `maxFreq` increases. If `maxFreq` stays "stale" (larger than the actual current frequency), it simply forces the window to stay the same size or shrink, preventing it from incorrectly reporting a new record.
-   **Efficiency:** Recalculating the actual `maxFreq` every time would require scanning the entire 26-character count array, which would turn our
     𝑂(𝑁) into 𝑂(26⋅𝑁)
    


Summary of the "Shrinking" Step

1.  **Check:** Is the number of characters to replace greater than 𝑘?
2.  **Act:** If yes, decrement the count of the character at the `left` pointer and move `left` forward.
3.  **Result:** The window size decreases, making it more likely to satisfy the `length - maxFreq <= k` rule.
