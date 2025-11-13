package B9Greedy_Algorithm;

import java.util.TreeMap;

/**
 * 846. Hand of Straights
 * Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.
 *
 * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 * Example 2:
 *
 * Input: hand = [1,2,3,4,5], groupSize = 4
 * Output: false
 * Explanation: Alice's hand can not be rearranged into groups of 4.
 *
 *
 *
 * Constraints:
 *
 * 1 <= hand.length <= 104
 * 0 <= hand[i] <= 109
 * 1 <= groupSize <= hand.length
 *
 *
 * Note: This question is the same as 1296: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 */
public class GA_HandOfStraights_BX {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        TreeMap<Integer, Integer> count = new TreeMap<>();

        // Count frequency of each card
        for (int card : hand) {
            count.put(card, count.getOrDefault(card, 0) + 1);
        }

        // Try to build groups starting from the smallest card
        for (int card : count.keySet()) {
            int freq = count.get(card);
            if (freq > 0) {
                // Try to build groupSize consecutive cards
                for (int i = 1; i < groupSize; i++) {
                    int nextCard = card + i;
                    if (count.getOrDefault(nextCard, 0) < freq) {
                        return false;
                    }
                    count.put(nextCard, count.get(nextCard) - freq);
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        GA_HandOfStraights_BX solver = new GA_HandOfStraights_BX();
        int[] hand = {1,2,3,6,2,3,4,7,8};
        int groupSize = 3;
        System.out.println("Can form groups? " + solver.isNStraightHand(hand, groupSize)); // Output: true
    }
}
