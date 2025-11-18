package A1Two_Pointers;

/**
 * Leetcode 647: Palindromic Substrings, using expand-around-center to count all palindromic substrings.
 * <p>
 * We’re looping through each character i in the string, treating it as the center of a potential palindrome.
 * <p>
 * For regular palindrome, you start left at left and right at the extreme right
 * compare and then move as left++ and right--
 * <p>
 * For expand-around-center
 * 1. for odd length, same center
 * 2. for even length, center, center+1
 * 3. compare and then move left-- & right++
 */
public class TP_PAL_PalindromicSubstrings_B58 {
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
