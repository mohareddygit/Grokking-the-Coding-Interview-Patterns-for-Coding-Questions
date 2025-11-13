package B3Tree_Depth_First_Search;

// Problem Statement: Path with Maximum Sum (hard)
// LeetCode Question: 124. Binary Tree Maximum Path Sum/*

/**
 * 124. Binary Tree Maximum Path Sum
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 */

public class DFS_7_Path_With_Maximum_Sum_B63 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int x) {
            this.val = x;
        }
    }

    private static int globalMaximumSum;

    public int findMaximumPathSum(TreeNode root) {
        globalMaximumSum = Integer.MIN_VALUE;
        findMaximumPathSumRecursive(root);
        return globalMaximumSum;
    }

    private static int findMaximumPathSumRecursive(TreeNode currentNode){
        if (currentNode == null) return 0;
        int maxPathSumFromLeft = findMaximumPathSumRecursive(currentNode.left);
        int maxPathSumFromRight = findMaximumPathSumRecursive(currentNode.right);
        maxPathSumFromLeft = Math.max(maxPathSumFromLeft, 0);
        maxPathSumFromRight = Math.max(maxPathSumFromRight, 0);
        int localMaximumSum = maxPathSumFromLeft + maxPathSumFromRight + currentNode.val;
        globalMaximumSum = Math.max(globalMaximumSum, localMaximumSum);
        return Math.max(maxPathSumFromLeft, maxPathSumFromRight) + currentNode.val;
    }

}
