package B1HashMap_HashTable;

import java.util.*;

/**
 * 49. Group Anagrams
 * using a hash map to group words by their sorted character signature.
 *
 */
public class HM_GroupAnagrams_B54 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            // Sort characters to form the key
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            // Group anagrams by sorted key
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        HM_GroupAnagrams_B54 solver = new HM_GroupAnagrams_B54();
        String[] input = {"eat","tea","tan","ate","nat","bat"};
        System.out.println("Grouped Anagrams: " + solver.groupAnagrams(input));
    }
}
