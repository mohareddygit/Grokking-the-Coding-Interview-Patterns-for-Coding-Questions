package B1HashMap_HashTable;

import java.util.*;

/**
 * Leetcode 271: Encode and Decode Strings
 */
public class AR_Codec_B59 {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append('#').append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < s.length()) {
            int j = i;
            // Find the delimiter '#'
            while (s.charAt(j) != '#') {
                j++;
            }
            int length = Integer.parseInt(s.substring(i, j));
            String str = s.substring(j + 1, j + 1 + length);
            result.add(str);
            i = j + 1 + length;
        }

        return result;
    }

    public static void main(String[] args) {
        AR_Codec_B59 ARCodecB59 = new AR_Codec_B59();
        List<String> input = Arrays.asList("hello", "world", "leet#code", "123");
        String encoded = ARCodecB59.encode(input);
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + ARCodecB59.decode(encoded));
    }
}
