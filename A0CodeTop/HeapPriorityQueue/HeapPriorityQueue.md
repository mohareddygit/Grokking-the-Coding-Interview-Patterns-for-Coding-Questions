**LeetCode 215: Kth Largest Element in an Array** in 2026, you should focus on two primary approaches: the **Min-Heap** (standard) and **Quickselect** (most common in high-level interviews).

1. The Core Idea

Finding the 𝑘𝑡ℎ

largest element is a selection problem. While you could sort the entire array in 𝑂(𝑛log𝑛)

, you only need to track the top 𝑘

elements, which can be done more efficiently.

2. Approach A: Min-Heap (PriorityQueue)

-   **Mechanism:** Maintain a Min-Heap of size 𝑘
-   **Logic:** Iterate through the array. For each element, add it to the heap. If the heap size exceeds 𝑘

    , remove the smallest element (`poll`).
-   **Result:** After one pass, the heap contains the 𝑘

    largest elements of the array. The root of the heap (the smallest of the top 𝑘) is the  𝑘𝑡ℎ largest element.

Java Implementation (Min-Heap)

```java
import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Min-Heap to keep track of the k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove the smallest element
            }
        }
        
        return minHeap.peek();
    }
}

```

-   **Time Complexity:** 𝑂(𝑛log𝑘)
    — better than 𝑂(𝑛log𝑛) when 𝑘≪𝑛
-   **Space Complexity:** 𝑂(𝑘)  to store the heap.

----------

3. Approach B: Quickselect (Divide and Conquer)

This is the most "interview-correct" answer for 2026. It is based on the **QuickSort** partitioning logic.

-   **Mechanism:** Pick a "pivot" and partition the array so all elements larger than the pivot are on the left, and smaller ones are on the right.
-   **Logic:**
    1.  If the pivot's final index is exactly  𝑘−1

        , you've found the answer.
    2.  If the index is greater than  𝑘−1

        , search only the left side.
    3.  If it's smaller, search only the right side.
-   **Performance:** On average, this only explores one side of the partition, leading to linear time.

Java Implementation (Quickselect)

```java
import java.util.Random;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) return nums[left];

        // Random pivot to prevent O(n^2) worst case
        int pivotIndex = left + new Random().nextInt(right - left + 1);
        pivotIndex = partition(nums, left, right, pivotIndex);

        if (pivotIndex == k) return nums[k];
        return pivotIndex > k ? quickSelect(nums, left, pivotIndex - 1, k) 
                               : quickSelect(nums, pivotIndex + 1, right, k);
    }

    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i <= right; i++) {
            if (nums[i] > pivotValue) { // Sorting descending
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, right);
        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

```

-   **Time Complexity:** **Average 𝑂(𝑛)

    **, Worst case 𝑂(𝑛2)

    (rare with random pivot).
-   **Space Complexity:** 𝑂(1)


[LeetCode 373. Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/), use a  **Min-Heap**  (PriorityQueue) to efficiently explore pairs in increasing order of their sums.

**Core Logic**

The key insight is that since  `nums1`  and  `nums2`  are sorted, the smallest sum is always  `nums1[0] + nums2[0]`. For any pair  `(nums1[i], nums2[j])`, the next smallest candidate involving  `nums1[i]`  must be  `(nums1[i], nums2[j+1])`.

1.  **Initialize**: Push the first element of  `nums1`  paired with the first  `k`  elements (or all elements, if fewer than  `k`) of  `nums2`  into a min-heap.
2.  **Extract**: Pop the smallest sum from the heap and add it to the result list.
3.  **Expand**: For every popped pair  `(i, j)`, if  `j + 1`  is within bounds of  `nums2`, push the next pair  `(i, j + 1)`  into the heap.
4.  **Terminate**: Continue until you have collected  `k`  pairs or the heap is empty.

To solve  **LeetCode 373: Find K Pairs with Smallest Sums**, think of the possible pairs as forming a grid. Since the arrays are sorted, the sums increase as you move right or down in the grid.

**Example Scenario**

**Input:**  `nums1 = [1, 7, 11]`,  `nums2 = [2, 4, 6]`,  `k = 3`

