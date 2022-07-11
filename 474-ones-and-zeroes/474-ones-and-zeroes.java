/*
explanation: ones and zeroes using algorithm of 0/1 knapsack with memoization! The program works just like 0/1 knapsack and has base recusive clause for including and excluding each element in array! It uses memozied dp array to store previous clause!

testcase: ["10","0001","111001","1","0"]
5
3
-> Works

Time & Space Complexity : O(2^n) & O(n): for the case where no dp array is used the time complexity is 2 raised to power n, also space complexity is linear due to stacks stored due to recursions!
*/


class Solution {
    String[] strs;
    
    public int[] countable(String str){
        int[] c = new int[2];
        for(int i=0; i<str.length(); i++){
            c[str.charAt(i) - '0']++;
        }
        return c;
    }
    
    // With memoization!
    int[][][] dp;
    public int getLargestSubsetLength(int index, int m, int n){
        if(index < 0 || (m < 0 && n < 0)) return 0;
        if(dp[index][m][n] != -1) return dp[index][m][n];
        
        int[] countBits = countable(strs[index]);
        
        if(countBits[0] > m || countBits[1] > n){
            // Insufficient balance of m,n! Therefore skip this element
            return dp[index][m][n] = getLargestSubsetLength(index-1, m, n);
        }
        else{
            // Sufficient balance!
            int include = 1 + getLargestSubsetLength(index-1, m-countBits[0], n-countBits[1]);
            int exclude = getLargestSubsetLength(index-1, m, n);
            
            return dp[index][m][n] = Math.max(include, exclude);
        }
    }
    
    public int findMaxForm(String[] strs, int m, int n) {
        this.strs = strs;
        dp = new int[strs.length][m+1][n+1];
        for (int[][] row : dp) {
            for (int[] rowColumn : row) {
                Arrays.fill(rowColumn, -1);
            }
        }

        return getLargestSubsetLength(strs.length-1, m, n);
    }
}