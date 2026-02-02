
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


[LeetCode 239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/solution/) uses a **monotonic deque** (double-ended queue) to maintain potential maximum elements in linear time. The standard **Max-Heap** approach is also valid but slightly slower.

https://youtube.com/shorts/OYXdNh70sUE?si=A-mekIKdLmSs4udF 

**Optimal Solution: Monotonic Deque (𝑂(𝑁))**

The deque stores **indices** of elements, ordered by their values in **descending** order from front to back.

-   **Logic:** When adding a new number, any smaller numbers currently in the deque are useless (they can never be the maximum again) and are removed from the back. The front of the deque is always the maximum for the current window.
-   **Efficiency:** Each element is added to and removed from the deque at most once, making the overall time complexity 𝑂(𝑁)
    
    In the context of the

**Sliding Window Maximum** problem, using a `Deque` to store **indices** (rather than the actual numbers) is the "secret sauce" that makes the 𝑂(𝑁)
solution work.

**1. Why store Indices instead of Values?**

Storing the index (e.g., `i = 5`) allows you to perform **two critical checks** that values alone cannot provide:

-   **Expiration Check:** You can tell if an element has "fallen out" of the sliding window. If the current index is `i` and the window size is `k`, any index in your deque that is **less than or equal to `i - k`** is old news and must be removed.
-   **Value Access:** You can still get the value whenever you need it by calling `nums[dq.peekFirst()]`.

**2. The "Monotonic" Property**

The `Deque` (Double-Ended Queue) is used here as a **Monotonic Queue**. This means we manually keep the indices in the deque sorted based on their values in `nums`:

-   **Front of Deque:** The index of the **largest** value in the current window.
-   **Back of Deque:** The index of the **smallest** value currently being considered.

**3. How the Operations Work**

The `Deque` is special because you can add/remove from **both ends**:

-   **From the Back (`pollLast`):** Before adding a new index `i`, you look at the back of the deque. If `nums[i]` is bigger than the values at those indices, those old indices are "useless." They will never be the maximum because your new number is bigger and will last longer in the window. You pop them off the back.
-   **From the Front (`pollFirst`):** You check the front index. If it’s too old (index `< i - k + 1`), you pop it off.
-   **Result:** After these two clean-ups, the index at the **very front** is guaranteed to be the maximum for your current window.

**4. Visual Example**

`nums = [1, 3, -1], k = 3, i = 2` (Window is `[1, 3, -1]`)

1.  **Process 1:** Deque: `[0]` (value 1)
2.  **Process 3:** 3 is bigger than 1. Pop `0`. Deque: `[1]` (value 3)
3.  **Process -1:** -1 is smaller than 3. Deque: `[1, 2]` (values 3, -1)
4.  **Result:** `nums[dq.peekFirst()]` is `nums[1]`, which is **3**.

**Interview Tip:** When explaining this to a **FAANG** interviewer, emphasize that this is a **Monotonic Deque** pattern. It's a favorite for "range" problems.

```java
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // Use ArrayDeque instead of LinkedList for 2x-3x faster performance in Java
        Deque<Integer> dq = new ArrayDeque<>(); 
        int n = nums.length;
        int[] ans = new int[n - k + 1];

        for (int i = 0; i < n; i++) {
            // 1. Remove index that just slid out of the window 
            // If the oldest index is exactly i - k, it's no longer in range
            if (!dq.isEmpty() && dq.peekFirst() == i - k) {
                dq.pollFirst();
            }

            // 2. Maintain Monotonic property: Remove indices of smaller elements
            // because they can never be the maximum again
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }

            // 3. Add current index to the back
            dq.offerLast(i);

            // 4. Record the current maximum once the first window is formed
            int start = i - k + 1;
            if (start >= 0) {
                ans[start] = nums[dq.peekFirst()];
            }
        }
        
        return ans;
    }
}

```

**Why this version is "Interview Ready":**

-   **Performance:**  I swapped  `LinkedList`  for  ArrayDeque. In Java,  `LinkedList`  allocates a new node object for every entry, while  `ArrayDeque`  uses a resizable array, making it significantly more memory-efficient and faster for sliding windows.
-   **Readability:**  Using  `int start = i - k + 1`  directly as your array index is a common pattern in  Fintech and FAANG technical interviews. It proves you can calculate bounds mathematically rather than relying on side-effect variables like  `idx++`.
-   **Logic:**  The condition  `dq.peekFirst() == i - k`  is the most succinct way to handle the "sliding out" of the window since we process indices sequentially.

**Next Step:**  You could apply this  **Monotonic Deque**  logic to solve  **1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit**.
