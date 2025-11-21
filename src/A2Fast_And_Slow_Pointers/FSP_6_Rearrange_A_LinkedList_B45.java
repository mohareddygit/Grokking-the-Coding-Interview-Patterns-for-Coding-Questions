package A2Fast_And_Slow_Pointers;

// Problem Statement: Rearrange a LinkedList (medium)
// LeetCode Question: 143. Reorder List is a linked list manipulation problem that
// combines finding the middle, reversing the second half, and merging two lists.

public class FSP_6_Rearrange_A_LinkedList_B45 {
    class ListNode{
        int val = 0;
        ListNode next;
        ListNode(int value){
            this.val = value;
        }
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // Step 1: Find middle using slow/fast pointers
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        ListNode second = reverse(slow.next);
        slow.next = null; // cut the list into two halves

        // Step 3: Merge two halves
        ListNode first = head;
        while (second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;

            first.next = second;
            second.next = tmp1;

            first = tmp1;
            second = tmp2;
        }
    }

    // Helper function to reverse a linked list
    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
