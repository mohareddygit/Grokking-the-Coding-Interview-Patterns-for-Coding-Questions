package B1HashMap_HashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * 242. Valid Anagram
 */
public class AR_ValidAnagram_B53 {
    public boolean isAnagram(String s, String t) {
        // If lengths are different, they cannot be anagrams.
        if (s.length() != t.length()) {
            return false;
        }

        // An array to store the frequency of each lowercase English letter (26 letters).
        int[] counts = new int[26];

        // Iterate through both strings simultaneously.
        for (int i = 0; i < s.length(); i++) {
            // Increment the count for the character in s.
            counts[s.charAt(i) - 'a']++;
            // Decrement the count for the character in t.
            counts[t.charAt(i) - 'a']--;
        }

        // Check if all counts are zero. If they are, they are valid anagrams.
        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * The hash table solution offers more flexibility if the input strings could contain a wider range of
     * characters (e.g., uppercase letters, numbers, symbols, or Unicode characters),
     * where a fixed-size array of 26 wouldn't suffice.
     *
     * This HashMap approach has the same asymptotic time complexity \(O(n)\)
     * but uses more memory overhead (space complexity \(O(k)\),
     * where \(k\) is the number of unique characters) compared
     * to the optimized \(O(1)\) space complexity of the fixed-size array solution.
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramWithHashMap(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // A hash map to store character frequencies.
        Map<Character, Integer> counts = new HashMap<>();

        // Iterate through string s and populate the map.
        for (char c : s.toCharArray()) {
            // Get the current count or 0 if not present, then increment.
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        // Iterate through string t and decrement counts.
        for (char c : t.toCharArray()) {
            // If a character in t isn't in our map, or its count is already zero,
            // t has a character that s doesn't have enough of.
            if (!counts.containsKey(c) || counts.get(c) == 0) {
                return false;
            }
            // Decrement the count.
            counts.put(c, counts.get(c) - 1);
        }

        // We don't need a final loop to check for zero counts,
        // because we return false immediately if t has extra characters.
        // If we reach here, all characters in t found a match in s.
        return true;
    }

    public static void main(String[] args) {
        AR_ValidAnagram_B53 solver = new AR_ValidAnagram_B53();
        System.out.println(solver.isAnagram("anagram", "nagaram")); // true
        System.out.println(solver.isAnagram("rat", "car"));         // false
    }
}
