package C5Backtracking;

import java.util.ArrayList;
import java.util.List;

public class CT24_Permutations_LC46 {

    public List<List<Integer>> permute1(int[] nums) {

        List<List<Integer>> resultList = new ArrayList<>();

        backtrack1(resultList, new ArrayList<>(), nums);
        return resultList;
    }

    private void backtrack1(List<List<Integer>> resultList,
                            ArrayList<Integer> current, int[] nums) {
        // If we match the length, it is a permutation
        if (current.size() == nums.length) {
            resultList.add(new ArrayList<>(current));
            return;
        }

        for (int number : nums) {
            // Skip if we get same element
            if (current.contains(number))
                continue;

            // Add the new element
            current.add(number);

            // Go back to try other element
            backtrack1(resultList, current, nums);

            // Remove the element
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        CT24_Permutations_LC46 CT24_Permutations_LC46 = new CT24_Permutations_LC46();
        //System.out.println(CT24_Permutations_LC46.permute1(new int[]{1, 2, 3}));
       //permute1 will fail if input has duplicates
        // System.out.println(CT24_Permutations_LC46.permute1(new int[]{1, 1, 2}));
       // System.out.println(CT24_Permutations_LC46.permute2(new int[]{1, 2, 3}));
        //System.out.println(CT24_Permutations_LC46.permute2(new int[]{1, 1, 2}));
    }


    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // used[i] will be true if nums[i] is already in the current permutation
        boolean[] used = new boolean[nums.length];
        backtrack2(result, new ArrayList<>(), nums, used);
        return result;
    }


    private void backtrack2(List<List<Integer>> result, List<Integer> currentPath, int[] nums, boolean[] used) {
        // BASE CASE: If the current path is the same length as nums, we found a permutation
        if (currentPath.size() == nums.length) {
            result.add(new ArrayList<>(currentPath)); // Snapshot of currentPath
            return;
        }

        // EXPLORE: Try every number in the array for the current position
        for (int i = 0; i < nums.length; i++) {
            // If the number is already used, skip it
            if (used[i]) continue;

            // 1. CHOOSE: Mark as used and add to path
            used[i] = true;
            currentPath.add(nums[i]);

            // 2. EXPLORE: Recurse to fill the next position
            backtrack2(result, currentPath, nums, used);

            // 3. BACKTRACK (UNDO): Unmark and remove from path
            currentPath.remove(currentPath.size() - 1);
            used[i] = false;
        }
    }
}
