package A3Sliding_Window;

import java.util.HashSet;

public class SW_LongestUniqueSubstring_B50 {
    public int lengthOfLongestSubstring(String s) {
        int left = 0; // Start of the window
        int maxLength = 0;
        HashSet<Character> seen = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            // Shrink window from the left until no duplicate
            while (seen.contains(current)) {
                seen.remove(s.charAt(left));
                left++;
            }

            seen.add(current);
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
