package A9Prefix_Sum;

// Problem Statement: Find the Middle Index in Array (easy)
// LeetCode Question: 1991. Find the Middle Index in Array

/** To solve LeetCode 1991, "Find the Middle Index in Array," we need to find an index where the sum of all elements to its left is equal to the sum of all elements to its right.

 This problem can be efficiently solved in a single pass using a prefix sum and tracking the remaining sum.
 Algorithm: Single Pass Prefix Sum
 Calculate Total Sum: First, sum all elements in the entire array.
 Iterate and Track Left Sum: Iterate through the array a second time. Maintain a running sum of elements to the left of the current index (leftSum).
 Calculate Right Sum: For the current index i, the sum of elements to its right is the totalSum minus the current element (nums[i]) minus the leftSum.
 Check Condition: If leftSum equals the calculated rightSum, the current index i is the middle index; return i.
 No Solution: If the loop finishes without finding such an index, return -1.
 */
public class Problem_1_Find_The_Middle_Index_In_Array {
    public int findMiddleIndex(int[] nums) {
        // Step 1: Calculate the total sum of all elements
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        // Step 2: Iterate through the array to check each index as a potential middle index
        for (int i = 0; i < nums.length; i++) {
            // The sum of elements to the right of index i
            // totalSum - current element - sum of elements to the left
            int rightSum = totalSum - nums[i] - leftSum;

            // Step 3: Check if left sum equals right sum
            if (leftSum == rightSum) {
                return i; // Found the middle index
            }

            // Update the left sum for the next iteration
            leftSum += nums[i];
        }

        // Step 4: If no middle index is found after checking all indices
        return -1;
    }
}

