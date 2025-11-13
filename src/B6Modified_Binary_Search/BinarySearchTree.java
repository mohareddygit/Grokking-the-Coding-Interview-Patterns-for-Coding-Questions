package B6Modified_Binary_Search;

/**
 * A
 * Binary Search Tree (BST) is a binary tree data structure where each node has a comparable key (value), and the following conditions are met for every node:
 * The key in a node is greater than all keys in the left subtree.
 * The key in a node is less than all keys in the right subtree.
 * Both the left and right subtrees must also be binary search trees.
 * This structure allows for efficient searching, insertion, and deletion operations, typically averaging O(log n) time complexity for balanced trees.
 */
public class BinarySearchTree {

    // Node class definition
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    // --- Insertion Method ---
    public void insert(int val) {
        root = insertRecursive(root, val);
    }

    private TreeNode insertRecursive(TreeNode root, int val) {
        // Base case: If the tree/subtree is empty, create a new node
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }

        // Recurse down the tree
        if (val < root.val) {
            root.left = insertRecursive(root.left, val);
        } else if (val > root.val) {
            root.right = insertRecursive(root.right, val);
        }
        // If val is equal to root.val, we typically do nothing (assuming unique values)

        return root;
    }

    // --- Search Method ---
    public boolean search(int val) {
        return searchRecursive(root, val);
    }

    private boolean searchRecursive(TreeNode root, int val) {
        // Base Cases:
        // 1. If the node is null, the value is not in the tree
        if (root == null) {
            return false;
        }
        // 2. If the value matches the current node's value
        if (root.val == val) {
            return true;
        }

        // Recurse based on the BST property
        if (val < root.val) {
            return searchRecursive(root.left, val);
        } else {
            return searchRecursive(root.right, val);
        }
    }

    // --- In-order Traversal (Prints elements in sorted order) ---
    public void inOrderTraversal() {
        inOrderRecursive(root);
        System.out.println();
    }

    private void inOrderRecursive(TreeNode root) {
        if (root != null) {
            inOrderRecursive(root.left);
            System.out.print(root.val + " ");
            inOrderRecursive(root.right);
        }
    }

    // Example Usage
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Inserting elements
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        // Printing in-order traversal (should be sorted)
        System.out.print("In-order traversal: ");
        bst.inOrderTraversal(); // Output: 20 30 40 50 60 70 80 

        // Searching for elements
        System.out.println("Search for 40: " + bst.search(40)); // Output: true
        System.out.println("Search for 99: " + bst.search(99)); // Output: false
    }
}
