/*
explanation: max consesecutive ones using array traversal and maintaining two variables, the algorithms
works by iterating over each item and maintaining vars current_ones, max_ones, where current_ones represent
number of one's seen so far in current iteration, and max_ones is maximum consequtive ones seen so far.
see program how it works:

testcase: [1,1,0,1,1,1] -> Works

Time & Space Complexity: O(n) & O(1): Time complexity is linear due to iteration required for each array item, also space complexity is constant due to countable extra variables used.
*/
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int current_ones = 0;
        int max_ones = 0;
        
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == 0) {
                // A bad digit ie 0 comes in, reset current ones and maximize max_ones
                max_ones = Math.max(max_ones, current_ones);
                current_ones = 0;
            } else {
                current_ones++;
            }
        }
        
        return Math.max(current_ones, max_ones);
    }
}