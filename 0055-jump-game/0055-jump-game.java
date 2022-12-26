/*
explanation: jump game using tabulation dp! The program works by defining a reachability array (reach) where reach[i] the ability of that element to be reached, if this is true => it can be reached by 0th element, else it cannot! We do this for step by step and find the state of reachability by last element, if this is true then it means we can reach by jumping, else not!

testcase: [2,3,1,1,4] -> Works

Time & Space Complexity: O(n^2) & O(n): Time complexity is quadratic because each element in worst case might need to traverse to end of array to set reachability true, also space complexity is linear due to storage required by reach array!
*/
class Solution {
    public boolean canJump(int[] nums) {
        int target = nums.length-1;
        for(int i=nums.length-2; i>=0; i--){
            // Check if current element at any jump can reach next closest possible target!
            if(i+nums[i] >= target){
                // Yes it is reachable! Now the target becomes i!
                target = i;
            } else {
                // Not reachable by current number, maybe the previous number can reach it? perhaps??
                if(i==0) return false;
            }
        }
        
        return true;
    }
}