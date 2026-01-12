class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0; // The "Writer" pointer

        for (int j = 0; j < nums.length; j++) { // The "Reader" pointer
            // If the current element is NOT the one we want to remove
            if (nums[j] != val) {
                // Move it to the 'i-th' position
                nums[i] = nums[j];
                // Increment i to prepare for the next valid element
                i++;
            }
        }

        // i is the count of elements that are not equal to val
        return i;
    }
}