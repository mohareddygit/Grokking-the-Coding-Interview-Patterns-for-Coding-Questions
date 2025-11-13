package A6LinkedList_In_Place_Traversal;

// Problem Statement: Merge K Sorted Lists (medium)
// LeetCode Question: 23. Merge k Sorted Lists

import java.util.PriorityQueue;

/**merge 𝑘 sorted linked lists into a single sorted list is a classic problem (LeetCode 23). The most efficient approach uses a Min-Heap (Priority Queue) to manage the smallest available node from all lists simultaneously.
 Algorithm: Using a Min-Heap
 The overall time complexity is O(N log K), where N is the total number of nodes across all lists, and K is the number of lists.
 Initialize Min-Heap: Create a min-heap that stores ListNode objects and prioritizes the node with the smallest value (val).
 Populate Heap: Add the head node of every non-empty list into the min-heap.
 Merge and Build: While the heap is not empty:
 Extract: Remove the node with the smallest value from the heap (this will be the next node in the merged list).
 Append: Append this extracted node to the tail of your resulting merged list.
 Advance: If the extracted node has a next node, add that next node into the heap.
 Return: The head of the newly merged list is the answer.
 */
public class LLKMer_1_Merge_K_Sorted_Lists_B43 {

    /**
     * Definition for singly-linked list.
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public class MergeKSortedLists {

        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }

            // 1. Initialize a Min-Heap (PriorityQueue) for ListNode objects
            // The comparator ensures the node with the SMALLEST value is at the top
            PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
                    (a, b) -> a.val - b.val
            );

            // 2. Add the head of each list to the heap
            for (ListNode node : lists) {
                if (node != null) {
                    minHeap.add(node);
                }
            }

            // Dummy head for the merged list; helps manage the head pointer easily
            ListNode dummyHead = new ListNode(0);
            ListNode currentTail = dummyHead;

            // 3. Merge and Build the new list
            while (!minHeap.isEmpty()) {
                // Extract the smallest node currently available across all lists
                ListNode smallest = minHeap.poll();

                // Append it to the merged list's tail
                currentTail.next = smallest;
                currentTail = currentTail.next;

                // If the extracted node has a next element in its original list,
                // add that next element to the heap for consideration
                if (smallest.next != null) {
                    minHeap.add(smallest.next);
                }
            }

            // 4. Return the head of the merged list (skipping the dummy node)
            return dummyHead.next;
        }
    }
}
