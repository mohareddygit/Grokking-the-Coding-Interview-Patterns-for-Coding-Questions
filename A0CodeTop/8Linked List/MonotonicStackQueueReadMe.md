
LeetCode 23. Merge k Sorted Lists, the most efficient approach is using a **Min-Heap (PriorityQueue)**. This allows you to always pick the smallest available node among all 𝑘 lists in 𝑂(log𝑘) time.

**The Strategy**

1.  **Initialize**: Create a Min-Heap and add the **head node** of each of the 𝑘 linked lists into it.
2.  **Extract & Append**:
    -   Poll the smallest node from the heap and attach it to your "Result" list.
    -   If the polled node has a `next` node, push that `next` node into the heap.
3.  **Repeat**: Continue until the heap is empty.


https://youtube.com/shorts/q7y8HJObWmU?si=e5clpJkvqLNDs7fN

<img width="753" height="174" alt="image" src="https://github.com/user-attachments/assets/df1be118-e759-457f-9239-698a47c2ec41" />



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


<img width="521" height="282" alt="image" src="https://github.com/user-attachments/assets/3d330c69-8db7-4464-9c33-74fcefcd25d6" />


**Strategic Insight**

-   **Container With Most Water:** You are looking for a **single maximum area**. By starting at the extreme left and right, you maximize the width (`R - L`). You then try to find taller walls to compensate for the decreasing width as you move inward.
-   **Trapping Rain Water:** You are summing up **individual units of water**. Each index `i` traps water based on the **lowest of the two tallest walls** surrounding it. If `h[i]` is taller than those walls, it traps zero water at that specific point.

**11. Container With Most Water**



```java
public int maxArea(int[] height) {
    int left = 0, right = height.length - 1;
    int maxArea = 0;

    while (left < right) {
        // Calculate area based on the shorter of the two walls
        int currentHeight = Math.min(height[left], height[right]);
        int width = right - left;
        maxArea = Math.max(maxArea, currentHeight * width);

        // Move the pointer that is "limiting" the height
        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }
    return maxArea;
}

```

**42. Trapping Rain Water**



```java
public int trap(int[] height) {
    int left = 0, right = height.length - 1;
    int leftMax = 0, rightMax = 0;
    int totalWater = 0;

    while (left < right) {
        if (height[left] < height[right]) {
            // Left side is the bottleneck
            if (height[left] >= leftMax) {
                leftMax = height[left]; // Update new peak
            } else {
                totalWater += leftMax - height[left]; // Trap water
            }
            left++;
        } else {
            // Right side is the bottleneck
            if (height[right] >= rightMax) {
                rightMax = height[right]; // Update new peak
            } else {
                totalWater += rightMax - height[right]; // Trap water
            }
            right--;
        }
    }
    return totalWater;
}

```

The **Container** problem uses pointers to find the **best pair**, while the **Trapping** problem uses pointers to maintain **global peaks** and calculate water at every single index. Both run in 𝑂(𝑛) time with 𝑂(1) space.

Would you like to explore how to solve Trapping Rain Water using a Monotonic Stack instead of Two Pointers?

**LeetCode 42: Trapping Rain Water** using a **Monotonic Stack**, you simulate the "layer-by-layer" filling of valleys rather than calculating water column by column.

**The Strategy: Monotonic Decreasing Stack**

1.  **Maintain Order**: Keep a stack of indices where heights are in **decreasing** order.
2.  **Find a Right Boundary**: When the current bar is taller than the bar at the stack's top, you've found a "right wall".
3.  **Process the "Valley"**:
    -   **Bottom**: Pop the top of the stack; this is the floor of your current water layer.
    -   **Left Boundary**: The new top of the stack is the left wall.
    -   **Calculate Water**: The trapped water for this layer is:  
        `Width * (min(LeftWallHeight, RightWallHeight) - BottomHeight)`.

[Uploading image.png…]()
![Uploading image.png…]()

https://youtu.be/iA-PK_hwzKc?si=kYDRqvLI8hwNY1kU

**Java Implementation**

Using `Deque` as a stack is the modern, high-performance standard for Java interviews.


```java
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int totalWater = 0;
        
        for (int i = 0; i < height.length; i++) {
            // While current bar is taller than the bar at stack top
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int bottomIdx = stack.pop(); // The floor of the valley
                
                if (stack.isEmpty()) break; // No left wall to trap water
                
                int leftWallIdx = stack.peek();
                int rightWallIdx = i;
                
                // Calculate horizontal layer of water
                int width = rightWallIdx - leftWallIdx - 1;
                int boundedHeight = Math.min(height[leftWallIdx], height[rightWallIdx]) - height[bottomIdx];
                
                totalWater += width * boundedHeight;
            }
            stack.push(i);
        }
        return totalWater;
    }
}

```

