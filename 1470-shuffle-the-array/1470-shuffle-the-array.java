/*
explanation: shuffle array by array traversal, algorithm works by setting value to indices of array in steps!

testcase: [2,5,1,3,4,7]
3

Time & Space Complexity: O(n) & O(n): Time an space complexity of this program is linear, due to iteratively looping over array elements once and that to store ans in another array!
*/
class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[2*n];
        for(int i=0; i<n; i+=1){
            ans[2*i] = nums[i];
            ans[2*i+1] = nums[i+n];
        }
        return ans;
    }
}