package A0CompC1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIS {
    int lengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        List<Integer> tails = new ArrayList<>();
        tails.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] > tails.get(tails.size() - 1)) {
                tails.add(nums[i]);

            } else {
                //int position = getPositionUsingBinarySearch(lis, nums[i]);
                int position = getPositionUsingBuiltInBinarySearch(tails, nums[i]);
                tails.set(position, nums[i]);

            }

        }

        return tails.size();

    }

    int getPositionUsingBuiltInBinarySearch(List<Integer> lis, int target) {

        int[] array = new int[lis.size()];
        for (int i = 0; i < lis.size(); i++) {
            array[i] = lis.get(i); // Auto-unboxing happens here
        }

        int index = Arrays.binarySearch(array, target);

        //insertion position
        if (index < 0) {
            index = -(index + 1);
        }
        return index;

    }


    int getIndexPositionUsingLowerBOundBinarySearch(List<Integer> lis, int target) {
        int left = 0;
        int right = lis.size() - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (lis.get(mid) >= target) {
                right = mid;

            } else {
                left = mid + 1;

            }

        }

        return left;
    }


}
