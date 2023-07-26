/*
explanation: search in rotated array using binary search and good/bad section technique, the algorithm works by checking the
endpoints (ie start, end) and matching them against element at mid, now for normal binary search this rule always applied:
nums[start] <= nums[mid] <= nums[end], however in our case since the array is rotated once, there can be instance when middle
element becomes smaller than start, or greater than end, in those cases we bifurface the problem in two parts: first check if
our element of consideration is in good sorted half (because at max only one half can be bad, not both..), if it is found there
then we apply binary search, however if not then we automatically assume the element to be present in bad half.

testcase: [4,5,6,7,0,1,2] -> Works

Time & Space Complexity: O(logn) & O(1): Time complexity is log because of binary search, space complexity is const due to countable variables required!
*/
class Solution {
    public boolean isInside(int[] nums, int start, int end, int target) {
        if (nums[start] <= target && target <= nums[end]) return true;
        return false;
    }

    public int brokenBinSearch(int start, int end, int target, int[] nums) {
        while (start <= end) {
            int mid = (start + end) / 2;
            // System.out.println("binSearch start:"+start+" end:"+end+" mid:"+mid+" var:"+nums[start] + " " + nums[mid] + " "
            //                  + nums[end] + " t:"+target);

            if (nums[mid] == target) return mid; // found solution
            // Check for sorted or good section of array
            // is it left? in that case nums[start] < nums[mid]
            // or right? then nums[mid] < nums[end]
            if (nums[start] < nums[mid] && isInside(nums, start, mid, target)) {
                // Left part contained!
                end = mid - 1;
            } else if (nums[mid] < nums[end] && isInside(nums, mid, end, target)) {
                // Right contained
                start = mid + 1;
            } else {
                // start binary searching on bad half
                if (nums[start] > nums[mid])
                    end = mid - 1;
                else if (nums[mid] > nums[end])
                    start = mid + 1;
                else {
                    // If nothing else worked, ie target not inside good range
                    // and ranges are good, it means target is not avaliable, return -1
                    return -1;
                }
            }
        }
        
        return -1;
    }

    public int search(int[] nums, int target) {
        // Implementing a broken binary search!
        return brokenBinSearch(0, nums.length - 1, target, nums);
    }
}