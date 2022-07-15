/*
explanation: cut a stick algorithm using memoization! The program works based on the principle of partition dynamic programming! The costcut function takes in the partition of each elements from k=i to k<j and finds best possible cut for each partition! it builds upon left and right part and finally returns the answer! the algorithm is like mcm where there could be different ways to cut things!

testcase:
7
[1,3,4,5] -> Works

Time & Space Complexity: O(n^3) and O(n^2): cubic time complexity due to the recursive function + n^2 calls to each problem, also space complexity is quadratic to store and maintain a 2d dp array.
*/

class Solution {
    int[] cuts;
    int[][] dp;
    
    int costcut(int i, int j, int lb, int rb){
        // System.out.println(i+" "+j+" "+lb+" "+rb);

        if(i==j) return 0;
        
        // Cache retrieve
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        
        int min = Integer.MAX_VALUE;
        for(int k=i; k<j; k++){            
            int val = cuts[k];
            
            // System.out.println("pivot "+k+" val "+val);

            // Assuming val b/w i and j
            
            int lcut = costcut(i, k, lb, val);
            int rcut = costcut(k+1, j, val, rb);
            int cos = (rb - lb) + lcut + rcut;
            
            min = Math.min(min, cos);
        }
        
        // Cache
        return dp[i][j] = min;
    }
    
    // int costcutl(int n, int l, int r){
    //     int e = cuts[n];
    //     if(l < e && )
    // }
    
    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        
        this.cuts = cuts;
        this.dp = new int[101][101];
        for(int[] d:dp){
            Arrays.fill(d, -1);
        }
            
        return costcut(0, cuts.length, 0, n);
        // return costcutl(0, 0, n);
    }
}