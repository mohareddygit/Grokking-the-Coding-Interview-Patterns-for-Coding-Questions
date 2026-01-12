package C5Backtracking;

import java.util.ArrayList;
import java.util.List;

class CT21_Subsets_LC78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Start the recursive process with an empty current path
        //backtrackPureBinaryRecursive(result, new ArrayList<>(), nums, 0);
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentPath, int[] nums, int start) {
        // Every state in the recursion tree is a valid subset
        // We add a copy of currentPath to the result
        result.add(new ArrayList<>(currentPath));

        //iterate horizontally
        for (int i = start; i < nums.length; i++) {

            System.out.println("About to add index " + i + "value:" + nums[i]);
            // 1. Choose: Add the element to the current subset
            currentPath.add(nums[i]);

            // 2. Explore: Recurse(vertically) with the next index to find further combinations
            backtrack(result, currentPath, nums, i + 1);

            System.out.println("About to remove from " + currentPath);
            // 3. Un-choose (Backtrack): Remove the last element to try others
            currentPath.remove(currentPath.size() - 1);
        }
    }

    private void backtrackPureBinaryRecursive(List<List<Integer>> result, List<Integer> currentPath, int[] nums, int index) {
        // Base Case: If we've considered every element, add the current subset
        if (index == nums.length) {
            result.add(new ArrayList<>(currentPath));
            return;
        }

        System.out.println("About to add index " + index + "value:" + nums[index]);
        // --- CHOICE 1: INCLUDE nums[index] .. Add the element to the current subset---
        currentPath.add(nums[index]);
        // 2. Explore: Recurse(vertically) with the next index to find further combinations
        backtrackPureBinaryRecursive(result, currentPath, nums, index + 1);

        System.out.println("About to remove from " + currentPath);
        // --- BACKTRACK STEP ---
        // Remove the element we just added to "clean up" for the next branch
        currentPath.remove(currentPath.size() - 1);

        // --- CHOICE 2: EXCLUDE nums[index] and recurse(horizontally) to try other elements ---
        backtrackPureBinaryRecursive(result, currentPath, nums, index + 1);
    }


    public static void main(String[] args) {
        List<List<Integer>> result = new CT21_Subsets_LC78().subsets(new int[]{1, 2, 3});
        System.out.println(result);

    }
}
