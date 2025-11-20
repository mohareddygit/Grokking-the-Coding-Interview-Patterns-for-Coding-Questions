package B9Greedy_Algorithm;

/**
 * 55. Jump Game
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 105
 */
public class GA_JumpGame_B26 {
    /**
     * Determines if it is possible to reach the last index of the array.
     * Consider nums = [3, 2, 2, 0, 4]:
     * goal = 4.
     * i = 3, nums[3] = 0. Condition 3 + 0 >= 4 is false. goal remains 4.
     * i = 2, nums[2] = 2. Condition 2 + 2 >= 4 is true. goal is updated to goal = 2.
     * i = 1, nums[1] = 2. Condition 1 + 2 >= 2 is true. goal is updated to goal = 1.
     * i = 0, nums[0] = 3. Condition 0 + 3 >= 1 is true. goal is updated to goal = 0.
     * Result: The final check return goal == 0; is true,
     * which is correct because you can jump 1 step from index 0 to index 1,
     * then 2 steps to index 3 (the 0), then 4 steps to index 4 (end).
     * The solution correctly identified that an earlier index could jump over the zero.
     * @param nums An array of non-negative integers representing maximum jump lengths.
     * @return True if the last index is reachable, false otherwise.
     */
    public boolean canJump(int[] nums) {
        // 'goal' represents the leftmost index from which we know we can reach the end.
        // We start with the last index as our goal.
        int goal = nums.length - 1;

        // Iterate backwards from the second-to-last index towards the start of the array.
        for (int i = nums.length - 2; i >= 0; i--) {
            // Check if, from the current index 'i', we can jump to or beyond the current 'goal'.
            //TODO:more current index + value in current index can take to goal or beyond goal index
            if (i + nums[i] >= goal) {
                // If we can, it means we've found a new, earlier starting point
                // from which the original end is reachable. We update our 'goal' to this index.
                goal = i;
            }
        }
        // If, by the end of the loop, our 'goal' has moved all the way back to index 0,
        // it means we can reach the end starting from the very first position.
        return goal == 0;
    }

    public static void main(String[] args) {
        GA_JumpGame_B26 solver = new GA_JumpGame_B26();
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println("Can reach end? " + solver.canJump(nums)); // Output: true
    }
}
