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



 Key Notes for the Java Implementation
String Boundaries: Unlike some languages, Java's String.charAt() will throw a StringIndexOutOfBoundsException if you aren't careful. The while loop condition left >= 0 && right < s.length() ensures you stay within bounds.
Counting vs. Finding: This specific problem (LeetCode 647) asks for the count of substrings. If you were solving "Longest Palindromic Substring" (LeetCode 5), you would instead track the max length and starting index.
Helper Method: Using a private helper method like expandFromCenter keeps the code clean and prevents you from repeating the expansion logic for both odd and even cases
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
