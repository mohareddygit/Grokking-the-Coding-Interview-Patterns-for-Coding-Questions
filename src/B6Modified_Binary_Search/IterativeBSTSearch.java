package B6Modified_Binary_Search;

/**
 * Performing an iterative binary search on a Binary Search Tree (BST) is straightforward and avoids the overhead of recursion call stacks.
 *
 * The core principle remains the same: compare the target value with the current node's value and decide whether to move left, right, or if the value matches.
 * Iterative Search Algorithm
 * Start a pointer (currentNode) at the root of the tree.
 * Loop while currentNode is not null.
 * Inside the loop, compare the target value with the currentNode.val:
 * If target == currentNode.val, the element is found; return true.
 * If target < currentNode.val, the target must be in the left subtree; update currentNode = currentNode.left.
 * If target > currentNode.val, the target must be in the right subtree; update currentNode = currentNode.right.
 * If the loop finishes without finding the value (the pointer becomes null), the value is not in the tree; return false.
 */
public class IterativeBSTSearch {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    // Assuming a BST instance exists with a 'root' reference
    private TreeNode root; 

    /**
     * Performs an iterative search for a value in the Binary Search Tree.
     *
     * @param target The value to search for.
     * @return true if the value is found, false otherwise.
     */
    public boolean searchIterative(int target) {
        // Start searching from the root
        TreeNode currentNode = this.root;

        // Loop until we either find the value or run out of nodes
        while (currentNode != null) {
            if (target == currentNode.val) {
                // Found the target value
                return true;
            } else if (target < currentNode.val) {
                // If target is smaller, go to the left subtree
                currentNode = currentNode.left;
            } else {
                // If target is larger, go to the right subtree
                currentNode = currentNode.right;
            }
        }

        // If the loop finishes, the value was not found in the tree
        return false;
    }

    // Example Usage (requires inserting nodes into 'root' first)
    public static void main(String[] args) {
        IterativeBSTSearch bst = new IterativeBSTSearch();
        // Manually build a small tree for demonstration
        bst.root = new TreeNode(50);
        bst.root.left = new TreeNode(30);
        bst.root.right = new TreeNode(70);
        bst.root.left.left = new TreeNode(20);
        bst.root.left.right = new TreeNode(40);

        System.out.println("Search for 40: " + bst.searchIterative(40)); // Output: true
        System.out.println("Search for 90: " + bst.searchIterative(90)); // Output: false
    }
}
