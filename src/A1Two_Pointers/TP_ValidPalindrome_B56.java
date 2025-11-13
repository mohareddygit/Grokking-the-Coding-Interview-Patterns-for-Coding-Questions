package A1Two_Pointers;

public class TP_ValidPalindrome_B56 {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            // Skip non-alphanumeric characters from the left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            // Skip non-alphanumeric characters from the right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare characters ignoring case
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        TP_ValidPalindrome_B56 checker = new TP_ValidPalindrome_B56();
        String input = "A man, a plan, a canal: Panama";
        System.out.println("Is valid palindrome? " + checker.isPalindrome(input)); // Output: true
    }
}