**Step 1: Initialization**

We start by pairing the first few elements of  `nums1`  with the first element of  `nums2`. This covers the "smallest possible" starts for each row in our imaginary grid.

-   **Heap Initial State**:  `[(sum:3, i:0, j:0), (sum:9, i:1, j:0), (sum:13, i:2, j:0)]`
    -   _Note:  `i`  is the index for  `nums1`,  `j`  is the index for  `nums2`._

**Step 2: First Extraction (k=1)**

1.  **Poll**  the smallest sum:  `(3, 0, 0)`  → Pair is  **[1, 2]**.
2.  **Offer**  the next candidate from the same "row" (increment  `j`):  `(nums1[0], nums2[1])`  → Sum:  `1 + 4 = 5`.

-   **Current Heap**:  `[(5, 0, 1), (9, 1, 0), (13, 2, 0)]`
-   **Result**:  `[[1, 2]]`

**Step 3: Second Extraction (k=2)**

1.  **Poll**  the smallest sum:  `(5, 0, 1)`  → Pair is  **[1, 4]**.
2.  **Offer**  the next candidate from the same row:  `(nums1[0], nums2[2])`  → Sum:  `1 + 6 = 7`.

-   **Current Heap**:  `[(7, 0, 2), (9, 1, 0), (13, 2, 0)]`
-   **Result**:  `[[1, 2], [1, 4]]`

**Step 4: Third Extraction (k=3)**

1.  **Poll**  the smallest sum:  `(7, 0, 2)`  → Pair is  **[1, 6]**.
2.  **Offer**: No more elements in  `nums2`  for this row.

-   **Final Result**:  `[[1, 2], [1, 4], [1, 6]]`.

**Why this works**

-   **Efficiency**: You only ever keep at most  `k`  elements in the heap.
-   **Sorted Advantage**: By only exploring  `(i, j+1)`  after  `(i, j)`  is popped, you guarantee that you never miss a smaller sum.
-   **No Duplicates**: This "row-by-row" expansion ensures each pair  `(i, j)`  is added to the heap exactly once.

**Java Implementation**



```java
import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // Min-heap stores int[]: {sum, index_in_nums1, index_in_nums2}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        List<List<Integer>> result = new ArrayList<>();
        
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        // Step 1: Initial candidates - first element of each possible row
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            pq.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }

        // Step 2 & 3: Extract min and push next candidate from same row
        while (k-- > 0 && !pq.isEmpty()) {
            int[] current = pq.poll();
            int i = current[1];
            int j = current[2];
            
            result.add(Arrays.asList(nums1[i], nums2[j]));

            if (j + 1 < nums2.length) {
                pq.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
        }
        
        return result;
    }
}

```

**Complexity Analysis**

-   **Time Complexity**:  **O(k log k)**. We perform  `k`  poll and offer operations, and the heap size never exceeds  `k`.
-   **Space Complexity**:  **O(k)**  to store the heap elements.

    (ignoring recursion stack).

Which one to choose?

