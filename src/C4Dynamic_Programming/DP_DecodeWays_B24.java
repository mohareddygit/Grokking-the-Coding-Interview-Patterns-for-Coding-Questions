package C4Dynamic_Programming;

/**
 * Leetcode 91: Decode Ways, using dynamic programming to count valid decodings of a digit string.
 */
public class DP_DecodeWays_B24 {

    /**
     * Complexity
     * Time: O(n) — single pass through the string.
     *
     * Space: O(n) for DP array (can be optimized to O(1)).
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;

        int n = s.length();
        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 1; // empty string has 1 way
        dp[1] = s.charAt(0) != '0' ? 1 : 0; // if first digit is '0', no valid decoding

        for (int i = 2; i <= n; i++) {
            // Single digit decode (must be non-zero)
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            // Two digit decode (must be between 10 and 26)
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }


    public int numDecodingsMemoryOptimized(String s) {
        if (s == null || s.length() == 0) return 0;

        int n = s.length();
        // prev = dp[i-1], prevPrev = dp[i-2]
        int prevPrev = 1; // dp[0] = 1 (empty string)
        int prev = s.charAt(0) != '0' ? 1 : 0; // dp[1]

        for (int i = 2; i <= n; i++) {
            int current = 0;

            // Single digit decode
            if (s.charAt(i - 1) != '0') {
                current += prev;
            }

            // Two digit decode
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                current += prevPrev;
            }

            // Shift window
            prevPrev = prev;
            prev = current;
        }

        return prev;
    }
}
