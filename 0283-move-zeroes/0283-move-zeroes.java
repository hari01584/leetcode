/*
explanation: move zeroes, the program works by using 2-pointers i, j and swapping elements at each iteration, if the current element is 0 then it should be replaced with non-zero (ie j is not increased).

testcase: [0,1,0,3,12] -> Works

Time & Space Complexity: O(n) & O(1): Time complexity is linear due to one pass needed to traverse over the array, also space complexity is constant due to countable number of variables required.
*/
class Solution {
    public void moveZeroes(int[] nums) {
        int j=0;
        for (int i=0; i<nums.length; i++) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            
            if (nums[j] != 0) j++;
        }
    }
}