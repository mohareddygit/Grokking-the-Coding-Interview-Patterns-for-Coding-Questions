package C5Backtracking;

import java.util.ArrayList;
import java.util.List;

class CT7_Combinations_LC77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), n, k, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentPath, int n, int k, int start) {
        // Base Case: If the current combination size reaches k
        if (currentPath.size() == k) {
            result.add(new ArrayList<>(currentPath));
            return;
        }

        // Standard backtracking loop starting from 'start'
        for (int i = start; i <= n; i++) {
            // 1. Choose: Add number to current path
            currentPath.add(i);
            
            // 2. Explore: Recurse to pick the next number (must be > current i)
            backtrack(result, currentPath, n, k, i + 1);
            
            // 3. Undo (Backtrack): Remove number to try different candidates
            currentPath.remove(currentPath.size() - 1);
        }
    }

    public static void main(String[] args) {
        CT7_Combinations_LC77 CT7_Combinations_LC77 = new CT7_Combinations_LC77();
        System.out.println(CT7_Combinations_LC77.combine(3,2));

    }
}
