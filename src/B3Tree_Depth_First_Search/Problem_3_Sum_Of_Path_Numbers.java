package B3Tree_Depth_First_Search;

// Problem Statement: Sum of Path Numbers (medium)
// LeetCode Question: 129. Sum Root to Leaf Numbers

/**
 * To solve LeetCode question 129, "Sum Root to Leaf Numbers," we need to calculate the total sum of all numbers represented by every root-to-leaf path. Each path forms a numerical value, where the root is the most significant digit.
 *
 * Algorithm: Depth-First Search (DFS) with Number Building
 * We can use a recursive Depth-First Search (DFS) approach. As we traverse the tree, we build the number represented by the current path. When we reach a leaf node, we add the final number to a global sum.
 * The current path number can be calculated at each step as (parent_path_number * 10) + current_node_value.
 */
public class Problem_3_Sum_Of_Path_Numbers {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode (int val) {
            this.val = val;
        }
    }
    // A global variable to store the total sum of all root-to-leaf numbers
    private int totalSum = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // Start the recursive traversal, beginning with an initial path number of 0
        dfs(root, 0);
        return totalSum;
    }

    private void dfs(TreeNode node, int currentPathNumber) {
        if (node == null) {
            return;
        }

        // Calculate the number formed by the path so far
        // Shift previous digits left (multiply by 10) and add the current digit
        currentPathNumber = (currentPathNumber * 10) + node.val;

        // Check if we have reached a leaf node
        if (node.left == null && node.right == null) {
            // We found a complete number. Add it to the total sum.
            totalSum += currentPathNumber;
            return;
        }

        // If not a leaf, continue the DFS traversal
        // Recursively call for left and right children, passing the updated path number
        dfs(node.left, currentPathNumber);
        dfs(node.right, currentPathNumber);
    }


    
}
