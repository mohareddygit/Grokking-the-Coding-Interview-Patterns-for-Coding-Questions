package A3Sliding_Window;

// Problem Statement: Maximum Sum Subarray of Size K (easy)
// LeetCode Question: 2461. Maximum Sum of Distinct Subarrays With Length K

import java.util.HashMap;
import java.util.Map;

public class Problem_1_MaximumSumSubArraySizeK {
    // Beats 100.00% of users with Java
    class Solution {
        public long maximumSubarraySum(int[] nums, int k) {
            int size = nums.length;
            int minNum = nums[0];
            int maxNum = nums[0];
            for (int idx = 1; idx < size; idx++) {
                if (nums[idx] < minNum) {
                    minNum = nums[idx];
                } else if (nums[idx] > maxNum) {
                    maxNum = nums[idx];
                }
            }

            if (k == 1) {
                return maxNum;
            }

            if (minNum == maxNum) {
                return 0L;
            }

            int range = maxNum - minNum + 1; // 9
            int[] windowSet = new int[range]; // [1,0,0,0,0,0,0,0,0]
            int windowSize = 0;
            int minIdx = 0;
            long windowSum = 0L;
            long maxWindowSum = 0L;
            for (int idx = 0; idx < size; idx++) {
                while (windowSet[nums[idx] - minNum] != 0) {
                    windowSet[nums[minIdx] - minNum] = 0;
                    windowSize--;
                    windowSum -= nums[minIdx];
                    minIdx++;
                }
                windowSet[nums[idx] - minNum] = idx + 1;
                windowSize++;
                windowSum += nums[idx];
                if (windowSize == k) {
                    if (maxWindowSum < windowSum) {
                        maxWindowSum = windowSum;
                    }
                    windowSet[nums[minIdx] - minNum] = 0;
                    windowSize--;
                    windowSum -= nums[minIdx];
                    minIdx++;
                }
            }
            return maxWindowSum;

        }
    }
    // Beats 18.65% of users with Java
    public int findMaxSumSubArray(int k, int[] arr) {
        // TODO: Write your code here
        int maxSum = 0, currentMax = 0;
        for(int i = 0; i < k; i++){
            currentMax += arr[i];
        }
        for(int j = k; j < arr.length; j++){
            currentMax += arr[j] - arr[j - k];
            maxSum = Math.max(maxSum, currentMax);
        }
        return maxSum;
    }

    /**
     * The LeetCode problem 2461, "Maximum Sum of Distinct Subarrays With Length K", asks for the maximum sum among all contiguous subarrays of a given length
     * k that contain only distinct elements.
     * This problem is solved efficiently using the Sliding Window technique combined with a HashSet or a HashMap to track element frequencies/distinctness within the current window.
     * Algorithm: Sliding Window and Hash Set
     * We iterate through the array, maintaining a window of size k. At each step, we ensure all elements in the current window are distinct and calculate their sum, updating the maximum sum found so far.
     * Time Complexity: O(N) because each element is processed a constant number of times (added to/removed from the window and set).
     * Space Complexity: O(K) for the set and to track the window elements.
     * @param nums
     * @param k
     * @return
     */
    public long maximumUniqueSubarraySum(int[] nums, int k) {
        long maxSum = 0;
        long currentWindowSum = 0;

        // A HashMap to store the count (frequency) of each element in the current window
        Map<Integer, Integer> windowCounts = new HashMap<>();

        for (int right = 0; right < nums.length; right++) {
            int numToAdd = nums[right];

            // Add the current element to the window sum and count map
            currentWindowSum += numToAdd;
            windowCounts.put(numToAdd, windowCounts.getOrDefault(numToAdd, 0) + 1);

            // Check if the window size has reached k
            if (right >= k - 1) {
                // The left pointer is k positions behind the right pointer
                int left = right - k + 1;

                // Check if all elements in the current window are distinct
                // A map size equal to k means all elements are unique
                if (windowCounts.size() == k) {
                    maxSum = Math.max(maxSum, currentWindowSum);
                }

                // Slide the window forward: remove the leftmost element
                int numToRemove = nums[left];
                currentWindowSum -= numToRemove;

                // Update the count map for the element being removed
                windowCounts.put(numToRemove, windowCounts.get(numToRemove) - 1);

                // If the count of the removed element becomes 0, remove it from the map
                if (windowCounts.get(numToRemove) == 0) {
                    windowCounts.remove(numToRemove);
                }
            }
        }

        return maxSum;
    }

}
