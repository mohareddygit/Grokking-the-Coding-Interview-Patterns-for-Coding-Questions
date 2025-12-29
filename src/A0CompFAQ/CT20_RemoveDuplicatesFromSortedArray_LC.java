package A0CompFAQ;

public class CT20_RemoveDuplicatesFromSortedArray_LC {
    public int removeDuplicates(int[] nums) {
        // Return 0 if the array is empty
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Pointer for the last unique element
        int i = 0;

        // Iterate through the array starting from the second element
        for (int j = 1; j < nums.length; j++) {
            // If the current element is different from the last unique one
            if (nums[j] != nums[i]) {
                i++; // Move unique pointer forward
                nums[i] = nums[j]; // Update the next unique position
            }
        }

        // Return the number of unique elements (index + 1)
        return i + 1;
    }
}