package C4Dynamic_Programming;

/**
 * Leetcode 91: Decode Ways, using dynamic programming to count valid decodings of a digit string.
 */
public class DP_DecodeWays_B24 {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;

        int n = s.length();
        int[] dp = new int[n + 1]; // dp[i] = ways to decode s[0..i-1]
        dp[0] = 1; // Empty string has one way

        // First character check
        dp[1] = s.charAt(0) != '0' ? 1 : 0;

        for (int i = 2; i <= n; i++) {
            // Single digit decode (e.g., '2' → 'B')
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            // Two digit decode (e.g., '12' → 'L')
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
