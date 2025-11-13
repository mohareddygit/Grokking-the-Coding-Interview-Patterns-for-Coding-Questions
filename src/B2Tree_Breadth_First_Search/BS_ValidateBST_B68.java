package B2Tree_Breadth_First_Search;

/**
 * 98. Validate Binary Search Tree
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys strictly less than the node's key.
 * The right subtree of a node contains only nodes with keys strictly greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
public class BS_ValidateBST_B68 {
    public boolean isValidBSTRecursive(TreeNode root) {
        return validateRecursive(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validateRecursive(TreeNode node, long min, long max) {
        if (node == null) return true;

        if (node.val <= min || node.val >= max) return false;

        return validateRecursive(node.left, min, node.val) &&
               validateRecursive(node.right, node.val, max);
    }

    private TreeNode prev = null;
    public boolean isValidBSTInorderTraversal(TreeNode root) {
        if (root == null) return true;

        if (!isValidBSTInorderTraversal(root.left)) return false;

        if (prev != null && root.val <= prev.val) return false;
        prev = root;

        return isValidBSTInorderTraversal(root.right);
    }

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
