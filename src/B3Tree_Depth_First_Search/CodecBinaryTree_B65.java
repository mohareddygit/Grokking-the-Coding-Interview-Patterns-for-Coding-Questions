package B3Tree_Depth_First_Search;

import java.util.*;

/**
 * Leetcode 297: Serialize and Deserialize Binary Tree, using preorder traversal and a delimiter-based encoding scheme.
 *
 * 🧠 Problem Summary
 * You need to implement two methods:
 *
 * serialize(TreeNode root): Converts a binary tree into a string.
 *
 * deserialize(String data): Converts the string back into the original binary tree.
 *
 * Constraints:
 *
 * Tree may contain any integer values.
 *
 * You must preserve structure and nulls.
 */
public class CodecBinaryTree_B65 {
    private static final String NULL = "#";
    private static final String SEP = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL).append(SEP);
        } else {
            sb.append(node.val).append(SEP);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(SEP)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Queue<String> nodes) {
        String val = nodes.poll();
        if (val.equals(NULL)) return null;

        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = buildTree(nodes);
        node.right = buildTree(nodes);
        return node;
    }

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
