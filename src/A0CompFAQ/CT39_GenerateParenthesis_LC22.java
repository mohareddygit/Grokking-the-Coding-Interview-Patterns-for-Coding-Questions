package A0CompFAQ;

import java.util.ArrayList;
import java.util.List;

/**
 * The optimal solution for LeetCode 22: Generate Parentheses uses backtracking to construct strings character by character while maintaining validity. This approach prunes invalid paths early, ensuring that every generated string is well-formed.
 * Java Solution (Backtracking)
 * This implementation uses a StringBuilder to efficiently build strings and a recursive helper function to track the number of open and closed parentheses.
 *
 */

/**
 * Key Logic & Rules
 * Keep Track: You must track the current count of open (() and closed ()) parentheses.
 * Add Open Parentheses: You can add an open parenthesis as long as open < n.
 * Add Closed Parentheses: You can only add a closed parenthesis if the current number of closed parentheses is less than the current number of open ones (close < open).
 * Backtracking Step: After each recursive call, the last character added must be removed (deleteCharAt) to explore other possible combinations for that position.
 */
class CT39_GenerateParenthesis_LC22 {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        // Base case: string length is 2*n
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        // Rule 1: We can add '(' if we haven't reached the limit 'n'
        if (open < max) {
            current.append("(");
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }

        // Rule 2: We can add ')' if it won't exceed the number of open parentheses
        if (close < open) {
            current.append(")");
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }
}