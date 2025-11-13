package B3Tree_Depth_First_Search;

// Problem Statement: Path With Given Sequence (medium)
// LeetCode Question: -

/**
 * The problem "
 * Path With Given Sequence" asks you to check if a specific sequence of numbers exists as a root-to-leaf path within a binary tree. This is essentially a restricted form of DFS or path finding.
 * Algorithm: Depth-First Search with Sequence Tracking
 * We can use recursion (DFS) to explore paths, keeping track of the current index in the sequence we are trying to match.
 * Base Case: If we reach a leaf node and we are at the last element of the sequence, we check if the leaf node's value matches the final element in the sequence.
 * Matching Check: At every node, we first verify if the current node's value matches the element in the sequence at the current sequenceIndex. If they don't match, this path is invalid, and we immediately return false.
 * Recursion: We increment the sequenceIndex and recursively search for the remaining sequence in the left or right subtrees.
 * Termination: The path is valid only if both the value matches and a valid path is found in the subsequent recursion down to a leaf node.
 */
public class Problem_4_Path_With_Given_Sequence {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int value) {
            int val = value;
        }
    }
    public boolean hasPathWithSequence(TreeNode root, int[] sequence) {
        if (root == null) {
            // An empty tree can only match an empty sequence
            return sequence.length == 0;
        }

        // Start the recursive check from the root with the first element of the sequence
        return dfs(root, sequence, 0);
    }

    private boolean dfs(TreeNode node, int[] sequence, int index) {

        // If we've run out of tree nodes before matching the entire sequence
        if (node == null) {
            return false;
        }

        // 1. Check if the current node's value matches the required value in the sequence
        if (index >= sequence.length || node.val != sequence[index]) {
            return false;
        }

        // 2. Check if we've successfully reached a leaf node and matched the *entire* sequence
        // This is a valid root-to-leaf path match.
        if (node.left == null && node.right == null && index == sequence.length - 1) {
            return true;
        }

        // 3. Recurse into children: Increment the sequence index for the next level
        // A path exists if it is found in the left *OR* the right subtree
        boolean foundInLeft = dfs(node.left, sequence, index + 1);
        boolean foundInRight = dfs(node.right, sequence, index + 1);

        return foundInLeft || foundInRight;
    }


}
