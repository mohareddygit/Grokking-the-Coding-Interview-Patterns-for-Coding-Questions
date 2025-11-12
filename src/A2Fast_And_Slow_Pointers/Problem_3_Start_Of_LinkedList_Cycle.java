package A2Fast_And_Slow_Pointers;

// Problem Statement: Start of LinkedList Cycle
// LeetCode Question: 142. Linked List Cycle II

/**
 * starting node of a linked list cycle in Java, a two-phase extension of the Floyd's Tortoise and Hare algorithm is used. This method provides an O(n) time and O(1) space solution.
 * Algorithm Explained (Two Phases)
 * Phase 1: Cycle Detection
 * Use the standard Tortoise and Hare algorithm to find a meeting point inside the cycle.
 * Initialize two pointers, slow and fast, both at the head.
 * Move slow one step and fast two steps at a time.
 * If they meet, a cycle exists. The meeting point is somewhere within the cycle.
 * Phase 2: Finding the Cycle Start Node
 * Once a meeting point is found, the mathematical property of the algorithm guarantees a specific way to find the cycle start.
 * Reset one of the pointers (usually slow) back to the head of the list.
 * Keep the other pointer (fast) at the meeting point they previously found.
 * Move both pointers at the same speed (one step at a time).
 * The node where they meet the second time is the start of the cycle.
 */
public class Problem_3_Start_Of_LinkedList_Cycle {
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
     * Finds the node where the cycle begins in the linked list.
     *
     * @param head The head node of the linked list.
     * @return The starting node of the cycle, or null if no cycle exists.
     */
    public static ListNode detectCycleStart(ListNode head) {
        if (head == null || head.next == null) {
            return null; // No cycle possible
        }

        ListNode slow = head;
        ListNode fast = head;
        boolean cycleDetected = false;

        // Phase 1: Detect the cycle and find the meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                cycleDetected = true;
                break; // Meeting point found
            }
        }

        // If no cycle was detected, return null
        if (!cycleDetected) {
            return null;
        }

        // Phase 2: Find the starting node of the cycle
        slow = head; // Reset slow pointer to the head

        while (slow != fast) {
            slow = slow.next; // Move both at the same speed (1 step)
            fast = fast.next;
        }

        // The node where they meet again is the start of the cycle
        return slow;
    }

    public static void main(String[] args) {
        // Example: Linked list with a cycle (3 -> 2 -> 0 -> -4, tail connects to 2)
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // Cycle starts at node with value 2

        ListNode cycleStart = detectCycleStart(node1);
        System.out.println("The cycle starts at node with value: " + (cycleStart != null ? cycleStart.val : "null")); // Output: 2
    }
}

