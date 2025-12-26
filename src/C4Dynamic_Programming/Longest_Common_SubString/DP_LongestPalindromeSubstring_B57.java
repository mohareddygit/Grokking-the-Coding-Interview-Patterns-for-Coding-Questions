package C4Dynamic_Programming.Longest_Common_SubString;

/**
 * Leetcode 5: Longest Palindromic Substring, using expand-around-center — a simple yet powerful approach.
 *
 * dont refer this code, refer the TP_PAL_LongestPalindromeSubstring_B57
 */
public class DP_LongestPalindromeSubstring_B57 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Odd length palindrome
            int len1 = expandFromCenter(s, i, i);
            // Even length palindrome
            int len2 = expandFromCenter(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > end - start) {
                // Update start and end indices
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // Length of palindrome
    }
}
