package A7Stack;

// Problem Statement: Simplify Path
// LeetCode Question: 71. Simplify Path

/**
 * The LeetCode problem 71 "Simplify Path" asks you to convert an absolute Unix-style file path into its simplified canonical form.
 *
 * The solution uses a Stack or a Deque (which is preferred in modern Java) to handle directory navigation and the special commands (., ..).
 * Algorithm: Using a Deque
 * Split the Path: Split the input string by the directory separator (/) into components (e.g., ["home", "", "foo"]).
 * Process Components: Iterate through the components and use a deque to manage the canonical path:
 * Ignore Empty/Current: Ignore components that are empty strings or a single dot (.).
 * Go Up: If the component is two dots (..), pop the last directory from the deque if it's not empty.
 * Go Down: For any other valid directory name, push it onto the deque.
 * Reconstruct Path: Join the directories remaining in the deque with / separators, and prepend a final / to form the canonical absolute path.
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Problem_6_SimplifyPath {
    public String simplifyPath(String path) {
        // Use a Deque (double-ended queue) as a stack.
        Deque<String> stack = new LinkedList<>();

        // Split the path by the '/' delimiter. The split function handles multiple adjacent slashes.
        String[] components = path.split("/");

        for (String component : components) {
            // Case 1: Ignore empty strings and the current directory symbol "."
            if (component.isEmpty() || component.equals(".")) {
                continue;
            }
            // Case 2: Go up one directory
            else if (component.equals("..")) {
                // If the stack is not empty, pop the previous directory off
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
            // Case 3: A valid directory name
            else {
                stack.push(component);
            }
        }

        // 3. Reconstruct the canonical path
        if (stack.isEmpty()) {
            return "/";
        }

        // Use a StringBuilder for efficient string concatenation
        StringBuilder simplifiedPath = new StringBuilder();

        // Iterate through the stack from bottom to top (using pollLast for correct order)
        while (!stack.isEmpty()) {
            simplifiedPath.append("/");
            simplifiedPath.append(stack.pollLast());
        }

        return simplifiedPath.toString();
    }
}

