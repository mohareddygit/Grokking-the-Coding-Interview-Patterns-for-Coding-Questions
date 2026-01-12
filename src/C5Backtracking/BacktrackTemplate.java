package C5Backtracking;

import java.util.ArrayList;
import java.util.List;

public class BacktrackTemplate {
    public List<List<Integer>> solveProblem(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        // Optional: Arrays.sort(nums); // Helps with duplicates or ordered results
        backtrack(results, new ArrayList<>(), nums, 0);
        return results;
    }

    private void backtrack(List<List<Integer>> results, List<Integer> currentPath, int[] nums, int start) {
        // 1. BASE CASE: If a valid solution is found, add a COPY to results
        if (isSolution(currentPath)) {
            results.add(new ArrayList<>(currentPath));
            return;
        }

        // 2. ITERATE: Loop through possible choices
        for (int i = start; i < nums.length; i++) {
            // 3. PRUNING: Skip invalid options (optional)
            if (isInvalid(nums[i], currentPath)) continue;

            // 4. CHOOSE: Make the decision
            currentPath.add(nums[i]);

            // 5. EXPLORE: Recurse to find the next part of the solution
            // Use 'i + 1' for combinations, '0' or 'i' for others depending on rules
            backtrack(results, currentPath, nums, i + 1);

            // 6. UNCHOOSE: Backtrack by removing the last element
            currentPath.remove(currentPath.size() - 1);
        }
    }

    private boolean isInvalid(int num, List<Integer> currentPath) {
        return true;
    }

    private boolean isSolution(List<Integer> currentPath) {
        return true;
    }
}
