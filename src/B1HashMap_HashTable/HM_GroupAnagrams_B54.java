package B1HashMap_HashTable;

import java.util.*;

/**
 * 49. Group Anagrams
 * using a hash map to group words by their sorted character signature.
 *
 */
public class HM_GroupAnagrams_B54 {
    /**
     * Groups a list of strings into lists of anagrams.
     *
     * @param strs An array of strings to be grouped.
     * @return A list of lists, where each sublist contains anagrams.
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // Create a HashMap to store sorted word as key and list of anagrams as value
        Map<String, List<String>> anagramMap = new HashMap<>();

        // Iterate through each word in the input array
        for (String word : strs) {
            // Convert the word to a character array and sort it
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedKey = new String(chars); // This will be the key for anagram groups

            // Add the original word to the correct anagram group in the map
            anagramMap.computeIfAbsent(sortedKey, k -> new ArrayList<>()).add(word);
        }

        // Return all the grouped anagrams as a list of lists
        return new ArrayList<>(anagramMap.values());
    }


    public static void main(String[] args) {
        HM_GroupAnagrams_B54 solver = new HM_GroupAnagrams_B54();
        String[] input = {"eat","tea","tan","ate","nat","bat"};
        System.out.println("Grouped Anagrams: " + solver.groupAnagrams(input));
    }
}
