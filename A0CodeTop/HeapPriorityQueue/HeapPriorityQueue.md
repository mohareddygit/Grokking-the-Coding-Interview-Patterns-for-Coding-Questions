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

    (ignoring recursion stack).

Which one to choose?

-   **Heap:** Choose this if the data is a stream (you don't have the whole array at once) or if 𝑘

    is very small.
-   **Quickselect:** Choose this in interviews to demonstrate a deep understanding of algorithm design and to achieve the best average time complexity.