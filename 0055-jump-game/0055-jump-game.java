/*
explanation: jump game using tabulation dp! The program works by defining a reachability array (reach) where reach[i] the ability of that element to be reached, if this is true => it can be reached by 0th element, else it cannot! We do this for step by step and find the state of reachability by last element, if this is true then it means we can reach by jumping, else not!

testcase: [2,3,1,1,4] -> Works

Time & Space Complexity: O(n^2) & O(n): Time complexity is quadratic because each element in worst case might need to traverse to end of array to set reachability true, also space complexity is linear due to storage required by reach array!
*/
class Solution {
    public boolean canJump(int[] nums) {
        boolean[] reach = new boolean[nums.length]; // Reachability array!
        reach[0] = true; // First element is automatically reachable!
        
        for(int i=0; i<reach.length; i++){
            // If this (current) element is reachable then set reachability of all elements possible to reach by this to be true!
            if(reach[i]){
                for(int j=i+1; j<=Math.min(nums.length-1, i+nums[i]); j++){
                    reach[j] = true;
                    if(j == reach.length-1) return true;
                }
            }
        }
        
        // Finally return reachability of last element, if this is true => can be reached, else not!
        return reach[reach.length-1];
    }
}