package A0CompFAQ;

import java.util.HashSet;


/**
 * Leetcode 3043. Find the Length of the Longest Common Prefix
 * use a HashSet to store all possible prefixes of numbers from the first array.
 * This allows for O(1) average-time lookups when checking prefixes against the second array.
 * Java Implementation (HashSet Approach)
 */
class CT7_LengthOfLongestCommonPrefix_LC3043 {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        HashSet<Integer> prefixes = new HashSet<>();
        
        // 1. Generate and store all possible prefixes for every number in arr1
        for (int val : arr1) {
            while (val > 0) {
                prefixes.add(val);
                val /= 10; // Remove the last digit to get the next shorter prefix
            }
        }
        
        int maxLength = 0;
        
        // 2. Check every number in arr2 against the stored prefixes
        for (int val : arr2) {
            while (val > 0) {
                // If a prefix exists, it's a common prefix
                if (prefixes.contains(val)) {
                    // Calculate length by converting to string or using math
                    int currentLength = String.valueOf(val).length();
                    maxLength = Math.max(maxLength, currentLength);
                    // Since we check from longest to shortest, break after first match
                    break; 
                }
                val /= 10;
            }
        }
        
        return maxLength;
    }
}
