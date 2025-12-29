package A0CompFAQ;

import java.util.HashMap;
import java.util.Map;

/**
 * The Java solution for LeetCode 532: K-diff Pairs in an Array finds unique pairs
 * \((nums[i],nums[j])\) such that \(|nums[i]-nums[j]|=k\).
 * The most efficient approach uses a HashMap to count frequencies,
 * which handles the uniqueness requirement and the special case where \(k=0\) in a single pass
 */

/**
 * Key LogicHandling \(k=0\): A valid pair exists if a number appears at least twice in the array
 * (e.g., [1, 1] with \(k=0\) forms the pair (1, 1)).Uniqueness: By iterating through the
 * unique keys of the HashMap and only checking for num + k,
 * we ensure each pair is counted exactly once.Early Exit: If \(k<0\),
 * the function returns 0 immediately because the absolute difference between
 * two numbers is always non-negative.Complexity AnalysisTime Complexity: \(O(N)\).
 * We traverse the array once to build the map and then iterate over the
 * unique keys in the map.Space Complexity: \(O(N)\) to store frequencies
 * in the HashMap.
 *
 * Alternative: Two-Pointer ApproachIf space is constrained,
 * you can sort the array and use two pointers. This reduces space to \(O(1)\)
 * (ignoring sorting overhead) but increases time complexity to \(O(N\log N)\)

 */
class KDiffPairsInArray_LC532 {
    public int findPairs(int[] nums, int k) {
        // Absolute difference cannot be negative
        if (k < 0) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        // Count the frequency of each number
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                // For k = 0, we need at least two occurrences of the same number
                if (entry.getValue() >= 2) {
                    count++;
                }
            } else {
                // For k > 0, check if the complement (num + k) exists in the map
                if (map.containsKey(entry.getKey() + k)) {
                    count++;
                }
            }
        }

        return count;
    }
}