package A1Two_Pointers;

public class TP_PAL_ValidPalindrome_B56 {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // Move left pointer forward if not alphanumeric
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // Move right pointer backward if not alphanumeric
            else if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // Compare characters after converting to lowercase
            else {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TP_PAL_ValidPalindrome_B56 checker = new TP_PAL_ValidPalindrome_B56();
        String input = "A man, a plan, a canal: Panama";
        System.out.println("Is valid palindrome? " + checker.isPalindrome(input)); // Output: true
    }
}
