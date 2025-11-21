package C4Dynamic_Programming;

import java.util.*;

/**
 * Leetcode 139: Word Break, using dynamic programming to determine if a string can be segmented into dictionary words.
 *
 *
 * i = 4 (Prefix "leet")
 * j = 0: Substring s[0...3] is "leet".
 * dp[j] (dp) is true.
 * dictSet.contains("leet") is true.
 * Condition met: dp[4] becomes true, then break the inner loop.
 * dp is now [T, F, F, F, T, F, F, F, F].
 *
 *
 * i = 8 (Prefix "leetcode")
 * ... inner loop checks j=0 through j=7 ...
 * j = 4: Substring s[4...7] is "code".
 * dp[j] (dp) is true (from i=4 check).
 * dictSet.contains("code") is true.
 * Condition met: dp becomes true, then break the inner loop.
 * dp is now [T, F, F, F, T, F, F, F, T].
 */
public class DP_WordBreak_B20 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict); // For O(1) lookup
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // Empty string is always segmentable

        //The outer loop grows the substring length.
        // The inner loop checks every possible split inside that substring.
        for (int i = 1; i <= n; i++) {
            // Check all possible start points (j) for the last word of this prefix
            for (int j = 0; j < i; j++) {
                // Check if the prefix up to j is valid (dp[j] is true)
                // AND if the new substring s[j...i-1] is in the dictionary
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // Found a valid way to segment up to index i, move to next i
                }
            }
        }
        // The answer is whether the whole string (up to index n) can be segmented
        return dp[n];
    }
}
