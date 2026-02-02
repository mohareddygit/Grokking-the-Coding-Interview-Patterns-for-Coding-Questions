
LeetCode 23. Merge k Sorted Lists, the most efficient approach is using a **Min-Heap (PriorityQueue)**. This allows you to always pick the smallest available node among all 𝑘 lists in 𝑂(log𝑘) time.

**The Strategy**

1.  **Initialize**: Create a Min-Heap and add the **head node** of each of the 𝑘 linked lists into it.
2.  **Extract & Append**:
    -   Poll the smallest node from the heap and attach it to your "Result" list.
    -   If the polled node has a `next` node, push that `next` node into the heap.
3.  **Repeat**: Continue until the heap is empty.


https://youtube.com/shorts/q7y8HJObWmU?si=e5clpJkvqLNDs7fN

<img width="686" height="295" alt="image" src="https://github.com/user-attachments/assets/66de7794-b936-4363-86fb-737d9ca35013" />


```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Min-Heap based on node values
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the first node of each list to the heap
        for (ListNode list : lists) {
            if (list != null) pq.offer(list);
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!pq.isEmpty()) {
            ListNode smallest = pq.poll();
            curr.next = smallest;
            curr = curr.next;

            // If there's a next node in the same list, add it to the heap
            if (smallest.next != null) {
                pq.offer(smallest.next);
            }
        }

        return dummy.next;
    }
}

```

**Complexity Analysis**

-   **Time Complexity**:**𝑂(𝑁log𝑘)**, where  𝑁
    
    is the total number of nodes across all lists and 𝑘
    
    is the number of lists. Each node is added to and removed from the heap once.
-   **Space Complexity**:**𝑂(𝑘)** for the PriorityQueue to store one node from each list at a time.

**Pro-Tip: Why use this over "Divide and Conquer"?**

While the Divide and Conquer approach also yields 𝑂(𝑁log𝑘)

time, the **Min-Heap** method is often preferred in interviews because:

1.  It is **easier to implement** under pressure.
2.  It uses **less space** than the recursive stack of Divide and Conquer.
3.  It is highly **scalable** for streaming data where you don't have all lists upfront.

**Follow-up Challenge**: How would you handle this if the number of lists 𝑘

was so large that the heads of all lists couldn't fit in memory? (Hint: Use an **External Merge Sort** pattern).
