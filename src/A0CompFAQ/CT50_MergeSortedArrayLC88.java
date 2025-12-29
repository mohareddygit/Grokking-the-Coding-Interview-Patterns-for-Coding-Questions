package A0CompFAQ;

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