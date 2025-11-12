package A2Fast_And_Slow_Pointers;

// Problem Statement: Middle of the LinkedList
// LeetCode Question: 876. Middle of the Linked List

/**
 * "Tortoise and Hare" two-pointer approach. This is the same logic used for detecting cycles, but applied differently.
 * Tortoise and Hare Approach
 * Key Idea:
 * Use two pointers:
 * Slow Pointer (Tortoise): Moves one node at a time.
 * Fast Pointer (Hare): Moves two nodes at a time.
 * When the fast pointer reaches the end of the linked list (null), the slow pointer will be exactly halfway through the list, at the middle node.
 * Time Complexity: O(n), as both pointers traverse the list in a single pass.
 * Space Complexity: O(1), as only two pointers are used.
 */
public class Problem_2_Middle_Of_The_LinkedList {
    // Definition for a singly-linked list node.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * Finds the middle node of a linked list using the two-pointer approach.
     *
     * @param head The head node of the linked list.
     * @return The middle node of the list.
     */
    public static ListNode findMiddle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // The loop continues as long as the fast pointer can move two steps.
        // If the list has an odd number of nodes, fast.next will be null at the end.
        // If the list has an even number of nodes, fast.next.next will be null at the end.
        while (fast != null && fast.next != null) {
            slow = slow.next;      // Move slow pointer one step
            fast = fast.next.next; // Move fast pointer two steps
        }

        // When the fast pointer reaches the end, the slow pointer is at the middle.
        return slow;
    }

    public static void main(String[] args) {
        // Example 1: Odd number of nodes (1 -> 2 -> 3 -> 4 -> 5)
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        ListNode middle1 = findMiddle(head1);
        System.out.println("Middle of List 1: " + (middle1 != null ? middle1.val : "null")); // Output: 3

        // Example 2: Even number of nodes (1 -> 2 -> 3 -> 4 -> 5 -> 6)
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        head2.next.next.next.next.next = new ListNode(6);
        ListNode middle2 = findMiddle(head2);
        System.out.println("Middle of List 2: " + (middle2 != null ? middle2.val : "null")); // Output: 4 (The second middle node)
    }
}

