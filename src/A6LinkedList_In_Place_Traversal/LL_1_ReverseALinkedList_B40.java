package A6LinkedList_In_Place_Traversal;

// Problem Statement: Reverse a LinkedList (easy)
// LeetCode Question: 206. Reverse Linked List

public class LL_1_ReverseALinkedList_B40 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverseIterative(ListNode head) {
        // 'prev' will eventually be the new head of the reversed list
        ListNode prev = null;
        // 'current' starts at the head of the original list
        ListNode current = head;

        // Iterate through the list until the end is reached
        while (current != null) {
            // 1. Save the next node before overwriting the 'next' pointer
            ListNode nextTemp = current.next;

            // 2. Reverse the current node's 'next' pointer to point to 'prev'
            current.next = prev;

            // 3. Move 'prev' pointer forward to the current node
            prev = current;

            // 4. Move 'current' pointer forward to the saved next node
            current = nextTemp;
        }

        // 'prev' is the last node of the original list, now the head of the reversed list
        return prev;
    }
}

