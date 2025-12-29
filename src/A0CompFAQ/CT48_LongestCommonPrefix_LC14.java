package A0CompFAQ;

/**
 * 14. Longest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * Example 1:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 *
 *
 *
 */
class CT48_LongestCommonPrefix_LC14 {

    /*
    Use the horizontal scanning approach with the startsWith() method.
     */
    public String longestCommonPrefix(String[] strs) {
        // Base case: if array is null or empty, there is no common prefix
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        // Assume the first string is the common prefix to start with
        String prefix = strs[0];
        
        // Compare the current prefix against every other string in the array
        for (int i = 1; i < strs.length; i++) {
            
            // While the current string (strs[i]) does not start with the prefix
            while (!strs[i].startsWith(prefix)) {
                
                // Shorten the prefix by removing the last character
                prefix = prefix.substring(0, prefix.length() - 1);
                
                // If at any point the prefix becomes empty, there is no common prefix
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        
        return prefix;
    }

    /**
     * Vertical Scanning: In Vertical Scanning,
     * you compare characters at the same index across all strings simultaneously.
     * You check the first character of every string, then the second character of every string, and so on.
     * @param strs
     * @return
     */

    public String longestCommonPrefixVerticalScan(String[] strs) {
        if (strs == null || strs.length == 0) return "";


        // Use the first string as the template to compare others against
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);

            // Check this character against the same index in all other strings
            for (int j = 1; j < strs.length; j++) {
                // If:
                // 1. Current string is shorter than the index we are checking
                // 2. The character at this index doesn't match
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    // Return the substring of the first string up to this point
                    return strs[0].substring(0, i);
                }
            }
        }

        // If the loop completes, the entire first string is the common prefix
        return strs[0];
    }

}
