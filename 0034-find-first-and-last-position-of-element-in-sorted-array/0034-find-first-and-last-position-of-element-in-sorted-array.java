/*
explanation: finding first and last position using agorithm of lower bound and upper bound, the program works by finding
them and using the fact that lower bound of an element will be its first occourence, also upper bound will be last occ + 1,
if elements do not exist then lower/upper bound will point to some different elements.

testcase: [5,7,7,8,8,10] -> Works

Time & Space Complexity: O(logn) & O(1): Time complexity is logn due to binary search, space is const due to countable variables used.
*/
class Solution {
    public int lb(int start, int end, int target, int[] nums) {
        int ans = nums.length;
        // Apply binsearch
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                ans = mid;
                end = mid - 1;
            }
        }

        return ans;
    }
    
    public int ub(int start, int end, int target, int[] nums) {
        int ans = nums.length;
        // Apply binsearch
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] <= target) {
                start = mid + 1;
            } else {
                ans = mid;
                end = mid - 1;
            }
        }

        return ans;
    }
    
    public int[] searchRange(int[] nums, int target) {
        // Find first and last position using binary search and lower/upper bound
        // the algorithm works by first finding lowerbound, which will be our starting index
        // also ending index will be upper_bound() - 1.
        int lower_bound = lb(0, nums.length - 1, target, nums);
        int upper_bound = ub(0, nums.length - 1, target, nums);
        
        // Verify correctness (ie do these elements really exist?)
        if (nums.length == 0 || lower_bound >= nums.length || upper_bound <= 0 || nums[lower_bound] != target || nums[upper_bound - 1] != target) {
            // bad case, element target not exist, return -1, -1
            return new int[] {-1, -1};
        }
        
        return new int[] {lower_bound, upper_bound-1};
    }
}