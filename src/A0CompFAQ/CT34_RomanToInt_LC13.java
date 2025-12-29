package A0CompFAQ;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 13: Roman to Integer, you must convert a Roman numeral string (e.g., "MCMXCIV") into its integer equivalent (1994). Roman numerals are typically additive, but they use a subtractive rule when a smaller numeral precedes a larger one.
 * Optimal Solution: Single-Pass Iteration
 * The most efficient approach O(n) time
 * 𝑂(1) space) is to iterate through the string and compare each numeral to the one that follows it.
 * Rule: If the current value is less than the next value, it is a subtractive case (like IV for 4 or CM for 900). Subtract the current value from the total.
 * Rule: Otherwise, simply add the current value to the total.
 */

/**
 * Common Pitfalls
 * Index Out of Bounds: Forgetting to check i + 1 < n before looking at the next character
 * will crash the program at the last digit.
 * Handling the Final Character: The last character in the string has no "next" character to
 * compare against. In the logic above,
 * it will always fall into the else block and be added to the total correctly.
 *
 */

class CT34_RomanToInt_LC13 {
    public int romanToInt(String s) {
        // Map symbols to their integer values
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int total = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int currentVal = romanMap.get(s.charAt(i));
            
            // Check if there is a 'next' character and apply subtractive logic
            if (i + 1 < n && currentVal < romanMap.get(s.charAt(i + 1))) {
                total -= currentVal; // Subtractive case (e.g., IV)
            } else {
                total += currentVal; // Standard additive case
            }
        }
        return total;
    }
}
