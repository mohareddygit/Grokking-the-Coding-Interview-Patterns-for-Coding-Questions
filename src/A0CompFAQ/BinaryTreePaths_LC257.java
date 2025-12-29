package A0CompFAQ;

import B2Tree_Breadth_First_Search.BS_ValidateBST_B68;

import java.util.ArrayList;
import java.util.List;

/**
 * The Java solution for LeetCode 257: Binary Tree Paths typically uses a Depth-First Search (DFS) approach to traverse the tree from the root to each leaf node.
 * Java Implementation (DFS)
 * This recursive solution builds a path string as it moves down the tree. When a leaf node is reached, the completed path is added to the result list.
 */

/**
 * String Building: The path is passed by value in each recursive call, effectively "saving" the path for each branch without needing explicit backtracking.
 */
class BinaryTreePaths_LC257 {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root != null) {
            dfs(root, "", result);
        }
        return result;
    }

    private void dfs(TreeNode node, String path, List<String> result) {
        // Build the current path string
        path += node.val;

        // Check if it's a leaf node (no children)
        if (node.left == null && node.right == null) {
            result.add(path);
        } else {
            // Append the arrow and recurse to children
            if (node.left != null) dfs(node.left, path + "->", result);
            if (node.right != null) dfs(node.right, path + "->", result);
        }
    }
}
