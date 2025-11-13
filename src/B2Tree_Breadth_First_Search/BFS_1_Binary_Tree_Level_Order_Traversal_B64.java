package B2Tree_Breadth_First_Search;

// Problem Statement: Binary Tree Level Order Traversal (easy)
// LeetCode Question: 102. Binary Tree Level Order Traversal

/**
 * The standard way to perform a
 * Level Order Traversal (which is a Breadth-First Search for a tree) in Java is by using a Queue data structure. This allows you to visit nodes level by level, from left to right.
 * Algorithm: Using a Queue
 * Initialize: Create a queue to hold TreeNode objects and an empty list of lists (List<List<Integer>>) to store the results grouped by level.
 * Start: Add the root node to the queue.
 * Process Levels: While the queue is not empty:
 * Get the current level's size (this is crucial for knowing how many nodes belong to the current level).
 * Create a temporary list to store values of the current level.
 * Iterate size times:
 * Dequeue a node.
 * Add its value to the temporary current level list.
 * Enqueue its left child (if not null).
 * Enqueue its right child (if not null).
 * Add the temporary level list to the main result list.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS_1_Binary_Tree_Level_Order_Traversal_B64 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x){
            this.val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        // Use a Queue (LinkedList implementation) for BFS
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // Get the number of nodes at the current level
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes in the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);

                // Add children to the queue for the next level
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            // Add the completed level list to the final result
            result.add(currentLevel);
        }

        return result;
    }
}

