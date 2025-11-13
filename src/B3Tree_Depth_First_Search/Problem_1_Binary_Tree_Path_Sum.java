package B3Tree_Depth_First_Search;

// Problem Statement: Binary Tree Path Sum (easy)
// LeetCode Question: 112. Path Sum

/**
 * To solve LeetCode question 112, "Path Sum", we need to determine if there exists a root-to-leaf path such that summing all the values along the path equals a given target sum.
 *
 * This problem is a classic application of Depth-First Search (DFS) or recursion, as we explore paths down the tree structure.
 * Algorithm: Depth-First Search (DFS) with Recursion
 * The approach is to traverse the tree recursively while keeping track of the remaining sum needed at each step.
 * Base Case (Leaf Node): When we reach a leaf node (a node with no children), we check if the current node's value is exactly equal to the remaining targetSum. If it is, we've found a valid path, and we return true.
 * Recursive Step (Internal Node): For an internal node, we subtract the current node's value from the targetSum. We then recursively check if a valid path exists in either the left subtree or the right subtree, passing down the updated remaining sum.
 * No Path Found: If the entire tree is traversed without finding a matching leaf, we return false.
 */
public class Problem_1_Binary_Tree_Path_Sum {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode (int val) {
            this.val = val;
        }
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        // If the root is null (empty tree), no path exists.
        if (root == null) {
            return false;
        }

        // Check if this is a leaf node
        if (root.left == null && root.right == null) {
            // If it's a leaf, check if the current node's value matches the target
            return targetSum == root.val;
        }

        // If it's not a leaf, explore the left and right subtrees
        // We pass the updated target sum (original target - current node's value)
        boolean leftSum = hasPathSum(root.left, targetSum - root.val);
        boolean rightSum = hasPathSum(root.right, targetSum - root.val);

        // A path exists if either the left or the right subtree found a path
        return leftSum || rightSum;
    }



}
