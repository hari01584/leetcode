/*
explanation: find missing number using arrays sum algorithm, the program works by summing all elements in array and comparing
it to expected sum, the different between these two will be our answer!

testcase: [3,0,1] -> Works

Time & Space Complexity: O(n) & O(1): Time complexity is linear due to iteration required for each element, also space complexity is constant.
*/
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int mySum = 0;
        
        // First calculated total sum of array
        for (int i=0; i<nums.length; i++) {
            mySum += nums[i];
        }
        
        // And find expected sum using formula n(n+1)/2
        int expectedSum = (n * (n + 1)) / 2;

        return expectedSum - mySum;
    }
}