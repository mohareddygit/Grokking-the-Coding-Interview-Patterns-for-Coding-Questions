package A7Stack;

// Problem Statement: Balanced Parentheses
// LeetCode Question: 20. Valid Parentheses

/**
 * To validate parentheses in a Java string (LeetCode 20, "Valid Parentheses"), we use a
 * Stack data structure.
 * The stack helps us maintain the order of opening brackets encountered so far. When we hit a closing bracket, we check the stack to see if the corresponding opening bracket was the last one opened.
 * Algorithm
 * Initialize an empty Stack<Character>.
 * Iterate through each character of the input string.
 * If the character is an opening bracket ((, {, [), push it onto the stack.
 * If the character is a closing bracket (), }, ]):
 * First, check if the stack is already empty. If it is, we have a closing bracket without a matching opener, so the string is invalid (return false).
 * Pop the top element from the stack.
 * Check if the popped opener matches the current closer. If they don't form a valid pair (e.g., popping { but the closer is )), the string is invalid (return false).
 * After iterating through the whole string:
 * If the stack is empty, it means every opening bracket found a matching closing bracket, and the string is valid (return true).
 * If the stack is not empty, it means we have leftover opening brackets that were never closed, so the string is invalid (return false).
 */

import java.util.Stack;

public class ST_1_ValidParentheses_B55 {

    public boolean isValid(String s) {
        // Use a Stack to keep track of opening brackets
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                // If it's an opening bracket, push it onto the stack
                stack.push(c);
            } else {
                // If it's a closing bracket

                // Check if the stack is empty first (closing a non-existent opener)
                if (stack.isEmpty()) {
                    return false;
                }

                // Pop the top element (the most recent opener)
                char topChar = stack.pop();

                // Check if the current closing bracket matches the popped opening bracket
                if (c == ')' && topChar != '(') {
                    return false;
                } else if (c == '}' && topChar != '{') {
                    return false;
                } else if (c == ']' && topChar != '[') {
                    return false;
                }
            }
        }

        // After iterating through the whole string, the stack must be empty
        // for the input string to be valid.
        return stack.isEmpty();
    }
}




