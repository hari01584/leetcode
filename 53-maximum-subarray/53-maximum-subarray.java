/*
explanation: the solution works by finding max contigous sum using Kadanes algorithm, while solving the problem the algorithm will loop over element by element and have a variable called Max_Sum, also using a sum variable which will be changed according to maximum of cumulative previous sum vs the current element, this ensures that the maximum of all is considered at any given time.

testcase:
[5,4,-1,7,8] -> Runs by looping over 5,4,-1,7,8
when 5, max sum is set 5, then loop to 4, the sum becomes 9 and since 9 > current element 4, therefore max sum becomes 9, now next when -1 comes the sum becomes 8 but since 8 < max_sum(9), It won't be updated. now looping over to 7 the sum becomes 8 + 7 = 15, since 15 > 9, max_sum becomes 15 and the algorithm continues so on.

Time and Space Complexity - O(n) and O(1):
Time complexity is O(n) since a single linear loop over array is used, whereas Space complexity is O(1) as few extra variables are defined and used in program.
*/

class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int l = nums.length;
        int sum = 0;
        
        for(int i=0; i<l; i++){
            sum += nums[i];
            sum = Math.max(sum, nums[i]);
            if(sum > maxSum) maxSum = sum;
        }
        
        // System.out.println(maxSum + " " + sum);
        
        return maxSum;
    }
}