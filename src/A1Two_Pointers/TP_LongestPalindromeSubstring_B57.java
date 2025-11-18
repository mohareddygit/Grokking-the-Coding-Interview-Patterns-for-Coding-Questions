package A1Two_Pointers;

/**
 * Leetcode 5: Longest Palindromic Substring, using expand-around-center — a simple yet powerful approach.
 */
public class TP_LongestPalindromeSubstring_B57 {


    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int[] odd = expandFromCenter(s, i, i);     // odd-length
            int[] even = expandFromCenter(s, i, i + 1); // even-length

            //If the newly found palindrome is longer than the current best, update the best boundaries.
            // Compare lengths directly using indices
            if (odd[1] - odd[0] > end - start) {
                start = odd[0];
                end = odd[1];
            }
            if (even[1] - even[0] > end - start) {
                start = even[0];
                end = even[1];
            }
        }
        //length is end- start
        //substring end index is exclusive
        return s.substring(start, end + 1);
    }


    private int[] expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        /**
         *  At this point
         *  left has moved one step too far left (past the palindrome boundary).
         *  right has moved one step too far right (past the palindrome boundary).
         */
        // After overshooting, the palindrome is between left+1 and right-1
        return new int[]{left + 1, right - 1};
    }


    /**
     *
     * This approach has crazy calculation of lenght along with start  end indexes
     * @param s
     * @return
     */
    public String longestPalindromeCrazyCalculation(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Odd length palindrome
            int len1 = expandFromCenterCrazyCalculation(s, i, i);
            // Even length palindrome
            int len2 = expandFromCenterCrazyCalculation(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > end - start) {
                // Update start and end indices
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandFromCenterCrazyCalculation(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // Length of palindrome
        /**
         * The loop expands outward as long as characters match.
         *
         * When the loop stops, it means either:
         *
         * left went too far left, or
         *
         * right went too far right, or
         *
         * s.charAt(left) != s.charAt(right) (mismatch).
         *
         * At this point:
         *
         * left has moved one step too far left (past the palindrome boundary).
         *
         * right has moved one step too far right (past the palindrome boundary).
         *
         * 🧮 Why right - left - 1
         * The palindrome actually spans from left+1 to right-1.
         *
         * Length of that range = (right - 1) - (left + 1) + 1
         *
         * Simplify: right - left - 1
         *
         * So this formula correctly gives the number of characters inside the palindrome.
         */
    }


}
