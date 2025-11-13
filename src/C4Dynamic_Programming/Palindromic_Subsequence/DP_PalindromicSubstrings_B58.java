package C4Dynamic_Programming.Palindromic_Subsequence;

/**
 * Leetcode 647: Palindromic Substrings, using expand-around-center to count all palindromic substrings.
 *
 */
public class DP_PalindromicSubstrings_B58 {
    public int countSubstrings(String s) {
        int count = 0;

        for (int center = 0; center < s.length(); center++) {
            // Count odd-length palindromes
            count += expandFromCenter(s, center, center);
            // Count even-length palindromes
            count += expandFromCenter(s, center, center + 1);
        }

        return count;
    }

    private int expandFromCenter(String s, int left, int right) {
        int count = 0;

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;    // Found a palindrome
            left--;
            right++;
        }

        return count;
    }
}
