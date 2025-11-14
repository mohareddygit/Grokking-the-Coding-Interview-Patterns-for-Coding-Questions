package C4Dynamic_Programming;

import java.util.*;

/**
 * Leetcode 139: Word Break, using dynamic programming to determine if a string can be segmented into dictionary words.
 *
 */
public class DP_WordBreak_B20 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict); // For O(1) lookup
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // Empty string is always segmentable

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // If s[0..j) is segmentable and s[j..i) is in the dictionary
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // No need to check further
                }
            }
        }

        return dp[n];
    }
}
