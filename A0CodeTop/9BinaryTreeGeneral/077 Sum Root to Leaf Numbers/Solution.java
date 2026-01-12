/*
 * 
 * 
 * 
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){}
    TreeNode(int val){
        this.val = val;
    }
    TreeNode (int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// input: head of the tree
// output: int, sum of all root-to-leaf numbers
class Solution {
    public int sumAllLeaf(TreeNode root){
        return dfs(root, 0);
    }

    ///
    private int dfs(TreeNode node, int currentSum) {
        // Base case: if the node is null, return 0
        if (node == null) return 0;
        
        // Update the current sum (append the node's value to the path)
        currentSum = currentSum * 10 + node.val;
        
        // If the node is a leaf, return the current sum
        if (node.left == null && node.right == null) {
            return currentSum;
        }
        
        // Recursive case: traverse left and right subtrees
        int leftSum = dfs(node.left, currentSum);
        int rightSum = dfs(node.right, currentSum);
        
        // Return the total sum from both subtrees
        return leftSum + rightSum;
    }
}