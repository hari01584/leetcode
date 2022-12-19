/*

*/
class Solution {
    public int getLongest(int[] dp, int indx){
        if(indx < 0) return 0;
        else return dp[indx];
    }
    
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int longest = 0;
        
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                dp[i] = 0;
            } else { // c = ')'
                if(i-1 < 0){
                    dp[i] = 0;
                    continue;
                }
                if(s.charAt(i-1) == '('){
                    // Simply match elementary () condition!
                    dp[i] = getLongest(dp, i-2) + 2;
                } else if((i-getLongest(dp, i-1)-1 >= 0) && s.charAt(i-getLongest(dp, i-1)-1) == '('){
                    dp[i] = getLongest(dp, i-1) + 2 + getLongest(dp, i - getLongest(dp, i-1)-2);
                }
            }
            
            longest = Math.max(longest, dp[i]);
        }
        
        return longest;
    }
}