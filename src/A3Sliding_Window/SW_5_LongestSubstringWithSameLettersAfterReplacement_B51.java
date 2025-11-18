package A3Sliding_Window;

// Problem Statement: Longest Substring with Same Letters after Replacement (hard)
// LeetCode Question: 424. Longest Repeating Character Replacement

import java.util.HashMap;
import java.util.Map;

public class SW_5_LongestSubstringWithSameLettersAfterReplacement_B51 {
    public int characterReplacement(String s, int k) {
        int left = 0;
        int maxf = 0; // Maximum frequency of any character in the current window
        int maxLength = 0;
        // Frequency array for 26 uppercase English letters
        int[] counts = new int[26];

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            counts[currentChar - 'A']++;
            // Update max frequency in the current window
            maxf = Math.max(maxf, counts[currentChar - 'A']);

            // Check if the current window is invalid (requires more than k replacements)
            // (right - left + 1) is the current window size
            while ((right - left + 1) - maxf > k) {
                char leftChar = s.charAt(left);
                counts[leftChar - 'A']--;
                left++; // Shrink the window from the left
                // Note: We don't necessarily need to recalculate maxf perfectly here to find the final max length,
                // an optimized approach maintains the largest valid window size found so far.
            }

            // Update the maximum length of a valid window found so far
            maxLength = Math.max(maxLength, (right - left + 1));
        }

        return maxLength;
    }
}
