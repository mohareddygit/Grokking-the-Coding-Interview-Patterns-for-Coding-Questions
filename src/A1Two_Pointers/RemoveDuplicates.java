package A1Two_Pointers;

/**
 * Explanation
 * Initialization: writePointer starts at 1 because the first element (nums[0]) is always unique in a non-empty array and is kept in its position.
 * Iteration: The readPointer (r) scans the entire array.
 * Comparison: If the element at r is different from the element immediately before it (r-1), it means we've found a new unique number.
 * Placement: This unique number is then written to the position indicated by the writePointer, and the writePointer is incremented to prepare for the next unique element.
 * Skipping Duplicates: If nums[r] is a duplicate (nums[r] == nums[r-1]), the writePointer does not move, effectively leaving the duplicate behind and overwriting it later with a new unique element.
 * Return Value: The final value of writePointer represents the count of unique elements, which is the new logical length of the array.
 */
public class RemoveDuplicates {
    /**
     * Removes duplicates from a sorted array in-place.
     *
     * @param nums The input sorted array.
     * @return The new length of the array after removing duplicates.
     */
    public static int removeDuplicates(int[] nums) {
        // If the array is empty, there are no duplicates to remove.
        if (nums.length == 0) {
            return 0;
        }

        // 'writePointer' tracks the index where the next unique element should be placed.
        // It starts at 1 because the first element is always unique in a non-empty array.
        int writePointer = 1;

        // 'readPointer' iterates through the array starting from the second element.
        for (int readPointer = 1; readPointer < nums.length; readPointer++) {
            // Compare the current element (readPointer) with the previous element (readPointer - 1).
            // If they are different, it's a new unique element.
            if (nums[readPointer] != nums[readPointer - 1]) {
                // Place the unique element at the 'writePointer' position.
                nums[writePointer] = nums[readPointer];
                // Move the 'writePointer' forward to the next available position.
                writePointer++;
            }
            // If they are the same (a duplicate), the 'writePointer' stays in place,
            // effectively skipping the duplicate element.
        }

        // The 'writePointer' now holds the count of unique elements,
        // which is the new length of the modified array.
        return writePointer;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int newLength = removeDuplicates(nums);

        System.out.println("New length of array: " + newLength);
        System.out.print("Array after removing duplicates: ");
        // Print the first 'newLength' elements
        for (int i = 0; i < newLength; i++) {
            System.out.print(nums[i] + " ");
        }
        // Output:
        // New length of array: 5
        // Array after removing duplicates: 0 1 2 3 4
    }
}
