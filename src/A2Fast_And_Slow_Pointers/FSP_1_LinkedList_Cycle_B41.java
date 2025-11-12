package A2Fast_And_Slow_Pointers;

// Problem Statement: LinkedList Cycle (easy)
// LeetCode Question: 141. Linked List Cycle


/**
 * This algorithm uses two pointers that traverse the list at different speeds.
 * Floyd's Tortoise and Hare Algorithm
 * Key Idea:
 * If a cycle exists in the linked list, a fast-moving pointer will eventually "lap" a slow-moving pointer, meaning they will both point to the same node within the cycle. If there is no cycle, the fast pointer will reach the end of the list (a null pointer).
 * Algorithm Steps:
 * Initialize two pointers, slow and fast, both starting at the head of the list.
 * Iterate: Move the slow pointer by one node at a time (slow = slow.next), and the fast pointer by two nodes at a time (fast = fast.next.next).
 * Check for Meeting Point: In each iteration, check if slow and fast pointers refer to the same node. If they do, a cycle is present, and you return true.
 * Check for End of List: The loop continues as long as the fast pointer (and its next pointer) has not reached null. If either becomes null, it means the end of the list has been reached without a meeting, so no cycle exists, and you return false.
 */
public class FSP_1_LinkedList_Cycle_B41 {
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
     * Detects if a cycle exists in the linked list using Floyd's algorithm.
     *
     * @param head The head node of the linked list.
     * @return true if a cycle is found, false otherwise.
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false; // Cannot have a cycle with 0 or 1 node
        }

        ListNode slow = head;
        ListNode fast = head;

        // Iterate until the fast pointer reaches the end of the list
        while (fast != null && fast.next != null) {
            slow = slow.next;      // Move slow pointer one step
            fast = fast.next.next; // Move fast pointer two steps

            // If the pointers meet, a cycle is detected
            if (slow == fast) {
                return true;
            }
        }

        return false; // Loop finished, fast pointer reached null, no cycle
    }

    public static void main(String[] args) {
        // Example 1: Linked list with a cycle
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // Creates a cycle: -4 points back to 2

        System.out.println("List 1 has a cycle: " + hasCycle(node1)); // Output: true

        // Example 2: Linked list without a cycle
        ListNode headA = new ListNode(1);
        ListNode headB = new ListNode(2);
        headA.next = headB;
        headB.next = null; // No cycle

        System.out.println("List 2 has a cycle: " + hasCycle(headA)); // Output: false
    }
}

/**
 * Alternative Method: Using a HashSet
 * Another method involves using a HashSet to store each visited node's reference (memory address) as you traverse the list. Before adding a node, you check if it's already in the set. If it is, a cycle exists. This approach has O(n) time complexity but requires O(n) space for the HashSet.
 */

//What are some use cases for linked lists with cycles?
