/*
explanation: min in rotated sorted array using good-bad half technique, the algorithm exploits the fact that in a good
sorted array (without rotation), the minimum would always be at starting index, so if our array is good in half start..end
ie nums[start] < nums[mid] < nums[end], then simply return minimum to be nums[start], however if there is break then we
take minimum of sorted good half, and the minimum of bad half. Recursively calling it will get our answer.

testcase: [3,4,5,1,2] -> Works

Time & Space Complexity: O(logn) & O(logn): Time complexity is logarithmic due to binary search used, also space complexity is also logn due to recursive stack space required.
*/
class Solution {
    public int recurBrokenMinBinSearch(int start, int end, int[] nums) {
        int mid = (start + end) / 2;
        // If our array is sorted, then there's no need to guess any more because
        // minimum of sorted array can only be at index start!
        if (nums[start] <= nums[mid] && nums[mid] <= nums[end]) {
            // ie good array, this is already a sorted array between start...end, so minimum can only be nums[start]
            return nums[start];
        }

        // If not, then take minimum of sorted good half of array, and apply same algorithm on bad half!
        int min = Integer.MAX_VALUE;
        if (nums[start] <= nums[mid]) {
            min = Math.min(nums[start], recurBrokenMinBinSearch(mid + 1, end, nums));
        } else if (nums[mid] <= nums[end]) {
            min = Math.min(nums[mid], recurBrokenMinBinSearch(start, mid - 1, nums));
        }

        return min;
    }
    
    public int findMin(int[] nums) {
        // Find minimum in rotated sorted array using broken binary search
        return recurBrokenMinBinSearch(0, nums.length - 1, nums);
    }
}