package B1HashMap_HashTable;

import java.util.HashSet;
import java.util.Set;

/**
 * 217. Contains Duplicate
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
 */
public class HM_ContainsDuplicate_B3 {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(num)) {
                return true; // Duplicate found
            }
            seen.add(num);
        }

        return false; // All elements are unique
    }

    public static void main(String[] args) {
        HM_ContainsDuplicate_B3 solver = new HM_ContainsDuplicate_B3();
        int[] nums = {1, 2, 3, 4, 1};
        System.out.println("Contains duplicate? " + solver.containsDuplicate(nums)); // Output: true
    }
}
