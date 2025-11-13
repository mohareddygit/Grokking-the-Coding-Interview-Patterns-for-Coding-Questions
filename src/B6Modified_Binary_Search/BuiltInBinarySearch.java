package B6Modified_Binary_Search;

import java.util.Arrays;

public class BuiltInBinarySearch {
    public static void main(String[] args) {
        int[] nums = {10, 20, 30, 40, 50};
        int target = 40;

        // Ensure the array is sorted (in this case, it already is)
        // Arrays.sort(nums); 

        // Perform the binary search
        int index = Arrays.binarySearch(nums, target);

        if (index >= 0) {
            System.out.println("Element " + target + " found at index: " + index);
        } else {
            System.out.println("Element " + target + " not found in the array.");
        }
    }
}
