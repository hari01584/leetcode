/*
explanation: partition string using dynamic programming! The program works by using dynamic programming to calculate strings that can be partitioned from ith index, it works by maintaining dp array which records the minimum substring required after that index!

testcase: 
"4672244"
68 - Works

Time & Space Complexity: O(n) & O(n): Both time and space complexity is linear due to 1D Dp used!
*/
import java.math.BigInteger;

class Solution {
    int k;
    String s;
    int[] dp;
    BigInteger kbig;
    String kstr;
    
    public boolean checkgoodpart(int a, int b){
        String part = s.substring(a, b+1);
        
        if(part.length() > kstr.length()) return false;
        if(part.length() < kstr.length()) return true;
        int cmp = part.compareTo(kstr);
        if(cmp > 0) return false;        
        return true;
    }
    
    public int dypart(int n){
        if(dp[n] != -1) return dp[n];

        if(n >= s.length()) return dp[n]=0;
        
        int min = Integer.MAX_VALUE;
        for(int i=n; i<s.length(); i++){
            // If this specific is good part then see the next ones!
            if(!checkgoodpart(n, i)) break;
            // System.out.println("Yes good");
            
            int nextpart = dypart(i+1);
            min = Math.min(min, nextpart);
        }
        
        // System.out.println(n+" "+(1+min));
        // if(min == Integer.MAX_VALUE) return 0;
        return dp[n] = 1 + min;
    }
    
    public int minimumPartition(String s, int k) {
        this.s = s;
        this.k = k;
        this.kbig = BigInteger.valueOf(k);
        this.kstr = String.valueOf(k);
        
        this.dp = new int[s.length()+1];
        Arrays.fill(dp, -1);
        
        int ans = dypart(0);
        if(ans >= 0) return ans;
        else return -1;
    }
}