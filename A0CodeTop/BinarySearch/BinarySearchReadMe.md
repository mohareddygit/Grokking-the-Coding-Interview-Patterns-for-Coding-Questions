Understanding the difference between standard and lower bound binary search is crucial for choosing the right algorithm for search versus insertion tasks.

1. **Standard Binary Search**

The goal of a standard binary search is to find if a specific value exists and return its index. If the value is not found, it typically returns `-1`.
```java
public int standardBinarySearch(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) { // Note the <=
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid; // Found it, return index immediately
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return -1; // Not found
}

```

Note: **Binary search for a key on an input array with Duplicates:** If the array contains multiple elements equal to the search key, there is no guarantee which specific index will be returned

2. **Lower Bound Binary Search** First index (≥)  target.

The goal of a lower bound search is to find the **first position** where an element is **not less than** (i.e., ≥) the target. This is useful for finding the first occurrence in a list with duplicates or the correct insertion point.

This is used in LIS algorithm, range queries, or inserting into a sorted list.
LIS = LongestIncreasingSubsequence problem.




```java
public int lowerBoundSearch(int[] arr, int target) {
    int left = 0;
    int right = arr.length; // Note: search range is [0, length)

    while (left < right) { // Note the <
        int mid = left + (right - left) / 2;

        if (arr[mid] >= target) {
            // This might be our answer, but there could be an 
            // even smaller index to the left that is also >= target.
            right = mid; 
        } else {
            left = mid + 1;
        }
    }
    return left; // Returns the index of first element >= target
}

```

If using binarySearch(int[] a, int key) then The "insertion point" is the index where the key would be inserted while maintaining order (the index of the first element greater than the key). 

The Math to get the actual index for insertion point: i = -(i + 1)

```java
int index = Arrays.binarySearch(array, target);

//insertion position
if (index < 0) {
index = -(index + 1);
}
```

2. Here is a breakdown of how Java's `Arrays.binarySearch` works in this specific context:
   Understanding the Return Value

`Arrays.binarySearch(array, fromIndex, toIndex, key)` returns:

1. **A non-negative index ( **≥0** ): If the `key` is found in the array.
2. **A negative index (<0**): If the `key` is **not** found.
   The "insertion point" is the index where the key would be
   inserted while maintaining order (the index of the first element greater than the key).
3. The Math to get the actual index for insertion point: `i = -(i + 1)`

When a number is not found, Java uses a specific formula to encode the **insertion point** into a negative number:

**Java binary search Returned  index=−(insertion  point)−1**

To get the actual index back, we reverse the formula:

-   If `binarySearch` returns `-1`: `i = -(-1 + 1)` →**Index 0**
-   If `binarySearch` returns `-5`: `i = -(-5 + 1)` → **Index 4**


```java 
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};
        
        // Finding an existing element
        int index = Arrays.binarySearch(numbers, 30); 
        System.out.println("Index of 30: " + index); // Output: 2

        // Searching for an element not in the array
        int notFound = Arrays.binarySearch(numbers, 35); 
        System.out.println("Result for 35: " + notFound); // Output: -4 (-(3)-1)
        //Insertion point = -(-4 + 1) = 3 
    }
}
```