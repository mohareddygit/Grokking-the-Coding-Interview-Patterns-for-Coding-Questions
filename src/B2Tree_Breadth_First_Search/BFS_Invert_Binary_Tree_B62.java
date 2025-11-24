package B2Tree_Breadth_First_Search;

// Problem Statement: Invert Binary Tree (medium)
// LeetCode Question: 226. Invert Binary Tree

import java.util.ArrayDeque;
import java.util.Deque;

public class BFS_Invert_Binary_Tree_B62 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public TreeNode invertTreeIterativeBFS(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // swap children
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            // add children to queue
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return root;
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root != null) {
            invertTree(root.left);
            invertTree(root.right);
            TreeNode temp = root.right;
            root.right = root.left;
            root.left = temp;
        }
        return root;
    }
}
