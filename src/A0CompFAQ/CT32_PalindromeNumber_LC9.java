package A0CompFAQ;

/**
 * 	Palindrome Number (LC 9) of a number
 * 	Variation of Valid Palindrome (LeetCode 125) for a string
 */
class CT32_PalindromeNumber_LC9 {
    public boolean isPalindrome(int x) {
        // Special cases:
        // 1. Negative numbers are not palindromes (e.g., -121 != 121-)
        // 2. Numbers ending in 0 (except 0 itself) are not palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;
        // Keep reversing digits until the reversed part is >= the remaining x
        // This signifies we have reached the middle of the number.
        while (x > reversedHalf) {
            reversedHalf = (reversedHalf * 10) + (x % 10);
            x /= 10;
        }

        // When the loop ends, x is either equal to reversedHalf (even length: 1221 -> 12 == 12)
        // or x is equal to reversedHalf / 10 (odd length: 121 -> 1 == 12/10)
        return x == reversedHalf || x == reversedHalf / 10;
    }
}