-   **Heap:** Choose this if the data is a stream (you don't have the whole array at once) or if 𝑘

    is very small.
-   **Quickselect:** Choose this in interviews to demonstrate a deep understanding of algorithm design and to achieve the best average time complexity.


LeetCode 703. Kth Largest Element in a Stream, use a **Min-Heap** of size 𝑘.

**The Strategy**

In a Min-Heap of size 𝑘

, the root (the smallest element in the heap) is actually the 𝑘𝑡ℎ

largest element of the entire set.

1.  **Maintain a Min-Heap**: Only keep the 𝑘 largest elements seen so far.
2.  **Add Operation**:
    -   Push the new value into the heap.
    -   If the heap size exceeds 𝑘, **poll** (remove) the smallest element.
3.  **Return**: The top of the heap (`peek()`) is always your answer.

```java
import java.util.PriorityQueue;

class KthLargest {
    private final PriorityQueue<Integer> minHeap;
    private final int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>(k);
        
        for (int num : nums) {
            add(num);
        }
    }
    
    public int add(int val) {
        minHeap.offer(val);
        
        // If heap exceeds size k, the smallest is no longer in the top k
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        
        return minHeap.peek();
    }
}

```

**Complexity Analysis**

-   **Initialization**:**𝑂(𝑁log𝑘**, where 𝑁 is the initial array size. Each of the 𝑁 elements is added to a heap of max size 𝑘
-   **Add Operation**:**𝑂(log𝑘)**. Adding a value and potentially removing the smallest takes logarithmic time relative to the heap size.
-   **Space**:**𝑂(𝑘)** to store the 𝑘 largest elements.

**Example Walkthrough**

**Input:** `k = 3`, `nums = [4, 5, 8, 2]`

1.  **Init**:
    -   Add 4, 5, 8 → Heap: `[4, 5, 8]`
    -   Add 2 → Heap: `[2, 4, 5, 8]` → Size > 3, Poll 2 → Heap: `[4, 5, 8]`
2.  **`add(3)`**:
    -   Push 3 → Heap: `[3, 4, 5, 8]` → Size > 3, Poll 3 → Heap: `[4, 5, 8]`
    -   **Return 4** (The 3rd largest).
3.  **`add(10)`**:
    -   Push 10 → Heap: `[4, 5, 8, 10]` → Size > 3, Poll 4 → Heap: `[5, 8, 10]`
    -   **Return 5** (The 3rd largest).
  

**LeetCode 1046: Last Stone Weight**

```java
class Solution {
    public int lastStoneWeight(int[] stones) {
        // Standard Max-Heap setup
        PriorityQueue<Integer> heavy = new PriorityQueue<>((a, b) -> b - a);

        for (int stone : stones) {
            heavy.offer(stone);
        }

        while (heavy.size() > 1) {
            // Because it's a Max-Heap, x is the largest and y is the second largest
            int x = heavy.poll();
            int y = heavy.poll();

            if (x != y) {
                heavy.offer(x - y);
            }
            // If x == y, both are destroyed, so we add nothing back
        }

        // Return the remaining stone weight, or 0 if the heap is empty
        return heavy.isEmpty() ? 0 : heavy.poll();
    }
}

```

While `PriorityQueue` approach is the **standard optimal solution** for technical interviews due to its balance of readability and efficiency (𝑂(𝑛log𝑛)), there is an even faster **Bucket Sort** method for cases with specific constraints.

**1. The standard winner: PriorityQueue (𝑂(𝑛log𝑛))**

In most interviews, the Max-Heap solution is preferred because it handles any stone weight range efficiently.

-   **Time Complexity:**𝑂(𝑛log𝑛)** to process 𝑛 stones, with each heap operation taking  log𝑛
-   **Space Complexity:**𝑂(𝑛)** to store the stones in the queue.

**2. The ultra-fast alternative: Bucket Sort (𝑂(𝑛+𝑊))**

Since the LeetCode constraints state the maximum stone weight is small (𝑊≤1000), you can use **Bucket Sort** (frequency array) to achieve linear time relative to the weight range.

**How it works:**

1.  Create an array `buckets` of size 1001
    
    where `buckets[i]` stores the count of stones with weight 𝑖.
2.  Iterate from the largest weight downward.
3.  If you find an odd count of a certain weight, "smash" it with the next heaviest stone you find.

**Bucket Sort Java Implementation:**

```java
class Solution {
    public int lastStoneWeight(int[] stones) {
        int[] buckets = new int[1001]; // Constraint: stones[i] <= 1000
        for (int s : stones) buckets[s]++;

        int first = 1000;
        while (first > 0) {
            if (buckets[first] == 0) {
                first--;
            } else {
                buckets[first] %= 2; // Even pairs cancel out immediately
                if (buckets[first] == 0) continue; // All cancelled
                
                // Found one remaining "heavy" stone, find the next one to smash
                int second = first - 1;
                while (second > 0 && buckets[second] == 0) second--;
                
                if (second == 0) return first; // No other stones left
                
                buckets[first]--;
                buckets[second]--;
                buckets[first - second]++; // Put the difference back
                
                // Reset 'first' to the new potentially largest stone
                if (first - second > second) first = first - second;
                else first = second;
            }
        }
        return 0;
    }
}

```

