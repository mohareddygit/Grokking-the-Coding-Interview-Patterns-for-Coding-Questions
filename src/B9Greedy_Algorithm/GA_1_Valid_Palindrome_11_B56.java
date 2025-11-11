package B9Greedy_Algorithm;

// Problem Statement: Valid Palindrome II (easy)
// LeetCode Question: 680. Valid Palindrome II

public class GA_1_Valid_Palindrome_11_B56 {
    public boolean isPalindromePossible(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    // Helper method to check if a substring is a palindrome
    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
