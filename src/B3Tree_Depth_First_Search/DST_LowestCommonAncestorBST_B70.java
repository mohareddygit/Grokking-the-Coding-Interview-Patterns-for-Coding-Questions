package B3Tree_Depth_First_Search;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 */
public class DST_LowestCommonAncestorBST_B70 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // If both nodes are smaller, LCA is in left subtree
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // If both nodes are greater, LCA is in right subtree
        else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // Split point: one node on each side or one equals root
        else {
            return root;
        }
    }

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
