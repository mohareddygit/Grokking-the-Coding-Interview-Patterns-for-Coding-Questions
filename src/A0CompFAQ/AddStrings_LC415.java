package A0CompFAQ;

/**
 * The Java solution for LeetCode 415: Add Strings involves simulating the process of manual column addition. Since you cannot use BigInteger or convert the strings directly to integers, you must process each digit from right to left using pointers
 */

/**
 * Key Logic Explained
 * Two-Pointer Approach: Start pointers i and j at the end of num1 and num2 respectively to process the "ones" place first.
 *
 * Safe Digit Extraction: By subtracting the character '0' from the charAt() result,
 * you get the integer value (e.g., '5' - '0' = 5).
 *
 * Handling Different Lengths: The condition (i >= 0) ensures that if one number is shorter than the other, the shorter number is padded with 0 for the calculation.
 * Carry Propagation: If a sum exceeds 9, the quotient sum / 10 (always 1 in this context) is carried over to the next loop iteration.
 * Complexity Analysis
 * Time Complexity: O(max(N, M)), where N and M are the lengths of the two strings. Each digit is visited exactly once.
 * Space Complexity: O(max(N, M)) to store the resulting string in the StringBuilder
 */
class AddStrings_LC415 {
    public String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        // Iterate from the end of both strings until all digits and carry are processed
        while (i >= 0 || j >= 0 || carry != 0) {
            // Convert character digit to integer; use 0 if pointer is out of bounds
            int x = (i >= 0) ? num1.charAt(i) - '0' : 0;
            int y = (j >= 0) ? num2.charAt(j) - '0' : 0;

            int sum = x + y + carry;
            result.append(sum % 10); // Store the last digit of the sum
            carry = sum / 10;        // Update carry for the next position

            i--;
            j--;
        }

        // The result is built backwards, so it must be reversed
        return result.reverse().toString();
    }
}
