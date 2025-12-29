package A0CompFAQ;

/**
 * Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */

/**
Leetcode 3043. Find the Length of the Longest Common Prefix
use a HashSet to store all possible prefixes of numbers from the first array.
This allows for O(1) average-time lookups when checking prefixes against the second array.
Java Implementation (HashSet Approach)


 */

/**
 * Logic Prefix Generation: In Java,
 * a prefix of an integer can be found by repeatedly dividing by 10 (e.g., prefixes of 123 are 123, 12, and 1).
 * Search Optimization: By iterating from the full number down to its smallest prefix,
 * the first match found in the HashSet for a number in arr2 is guaranteed to be its longest common prefix.
 * Alternative (Trie): You can also solve this using a Trie (Prefix Tree),
 * but it is significantly more complex to implement in an interview and offers similar time complexity.
 * Complexity Analysis Time Complexity: O(N * log₁₀M + K * log₁₀M),
 * where \(N\) and \(K\) are the lengths of arr1 and arr2, and \(M\) is the maximum value.
 * Each number has at most \(\approx 9\) digits (log₁₀M), making prefix extraction very fast.
 * Space Complexity: O(N * log₁₀M) to store the prefixes in the HashSet.
 * Edge Cases No Common Prefix: If no common digits exist, the loop finishes and returns 0.
 * Single Digit Numbers: Handled correctly by the val > 0 condition.
 */

/**
 * Definition for singly-linked list.
 */
class ListNode {
      int val;
     ListNode next;
      ListNode(int x) { val = x; }
  }
class AddTwoNumbers{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0); //
        ListNode curr = dummyHead;
        int carry = 0;
        
        // Loop while any list has nodes or there's a leftover carry
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0; // If list ends, treat as 0
            int y = (l2 != null) ? l2.val : 0;
            
            int sum = x + y + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        
        return dummyHead.next; //
    }
}
