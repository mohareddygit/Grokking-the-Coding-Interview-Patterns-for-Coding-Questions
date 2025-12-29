package A0CompFAQ;

import java.util.*;

/**
 * In LeetCode 71: Simplify Path, you must convert an absolute Unix-style file
 * path into its simplified canonical form.
 * This is best solved using a Stack data structure to track directory levels as you move through the path
 */
class CT21_SimplifyPath_LC71 {
    public String simplifyPath(String path) {
        // 1. Split path by slashes. Multiple slashes result in empty strings.
        String[] components = path.split("/");

        Stack<String> oldStack = new Stack();
        Deque<String> stack = new ArrayDeque<>();

        for (String dir : components) {
            // 2. Ignore empty strings (from //) and current directory (.)
            if (dir.isEmpty() || dir.equals(".")) {
                continue;
            }
            
            // 3. Handle moving up one directory
            if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    oldStack.pop();
                    stack.pollLast(); // Move up by removing the last directory
                }
            } 
            // 4. Handle valid directory names
            else {
                oldStack.push(dir);
                stack.offerLast(dir);
            }
        }

        // 5. Join the remaining directories with a leading slash
        //return "/" + String.join("/", oldStack);
        return "/" + String.join("/", stack);
    }
}