package A0CompC1;

/**
 * The Java solution for LeetCode 3101: Count Alternating Subarrays involves counting the total number of contiguous subarrays where no two adjacent elements are the same.
 * Java Implementation
 * You can solve this in O(N) time by keeping a running count of alternating subarrays that end at the current index.
 */

/**
 * Key Logic Running Count: Every single element is itself an alternating subarray.Incremental Growth: If nums[i] is different from nums[i-1], it extends all previous alternating subarrays ending at i-1. For example, if you have [0, 1] and add 0, you gain the new subarrays [0], [1, 0], and [0, 1, 0].Pattern Reset: If nums[i] == nums[i-1], the only new alternating subarray ending at index i is the single element [nums[i]], so the segment length resets to 1.Long Return Type: Since the total number of subarrays can reach approximately \(N(N+1)/2\), and \(N=10^{5}\), the result must be a long to avoid overflow. Complexity Analysis Time Complexity: \(O(N)\), where \(N\) is the length of the array. We traverse the array exactly once.Space Complexity: \(O(1)\) as we only use a few variables to store the counts
 */
class CountAlternatingSubarrays_LC3101 {
    public long countAlternatingSubarrays(int[] nums) {
        long totalCount = 1; // Start with 1 for the first element
        long currentLen = 1; // Length of the current alternating segment

        for (int i = 1; i < nums.length; i++) {
            // Check if the current element alternates with the previous one
            if (nums[i] != nums[i - 1]) {
                currentLen++;
            } else {
                // If they match, the alternating pattern resets
                currentLen = 1;
            }
            // Add the number of new alternating subarrays ending at this index
            totalCount += currentLen;
        }

        return totalCount;
    }
}
