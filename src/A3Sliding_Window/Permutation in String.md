**LeetCode 567: Permutation in String**, the most effective approach in 2026 is a **fixed-size sliding window**. Two strings are permutations of each other if and only if they have the exact same character frequencies.

The Idea

Since we are looking for a permutation of `s1` inside `s2`, any such substring must be **exactly the same length** as `s1`. We maintain a window of size `s1.length()` and slide it across `s2`, updating the character counts as we go.

The Approach

1.  **Frequency Arrays:** Use two `int[26]` arrays to store character counts for `s1` and the current window in `s2`.
2.  **Initial Window:** Fill the frequency array for `s1` and the first window of `s2`.
3.  **Slide:** In each step, add the character at the `right` pointer and remove the character that just fell out of the window at the `left` pointer.
4.  **Compare:** Check if the two frequency arrays match at any point. If they do, a permutation exists.
```java
public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 > n2) return false;

        int[] s1Counts = new int[26];
        int[] s2Counts = new int[26];

        // Fill initial frequency for s1 and the first window of s2
        for (int i = 0; i < n1; i++) {
            s1Counts[s1.charAt(i) - 'a']++;
            s2Counts[s2.charAt(i) - 'a']++;
        }

        // Slide the window across s2
        for (int i = 0; i < n2 - n1; i++) {
            if (matches(s1Counts, s2Counts)) return true;
            
            // Move window: add next char, remove leftmost char
            s2Counts[s2.charAt(i + n1) - 'a']++;
            s2Counts[s2.charAt(i) - 'a']--;
        }

        // Check the very last window
        return matches(s1Counts, s2Counts);
    }

    private boolean matches(int[] s1Counts, int[] s2Counts) {
        for (int i = 0; i < 26; i++) {
            if (s1Counts[i] != s2Counts[i]) return false;
        }
        return true;
    }
}

```
Complexity Analysis

-   **Time Complexity:**** 𝑂(𝑁2), where
𝑁2 is the length of `s2`. We iterate through `s2` once. The frequency comparison takes
𝑂(26), which is constant time.
-   **Space Complexity:**** 𝑂(1)
    
    **, as we use fixed-size arrays of length 26 regardless of input size.

