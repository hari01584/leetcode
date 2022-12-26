/*
explanation: frog jump using dynamic programming/greedy! the algorithm works by first selecting a stone and finding max jumps in both forward/backward if the frog uses next stone for forward/backward jumping, ie let us assume frog is on nth stone, then it will try jumping on nth stone in forward direction and calculate parameters, also if frog skips this stone then it means the frog will use it in backward directions, hence calculating some more parameters, we do this for each stone, memoize the best path and finally return the answer!

testcase: [0,2,5,6,7] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this program is linear, because the algorithm uses memoization to retrieve previous best jump from next node (which when calculated, automatically have next node result stored), therefore time complexity is linear, also space complexity of this program is linear due to storage required by dp array!
*/
class Solution {
    int[] stones;
    
    class J {
        int fmx;
        int bmx;
        int fp;
        int bp;
        
        J(int fm, int bm, int f, int b){
            fmx = fm;
            bmx = bm;
            fp = f;
            bp = b;
        }
        
        @Override
        public String toString(){
            return String.format("{f: %d, b: %d, fp:%d, bp:%d}", fmx, bmx, fp, bp);
        }
    }
    
    J[] dp;
    J dyjump(int n){
        if(n == stones.length-2){
            // Last 2nd, create returning loop!
            int sz = stones[n+1] - stones[n];
            return dp[n] = new J(sz, sz, sz, sz);
        }
        
        if(dp[n] != null) return dp[n];
        
        // Recursively choose this stone for forward/backward wrt to next stones!
        J next = dyjump(n+1);
        // System.out.println("at n="+stones[n]+" JNext:"+next);
        
        // Find distance between these two stones
        int distance = stones[n+1] - stones[n];
        
        // Find for forward!
        int ffm = Math.max(distance, next.fmx);
        int fbm = Math.max(next.bp+distance, next.bmx);
        int fc = Math.max(ffm, fbm);
        
        // If use next stone in forward jumping!
        int bfm = Math.max(next.fp+distance, next.fmx);
        int bbm = Math.max(distance, next.bmx);
        int bc = Math.max(bfm, bbm);
        
        if(fc <= bc){
            // Forward integration better! (ie jump forwardly to next node)
            return dp[n] = new J(ffm, fbm, distance, next.bp+distance);
        } else {
            // Backward integration (use next node for back jump!)
            return dp[n] = new J(bfm, bbm, next.fp+distance, distance);
        }
    }
    
    public int maxJump(int[] stones) {
        this.stones = stones;
        dp = new J[stones.length];
        
        J res = dyjump(0);        
        return Math.max(res.fmx, res.bmx);
    }
}