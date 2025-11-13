package A3Sliding_Window;

// Problem Statement: Longest Substring with K Distinct Characters (medium)
// LeetCode Question: 340. Longest Substring With At Most K Distinct Characters

import java.util.HashMap;
import java.util.Map;

/**
 * This problem is solved using a dynamic sliding window approach and a HashMap to track the frequency of characters within the window.
 * Algorithm: Dynamic Sliding Window with Frequency Map
 * Initialize: Use two pointers (left, right), a HashMap to store character counts, and a variable to track the maximum length found (maxLength).
 * Expand Window: Move the right pointer to expand the window, adding each new character to the map and updating its frequency.
 * Contract Window (Maintain Invariant): If the number of distinct characters in the map (map.size()) exceeds
 * kk
 * 𝑘, the window is invalid. Contract the window from the left side until it becomes valid again:
 * Decrement the count of the character at the left pointer.
 * If its count drops to zero, remove the character from the map entirely.
 * Move the left pointer forward.
 * Update Maximum Length: After each operation (and ensuring the window is valid), calculate the current window length (right - left + 1) and update maxLength.
 */
public class SW_3_LongestSubstringWithKDistinctCharacters_B50ish {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }

        int left = 0;
        int maxLength = 0;
        // Map to store character frequencies in the current window
        Map<Character, Integer> counts = new HashMap<>();

        // Iterate with the right pointer to expand the window
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            counts.put(rightChar, counts.getOrDefault(rightChar, 0) + 1);

            // Contract the window if the number of distinct characters exceeds k
            while (counts.size() > k) {
                char leftChar = s.charAt(left);
                counts.put(leftChar, counts.get(leftChar) - 1);

                // If the count drops to zero, remove the character from the map
                if (counts.get(leftChar) == 0) {
                    counts.remove(leftChar);
                }
                // Move the left pointer forward
                /**
                 * That is an important distinction to make, but in the case of the "Longest Substring with At Most K Distinct Characters" problem, the
                 * left++ must happen inside the while (counts.size() > k) loop.
                 * Why left++ belongs Inside the while loop:
                 * The purpose of that while loop is to restore the validity of the sliding window after it has been expanded too far by the right pointer.
                 * The window only becomes valid again after enough characters have been removed from the left side to bring the count of distinct characters back down to k or less.
                 */
                left++;
            }

            // Update the maximum length after ensuring the window is valid
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

}
