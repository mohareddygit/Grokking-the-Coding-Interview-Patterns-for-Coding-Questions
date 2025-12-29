package A0CompFAQ;

/**
 * The Java solution for LeetCode 3042: Count Prefix and Suffix Pairs I requires counting index pairs (i, j) where \(i<j\) and words[i] is both a prefix and a suffix of words[j]. Given the small constraints (up to 50 words, each with a maximum length of 10), a straightforward Brute Force (O(n² × m)) approach is efficient enough to pass. Java Implementation The most readable approach uses the built-in startsWith and endsWith methods of the String class
 */
class CountPrefixSuffixPairs_LC3042 {
    public int countPrefixSuffixPairs(String[] words) {
        int count = 0;
        int n = words.length;

        // Iterate through all pairs (i, j) where i < j
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Check if words[i] is both a prefix and a suffix of words[j]
                if (isPrefixAndSuffix(words[i], words[j])) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isPrefixAndSuffix(String str1, String str2) {
        // str1 cannot be a prefix/suffix if it is longer than str2
        if (str1.length() > str2.length()) {
            return false;
        }
        // Use built-in Java methods for prefix and suffix checks
        return str2.startsWith(str1) && str2.endsWith(str1);
    }
}
