package B3Tree_Depth_First_Search;

// Problem Statement: All Paths for a Sum (medium)
// LeetCode Question: 113. Path Sum II

/**
 * To find
 * all root-to-leaf paths that sum to a target value in a binary tree (LeetCode 113, "Path Sum II"), you need a more advanced recursive approach than just checking if a path exists. This approach collects every valid path as a list of node values.
 * Algorithm: Backtracking DFS
 * We use Depth-First Search (DFS) combined with a backtracking technique. We build a temporary path as we traverse down the tree. When we find a valid path to a leaf node, we store a copy of that path. As we backtrack up the tree (returning from recursive calls), we remove the last visited node from the temporary path.
 * <p>
 * The critical part of this solution is the combination of adding a node before recursion and removing it after the recursive call returns:
 * currentPath.add(node.val);
 * findPathsRecursive(...);
 * currentPath.remove(currentPath.size() - 1);
 * This mechanism ensures that the currentPath list always accurately represents the path from the root to the current node being processed. When a function call returns, the path list is "reset" to its state before that call, allowing the algorithm to explore sibling branches correctly without carrying extra nodes.
 */
/**
 * The critical part of this solution is the combination of adding a node before recursion and removing it after the recursive call returns:
 * currentPath.add(node.val);
 * findPathsRecursive(...);
 * currentPath.remove(currentPath.size() - 1);
 * This mechanism ensures that the currentPath list always accurately represents the path from the root to the current node being processed. When a function call returns, the path list is "reset" to its state before that call, allowing the algorithm to explore sibling branches correctly without carrying extra nodes.
 */

import java.util.ArrayList;
import java.util.List;

public class Problem_2_All_Paths_For_A_Sum {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    List<List<Integer>> allPaths = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return allPaths;
        }

        // Start the recursive helper function with an empty temporary path
        findPathsRecursive(root, targetSum, new ArrayList<>());
        return allPaths;
    }

    private void findPathsRecursive(TreeNode node, int remainingSum, List<Integer> currentPath) {
        if (node == null) {
            return;
        }

        // 1. Add the current node's value to the temporary path list
        currentPath.add(node.val);

        // 2. Check if it's a valid root-to-leaf path that sums to the target
        if (node.left == null && node.right == null && node.val == remainingSum) {
            // Found a valid path! Add a *copy* of the currentPath to the result list.
            // We must add a copy because 'currentPath' will continue to change due to backtracking.
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            // 3. Recurse down left and right subtrees
            findPathsRecursive(node.left, remainingSum - node.val, currentPath);
            findPathsRecursive(node.right, remainingSum - node.val, currentPath);
        }

        // 4. Backtrack: Remove the current node's value as we return from the recursion
        // This clears the path for the next branch of the tree being explored.
        currentPath.remove(currentPath.size() - 1);
    }
}



