package A6LinkedList_In_Place_Traversal;

// Problem Statement: Reverse a Sub-list
// LeetCode Question: 92. Reverse Linked List II

/**
 * To reverse a specific portion (sublist) of a linked list in Java efficiently, we adapt the iterative reversal approach. This method requires careful handling of the nodes immediately before the sublist starts and immediately after it ends.
 * Algorithm Steps
 * Locate the start: Find the node before the sublist begins (nodeBeforeSublist).
 * Mark the tail: Identify the first node of the sublist, which will become the tail after reversal (sublistTail).
 * Reverse the segment: Use the three-pointer iterative method to reverse the nodes within the specified range (from left to right).
 * Connect the ends: Reconnect the reversed sublist to the main list:
 * nodeBeforeSublist must point to the new head of the reversed segment.
 * sublistTail must point to the node immediately after the original sublist (nextNodeAfterSublist).
 */
public class Problem_2_ReverseASubList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * Reverses the nodes of a linked list from index 'left' to 'right'.
     * <p>
     * Note: 'left' and 'right' are 1-based indices.
     *
     * @param head  The head of the main linked list.
     * @param left  The starting index (1-based).
     * @param right The ending index (1-based).
     * @return The head of the modified linked list.
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // Use a dummy node to handle the case where the reversal starts at the head of the list
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 1. Move 'nodeBeforeSublist' to the node immediately before the 'left' index
        ListNode nodeBeforeSublist = dummy;
        for (int i = 0; i < left - 1; i++) {
            nodeBeforeSublist = nodeBeforeSublist.next;
        }

        // 'sublistTail' is the node at the 'left' position (which will be the tail after reversal)
        ListNode sublistTail = nodeBeforeSublist.next;

        // 2. Perform the reversal of the segment iteratively
        // The pointers 'prev' and 'curr' will reverse the nodes between 'left' and 'right'
        ListNode prev = nodeBeforeSublist.next; // Start 'prev' at the first node to be reversed
        ListNode curr = prev.next;

        // We iterate 'right - left' times to reverse that many links
        for (int i = 0; i < right - left; i++) {
            ListNode nextTemp = curr.next; // Save the next node
            curr.next = prev;              // Reverse the current node's pointer
            prev = curr;                   // Move 'prev' forward
            curr = nextTemp;               // Move 'curr' forward
        }

        // 3. Reconnect the reversed segment to the rest of the list

        // The node before the sublist must point to the *new* head of the reversed segment ('prev')
        nodeBeforeSublist.next = prev;

        // The 'sublistTail' (the original 'left' node) must point to the node after the 'right' position ('curr')
        sublistTail.next = curr;

        return dummy.next; // Return the head of the modified list
    }


    public static void main(String[] args) {
        // List: 1 -> 2 -> 3 -> 4 -> 5 -> null
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // Reverse sublist from index 2 to 4 (values 2, 3, 4)
        // Result expected: 1 -> 4 -> 3 -> 2 -> 5 -> null
        ListNode newHead = Problem_2_ReverseASubList.reverseBetween(head, 2, 4);

        // Print the resulting list
        ListNode current = newHead;
        while (current != null) {
            System.out.print(current.val + (current.next != null ? " -> " : " -> null"));
            current = current.next;
        }
    }
}




