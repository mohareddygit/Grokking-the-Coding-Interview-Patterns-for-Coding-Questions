/*
 * 
 * 
 * 
 */

// input: an integer array
// output: list of all unique triplets [num[i], num[j], num[k]] such that num[i] + num[j]+ num[k] == 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		// Step 1: Sort the array to use two pointers and handle duplicates
		Arrays.sort(nums);

		// loop through the array, stopping two elements before the end
		for (int i = 0; i < nums.length - 2; i++) {

			// Early exit: if the current smallest number is > 0, no zero sum is possible
			if (nums[i] > 0) break;

			// Step 2: Skip duplicates for the first element
			if (i > 0 && nums[i] == nums[i - 1]) continue;

			// Step 3: set two pointer
			// one just after the current number and the other at the end of the nums
			int left = i + 1;
			int right = nums.length - 1;

			// use two pointer to find triplet
			while (left < right) {
				int sum = nums[i] + nums[left] + nums[right];

				if (sum < 0) {
					left++; // if sum is less than zero, move the left pointer to increase the sum
				} else if (sum > 0) {
					right--;  // if the sum is greater than zero, move the right pointer to decrease the sum
				} else {
					result.add(Arrays.asList(nums[i], nums[left], nums[right]));
					// Skip duplicates for the second number
					while (left < right && nums[left] == nums[left + 1]) left++;
					// Skip duplicates for the third number
					while (left < right && nums[right] == nums[right - 1]) right--;
					// Move pointers inward after finding a match
					left++;
					right--;
				}
			}
		}

		return result;
	}
}