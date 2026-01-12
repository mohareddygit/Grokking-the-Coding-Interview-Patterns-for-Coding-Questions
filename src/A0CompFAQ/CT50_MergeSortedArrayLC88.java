package A0CompFAQ;

/**
* To solve LeetCode 88: Merge Sorted Array, you are given two arrays nums1 and nums2, both sorted in non-decreasing order. 
nums1 has enough extra space at the end (filled with 0s) to hold all elements from nums2. 

Optimal Strategy: 
Three-Pointer (Right to Left) The "trick" to doing this in-place with \(O(1)\) extra space is to 
fill nums1 starting from the back (the largest elements). 
If you started from the front, you would overwrite elements in nums1 that you still need to 
compare. Initialize Pointers:i at the last actual element of nums1 (m - 1).j 
at the last element of nums2 (n - 1).k at the very end of the nums1 array (m + n - 1).

Compare and Fill: While both i and j are \(\ge 0\), take the larger of nums1[i] and nums2[j], 
place it at nums1[k], and move the pointers backward.

Handle Leftovers:
If j >= 0 after the first loop, copy the remaining elements from nums2 into nums1. (If i >= 0, they are already in the correct place).


Why Right-to-Left?
No Overwriting: By filling from the back (m+n-1), we use the "empty" space (the zeros) first. 
We only ever write to index k. Since \(k\) is always \(\ge i\) and \(\ge j\),
we never destroy data we haven't checked yet.

Efficiency: This is \(O(m+n)\) time and \(O(1)\) space. A brute-force approach (merging then sorting) would be \(O((m+n)\log (m+n))\).

Common Interview Pitfalls The Second While Loop: Many forget to check if nums2 still has elements after the first loop finishes.
If nums1 becomes empty first (e.g., nums1 = [0], m = 0, nums2 = [1], n = 1),
you must copy the rest of nums2 over.m vs nums1.length: 
Remember that m is the number of elements, but nums1.length is the total capacity. 
Always use m-1 for your pointer, not nums1.length-1. Merge Sorted Array - LeetCode
*/
class CT50_MergeSortedArrayLC88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;         // Pointer for last valid element in nums1
        int j = n - 1;         // Pointer for last element in nums2
        int k = m + n - 1;     // Pointer for last position in nums1 array

        // Move backwards and pick the larger of the two elements
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        // If there are leftover elements in nums2, copy them.
        // We don't need to check leftovers for nums1 because they are already in place.
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}
