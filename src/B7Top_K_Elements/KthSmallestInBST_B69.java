package B7Top_K_Elements;

/**
 * 230. Kth Smallest Element in a BST
 * using in-order traversal which naturally visits nodes in ascending order in a Binary Search Tree.
 */
public class KthSmallestInBST_B69 {
    private int count = 0;
    private int result = -1;

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return result;
    }

    private void inOrder(TreeNode node, int k) {
        if (node == null) return;

        inOrder(node.left, k);

        count++;
        if (count == k) {
            result = node.val;
            return;
        }

        inOrder(node.right, k);
    }

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
