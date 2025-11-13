package B1HashMap_HashTable;

/**
 * 242. Valid Anagram
 */
public class AR_ValidAnagram_B53 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] count = new int[26]; // For lowercase English letters

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++; // Increment for s
            count[t.charAt(i) - 'a']--; // Decrement for t
        }

        for (int c : count) {
            if (c != 0) return false; // Mismatch in character count
        }

        return true;
    }

    public static void main(String[] args) {
        AR_ValidAnagram_B53 solver = new AR_ValidAnagram_B53();
        System.out.println(solver.isAnagram("anagram", "nagaram")); // true
        System.out.println(solver.isAnagram("rat", "car"));         // false
    }
}
