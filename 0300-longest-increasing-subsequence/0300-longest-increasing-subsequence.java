/*
explanation: longest increasing subsequence using dp! The program works by recursing through each element in array and finding lis using the property that lis[n] = max(1, 1+lis[j]), where j is the indices of elements whose values is greater than nums[n], ie if nums[n] is 4, then we will create list from elements greater than 4!
*/
class Solution {
    int ans = 1;
    int[] dp;
    int dylis(int n, int[] nums){
        if(n == nums.length-1) return dp[n] = 1; // Last element!
        if(dp[n] != 0) return dp[n];
        
        int mx = 1;
        for(int i=n+1; i<nums.length; i++){
            int c = 1+dylis(i, nums);
            if(nums[i] > nums[n])
                mx = Math.max(mx, c); // Only take into consideration iff next element is greater than n, thus taking it into consideration
            // for example in, 4,0,5, the lis possible for element 4 will be derived from list possible from 5, as any element >4 will automatically be
            // greater than 5
        }
        
        // System.out.println("was called on "+nums[n]+" with return mx: "+mx);
        ans = Math.max(ans, mx);
        return dp[n] = mx;
    }
    
    public int lengthOfLIS(int[] nums) {
        dp = new int[nums.length];
        dylis(0, nums);
        return ans;
    }
}