package A3Sliding_Window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Leetcode 3: Longest Substring Without Repeating Characters
 */
public class SW_LongestUniqueSubstring_B50 {
    public int lengthOfLongestSubstring(String s) {
        int left = 0; // Start of the window
        int maxLength = 0;
        // rather than hashset use the hashmap approach
        //With a Set: You use a while loop to shrink the window until it's valid again. This may move left multiple times.
        //With a Map: You can jump left directly to the necessary position in one step
        // more efficient for very long strings

        /* HashSet<Character> seen = new HashSet<>();
        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            // Shrink window from the left until no duplicate
            //s = "abcba"
            while (seen.contains(current)) {
                seen.remove(s.charAt(left));
                left++;
            }

            seen.add(current);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        */

        // Map to store character -> latest index
        Map<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // If character seen before, move left pointer
            if (map.containsKey(c)) {
                // Jump left to one past the last occurrence of c
                //left = map.get(currentChar) + 1: Dangerous because it can pull the left pointer backward
                left = Math.max(left, map.get(c) + 1);
            }

            // Update latest index of current character
            map.put(c, right);

            // Update max length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        SW_LongestUniqueSubstring_B50 solver = new SW_LongestUniqueSubstring_B50();
        String input = "abcabcbb";
        System.out.println("Longest Unique Substring Length: " + solver.lengthOfLongestSubstring(input)); // Output: 3
    }
}
