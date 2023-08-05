/*
explanation: split array largest sum using binary search! The algorithm works by progressively scanning through each
kind of largest sum bound. we find the number of such partitions for each of the largest sum and use binary search to
find the correct parameter for the same!

testcase: [7,2,5,10,8] -> Works

Time & Space Complexity: O(nlogn) & O(1): Time complexity is nlogn due to linear time of iteration and logn time to search
for optimum parameter, also space complexity is constant due to countable extra variables required.
*/
class Solution {
    public int getSubarrays(int[] nums, int largest_sum) {
        // find how many subarrays are possible to get this largest sum
        int ans = 1;
        int sum = 0;
        for (int i=0; i<nums.length; i++) {
            int element = nums[i];
            if (sum + element > largest_sum) {
                // If sum greater than whats being permitted, then create new subarr section.. ie
                sum = element;
                ans++;
            } else {
                sum += element;
            }
        }
        
        if (sum > largest_sum) {
            ans++;
        }
        
        return ans;
    }

    public int splitArray(int[] nums, int target_k) {
        // largest sum minimized, ie start and end should be:
        // start -> minimum of all elements
        // end -> sum of all
        int start = Integer.MIN_VALUE;
        int end = 0;
        for (int i=0; i<nums.length; i++) {
            start = Math.max(start, nums[i]);
            end += nums[i];
        }
        
        int ans = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            int k = getSubarrays(nums, mid);
            // System.out.println("At start "+start+" end "+end+" mid:"+mid+" k="+k);
            if (k <= target_k) {
                // Less subarrays are created, ie the bound is too large, decrease!
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        
        return ans;
    }
}