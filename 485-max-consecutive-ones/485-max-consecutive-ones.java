/*
explanation: Finding max sum using kadane's algorithm for finding max consecutive sum subarray over array, here we maintain a current_sum and max_sum variables where current_sum is set to 0 whenever a zero is occured in array, also max_sum records the greatest value of current_sum over loop.

testcase:
[1,1,0,1,1,1] -> Works

Time and Space Complexity: O(n) and O(1)
Since a linear loop is used with countable number of variables defined therefore the time and space complexity is O(n) and O(1) respectively.
*/

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int sum = 0;
        int maxsum_so_far = 0;
        for(int i=0; i<nums.length; i++){
            int e = nums[i];
            if(e == 0) sum = 0;
            else sum++;
            maxsum_so_far = Math.max(sum, maxsum_so_far);
        }
        return maxsum_so_far;
    }
}