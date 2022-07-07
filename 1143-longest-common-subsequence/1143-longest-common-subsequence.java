/*
explanation: common subsequence by maintaining a dp table and setting elements according to the algorithm! algo - https://leetcode.com/problems/longest-common-subsequence/discuss/348884/C%2B%2B-with-picture-O(nm)
*/

class Solution {
    public int getElement(int[][] dp, int px, int py){
        if(px < 0 || py < 0) return 0;
        else return dp[px][py];
    }
    public int longestCommonSubsequence(String text1, String text2) {
        int n1=text1.length();
        int n2=text2.length();
        
        int[][] dp = new int[n1][n2];
        
        for(int i=0; i<n1; i++){
            for(int j=0; j<n2; j++){
                if(text1.charAt(i)==text2.charAt(j)){
                    dp[i][j] = getElement(dp, i-1, j-1) + 1;
                }
                else{
                    dp[i][j] = Math.max(getElement(dp, i-1, j), getElement(dp, i, j-1));
                }
            }
        }
        
        return dp[n1-1][n2-1];
    }
}