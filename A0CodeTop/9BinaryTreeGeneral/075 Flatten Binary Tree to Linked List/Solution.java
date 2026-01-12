/*
 * 
 * 
 * 
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode (){}
    TreeNode(int val){}
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// input Head of the tree
// output: flatten the tree to linked list
class Solution {
    public void flatten(TreeNode root){
        if (root == null) return;

        TreeNode current = root;

        while (current != null){
            if (current.left != null){
                // find the rightmost node
                TreeNode rightmost = current.left;
                while(rightmost.right != null){
                    rightmost = rightmost.right;
                }

                // connect rightmost node to the current.right subtree
                rightmost.right = current.right;

                // Mode the left subtree to the right
                current.right = current.left;
                current.left = null;

            }

            // move the next node
            current = current.right;
        }
    }
}