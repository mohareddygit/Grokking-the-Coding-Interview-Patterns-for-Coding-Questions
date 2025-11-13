package B9Greedy_Algorithm;

/**
 * 55. Jump Game
 * Medium
 * Topics
 * premium lock icon
 * Companies
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
    public boolean canJump(int[] nums) {
        int reachable = 0; // Farthest index we can reach

        for (int i = 0; i < nums.length; i++) {
            // If current index is beyond reachable range, we can't proceed
            if (i > reachable) return false;

            // Update reachable index
            reachable = Math.max(reachable, i + nums[i]);
        }

        return true;
    }

    public static void main(String[] args) {
        GA_JumpGame_B26 solver = new GA_JumpGame_B26();
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println("Can reach end? " + solver.canJump(nums)); // Output: true
    }
}
