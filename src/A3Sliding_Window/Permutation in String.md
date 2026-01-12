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
        int n1 = s1.length(), n2 = s2.length();
        if (n1 > n2) return false;

        int[] s1Counts = new int[26];
        int[] s2Counts = new int[26];

        // Initialize: Build the first window of size n1
        for (int i = 0; i < n1; i++) {
            s1Counts[s1.charAt(i) - 'a']++;
            s2Counts[s2.charAt(i) - 'a']++;
        }

        // Slide: i represents the RIGHT edge of the window
        for (int i = n1; i < n2; i++) {
            if (isMatch(s1Counts, s2Counts)) return true;

            // Add the new character at the right edge
            s2Counts[s2.charAt(i) - 'a']++;
            // Remove the character that fell out of the left edge (i - n1)
            s2Counts[s2.charAt(i - n1) - 'a']--;
        }

        // Final check for the last window position
        return isMatch(s1Counts, s2Counts);
    }

    private boolean isMatch(int[] c1, int[] c2) {
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) return false;
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

    Frequency Map Solution (Flexible)
This approach is more readable and handles non-alphabetic characters.

```java
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 > n2) return false;

        Map<Character, Integer> s1Map = new HashMap<>();
        Map<Character, Integer> s2Map = new HashMap<>();

        // Initialize: Build the first window
        for (int i = 0; i < n1; i++) {
            s1Map.put(s1.charAt(i), s1Map.getOrDefault(s1.charAt(i), 0) + 1);
            s2Map.put(s2.charAt(i), s2Map.getOrDefault(s2.charAt(i), 0) + 1);
        }

        // Slide: i represents the RIGHT edge
        for (int i = n1; i < n2; i++) {
            if (s1Map.equals(s2Map)) return true;

            // Add new character (Right)
            char rightChar = s2.charAt(i);
            s2Map.put(rightChar, s2Map.getOrDefault(rightChar, 0) + 1);

            // Remove old character (Left: i - n1)
            char leftChar = s2.charAt(i - n1);
            if (s2Map.get(leftChar) == 1) {
                s2Map.remove(leftChar); // Crucial: Remove key to allow .equals() to work
            } else {
                s2Map.put(leftChar, s2Map.get(leftChar) - 1);
            }
        }

        return s1Map.equals(s2Map);
    }
}

```



-   **Map Equality:** The `Map.equals()` method in Java is 𝑂(𝐾), where
    𝐾 is the number of unique characters. Since there are at most 26 unique lowercase English letters, this remains a constant time operation 𝑂(26)
    
-   **Key Removal:** It is critical to `remove()` a key when its count hits zero. If you only update the value to `0`, `s1Counts.equals(s2Counts)` may return `false` because one map has a key with value `0` that the other map does not have at all.
-   **Performance:** While `HashMap` is more flexible, using an `int[26]` array is still significantly faster in Java due to reduced object overhead and direct indexing. Use the `HashMap` approach when readability or diverse character sets (like Unicode) are priorities
