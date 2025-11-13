package B6Modified_Binary_Search;

// Problem Statement: Order-agnostic Binary Search (easy)
// LeetCode Question: 704. Binary Search

/**
 * The traditional way to calculate the middle index in binary search is
 * int mid = (left + right) / 2;.
 * While simple, this calculation can lead to a rare but critical error: integer overflow in Java.
 * The expression left + right might exceed the maximum possible value an int data type can hold (which is 2,147,483,647), causing the sum to wrap around to a negative number. This results in an incorrect mid index and causes the algorithm to fail or throw an ArrayIndexOutOfBoundsException.
 * Why left + (right - left) / 2 is Safer
 * The alternative formula int mid = left + (right - left) / 2; solves this overflow problem:
 * Subtraction First: It first calculates the distance between the left and right pointers (right - left). This difference is guaranteed to fit within an integer range because both left and right are valid indices within the array (and thus fit within the int range themselves).
 * Halving the Distance: The distance is then divided by 2 (/ 2), finding the offset from the start of the current search space.
 * Adding the Offset: Finally, this offset is added to the left pointer.
 * This calculation never requires storing a single sum that exceeds the maximum array index (or int size). It is a more robust and safer way to determine the middle index.
 * Example of Potential Overflow
 * Imagine searching a massive array where left is 2,000,000,000 and right is 2,100,000,000.
 * mid = (left + right) / 2;
 * 2,000,000,000 + 2,100,000,000 results in a sum of 4,100,000,000.
 * This number is larger than Integer.MAX_VALUE, causing an overflow and potentially a negative mid index.
 * mid = left + (right - left) / 2;
 * right - left is 100,000,000.
 * (right - left) / 2 is 50,000,000.
 * left + offset is 2,000,000,000 + 50,000,000 = 2,050,000,000.
 * This calculation stays within valid int bounds throughout the process.
 */

/**
 *
 The condition
 while (left <= right) is the fundamental control mechanism for an iterative binary search algorithm. It defines the boundaries of the current "search space" within the sorted array.
 What the Condition Means
 left is the starting index of the current range being searched.
 right is the ending index of the current range being searched.
 The loop continues as long as the search space is valid and potentially contains the target element.
 Why It Uses <= (Less than or Equal to)
 The use of <= is crucial because a valid search space can be a single element wide.
 left < right: This condition would terminate the loop when only one element is left in the range (left and right point to the same index). If that single element happens to be the target, the loop exits before it's checked, causing a failure.
 left <= right: This condition ensures the loop continues even when left and right point to the exact same index. This allows the algorithm to check that final potential target element.
 Step-by-Step Example
 Consider the array `` and target = 5.
 Initial: left = 0, right = 4. The condition 0 <= 4 is true.
 Iteration 1: mid = 2 (arr[2] = 8). Target 5 is less than 8, so right becomes mid - 1 (which is 1).
 Iteration 2: left = 0, right = 1. The condition 0 <= 1 is true.
 Iteration 3: mid = 0 (arr[0] = 2). Target 5 is greater than 2, so left becomes mid + 1 (which is 1).
 Iteration 4: left = 1, right = 1. The condition 1 <= 1 is true.
 Iteration 5: mid = 1 (arr[1] = 5). The target is found at the middle index, and the function return mid; exits the loop successfully.
 When the Loop Terminates
 The loop naturally terminates when left becomes greater than right (e.g., left = 3, right = 2). This happens when the search space has narrowed down to zero elements, meaning the target number does not exist within the array.
 The condition ensures we cover all possibilities while maintaining the efficiency of binary search.

 */
public class Problem_1_Binary_Search {

    public static int search(int[] arr, int key){
        int start = 0, end = arr.length - 1;
        boolean isAscending = arr[start] < arr[end];
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key == arr[mid]) return mid;
            if (isAscending) {
                if (key < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (key > arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

}
