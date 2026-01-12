package C5Backtracking;

import java.util.*;

class CT33_LetterCombinationsPhoneNumber_LC17 {
    // Standard phone keypad mapping
    private static final String[] KEYPAD = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        // Base case: if current string length matches digits length, we found a combination
        if (current.length() == digits.length()) {
        //if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get letters for the current digit
        String letters = KEYPAD[digits.charAt(index) - '0'];
        for (char letter : letters.toCharArray()) {
            current.append(letter);             // Choose
            backtrack(result, current, digits, index + 1); // Explore
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }
}
