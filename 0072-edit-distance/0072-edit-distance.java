/*
explanation: finding minimum of operations using dynamic programming (memoization)! It works by considering all the subcases to an optimal substructure problem where each case can only have 3 possible operations, ie insert/delete/replace! Now a common observation can be made that for each of these cases we can simply change i, j and solve this problem just typical like LCS! 

testcase: 
"intention"
"execution" -> Works

Time & Space Complexity : O(n^3) & O(n^2), here the  worst case time complexity is n cube since each case can branch into 3 different subproblems which further goes on! Also space complexity is O(n^2) as we maintain a 2d array dp for storing results!
*/


class Solution {
    String str1;
    String str2;
    
    int dp[][];
    
    int minops(int i, int j){
        // Memoize
        if(dp[i][j] != -1) return dp[i][j];
        
        // Base case
        if(i == str1.length()) return dp[i][j] = str2.length() - j;
        if(j == str2.length()) return dp[i][j] = str1.length() - i;;
        
        if(str1.charAt(i) == str2.charAt(j)){
            return dp[i][j] = minops(i+1, j+1);
        }
        
        // Recursive
        int deleteOp = 1 + minops(i+1, j);
        int addOp = 1 + minops(i, j+1);
        int replOp = 1 + minops(i+1, j+1);
        
        return dp[i][j] = Math.min(Math.min(deleteOp, addOp), replOp);
    }
    
    public int minDistance(String word1, String word2) {
        str1 = word1;
        str2 = word2;
        
        dp = new int[str1.length()+1][str2.length()+1];
        for(int[] d : dp){
            Arrays.fill(d, -1);
        }
        
        return minops(0, 0);
    }
}