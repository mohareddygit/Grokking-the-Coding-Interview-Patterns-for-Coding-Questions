package B1HashMap_HashTable;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. Longest Consecutive Sequence
 */
public class HM_LongestConsecutiveSequence_B31 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int longest = 0;

        for (int num : set) {
            // Only start counting if it's the beginning of a sequence
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int streak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    streak++;
                }

                longest = Math.max(longest, streak);
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        HM_LongestConsecutiveSequence_B31 solver = new HM_LongestConsecutiveSequence_B31();
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println("Longest consecutive sequence length: " + solver.longestConsecutive(nums)); // Output: 4
    }
}
