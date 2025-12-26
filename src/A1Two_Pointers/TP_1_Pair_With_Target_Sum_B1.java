package A1Two_Pointers;

// Problem Statement: Pair with Target Sum (easy)
// LeetCode Question: 1. Two Sum

import java.util.HashMap;
import java.util.Map;

public class TP_1_Pair_With_Target_Sum_B1 {

    public int[] twoSum(int[] nums, int target) {
        // Map stores: Key = Value needed (complement), Value = Index of current number
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // If the complement is already in the map, we've found our pair
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            // Otherwise, store the current number and its index
            map.put(nums[i], i);
        }

        // Per problem constraints, a solution always exists
        return new int[] {};
    }



    public static int[] search(int[] arr, int targetSum){
        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            int currentSum = arr[left] + arr[right];
            if (currentSum == targetSum) {
               return new int[]{left, right};
            }
            if (currentSum > targetSum) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] {-1, -1};
    }
}
