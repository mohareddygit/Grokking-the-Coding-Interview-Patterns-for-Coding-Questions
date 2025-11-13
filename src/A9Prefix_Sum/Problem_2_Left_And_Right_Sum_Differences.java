package A9Prefix_Sum;

// Problem Statement: Left and Right Sum Differences (easy)
// LeetCode Question: 2574. Left and Right Sum Differences

public class Problem_2_Left_And_Right_Sum_Differences {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Calculate the total sum of the entire array initially
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        // Iterate through each index of the array
        for (int i = 0; i < n; i++) {
            // Calculate the sum of elements strictly to the right of index i
            // This is the total sum minus all elements to the left, minus the current element
            int rightSum = totalSum - leftSum - nums[i];

            // The answer at this index is the absolute difference between the two sums
            answer[i] = Math.abs(leftSum - rightSum);

            // Update the left sum to include the current element for the next iteration
            leftSum += nums[i];
        }

        return answer;
    }
}

