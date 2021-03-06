/*
explanation: program works by scanning part of string and using modified version of partition dp to check the occurrence in wordlist! the program works by looking from i..j and making partition on k between i and j, if the substring from i..k is in list then the program looks next for further string and sets starting point to k!

testcase:
"leetcode"
["leet","code"] -> Works

Time & Space Complexity: O(n^2) & O(n): Time complexity is quadratic due to recursion and space is linear due to one dimensional array needed!
*/

class Solution {
    String str;
    List<String> wordDict;
    int[] dp;
    
    boolean wbreak(int n){
        if(n >= str.length()) return true;
        
        if(dp[n] != -1) return dp[n] == 1;

        for(int i=n; i<str.length(); i++){
            String subst = str.substring(n, i+1);
            // System.out.println(subst+" ");
            
            if(wordDict.contains(subst)){
                boolean r = wbreak(i+1);
                if(!r) continue; // Skip in case the future solution fails to solve the problem.
                dp[n] = r?1:0;
                return r;
            }
        }
        
        dp[n] = 0;
        return false;
    }
    
    public boolean wordBreak(String s, List<String> wordDict) {
        this.str = s;
        this.wordDict =  wordDict;
        this.dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return wbreak(0);
    }
}