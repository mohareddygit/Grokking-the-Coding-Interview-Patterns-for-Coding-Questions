package A3Sliding_Window;

/**
 * Leetcode 567: Permutation in String, using the sliding window + frequency array technique.
 */
public class SW_PermutationInString_BX {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] count = new int[26]; // Frequency difference

        // Initialize frequency for s1 and first window of s2
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }

        if (allZero(count)) return true;

        // Slide the window over s2
        for (int i = s1.length(); i < s2.length(); i++) {
            count[s2.charAt(i) - 'a']--; // Add new char
            count[s2.charAt(i - s1.length()) - 'a']++; // Remove old char

            if (allZero(count)) return true;
        }

        return false;
    }

    private boolean allZero(int[] count) {
        for (int c : count) {
            if (c != 0) return false;
        }
        return true;
    }
}