**Complexity Analysis**

-   **Time**:**𝑂(𝑛)**  Each index is pushed and popped exactly once.
-   **Space**:**𝑂(𝑛)** for the stack in the worst-case (a strictly decreasing elevation map).

**Why use this over Two Pointers?**

While the [Two-Pointer technique](https://neetcode.io/solutions/trapping-rain-water) uses less space (𝑂(1)), the **Monotonic Stack** is often preferred if the interviewer asks you to **visualize the process** or handle more complex versions like **84. Largest Rectangle in Histogram**, which uses a nearly identical stack pattern

**LeetCode 84. Largest Rectangle in Histogram**, we use a  **Monotonic Increasing Stack**.

Just like in Trapping Rain Water, the stack helps us identify "boundaries." However, instead of finding a valley to fill with water, we are looking for the  **width**  of a rectangle that can be formed using a specific bar as the  **minimum height**.

**The Strategy: Monotonic Increasing Stack**

1.  **Maintain Order**: Keep a stack of indices where heights are in  **increasing**  order.
2.  **Find a Right Boundary**: When the current bar is  **shorter**  than the bar at the stack's top, it means the rectangle for the top bar cannot extend any further to the right.
3.  **Calculate the Area**:
    -   **Height**: Pop the top of the stack; this bar is the "bottleneck" height for the rectangle we are currently calculating.
    -   **Right Boundary**: The current index  `i`.
    -   **Left Boundary**: The new top of the stack (the first bar to the left that is shorter than our popped bar).
    -   **Width**:  `(Right Boundary - Left Boundary - 1)`.

```java 
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            // Use 0 height at the end (i == n) to force-empty the stack
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                // If stack is empty, it means this bar was the smallest seen so far
                // so the width extends all the way to the start
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }
        return maxArea;
    }
}

```

**Comparison: Trapping Water vs. Histogram**

-   **Order**: Trapping Water uses a  **decreasing**  stack (finding a hole); Histogram uses an  **increasing**  stack (finding a bottleneck).
-   **Trigger**: In Trapping Water, a  **taller**  bar triggers the calculation. In Histogram, a  **shorter**  bar triggers the calculation.
-   **Result**: Water is calculated  **horizontally**  (layer-by-layer); Histogram area is calculated  **vertically**  (using the popped bar as the height).

**FAANG Interview Tip**

If you master this, you can solve  **85. Maximal Rectangle**  by treating each row of a 2D matrix as a histogram and calling this exact function.

Would you like to see how to extend this logic to  **2D matrices**  (Maximal Rectangle)?

**85. Maximal Rectangle**, you apply the **Largest Rectangle in Histogram** logic to every row of the 2D matrix.

**The Strategy: Histogram Row-by-Row**

1.  **Row as Base**: Treat each row as the "ground" for a histogram.
2.  **Update Heights**: For each cell, if `matrix[row][col] == '1'`, the histogram height at that column increases by 1. If it's `'0'`, the height resets to 0 (since the rectangle must be contiguous).
3.  **Calculate Area**: After updating the heights for a row, run the **Monotonic Stack** algorithm from LeetCode 84 on that row's height array.

**Java Implementation (𝑂(𝑅×𝐶))**

This solution reuses the histogram logic to achieve optimal time complexity.

```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Update heights: if '1', add to previous height; if '0', reset
                heights[c] = (matrix[r][c] == '1') ? heights[c] + 1 : 0;
            }
            // Use the Monotonic Stack logic to find the max area for this row's histogram
            maxArea = Math.max(maxArea, calculateHistogramArea(heights));
        }
        return maxArea;
    }

    private int calculateHistogramArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int curH = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && curH < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }
        return max;
    }
}

```

**Complexity Analysis**

-   **Time Complexity**:**𝑂(𝑅×𝐶)**. We visit every cell in the matrix once and perform an 𝑂(𝐶) histogram calculation for each row.
-   **Space Complexity**:**𝑂(𝐶)**. We only need an array and a stack to store the heights and indices of one row.

**FAANG Interview Tip**

This problem is a classic example of **problem reduction**. By proving you can reduce a complex 2D problem into a 1D problem you've already solved, you demonstrate high-level architectural thinking required for **Staff Engineer** roles. For more details on the monotonic stack pattern, check out the NeetCode explanation.

Would you like to try another 2D matrix problem, such as **221. Maximal Square**, which uses **Dynamic Programming** instead of stacks?
