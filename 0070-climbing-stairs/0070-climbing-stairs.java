class Solution {
    int[] dp;
    public int climbStairs(int n) {
        if(dp == null) dp = new int[n+1];
        
        if(n<=1) return 1;
        if(dp[n] != 0) return dp[n];
        
        int st1 = climbStairs(n-1);
        int st2 = climbStairs(n-2);
        
        return dp[n] = st1+st2;
    }
}