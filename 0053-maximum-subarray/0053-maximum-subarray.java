/*
explanation: maximum subarray using memoization! The program works by maintaining 2 parameters included and excluded for each element of nums array, which is computed and optimized at each step, in the end we return the maximum of these two parameter at index 0!

testcase: [-2,1,-3,4,-1,2,1,-5,4] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this program is linear since 1D dynamic programming is used, also for the space required to store dp array!
*/
class Solution {
    class R {
        int inc;
        int exc;
        
        R(int i, int e){
            inc = i;
            exc = e;
        }
        
        @Override
        public String toString(){
            return "inc:"+inc+", exc:"+exc;
        }
    }
    
    int[] nums;
    R[] dp;
    
    R dysum(int n){
        if(n >= nums.length) return new R(-1000000, -1000000);
        if(dp[n] != null) return dp[n];
        
        R next = dysum(n+1);
        int inc = Math.max(nums[n], nums[n] + next.inc);
        int exc = Math.max(nums[n], Math.max(next.inc, next.exc));
        R res = new R(inc, exc);
        
        // System.out.println("At element "+nums[n]+" val:"+res);
        return dp[n] = res;
    }
    
    public int maxSubArray(int[] nums) {
        this.nums = nums;
        this.dp = new R[nums.length];
        
        R res = dysum(0);
        
        return Math.max(res.inc, res.exc);
    }
}