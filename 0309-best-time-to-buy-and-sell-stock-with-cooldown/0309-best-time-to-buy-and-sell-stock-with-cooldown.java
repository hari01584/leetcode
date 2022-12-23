/*
explanation: best time to buy and sell stock using dp/memoization! Works by calculating all the possible cases
*/
class Solution {
    int[] derivative;
    int[][] dp;
    public int dynmax(int n, int hold){
        if(n >= derivative.length) return 0;
        
        if(dp[n][hold] != -1) return dp[n][hold];
        // System.out.println("day "+n+" hold:"+hold);
        
        if(hold==1){
            // We already holding the stock! So only wait/sell
            int wait = dynmax(n+1, 1);
            int sell = derivative[n] + dynmax(n+2, 0);
            
            int r = Math.max(wait, sell);
            // System.out.println("stocks in hold, "+n+" "+wait+" "+sell);

            return dp[n][hold] = r;
        } else {
            // We don't have stock, either buy or wait for next day!
            int wait = dynmax(n+1, 0);
            int buy = -derivative[n] + dynmax(n+1, 1);
            
            // System.out.println("stocks dont have, "+n+" "+wait+" "+buy);
            return dp[n][hold] = Math.max(wait, buy);
        }
    }
    
    public int maxProfit(int[] prices) {
//         derivative = new int[prices.length-1];
        
//         for(int i=1; i<prices.length; i++){
//             derivative[i-1] = prices[i] - prices[i-1];
//         }
        
        derivative = prices;
        
        dp = new int[prices.length][2];
        for(int[] d : dp){
            Arrays.fill(d, -1);
        }
        
        // System.out.println(Arrays.toString(prices));
                
        return dynmax(0, 0);
    }
}