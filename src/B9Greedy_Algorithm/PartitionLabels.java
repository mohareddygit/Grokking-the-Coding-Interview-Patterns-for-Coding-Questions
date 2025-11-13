package B9Greedy_Algorithm;

import java.util.*;

/**
 * Leetcode 763: Partition Labels, using a greedy + two-pointer strategy.
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        int[] last = new int[26]; // Last occurrence of each character

        // Step 1: Record last index of each character
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        // Step 2: Greedy partitioning
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        PartitionLabels solver = new PartitionLabels();
        String s = "ababcbacadefegdehijhklij";
        System.out.println("Partition sizes: " + solver.partitionLabels(s)); // Output: [9,7,8]
    }
}
