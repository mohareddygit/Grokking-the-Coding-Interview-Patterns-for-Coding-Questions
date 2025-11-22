# Blind 75 Problems (1–10)

| # | Problem | Solution Description (with Time & Space Complexity) | Java Code Snippet |
|---|----------|----------------------------------------------------|------------------|
| 1 | Two Sum | Use HashMap to store complements and return indices when match found. **Time:** O(n), **Space:** O(n) | 
```java
public int[] twoSum(int[] nums, int target) {
    Map<Integer,Integer> map = new HashMap<>();
    for (int i=0;i<nums.length;i++) {
        int comp = target - nums[i];
        if (map.containsKey(comp)) return new int[]{map.get(comp), i};
        map.put(nums[i], i);
    }
    return new int[]{};
}
```
| # | Problem | Solution Description (with Time & Space Complexity) | Java Code Snippet |
|---|----------|----------------------------------------------------|------------------|
| 2 | Best Time to Buy and Sell Stock | Track minimum price and maximum profit while iterating. **Time:** O(n), **Space:** O(1) | ```java
public int maxProfit(int[] prices) {
    int min = Integer.MAX_VALUE, profit = 0;
    for (int p : prices) {
        min = Math.min(min, p);
        profit = Math.max(profit, p - min);
    }
    return profit;
}
``` |
| 3 | Contains Duplicate | Use HashSet to track seen elements. **Time:** O(n), **Space:** O(n) | ```java
public boolean containsDuplicate(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
        if (!set.add(num)) return true;
    }
    return false;
}
``` |
| 4 | Product of Array Except Self | Compute prefix and suffix products without division. **Time:** O(n), **Space:** O(1) (excluding output array) | ```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    res[0] = 1;
    for (int i=1;i<n;i++) res[i] = res[i-1]*nums[i-1];
    int suffix = 1;
    for (int i=n-1;i>=0;i--) {
        res[i] *= suffix;
        suffix *= nums[i];
    }
    return res;
}
``` |
| 5 | Maximum Subarray | Kadane’s algorithm: track current and max sum. **Time:** O(n), **Space:** O(1) | ```java
public int maxSubArray(int[] nums) {
    int max = nums[0], cur = nums[0];
    for (int i=1;i<nums.length;i++) {
        cur = Math.max(nums[i], cur + nums[i]);
        max = Math.max(max, cur);
    }
    return max;
}
``` |
| 6 | Maximum Product Subarray | Track max and min products (swap when negative). **Time:** O(n), **Space:** O(1) | ```java
public int maxProduct(int[] nums) {
    int max = nums[0], min = nums[0], res = nums[0];
    for (int i=1;i<nums.length;i++) {
        int cur = nums[i];
        if (cur < 0) {
            int temp = max;
            max = min;
            min = temp;
        }
        max = Math.max(cur, max * cur);
        min = Math.min(cur, min * cur);
        res = Math.max(res, max);
    }
    return res;
}
``` |
| 7 | Find Minimum in Rotated Sorted Array | Binary search comparing mid vs right. **Time:** O(log n), **Space:** O(1) | ```java
public int findMin(int[] nums) {
    int l=0,r=nums.length-1;
    while(l<r){
        int mid=l+(r-l)/2;
        if(nums[mid]>nums[r]) l=mid+1;
        else r=mid;
    }
    return nums[l];
}
``` |
| 8 | Search in Rotated Sorted Array | Modified binary search, check sorted half. **Time:** O(log n), **Space:** O(1) | ```java
public int search(int[] nums,int target){
    int l=0,r=nums.length-1;
    while(l<=r){
        int mid=l+(r-l)/2;
        if(nums[mid]==target) return mid;
        if(nums[mid]<=nums[r]){
            if(target>nums[mid]&&target<=nums[r]) l=mid+1;
            else r=mid-1;
        } else {
            if(target>=nums[l]&&target<nums[mid]) r=mid-1;
            else l=mid+1;
        }
    }
    return -1;
}
``` |
| 9 | 3Sum | Sort + two pointers to find triplets summing to zero. **Time:** O(n²), **Space:** O(1) | ```java
public List<List<Integer>> threeSum(int[] nums){
    Arrays.sort(nums);
    List<List<Integer>> res=new ArrayList<>();
    for(int i=0;i<nums.length-2;i++){
        if(i>0&&nums[i]==nums[i-1]) continue;
        int l=i+1,r=nums.length-1;
        while(l<r){
            int sum=nums[i]+nums[l]+nums[r];
            if(sum==0){
                res.add(Arrays.asList(nums[i],nums[l],nums[r]));
                while(l<r&&nums[l]==nums[l+1]) l++;
                while(l<r&&nums[r]==nums[r-1]) r--;
                l++;r--;
            } else if(sum<0) l++; else r--;
        }
    }
    return res;
}
``` |
| 10 | Container With Most Water | Two pointers inward, maximize area. **Time:** O(n), **Space:** O(1) | ```java
public int maxArea(int[] height){
    int l=0,r=height.length-1,max=0;
    while(l<r){
        max=Math.max(max,Math.min(height[l],height[r])*(r-l));
        if(height[l]<height[r]) l++;
        else r--;
    }
    return max;
}
``` |
