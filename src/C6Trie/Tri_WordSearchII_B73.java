package C6Trie;

import java.util.*;

/**
 * Leetcode 212: Word Search II is solved using a combination of Trie (prefix tree) and DFS backtracking to efficiently find all words from a given list that can be formed on a 2D board.
 */
public class Tri_WordSearchII_B73 {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null; // Store word at end node
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        // Step 1: Build Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                node = node.children.computeIfAbsent(ch, k -> new TrieNode());
            }
            node.word = word; // Mark end of word
        }

        // Step 2: DFS from each cell
        int rows = board.length, cols = board[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(board, r, c, root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int r, int c, TrieNode node, List<String> result) {
        char ch = board[r][c];
        if (ch == '#' || !node.children.containsKey(ch)) return;

        TrieNode nextNode = node.children.get(ch);
        if (nextNode.word != null) {
            result.add(nextNode.word);
            nextNode.word = null; // Avoid duplicates
        }

        board[r][c] = '#'; // Mark visited

        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i];
            if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length) {
                dfs(board, nr, nc, nextNode, result);
            }
        }

        board[r][c] = ch; // Restore cell
    }
}
