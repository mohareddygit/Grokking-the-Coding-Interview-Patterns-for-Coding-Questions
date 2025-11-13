package B2Tree_Breadth_First_Search;

import java.util.*;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 */
public class BuildTreeFromTraversals_B67 {
    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return build(preorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) return null;

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int inRootIndex = inorderIndexMap.get(rootVal);
        int leftSize = inRootIndex - inStart;

        root.left = build(preorder, preStart + 1, preStart + leftSize,
                          inStart, inRootIndex - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                           inRootIndex + 1, inEnd);

        return root;
    }

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
