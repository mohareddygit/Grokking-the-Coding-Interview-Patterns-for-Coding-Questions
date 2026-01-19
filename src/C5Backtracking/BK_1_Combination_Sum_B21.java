package C5Backtracking;

// Problem Statement: Combination Sum (medium)
// LeetCode Question: 39. Combination Sum

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BK_1_Combination_Sum_B21 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // Optimization: Sorting allows for the "break" optimization below
        //Arrays.sort(candidates);
        backtrack(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] candidates, int start, int target, List<Integer> comb, List<List<Integer>> res) {
        if (target == 0) { // Base case: target reached
            res.add(new ArrayList<>(comb));
            return; //observe
        }

        if (target < 0) { // Base case: exceeded target
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            // Optimization: If the current number is already too big,
            // because the array is sorted, all numbers after it will also be too big.
            // target < 0 is handled by the loop condition if we use pruning, so above target < 0 can be removed
            /*if (candidates[i] > target) {
                break;
            }
             */

            comb.add(candidates[i]);
            // We use 'i' instead of 'i + 1' because we can reuse the same element
            backtrack(candidates, i, target - candidates[i], comb, res);
            comb.remove(comb.size() - 1); // Backtrack: remove last element
        }
    }
}
